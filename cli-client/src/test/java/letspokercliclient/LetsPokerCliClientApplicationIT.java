package letspokercliclient;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class LetsPokerCliClientApplicationIT {

    @MockBean
    private LetsPokerCommandLineRunner letsPokerCommandLineRunner;

    @Test
    void contextLoads() {
        verify(letsPokerCommandLineRunner).run(any());
    }

}