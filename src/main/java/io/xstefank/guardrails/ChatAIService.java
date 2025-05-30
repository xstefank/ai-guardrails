package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import io.xstefank.guardrails.output.JsonReprompt;
import io.xstefank.guardrails.output.JsonRewrite;

@RegisterAiService
public interface ChatAIService {

    @OutputGuardrails({JsonRewrite.class,
        JsonReprompt.class})
    String chat(String prompt);
}
