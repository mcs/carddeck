package letspokercliclient.cli.boundary;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.springframework.stereotype.Component;

@Component
public class TextIoTerminal {
    private final TextTerminal<?> terminal;
    private final TextIO textIO;


    public TextIoTerminal(TextTerminal<?> terminal) {
        this.terminal = terminal;
        textIO = new TextIO(terminal);

    }

    public void println(String text) {
        terminal.println(text);
    }

    public void printf(String template, Object... pattern) {
        terminal.printf(template, pattern);
    }

    public final <T extends Enum<T>> T ask(String question, Class<T> answerClass, T... allowedAnswers) {
        return textIO
                .newEnumInputReader(answerClass)
                .withInlinePossibleValues(allowedAnswers)
                .read(question);
    }
}
