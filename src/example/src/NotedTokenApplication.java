import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import nu.rolandsson.rsa.config.RsaDetails;
import nu.rolandsson.rsa.facade.RsaDetailsFacade;

@SpringBootApplication
public class NotedTokenApplication {

  public static void main(String[] args) {
    SpringApplication.run(NotedTokenApplication.class, args);
  }

  @Bean
  ApplicationRunner applicationRunner(RsaDetails rsaDetails) {
    return args -> RsaDetailsFacade.decodeRsa(rsaDetails);
  }
}
