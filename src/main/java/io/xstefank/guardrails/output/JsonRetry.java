package io.xstefank.guardrails.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.data.message.AiMessage;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrail;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrailResult;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JsonRetry implements OutputGuardrail {

    @Inject
    ObjectMapper mapper;

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        try {
            mapper.readTree(aiMessage.text());
        } catch (Exception e) {
            Log.warn("Invalid JSON", e);
            return retry("Invalid JSON, return a valid JSON object", e);
        }
        return success();
    }

}