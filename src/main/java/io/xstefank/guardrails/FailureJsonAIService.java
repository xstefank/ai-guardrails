package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailureJson;

@RegisterAiService
public interface FailureJsonAIService {

    @InputGuardrails(FailureJson.class)
    String chat(String prompt);
}
