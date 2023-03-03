package letspokercliclient.cli;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LetsPokerTerminalTest {

    @Mock
    private Terminal terminal;
    @Mock
    private IGame game;

    @InjectMocks
    private LetsPokerTerminal testee;

    @Test
    void runInternal() {
        when(game.getPlayerPosition()).thenReturn("SB");
        when(game.getHoleCards()).thenReturn("6s 2h");
        when(terminal.ask(
                "Choose if you want to Fold, Call or Raise",
                PlayerAction.class,
                PlayerAction.FOLD, PlayerAction.CALL, PlayerAction.RAISE
        )).thenReturn(PlayerAction.FOLD);

        testee.runInternal();

        InOrder inOrder = Mockito.inOrder(terminal);
        inOrder.verify(terminal).println("Let's play Poker!");
        inOrder.verify(terminal).println("=================");
        inOrder.verify(terminal).printf("You are %s.%n", "SB");
        inOrder.verify(terminal).println("Your hole cards: 6s 2h");
        inOrder.verify(terminal).printf("You chose %s.%n", PlayerAction.FOLD);
    }
}