package poker;

import carddeck.Card;
import carddeck.Rank;
import carddeck.Suit;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsThreeOfAKindTest {

    private final IsNOfAKind isThreeOfAKind = new IsNOfAKind(IsNOfAKind.ThreeOrFour.THREE);

    @Test
    void shouldBeThreeOfAKindWithJacksAtStart() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS)
        );

        boolean result = isThreeOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldNotBeThreeOfAKindWithThreeJacksAndFourQueens() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.CLUBS),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS),
                new Card(Rank.JACK, Suit.SPADES)
        );

        boolean result = isThreeOfAKind.test(cards);

        assertFalse(result);
    }

    @Test
    void shouldNotBeThreeOfAKind() {
        List<Card> cards = List.of(
                new Card(Rank.JACK, Suit.SPADES),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS)
        );

        boolean result = isThreeOfAKind.test(cards);

        assertFalse(result);
    }

    @Test
    void shouldBeThreeOfAKindWithJacksAtEnd() {
        List<Card> cards = List.of(
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.JACK, Suit.HEARTS),
                new Card(Rank.JACK, Suit.DIAMONDS)
        );

        boolean result = isThreeOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldBeThreeOfAKindWithQueensAtStartAndEnd() {
        List<Card> cards = List.of(
                new Card(Rank.QUEEN, Suit.SPADES),
                new Card(Rank.JACK, Suit.CLUBS),
                new Card(Rank.SEVEN, Suit.SPADES),
                new Card(Rank.EIGHT, Suit.CLUBS),
                new Card(Rank.TWO, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.HEARTS),
                new Card(Rank.QUEEN, Suit.DIAMONDS)
        );

        boolean result = isThreeOfAKind.test(cards);

        assertTrue(result);
    }

    @Test
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isThreeOfAKind.test(null));
    }

}
