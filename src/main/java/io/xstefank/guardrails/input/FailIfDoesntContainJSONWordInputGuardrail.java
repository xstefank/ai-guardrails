package io.xstefank.guardrails.input;

import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailParams;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import static io.smallrye.mutiny.helpers.Subscriptions.fail;

@ApplicationScoped
public class FailIfDoesntContainJSONWordInputGuardrail implements InputGuardrail {

    @Inject
    FailureAggregator failureAggregator;

    @Override
    public InputGuardrailResult validate(InputGuardrailParams params) {
        String s = params.userMessage().singleText();
        if (!s.toLowerCase().contains("json")) {
            failureAggregator.failureMap.put("missing-json", "Response does not contain word JSON.");
            return failure("Input prompt must contain a JSON word in order to pass.");
        }
        return success();
    }
}
