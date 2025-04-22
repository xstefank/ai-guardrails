package io.xstefank.guardrails.output;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.langchain4j.data.message.AiMessage;
import io.quarkiverse.langchain4j.guardrails.JsonGuardrailsUtils;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrail;
import io.quarkiverse.langchain4j.guardrails.OutputGuardrailResult;
import io.quarkus.logging.Log;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class JsonRewrite implements OutputGuardrail {

    @Inject
    ObjectMapper mapper;

    @Inject
    JsonGuardrailsUtils jsonGuardrailsUtils;

    @Override
    public OutputGuardrailResult validate(AiMessage aiMessage) {
        try {
            mapper.readTree(aiMessage.text());
        } catch (Exception e) {
            Log.warn("Invalid JSON: " + aiMessage.text());
            return successWith("""
                The LLM couldn't generate a valid JSON object. So here is one:
                
                {
                    "foo": "bar"
                }
                """);
        }
        return success();
    }

}