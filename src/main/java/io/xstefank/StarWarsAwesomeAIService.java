package io.xstefank;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import io.quarkiverse.langchain4j.RegisterAiService;

@RegisterAiService
public interface StarWarsAwesomeAIService {

    @SystemMessage("You are big Star Wars fan.")
    @UserMessage("""
        Rate the following user message if it contains
        any negative sentiment about Star Wars.
        
        The user input is "{prompt}".
        """)
    boolean isImproperStarWars(String prompt);

}
