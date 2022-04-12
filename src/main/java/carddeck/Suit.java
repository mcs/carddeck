package carddeck;

public enum Suit {
    CLUBS("c"),
    SPADES("s"),
    HEARTS("h"),
    DIAMONDS("d");

    private final String shortcut;

    Suit(String shortcut) {
        this.shortcut = shortcut;
    }

    public String getShortcut() {
        return shortcut;
    }
}
