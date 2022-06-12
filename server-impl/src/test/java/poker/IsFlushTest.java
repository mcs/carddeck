package poker;

import carddeck.Card;
import carddeck.CardFactory;
import carddeck.PokerDeck;
import carddeck.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class IsFlushTest {

    private final IsFlush isFlush = new IsFlush();

    @Test
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isFlush.test(null));
    }

    @Test
    void shouldNotResolveFlushForEmptyList() {
        assertFalse(isFlush.test(List.of()));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ad 3d 4d 5d 6d",
            "Ad 2s 3d 4d 5d 6d"
    })
    void shouldResolveValidFlush(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);
        assertTrue(isFlush.test(cards));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ah 2h 3h 4h 5s",
            "Ah 2h 3h 4h 5h",
            "3s"
    })
    void shouldNotResolveInvalidFlush(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);
        assertFalse(isFlush.test(cards));
    }

    @Test
    void shouldFindAllFlushCombinations() {
        PokerDeck deck = new PokerDeck();
        List<Card> allCards = new ArrayList<>();
        while (deck.size() > 0) {
            Card card = deck.deal();
            // reduce amount of tested cards in order to speed up test
            if (card.suit() == Suit.CLUBS || card.suit() == Suit.HEARTS) {
                allCards.add(card);
            }
        }

        int deckSize = allCards.size();
        int i = 0;
        int countResolvedStraightFlushes = 0;
        for (int a = 0; a < deckSize - 4; a++) {
            for (int b = a + 1; b < deckSize - 3; b++) {
                for (int c = b + 1; c < deckSize - 2; c++) {
                    for (int d = c + 1; d < deckSize - 1; d++) {
                        for (int e = d + 1; e < deckSize; e++) {
                            i++;
                            if (isFlush.test(List.of(
                                    allCards.get(a),
                                    allCards.get(b),
                                    allCards.get(c),
                                    allCards.get(d),
                                    allCards.get(e)
                            ))) {
                                countResolvedStraightFlushes++;
                            }
                        }
                    }
                }
            }
        }
        assumeTrue(65_780 == i);
        assertEquals(2_554, countResolvedStraightFlushes);
    }

}
