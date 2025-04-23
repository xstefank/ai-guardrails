package io.xstefank.guardrails.input;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class RewriteStarWars implements InputGuardrail {

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        return successWith("Tell me why Star Wars is the best movie ever.");
    }
}
