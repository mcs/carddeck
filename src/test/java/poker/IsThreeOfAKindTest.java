package poker;

import carddeck.Card;
import carddeck.CardFactory;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class IsThreeOfAKindTest {

    private final IsNOfAKind isThreeOfAKind = new IsNOfAKind(IsNOfAKind.ThreeOrFour.THREE);

    @Test
    void shouldReturnFalseIfNullIsPassed() {
        assertFalse(isThreeOfAKind.test(null));
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Qs Jc Jh Jd 7s 8c 2h",
            "7s 8c 2h Qs Jc Jh Jd",
            "Qs Jc 7s 8c 2h Qh Qd"
    })
    void shouldBeThreeOfAKind(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);

        boolean result = isThreeOfAKind.test(cards);

        assertTrue(result);
    }

    @ParameterizedTest
    @ValueSource(strings = {
            "Qs Qh Qc Jc Jh Jd Js",
            "Js 7s Jc 8c Jh 2h Jd"
    })
    void shouldNotBeThreeOfAKind(String cardsParam) {
        List<Card> cards = CardFactory.createCards(cardsParam);

        boolean result = isThreeOfAKind.test(cards);

        assertFalse(result);
    }

}
