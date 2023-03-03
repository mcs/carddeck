package poker;

import carddeck.Card;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public final class IsNOfAKind implements Predicate<List<Card>> {
    private final ThreeOrFour threeOrFour;

    public IsNOfAKind(ThreeOrFour threeOrFour) {
        Objects.requireNonNull(threeOrFour);
        this.threeOrFour = threeOrFour;
    }

    @Override
    public boolean test(List<Card> cardList) {
        if (cardList == null) {
            return false;
        }
        return findMaxRankOccurrences(cardList);
    }

    private boolean findMaxRankOccurrences(List<Card> cardList) {
        return cardList.stream()
                .filter(Objects::nonNull)
                .map(Card::rank)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .values().stream()
                .mapToInt(Math::toIntExact)
                .max()
                .orElse(0) == threeOrFour.n;
    }

    public enum ThreeOrFour {
        THREE(3),
        FOUR(4);

        private final int n;

        ThreeOrFour(int n) {
            this.n = n;
        }
    }

}
