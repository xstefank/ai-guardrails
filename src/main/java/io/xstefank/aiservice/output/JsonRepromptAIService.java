package io.xstefank.aiservice.output;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import io.xstefank.guardrails.output.JsonReprompt;

@RegisterAiService
public interface JsonRepromptAIService {

    @OutputGuardrails(JsonReprompt.class)
    String chat(String prompt);
}
