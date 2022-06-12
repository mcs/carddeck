package letspokercliclient;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.SpringApplication;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mockStatic;

@ExtendWith(MockitoExtension.class)
class LetsPokerCliClientApplicationTest {

    @Test
    void testMainCallsRun() {
        try (MockedStatic<SpringApplication> app = mockStatic(SpringApplication.class)) {
            LetsPokerCliClientApplication.main(null);
            app.verify(() -> SpringApplication.run(eq(LetsPokerCliClientApplication.class), any()));
        }

    }
}