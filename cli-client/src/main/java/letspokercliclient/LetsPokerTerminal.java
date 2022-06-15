package letspokercliclient;

import lombok.RequiredArgsConstructor;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.springframework.stereotype.Component;

import java.time.Month;

@Component
@RequiredArgsConstructor
public class LetsPokerTerminal {

    private final TextTerminal<?> terminal;

    public void runInternal() {
        TextIO textIO = new TextIO(terminal);
        terminal.println("Let's play Poker!");
        terminal.println("=================");
        terminal.println("You are the Button. Do you want to Fold, Call or raise to amount X?");

        String user = textIO.newStringInputReader()
                .withDefaultValue("admin")
                .read("Username");

        String password = textIO.newStringInputReader()
                .withMinLength(6)
                .withInputMasking(true)
                .read("Password");

        int age = textIO.newIntInputReader()
                .withMinVal(13)
                .read("Age");

        Month month = textIO.newEnumInputReader(Month.class)
                .read("What month were you born in?");

        terminal.printf("User %s is %d years old, was born in %s and has the password %s.\n",
                user, age, month, password);
    }

}