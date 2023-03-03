package game;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ServerGameTest {

    @Test
    void shouldRejectConstructorCallWithNull() {
        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> new ServerGame(null));
        assertEquals("table required", ex.getMessage());
    }

    @Test
    void shouldHaveTableConstructor() {
        Table table = new Table(2);
        ServerGame serverGame = new ServerGame(table);
        assertEquals(table, serverGame.table());
    }

}