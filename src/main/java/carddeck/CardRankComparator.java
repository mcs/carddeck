package carddeck;

import java.util.Comparator;

public class CardRankComparator implements Comparator<Card> {
    public int compare(Card c1, Card c2) {
        return c1.rank().ordinal() - c2.rank().ordinal();
    }
}
