package carddeck;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

public class PokerDeck {

    private final List<Card> cards = new LinkedList<>();
    private final SecureRandom rng = new SecureRandom();

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

    public Card deal() {
        if (cards.isEmpty())
            throw new IllegalArgumentException("Not enough remaining cards in the deck");
        return cards.remove(rng.nextInt(cards.size()));
    }

}
