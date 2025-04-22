package io.xstefank;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;
import jakarta.enterprise.context.ApplicationScoped;

@RegisterAiService
@ApplicationScoped
public interface DemoAIService {

    @UserMessage("{prompt}")
    String chat(@MemoryId int memoryId, String prompt);
}

