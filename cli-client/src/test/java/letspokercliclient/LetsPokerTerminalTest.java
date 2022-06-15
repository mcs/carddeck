package letspokercliclient;

import org.beryx.textio.mock.MockTextTerminal;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class LetsPokerTerminalTest {

    @Spy
    private MockTextTerminal mockTextTerminal;

    @InjectMocks
    private LetsPokerTerminal testee;

    @Test
    void runInternal() {
        mockTextTerminal.getInputs().addAll(List.of("woozah", "woopas", "23", "3"));
        String expected = """
Let's play Poker!
=================
You are the Button. Do you want to Fold, Call or raise to amount X?
Username [admin]: woozah
Password: woopas
Age: 23
What month were you born in?
1: JANUARY
2: FEBRUARY
3: MARCH
4: APRIL
5: MAY
6: JUNE
7: JULY
8: AUGUST
9: SEPTEMBER
10: OCTOBER
11: NOVEMBER
12: DECEMBER
Enter your choice: 3
User woozah is 23 years old, was born in MARCH and has the password woopas.""";

        testee.runInternal();

        String result = mockTextTerminal.getOutput();
        assertEquals(expected, result);
    }
}