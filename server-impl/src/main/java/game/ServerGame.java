package game;

public class ServerGame {

    private final Table table;

    public ServerGame(Table table) {
        if (table == null) {
            throw new IllegalArgumentException("table required");
        }
        this.table = table;
    }

    public Table table() {
        return table;
    }
}
