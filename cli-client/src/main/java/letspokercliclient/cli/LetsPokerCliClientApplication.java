package letspokercliclient.cli;

import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class LetsPokerCliClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(LetsPokerCliClientApplication.class, args);
    }

    @Bean
    @SuppressWarnings("java:S1452")
    public TextTerminal<?> textTerminal() {
        return TextIoFactory.getTextTerminal();
    }
}
