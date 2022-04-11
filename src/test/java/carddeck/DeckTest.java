package carddeck;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
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

        IllegalStateException ex = assertThrows(IllegalStateException.class, deck::take);
        assertEquals("Deck is empty", ex.getMessage());
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
        assumeTrue(deck.size() == 2);

        assertEquals("Ah", deck.take().value());
        assertEquals("Ts", deck.take().value());
    }

    @Test
    void takeNShouldOnlyAcceptPositiveNumbers() {
        Deck deck = new Deck(new Card("1"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> deck.take(0));
        assertEquals("Must take at least one card", ex.getMessage());
    }

    @Test
    void takeNShouldCheckIfNIsTooHigh() {
        Deck deck = new Deck(new Card("1"));

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> deck.take(2));
        assertEquals("Not enough remaining cards in the deck", ex.getMessage());
    }

    @Test
    void take1From1() {
        Card card1 = new Card("1");
        Deck deck = new Deck(card1);
        assumeTrue(deck.size() == 1);
        List<Card> expected = List.of(card1);

        assertEquals(expected, deck.take(1));
        assertEquals(0, deck.size());
    }

    @Test
    void take2From2() {
        Card card1 = new Card("1");
        Card card2 = new Card("2");
        Deck deck = new Deck(card1, card2);
        assumeTrue(deck.size() == 2);
        List<Card> expected = List.of(card1, card2);

        assertEquals(expected, deck.take(2));
        assertEquals(0, deck.size());
    }

    @Test
    void take1From2() {
        Card card1 = new Card("1");
        Card card2 = new Card("2");
        Deck deck = new Deck(card1, card2);
        assumeTrue(deck.size() == 2);
        List<Card> expected = List.of(card1);

        assertEquals(expected, deck.take(1));
        assertEquals(1, deck.size());
    }

    @Test
    void acceptSameCardMultipleTimes() {
        Card card = new Card("Ah");
        Deck deck = new Deck(card, card);

        assertEquals(2, deck.size());
        assertEquals("Ah", deck.take().value());
        assertEquals(1, deck.size());
        assertEquals("Ah", deck.take().value());
        assertEquals(0, deck.size());
    }

    @Test
    void appendCards() {
        Deck deck = new Deck();

        Card card1 = new Card("1");
        Card card2 = new Card("2");
        deck.append(card1);
        deck.append(card2);

        assertSame(card1, deck.take());
        assertSame(card2, deck.take());
    }

    @Test
    void shuffle100Cards() {
        Deck originalDeck = new Deck();
        Deck toBeShuffled = new Deck();
        // 100 cards provide 100! = 10^157 possibilities, so should practically never randomly fail
        for (int i = 1; i <= 100; i++) {
            Card c = new Card("" + i);
            originalDeck.append(c);
            toBeShuffled.append(c);
        }

        toBeShuffled.shuffle();

        assertEquals(originalDeck.size(), toBeShuffled.size());
        boolean shuffled = false;
        while (!shuffled && toBeShuffled.size() > 0) {
            if (!Objects.equals(toBeShuffled.take(), originalDeck.take())) {
                shuffled = true;
            }
        }
        assertTrue(shuffled, "Deck has not been shuffled");
    }
}
