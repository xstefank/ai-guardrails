package io.xstefank.aiservice.input;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.xstefank.guardrails.input.HallucinationDetectionGuardrail;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
@RegisterAiService
public interface NameHallucinationDetectionAIService {

    @InputGuardrails(HallucinationDetectionGuardrail.class)
    String chat(@MemoryId int id, @UserMessage String prompt);
}
