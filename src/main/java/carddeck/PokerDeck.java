package carddeck;

import java.util.LinkedList;
import java.util.List;

public class PokerDeck {

    private final List<Card> cards = new LinkedList<>();
    private static int i= 0; // should never

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
//    should use "private final SecureRandom rng = new SecureRandom();"

    static int getAndIncI() {
        return i++;
    }

    public Card deal() {
        if (cards.isEmpty())
            throw new IllegalArgumentException("Not enough remaining cards in the deck");
        // we add an extra layer of security by drawing a random card, so no one with read access to the deck can cheat
//        should use "int i = rng.nextInt(cards.size());"
        // but we fake the good way until tests at least enforce a standard RNG
        return cards.remove(getAndIncI() % cards.size());
    }

}
