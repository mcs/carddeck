package carddeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardSuitComparatorTest {

    private final CardSuitComparator cardSuitComparator = new CardSuitComparator();

    @Test
    void compareSuit1Smaller2() {
        Card c1 = new Card(null, Suit.CLUBS);
        Card c2 = new Card(null, Suit.SPADES);

        int result = cardSuitComparator.compare(c1, c2);

        assertTrue(result < 0);
    }

    @Test
    void compareSuit1Greater2() {
        Card c1 = new Card(null, Suit.SPADES);
        Card c2 = new Card(null, Suit.CLUBS);

        int result = cardSuitComparator.compare(c1, c2);

        assertTrue(result > 0);
    }

    @Test
    void compareSuit1Equal2() {
        Card c1 = new Card(Rank.FOUR, Suit.CLUBS);
        Card c2 = new Card(Rank.THREE, Suit.CLUBS);

        int result = cardSuitComparator.compare(c1, c2);

        assertEquals(0, result);
    }
}