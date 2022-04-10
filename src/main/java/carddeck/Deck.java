package carddeck;

public class Deck {

    private final Card[] cards;

    public Deck(Card... cards) {
        if (cards == null) {
            throw new IllegalArgumentException("cards must not be null");
        }
        for (Card card : cards) {
            if (card == null) {
                throw new IllegalArgumentException("every card must not be null");
            }
        }
        this.cards = cards;
    }

    public Deck() {
        this.cards = new Card[0];
    }

    public int size() {
        return cards.length;
    }
}
