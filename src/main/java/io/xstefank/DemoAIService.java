package io.xstefank;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import io.xstefank.guardrails.output.JsonRewrite;

@RegisterAiService
public interface DemoAIService {

    @OutputGuardrails(JsonRewrite.class)
    String chat(String prompt);
}
