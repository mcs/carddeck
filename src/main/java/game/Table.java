package game;

import java.util.Collection;
import java.util.List;
import java.util.stream.IntStream;

public class Table {

    private final int maxSeats;

    public Table(int maxSeats) {
        if (maxSeats < 2) {
            throw new IllegalArgumentException("Table must have at least 2 seats");
        }
        this.maxSeats = maxSeats;
    }

    public Collection<Object> activeSeats() {
        return List.of();
    }

    public Collection<Integer> allSeats() {
        return IntStream.range(0, maxSeats)
                .boxed()
                .toList();
    }
}
