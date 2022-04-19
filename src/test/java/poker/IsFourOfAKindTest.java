package poker;

import carddeck.Card;
import carddeck.Rank;
import carddeck.Suit;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IsFourOfAKindTest {

    private final IsNOfAKind isFourOfAKind = new IsNOfAKind(IsNOfAKind.ThreeOrFour.FOUR);

    @Test
    void shouldFailFastIfConstructingWithNull() {
        assertThrows(NullPointerException.class, () -> new IsNOfAKind(null));
    }

    @Test
    void shouldBeFourOfAKindWithJacksAtStart() {
        List<Card> cards = List.of(
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS)
        );

        boolean result = isFourOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldIgnoreNullElements() {
        List<Card> cards = new ArrayList<>();

        cards.add(null);
        cards.add(new Card(Rank.JACK, Suit.SPADES));
        cards.add(new Card(Rank.JACK, Suit.CLUBS));
        cards.add(null);
        cards.add(new Card(Rank.JACK, Suit.HEARTS));
        cards.add(new Card(Rank.JACK, Suit.DIAMONDS));
        cards.add(null);

        boolean result = isFourOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldNotBeFourOfAKind() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS)
        );

        boolean result = isFourOfAKind.test(cards);

        assertFalse(result);
    }

    @Test
    void shouldBeFourOfAKindWithJacksAtEnd() {
        List<Card> cards = List.of(
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS)
        );

        boolean result = isFourOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldBeFourOfAKindWithQueensAtStartAndEnd() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS)
        );

        boolean result = isFourOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldBeFourOfAKindWithThreeJacksAndFourQueens() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.JACK, Suit.SPADES)
        );

        boolean result = isFourOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isFourOfAKind.test(null));
    }

}
