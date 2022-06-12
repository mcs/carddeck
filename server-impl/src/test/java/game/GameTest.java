package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class GameTest {

    @Test
    void shouldRejectConstructorCallWithNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new Game(null));
        assertEquals("table required", ex.getMessage());
    }

    @Test
    void shouldHaveTableConstructor() {
        Table table = new Table(2);
        Game game = new Game(table);
        assertEquals(table, game.table());
    }

    @Test
    void shouldHaveEmptySeats() {

    }

}