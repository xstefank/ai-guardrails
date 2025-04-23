package io.xstefank.aiservice.output;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import io.xstefank.guardrails.output.JsonRetry;

@RegisterAiService
public interface JsonRetryAIService {

    @OutputGuardrails(JsonRetry.class)
    String chat(String prompt);
}
