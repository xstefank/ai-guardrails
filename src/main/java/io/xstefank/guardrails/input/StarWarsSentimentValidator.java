package io.xstefank.guardrails.input;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService(modelName = "star-wars-validator")
public interface StarWarsSentimentValidator {

    @SystemMessage("You are a bot that checks improper user inputs.")
    @UserMessage("""
        Return whether the following user input contains any bad sentiment about Star Wars.
        
        The user input is "{input}".
        
        Return strictly one of the [true, false] values.
        """)
    boolean isImproper(String input);
}
