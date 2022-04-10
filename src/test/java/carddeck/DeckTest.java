package carddeck;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class DeckTest {

    @Test
    void createWith32Cards() {
        Deck deck = new Deck(0);

        assertEquals(0, deck.size());
    }

    @Test
    void deckMustNotHaveNegativeCardsOnInit() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Deck(-1));

        assertEquals("A deck must not be less than empty", ex.getMessage());
    }

}
