package lib.nu.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.NotNull;
import lib.nu.type.RsaDecoder;
import lombok.Getter;
import lombok.Setter;

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
