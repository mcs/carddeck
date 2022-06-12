package carddeck;

public record Card(Rank rank, Suit suit) {

    @Override
    public String toString() {
        return rank.getShortcut() + suit.getShortcut();
    }

}