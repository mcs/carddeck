package letspokercliclient.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class LetsPokerCommandLineRunnerTest {

    @Mock
    private LetsPokerTerminal letsPokerTerminal;
    @InjectMocks
    private LetsPokerCommandLineRunner testee;

    @Test
    void testRun() {
        testee.run();

        verify(letsPokerTerminal).runInternal();
    }
}