package learning;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class RecordLearningTest {

    private record MyRecord(String str) {

    }

    @Test
    void equals() {
        MyRecord r1a = new MyRecord("1");
        MyRecord r1b = new MyRecord("1");

        assertEquals(r1a, r1b);
        assertNotSame(r1a, r1b);
    }
}
