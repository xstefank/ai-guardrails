package io.xstefank.aiservice.input;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.StarWarsCheckInputGuardrail;

@RegisterAiService(modelName = "star-wars-validator")
//@RegisterAiService
public interface StarWarsAwesomeAIService {

    @InputGuardrails(StarWarsCheckInputGuardrail.class)
    String chat(String prompt);
}
