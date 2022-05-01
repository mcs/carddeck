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

class IsStraightFlushTest {

    private final IsStraightFlush isStraightFlush = new IsStraightFlush();

    @Test
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isStraightFlush.test(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Ah Th Kh Jh Qh",
            "2s 3s 4s 6s 5s",
            "As 2s 3s 4s 5s",
            "9h 2d 3d 4d 5d 6d Tc"
    })
    void shouldResolveValidStraightFlush(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);
        assertTrue(isStraightFlush.test(cards));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "2h 3h 4h 5h 7h",
            "Kh Ah 2h 3h 4h",
            "As 2h 3h 4h 5h 6c 7h"
    })
    void shouldNotResolveInvalidStraightFlush(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);
        assertFalse(isStraightFlush.test(cards));
    }

    @Test
    void shouldFindAllStraightFlushCombinations() {
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
                            if (isStraightFlush.test(List.of(
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
        assertEquals(20, countResolvedStraightFlushes);
    }

}
