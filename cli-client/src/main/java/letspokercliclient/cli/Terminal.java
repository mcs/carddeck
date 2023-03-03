package letspokercliclient.cli;

import org.springframework.stereotype.Component;

@Component
public interface Terminal {
    void println(String text);

    void printf(String template, Object... pattern);

    @SuppressWarnings("unchecked")
    <T extends Enum<?>> T ask(String question, Class<T> answerClass, T... allowedAnswers);
}
