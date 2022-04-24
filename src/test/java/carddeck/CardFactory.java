package carddeck;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardFactory {

    public static Card createCard(String cardShortcut) {
        String rankShortcut = cardShortcut.substring(0, 1);
        String suitShortcut = cardShortcut.substring(1, 2);
        var rank = Arrays.stream(Rank.values())
                .filter(r -> r.getShortcut().equals(rankShortcut))
                .findFirst()
                .orElseThrow();
        var suit = Arrays.stream(Suit.values())
                .filter(s -> s.getShortcut().equals(suitShortcut))
                .findFirst()
                .orElseThrow();
        return new Card(rank, suit);
    }

    public static List<Card> createCards(String cardShortcuts) {
        List<Card> cards = new ArrayList<>();
        for (String cardShortcut : cardShortcuts.split("\\W+")) {
            Card card = createCard(cardShortcut);
            cards.add(card);
        }
        return cards;
    }

    static class CardFactoryTest {

        @Test
        void shouldCreateAceOfSpades() {
            Card expected = new Card(Rank.ACE, Suit.SPADES);

            Card result = createCard("As");

            assertEquals(expected, result);
        }

        @Test
        void shouldCreateAceOfSpadesAndKingOfHeartsAndTwoOfDiamondsAndThreeOfClubs() {
            List<Card> expected = List.of(
                    new Card(Rank.ACE, Suit.SPADES),
                    new Card(Rank.KING, Suit.HEARTS),
                    new Card(Rank.TWO, Suit.DIAMONDS),
                    new Card(Rank.THREE, Suit.CLUBS)
            );

            List<Card> result = createCards("As Kh, 2d/3c");

            assertEquals(expected, result);
        }
    }
}
