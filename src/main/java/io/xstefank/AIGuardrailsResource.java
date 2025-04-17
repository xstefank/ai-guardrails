package io.xstefank;

import io.xstefank.guardrails.ContainsJsonAndGenerateInputGuardrailAIService;
import io.xstefank.guardrails.ContainsJsonInputGuardrailAIService;
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
    ContainsJsonInputGuardrailAIService containsJssonInputGuardrailAIService;

    @Inject
    ContainsJsonAndGenerateInputGuardrailAIService containsJsonAndGenerateInputGuardrailAIService;

    @POST
    public RestResponse<Object> chat(ChatPrompt chatPrompt) {
        System.out.println("Chat prompt: " + chatPrompt);
        try {
            switch (chatPrompt.guardrails()) {
                case "fail-if-doesnt-contain-json":
                    return RestResponse.ok(containsJssonInputGuardrailAIService.chat(chatPrompt.prompt()));
                case "fail-if-doesnt-contain-json-and-generate":
                    return RestResponse.ok(containsJsonAndGenerateInputGuardrailAIService.chat(chatPrompt.prompt()));
            }

            return RestResponse.ok(noGuardrailsAIService.chat(chatPrompt.prompt()));
        } catch (Exception e) {
            StringBuilder message = new StringBuilder(e.getMessage());
            if (!failureAggregator.failureMap.isEmpty()) {
                message.append("\n\nAggregated errors: \n");
                failureAggregator.failureMap.forEach((key, value) -> message.append(key).append(": ").append(value).append("\n"));
            }
            return RestResponse.ResponseBuilder.serverError().entity("Internal Server Error: " + message.toString()).build();
        }
    }
}
