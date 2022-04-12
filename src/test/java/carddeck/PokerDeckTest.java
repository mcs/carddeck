package carddeck;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class PokerDeckTest {

    public static final int AMOUNT_ALL_CARDS = 52;
    private final PokerDeck deck = new PokerDeck();

    @Test
    void assertSize() {
        assertEquals(AMOUNT_ALL_CARDS, deck.size());
    }

    @Test
    void testDeal() {
        assumeTrue(deck.size() == AMOUNT_ALL_CARDS);

        Card card = deck.deal();

        assertNotNull(card);
        assertEquals(51, deck.size());
    }

    @Test
    void testDealWhenDeckEmpty() {
        IntStream.range(0, 52).forEach(i -> deck.deal());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, deck::deal);
        assertEquals("Not enough remaining cards in the deck", ex.getMessage());
    }

    @Test
    void assertCorrectCardDeck() {
        var cards = new ArrayList<Card>();
        while (deck.size() > 0)
            cards.add(deck.deal());
        var cardsPerSuit = cards.stream()
                .collect(Collectors.toMap(
                        Card::suit,
                        List::of,
                        (l1, l2) -> Stream
                                .concat(l1.stream(), l2.stream())
                                .collect(toList())));

        assertAll(() -> {
                    for (Suit suit : Suit.values()) {
                        List<Card> suitedCards = cardsPerSuit.get(suit);
                        assertEquals(AMOUNT_ALL_CARDS / 4, suitedCards.size());
                        for (Rank rank : Rank.values()) {
                            Card card = new Card(rank, suit);
                            assertTrue(suitedCards.contains(card), "Card not found: " + card);
                        }
                    }
                }
        );
    }

    @Test
    void testForRandomSuit() {
        Random rng = new Random();
        Suit suit = Suit.values()[rng.nextInt(Suit.values().length)];
        testRandomDealing(deck -> {
            Card card = deck.deal();
            return card.suit() == suit;
        }, 0.25, 0.005);
    }

    @Test
    void testTwoCardSuits() {
        testRandomDealing(deck -> {
            Card card1 = deck.deal();
            Card card2 = deck.deal();
            System.out.println(card1.suit() + " " + card2.suit() + " = " + (card1.suit() == card2.suit()));
            return card1.suit() == card2.suit();
        }, 12. / 51, 0.0049);
    }

    @Test
    void testTwoCardRanks() {
        testRandomDealing(deck -> {
            Card card1 = deck.deal();
            Card card2 = deck.deal();
            System.out.println(card1.rank() + " " + card2.rank() + " = " + (card1.rank() == card2.rank()));
            return card1.rank() == card2.rank();
        }, 3. / 51, 0.005);
    }


    private void testRandomDealing(Predicate<PokerDeck> checkFunction, double probability, double epsilon) {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            int counter = 0;
            int i;
            for (i = 0; i < 100 || Math.abs((double) counter / i - probability) > epsilon; i++) {
                PokerDeck deck = new PokerDeck();
                if (checkFunction.test(deck))
                    counter++;
            }
        });
    }

}