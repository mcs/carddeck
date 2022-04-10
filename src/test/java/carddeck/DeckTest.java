package carddeck;

import org.junit.jupiter.api.Assumptions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class DeckTest {

    @Test
    void createEmpty() {
        Deck deck = new Deck();

        assertEquals(0, deck.size());
    }

    @Test
    void createDeckWith1Card() {
        Deck deck = new Deck(new Card(""));

        assertEquals(1, deck.size());
    }

    @Test
    void createDeckWith2Cards() {
        Deck deck = new Deck(new Card(""), new Card(""));

        assertEquals(2, deck.size());
    }

    @Test
    void dontAllowNullParameterForConstructor() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Deck((Card[]) null));

        assertEquals("cards must not be null", ex.getMessage());
    }

    @Test
    void dontAllowNullCards() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Deck((Card) null));

        assertEquals("every card must not be null", ex.getMessage());
    }

    @Test
    void throwIllegalStateExceptionIfDrawingEmptyDeck() {
        Deck deck = new Deck();
        assumeTrue(deck.size() == 0);

        assertThrows(IllegalStateException.class, deck::take);
    }

    @Test
    void takeCard() {
        Card expectedCard = new Card("");
        Deck deck = new Deck(expectedCard);
        assumeTrue(deck.size() == 1);

        Card card = deck.take();

        assertEquals(expectedCard, card);
        assertEquals(0, deck.size());
    }

    @Test
    void takeOrder() {
        Deck deck = new Deck(new Card("Ah"), new Card("Ts"));

        assertEquals("Ah", deck.take().value());
        assertEquals("Ts", deck.take().value());
    }
}
