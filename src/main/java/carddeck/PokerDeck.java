package carddeck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PokerDeck {

    private final List<Card> cards = new ArrayList<>();

    public PokerDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards.add(new Card(r, s));
            }
        }

    }

    public int size() {
        return cards.size();
    }

    public Card take() {
        return take(1).get(0);
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

    public void append(Card card) {
        cards.add(card);
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    @Override
    public String toString() {
        List<String> cardsAsString = cards.stream()
                .map(Card::toString)
                .toList();
        return "[" + String.join(" ", cardsAsString) + "]";
    }
}
