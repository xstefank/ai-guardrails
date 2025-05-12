package io.xstefank.guardrails.input;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.memory.ChatMemory;
import io.quarkiverse.langchain4j.guardrails.InputGuardrail;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailParams;
import io.quarkiverse.langchain4j.guardrails.InputGuardrailResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class HallucinationDetectionGuardrail implements InputGuardrail {

    @Override
    public InputGuardrailResult validate(InputGuardrailParams params) {
        ChatMemory memory = params.memory();

        String name = null;

        for (ChatMessage m : memory.messages()) {
            if (m instanceof UserMessage um) {
                String text = um.singleText();
                if (text.contains("My name is")) {
                    name = parseName(text);
                    break;
                }
            }
        }

        UserMessage userMessage = params.userMessage();
        if (name != null && userMessage.singleText().contains("My name is") && !name.equals(parseName(userMessage.singleText()))) {
            return fatal(String.format("AI hallucinated a name. Your name is %s, not %s.", name, parseName(userMessage.singleText())));
        }

        return success();
    }

    private static String parseName(String text) {
        String start = text.substring(text.indexOf("My name is") + 11);
        return start.substring(0, start.indexOf("."));
    }
}
