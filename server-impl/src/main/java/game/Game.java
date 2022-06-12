package game;

public class Game {

    private final Table table;

    public Game(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("table required");
        }
        this.table = table;
    }

    public Table table() {
        return table;
    }
}
