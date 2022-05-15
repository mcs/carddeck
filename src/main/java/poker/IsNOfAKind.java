package poker;

import carddeck.Card;
import carddeck.Rank;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

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
        var sortedCards = sortByRank(cardList);
        int maxInARow = findMaxRankOccurrences(sortedCards);
        return maxInARow == threeOrFour.n;
    }

    private List<Card> sortByRank(List<Card> cardList) {
        return cardList.stream()
                .filter(Objects::nonNull)
                .sorted(Comparator.comparing(Card::rank))
                .toList();
    }

    private int findMaxRankOccurrences(List<Card> cards) {
        int currentInARow = 0;
        int maxInARow = 0;
        Rank lastRank = null;
        for (Card card : cards) {
            Rank rank = card.rank();
            if (lastRank == rank) {
                currentInARow++;
                maxInARow = Math.max(currentInARow, maxInARow);
            } else {
                currentInARow = 1;
            }
            lastRank = rank;
        }
        return maxInARow;
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
