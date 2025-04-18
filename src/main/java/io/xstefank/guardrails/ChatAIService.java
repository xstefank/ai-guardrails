package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailureJson;

@RegisterAiService
@InputGuardrails(FailureJson.class)
public interface ChatAIService {

    String chat(String prompt);
}
