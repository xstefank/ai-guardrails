package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailureGenerate;
import io.xstefank.guardrails.input.FailureJson;

@RegisterAiService
public interface FailureJsonFailureGenerateAIService {

    @InputGuardrails({FailureJson.class,
            FailureGenerate.class})
    String chat(String prompt);
}
