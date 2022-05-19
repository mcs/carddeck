package poker;

import carddeck.Card;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class IsFlush {

    private final IsStraightFlush isStraightFlush = new IsStraightFlush();

    public boolean test(List<Card> cards) {
        if (cards == null || isStraightFlush.test(cards)) {
            return false;
        }
        return cards.stream()
                .map(Card::suit)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .anyMatch(count -> count >= 5);
    }

}
