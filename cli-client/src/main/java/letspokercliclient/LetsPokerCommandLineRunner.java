package letspokercliclient;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class LetsPokerCommandLineRunner implements CommandLineRunner {

    private final LetsPokerTerminal letsPokerTerminal;

    @Override
    public void run(String... args) {
        letsPokerTerminal.runInternal();
    }

}
