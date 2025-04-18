package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailureJson;
import io.xstefank.guardrails.input.FatalGenerate;

@RegisterAiService
public interface FailureJsonFatalGenerateAIService {

    @InputGuardrails({FailureJson.class,
            FatalGenerate.class})
    String chat(String prompt);
}
