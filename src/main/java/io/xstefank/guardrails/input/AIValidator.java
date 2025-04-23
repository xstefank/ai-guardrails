package io.xstefank.guardrails.input;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface AIValidator {

//    @SystemMessage("You are a bot that check improper user inputs.")
    @UserMessage("""
        "Does {{input}} has a positive sentiment?"
        """)
    boolean validate(String input);
}
