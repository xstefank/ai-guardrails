package io.xstefank.guardrails.output;

import io.quarkiverse.langchain4j.guardrails.AbstractJsonExtractorOutputGuardrail;
import io.xstefank.model.Person;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PersonOutputGuardrail extends AbstractJsonExtractorOutputGuardrail {
    @Override
    protected Class<?> getOutputClass() {
        return Person.class;
    }
}
