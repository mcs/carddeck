package poker;

import carddeck.Card;
import carddeck.Suit;

import java.util.Comparator;
import java.util.List;

public class IsFlush {

    private final IsStraightFlush isStraightFlush = new IsStraightFlush();

    public boolean test(List<Card> cards) {
        if (cards == null || isStraightFlush.test(cards)) {
            return false;
        }
        List<Card> sortedCards = cards.stream()
                .sorted(Comparator.comparing(Card::suit))
                .toList();
        int suitsInARow = 1;
        Suit lastSuit = null;
        for (Card card : sortedCards) {
            Suit suit = card.suit();
            if (lastSuit != null && lastSuit == suit) {
                suitsInARow++;
                if (suitsInARow == 5)
                    return true;
            } else {
                suitsInARow = 1;
            }
            lastSuit = suit;
        }
        return false;
    }

}
