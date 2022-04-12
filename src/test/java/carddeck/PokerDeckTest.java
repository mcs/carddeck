package carddeck;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.util.List;
import java.util.Map;
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
    void testToString() {
        assertEquals("[2c 3c 4c 5c 6c 7c 8c 9c Tc Jc Qc Kc Ac 2s 3s 4s 5s 6s 7s 8s 9s Ts Js Qs Ks As 2h 3h 4h 5h 6h 7h 8h 9h Th Jh Qh Kh Ah 2d 3d 4d 5d 6d 7d 8d 9d Td Jd Qd Kd Ad]", deck.toString());
    }

    @Test
    void assertSize() {
        assertEquals(AMOUNT_ALL_CARDS, deck.size());
    }

    @Test
    void assertCorrectCardDeck() {
        Map<Suit, List<Card>> cardsPerSuit = deck.getCards().stream()
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
        testShuffle(deck -> {
            Card card = deck.getCards().get(0);
            return card.suit() == suit;
        }, 0.25, 0.005);
    }

    @Test
    void testTwoCards() {
        testShuffle(deck -> {
            Card card1 = deck.getCards().get(0);
            Card card2 = deck.getCards().get(1);
            return card1.suit() == card2.suit();
        }, 12./51, 0.0049);
    }


    private void testShuffle(Predicate<PokerDeck> checkFunction, double probability, double epsilon) {
        assertTimeoutPreemptively(Duration.ofSeconds(2), () -> {
            int counter = 0;
            int i;
            for (i = 0; i < 100 || Math.abs((double) counter / i - probability) > epsilon; i++) {
                deck.shuffle();
                if (checkFunction.test(deck))
                    counter++;
            }
        });
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
        IntStream.range(0, 52).forEach( i -> deck.deal());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, deck::deal);
        assertEquals("Not enough remaining cards in the deck", ex.getMessage());
    }
}