package io.xstefank.aiservice.input;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailureJson;
import io.xstefank.guardrails.input.FatalGenerate;

@RegisterAiService
public interface FatalGenerateFailureJsonAIService {

    @InputGuardrails({FatalGenerate.class,
            FailureJson.class})
    String chat(String prompt);
}
