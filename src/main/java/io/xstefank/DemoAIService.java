package io.xstefank;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.InputGuardrails;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface DemoAIService {

//    @InputGuardrails(StarWarsIsAwesome.class)
    @OutputGuardrails(CorrectJsonOutputGuardrail.class)
    String chat(@MemoryId int memoryId, @UserMessage String prompt);
}

