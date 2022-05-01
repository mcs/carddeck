package poker;

import carddeck.Card;
import carddeck.CardFactory;
import carddeck.Rank;
import carddeck.Suit;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

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
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isFourOfAKind.test(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Js Jc Jh Jd 7s 8c 2h",
            "7s 8c 2h Js Jc Jh Jd",
            "Qs Qc 7s 8c 2h Qh Qd",
            "Qs Qh Qc Jc Jh Jd Js",
            "Ah 2c 2s Ac 2h 2d As"
    })
    void shouldResolveFourOfAKind(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);

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
        List<Card> cards = CardFactory.createCards("Qs 7s Jc 8c Jh 2h Jd");

        boolean result = isFourOfAKind.test(cards);

        assertFalse(result);
    }

}
