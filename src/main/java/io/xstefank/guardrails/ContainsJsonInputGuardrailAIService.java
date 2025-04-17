package io.xstefank.guardrails;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.FailIfDoesntContainJSONWordInputGuardrail;

@RegisterAiService
public interface ContainsJsonInputGuardrailAIService {

    @InputGuardrails(FailIfDoesntContainJSONWordInputGuardrail.class)
    String chat(String prompt);
}
