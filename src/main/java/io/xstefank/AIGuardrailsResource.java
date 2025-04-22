package io.xstefank;

import io.xstefank.aiservice.input.FailureJsonFailureGenerateAIService;
import io.xstefank.aiservice.input.FailureJsonAIService;
import io.xstefank.aiservice.input.FailureJsonFatalGenerateAIService;
import io.xstefank.aiservice.input.FatalGenerateFailureJsonAIService;
import io.xstefank.aiservice.output.JsonRepromptAIService;
import io.xstefank.aiservice.output.JsonRewriteAIService;
import io.xstefank.guardrails.NoGuardrailsAIService;
import io.xstefank.guardrails.input.FailureAggregator;
import io.xstefank.guardrails.output.JsonReprompt;
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
    DemoAIService demoAIService;

    // Input guardrails AI services

    @Inject
    FailureJsonAIService failureJsonAIService;

    @Inject
    FailureJsonFailureGenerateAIService failureJsonFailureGenerateAIService;

    @Inject
    FatalGenerateFailureJsonAIService fatalGenerateFailureJsonAIService;

    @Inject
    FailureJsonFatalGenerateAIService failureJsonFatalGenerateAIService;

    // Output guardrails AI services

    @Inject
    JsonRepromptAIService jsonRepromptAIService;

    @Inject
    JsonRewriteAIService jsonRewriteAIService;

    @POST
    public RestResponse<Object> chat(ChatPrompt chatPrompt) {
        System.out.println("Chat prompt: " + chatPrompt);

        if (chatPrompt.demoAiService()) {
            return RestResponse.ok(demoAIService.chat(chatPrompt.prompt()));
        }

        // clear failure aggregator list
        failureAggregator.failureList.clear();

        try {
            switch (chatPrompt.inputGuardrails()) {
                case "failure-json":
                    return RestResponse.ok(failureJsonAIService.chat(chatPrompt.prompt()));
                case "failure-json-failure-generate":
                    return RestResponse.ok(failureJsonFailureGenerateAIService.chat(chatPrompt.prompt()));
                case "fatal-generate-failure-json":
                    return RestResponse.ok(fatalGenerateFailureJsonAIService.chat(chatPrompt.prompt()));
                case "failure-json-fatal-generate":
                    return RestResponse.ok(failureJsonFatalGenerateAIService.chat(chatPrompt.prompt()));
            }

            switch (chatPrompt.outputGuardrails()) {
                case "json-reprompt":
                    return RestResponse.ok(jsonRepromptAIService.chat(chatPrompt.prompt()));
                case "json-rewrite":
                    return RestResponse.ok(jsonRewriteAIService.chat(chatPrompt.prompt()));
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
