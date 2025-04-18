package io.xstefank;

import io.xstefank.guardrails.FailureJsonFailureGenerateAIService;
import io.xstefank.guardrails.FailureJsonAIService;
import io.xstefank.guardrails.NoGuardrailsAIService;
import io.xstefank.guardrails.input.FailureAggregator;
import io.xstefank.model.ChatPrompt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.jboss.resteasy.reactive.RestResponse;

@Path("/chat")
@ApplicationScoped
public class AIGuardrailsResource {

    @Inject
    FailureAggregator failureAggregator;

    @Inject
    NoGuardrailsAIService noGuardrailsAIService;

    @Inject
    FailureJsonAIService failureJsonAIService;

    @Inject
    FailureJsonFailureGenerateAIService failureJsonFailureGenerateAIService;

    @POST
    public RestResponse<Object> chat(ChatPrompt chatPrompt) {
        System.out.println("Chat prompt: " + chatPrompt);

        // clear failure aggregator list
        failureAggregator.failureList.clear();

        try {
            switch (chatPrompt.inputGuardrails()) {
                case "failure-json":
                    return RestResponse.ok(failureJsonAIService.chat(chatPrompt.prompt()));
                case "failure-json-failure-generate":
                    return RestResponse.ok(failureJsonFailureGenerateAIService.chat(chatPrompt.prompt()));
            }

            return RestResponse.ok(noGuardrailsAIService.chat(chatPrompt.prompt()));
        } catch (Exception e) {
            StringBuilder message = new StringBuilder();
            if (!failureAggregator.failureList.isEmpty()) {
                message.append("Aggregated errors: \n");
                failureAggregator.failureList.forEach(value -> message.append("- ").append(value).append("\n"));
            }

            message.append("\n\nException: ").append(e.getMessage());

            return RestResponse.ResponseBuilder.serverError().entity(message).build();
        }
    }
}
