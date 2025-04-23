package io.xstefank.aiservice.output;

import io.quarkiverse.langchain4j.RegisterAiService;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrails;
import io.xstefank.guardrails.output.PersonOutputGuardrail;
import io.xstefank.model.Person;

@RegisterAiService
public interface PersonAIService {

    @OutputGuardrails(PersonOutputGuardrail.class)
    Person generateSamplePerson(String prompt);
}
