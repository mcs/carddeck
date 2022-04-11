package carddeck;

import java.util.ArrayList;
import java.util.Collections;
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
            throw new IllegalStateException("Deck is empty");
        return cards.remove(0);
    }

    public void shuffle() {
        Collections.reverse(cards);
    }

    public void append(Card card) {
        cards.add(card);
    }

    public List<Card> take(int amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Must take at least one card");
        if (size() < amount)
            throw new IllegalArgumentException("Not enough remaining cards in the deck");
        List<Card> takenCards = new ArrayList<>();
        for (int i = amount; i > 0; i--) {
            takenCards.add(this.cards.remove(0));
        }
        return takenCards;
    }
}
