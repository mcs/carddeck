package carddeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CardTest {

    @Test
    void testToString() {
        Card card = new Card(Rank.ACE, Suit.HEARTS);

        assertEquals("Ah", card.toString());
    }
}