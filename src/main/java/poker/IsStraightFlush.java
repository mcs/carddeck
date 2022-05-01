package poker;

import carddeck.Card;
import carddeck.Rank;
import carddeck.Suit;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class IsStraightFlush implements Predicate<List<Card>> {

    @Override
    public boolean test(List<Card> cards) {
        if (cards == null)
            return false;
        return findEveryButLowestStraightFlush(cards) || isLowestStraightFlush(cards);
    }

    private boolean findEveryButLowestStraightFlush(List<Card> cards) {
        List<Card> sortedCards = new ArrayList<>(cards);
        sortedCards.sort(Comparator.comparing(Card::suit).thenComparing(Card::rank));
        int maxFoundInARow = 0;
        int count = 0;
        Card lastCard = null;
        for (Card c : sortedCards) {
            if (lastCard != null) {
                if (lastCard.suit() == c.suit()
                        && lastCard.rank().ordinal() + 1 == c.rank().ordinal()) {
                    count++;
                } else {
                    count = 0;
                }
            }
            maxFoundInARow = Math.max(maxFoundInARow, count);
            lastCard = c;
        }
        return maxFoundInARow >= 4;
    }

    private boolean isLowestStraightFlush(List<Card> cards) {
        Set<Card> cardSet = new HashSet<>(cards);
        List<Rank> ranks = List.of(Rank.ACE, Rank.TWO, Rank.THREE, Rank.FOUR, Rank.FIVE);
        for (Suit suit : Suit.values()) {
            Set<Card> lowestStraightFlushCards = ranks.stream()
                    .map(r -> new Card(r, suit))
                    .collect(Collectors.toSet());
            if (cardSet.containsAll(lowestStraightFlushCards)) {
                return true;
            }
        }
        return false;
    }

}
