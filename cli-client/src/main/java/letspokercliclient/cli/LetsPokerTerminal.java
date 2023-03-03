package letspokercliclient.cli;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetsPokerTerminal {

    private final Terminal terminal;
    private final IGame game;

    public void runInternal() {
        String position = game.getPlayerPosition();
        String holeCards = game.getHoleCards();

        terminal.println("Let's play Poker!");
        terminal.println("=================");
        terminal.printf("You are %s.%n", position);
        terminal.println("Your hole cards: " + holeCards);

        PlayerAction action = terminal.ask(
                "Choose if you want to Fold, Call or Raise",
                PlayerAction.class,
                PlayerAction.FOLD, PlayerAction.CALL, PlayerAction.RAISE
        );

        terminal.printf("You chose %s.%n", action);
    }

}