package carddeck;

import java.util.ArrayList;
import java.util.List;

public class Deck {

    private final List<Card> cards;

    public Deck(Card... cards) {
        if (cards == null) {
            throw new IllegalArgumentException("cards must not be null");
        }
        this.cards = new ArrayList<>();
        for (Card c : cards) {
            if (c == null) {
                throw new IllegalArgumentException("every card must not be null");
            }
            this.cards.add(c);
        }
    }

    public Deck() {
        cards = new ArrayList<>();
    }

    public int size() {
        return cards.size();
    }

    public Card take() {
        if (size() == 0)
            throw new IllegalStateException();
        return cards.remove(0);
    }
}
