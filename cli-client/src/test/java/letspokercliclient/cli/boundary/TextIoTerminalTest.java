package letspokercliclient.cli.boundary;

import letspokercliclient.cli.PlayerAction;
import org.beryx.textio.mock.MockTextTerminal;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class TextIoTerminalTest {

    @Spy
    private MockTextTerminal mockTextTerminal;

    private TextIoTerminal testee;

    @BeforeEach
    void setUp() {
        testee = new TextIoTerminal(mockTextTerminal);
    }

    @Test
    void runInternal() {
        mockTextTerminal.getInputs().addAll(List.of("CALL", "FOLD", "0", "3", "2"));
        String expected = """
                Hello Testplayer!
                Do you fold or raise? (FOLD, RAISE): CALL
                Invalid value. Enter a value between 1 and 2.
                Do you fold or raise? (FOLD, RAISE): FOLD
                Invalid value. Enter a value between 1 and 2.
                Do you fold or raise? (FOLD, RAISE): 0
                Invalid value. Enter a value between 1 and 2.
                Do you fold or raise? (FOLD, RAISE): 3
                Invalid value. Enter a value between 1 and 2.
                Do you fold or raise? (FOLD, RAISE): 2
                You chose RAISE as action.""";

        testee.println("Hello Testplayer!");
        PlayerAction askResult = testee.ask(
                "Do you fold or raise?",
                PlayerAction.class,
                PlayerAction.FOLD, PlayerAction.RAISE);
        testee.printf("You chose %s as action.", askResult);

        String result = mockTextTerminal.getOutput();
        assertEquals(expected, result);
    }

}