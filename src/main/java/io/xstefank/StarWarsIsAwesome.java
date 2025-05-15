package io.xstefank;

import dev.langchain4j.data.message.UserMessage;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class StarWarsIsAwesome implements InputGuardrail {

    @Inject
    StarWarsAwesomeAIService starWarsAwesomeAIService;

    @Override
    public InputGuardrailResult validate(UserMessage userMessage) {
        if (starWarsAwesomeAIService.isImproperStarWars(userMessage.singleText())) {
            return successWith("Star Wars is AWESOME!");
        }

        return success();
    }
}
