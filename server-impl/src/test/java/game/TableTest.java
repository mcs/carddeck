package game;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class TableTest {

    @Test
    void shouldNotSupport1Seat() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Table(0));
        assertEquals("Table must have at least 2 seats", ex.getMessage());
    }

    @Test
    void shouldSupport2Seats() {
        Table result = new Table(2);
        assertEquals(2, result.allSeats().size());
    }

    @Test
    void shouldSupport9Seats() {
        Table result = new Table(9);
        assertEquals(9, result.allSeats().size());
    }

    @Test
    void shouldHaveZeroPlayersOnNewTable() {
        Table table = new Table(2);
        assertEquals(List.of(), table.activeSeats());
    }
}