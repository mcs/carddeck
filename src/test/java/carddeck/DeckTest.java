package carddeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckTest {

    @Test
    void createEmpty() {
        Deck deck = new Deck();

        assertEquals(0, deck.size());
    }

    @Test
    void createDeckWith1Card() {
        Deck deck = new Deck(new Card());

        assertEquals(1, deck.size());
    }

    @Test
    void createDeckWith2Cards() {
        Deck deck = new Deck(new Card(), new Card());

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

}
