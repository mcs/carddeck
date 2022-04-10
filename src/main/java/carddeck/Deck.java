package carddeck;

public class Deck {

    private final int cards;

    public Deck(int cards) {
        if (cards < 0) throw new IllegalArgumentException("A deck must not be less than empty");
        this.cards = cards;
    }

    public int size() {
        return cards;
    }
}
