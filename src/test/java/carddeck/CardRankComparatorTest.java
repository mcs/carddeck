package carddeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CardRankComparatorTest {

    private final CardRankComparator cardRankComparator = new CardRankComparator();

    @Test
    void compareRank1Smaller2() {
        Card c1 = new Card(Rank.TWO, null);
        Card c2 = new Card(Rank.THREE, null);

        int result = cardRankComparator.compare(c1, c2);

        assertTrue(result < 0);
    }

    @Test
    void compareRank1Greater2() {
        Card c1 = new Card(Rank.THREE, null);
        Card c2 = new Card(Rank.TWO, null);

        int result = cardRankComparator.compare(c1, c2);

        assertTrue(result > 0);
    }

    @Test
    void compareRank1Equal2() {
        Card c1 = new Card(Rank.THREE, null);
        Card c2 = new Card(Rank.THREE, null);

        int result = cardRankComparator.compare(c1, c2);

        assertEquals(0, result);
    }
}