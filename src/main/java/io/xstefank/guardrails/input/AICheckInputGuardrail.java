package io.xstefank.guardrails.input;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class AICheckInputGuardrail implements InputGuardrail {

    @Inject
    AIValidator aiValidator;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        String input = userMessage.singleText();
        if (!aiValidator.validate(input)) {
            // user input is considered improper
            return fatal(String.format("Your input \"%s\" is considered improper and " +
                "it won't be sent to the LLM.", input));
        }

        return success();
    }
}
