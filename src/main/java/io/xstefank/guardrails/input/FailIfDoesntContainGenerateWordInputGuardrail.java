package io.xstefank.guardrails.input;

import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailParams;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FailIfDoesntContainGenerateWordInputGuardrail implements InputGuardrail {

    @Inject
    FailureAggregator failureAggregator;

    @Override
    public InputGuardrailResult validate(InputGuardrailParams params) {
        String s = params.userMessage().singleText();
        if (!s.toLowerCase().contains("generate")) {
            failureAggregator.failureMap.put("missing-generate", "Response does not contain word generate.");
            return fatal("Input prompt must contain a word \"generate\" in order to pass.");
        }

        return success();
    }
}
