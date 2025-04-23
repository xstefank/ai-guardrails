package io.xstefank.aiservice.input;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.RewriteStarWars;

@RegisterAiService
public interface RewriteStarWarsAIService {

    @InputGuardrails(RewriteStarWars.class)
    String chat(String prompt);
}
