package carddeck;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class PokerDeck {

    private final List<Card> cards = new LinkedList<>();

    public PokerDeck() {
        for (Suit s : Suit.values()) {
            for (Rank r : Rank.values()) {
                cards.add(new Card(r, s));
            }
        }

    }

    List<Card> getCards() {
        return Collections.unmodifiableList(cards);
    }

    public int size() {
        return cards.size();
    }

    public Card deal() {
        if (cards.isEmpty())
            throw new IllegalArgumentException("Not enough remaining cards in the deck");
        return cards.remove(0);

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
