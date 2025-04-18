package io.xstefank.guardrails.input;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FailureJson implements InputGuardrail {

    @Inject
    FailureAggregator failureAggregator;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String s = userMessage.singleText();
        if (!s.toLowerCase().contains("json")) {
            failureAggregator.failureList.add("missing-json");
            return failure("Input prompt must contain a JSON word in order to pass.");
        }
        return success();
    }
}
