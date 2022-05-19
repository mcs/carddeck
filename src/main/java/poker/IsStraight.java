package poker;

import carddeck.Card;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsStraight implements Predicate<List<Card>> {
    private final IsStraightFlush isStraightFlush = new IsStraightFlush();

    @Override
    public boolean test(List<Card> cards) {
        if (cards == null || isStraightFlush.test(cards))
            return false;

        List<Card> sorted = cards.stream()
                .sorted(Comparator.comparing(Card::rank))
                .map(c -> new Card(c.rank(), null))
                .collect(Collectors.toCollection(ArrayList::new));
        sorted = new ArrayList<>(new LinkedHashSet<>(sorted));
        Card lastCard = sorted.get(sorted.size() - 1);
        sorted.add(0, lastCard);
        Card previousCard = null;
        int maxFoundInARow = 0;
        int inARow = 1;
        for (Card card : sorted) {
            if (previousCard != null) {
                if ((previousCard.rank().ordinal() + 1) % 13 == card.rank().ordinal()) {
                    inARow++;
                } else {
                    inARow = 1;
                }
            }
            maxFoundInARow = Math.max(maxFoundInARow, inARow);
            previousCard = card;
        }
        return maxFoundInARow >= 5;
    }
}
