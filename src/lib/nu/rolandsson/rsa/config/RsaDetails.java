package nu.rolandsson.rsa.config

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import nu.rolandsson.rsa.type.RsaDecoder;

@Component
@ConfigurationProperties("rsa")
@Validated
@Getter
@Setter
public class RsaDetails {

  @NotNull
  @RsaDecoder
  private String privateKey;

  @NotNull
  @RsaDecoder
  private String publicKey;

}
