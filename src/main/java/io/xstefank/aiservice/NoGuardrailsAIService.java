package io.xstefank.aiservice;

import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface NoGuardrailsAIService {

    String chat(String prompt);
}
