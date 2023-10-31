package validation;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import example.src.token.AuthenticateTokenRequest;
import example.src.token.RefreshTokenRequest;
import io.jsonwebtoken.lang.Assert;
import lib.nu.rolandsson.exception.InvalidAuthTokenRequestException;
import lib.nu.rolandsson.exception.InvalidRefreshTokenException;
import lib.nu.rolandsson.validation.TokenValidation;

@Configuration
public class TokenRequestValidation {

  @Bean
  public TokenValidation<AuthenticateTokenRequest> authTokenValidation() {
    return request -> {
      try {
        Assert.notNull(request.getUsername());
        Assert.notNull(request.getPassword());
      } catch (IllegalArgumentException ex) {
        throw new InvalidAuthTokenRequestException("Username and password must not be null");
      }

      try {
        Assert.isNull(request.getToken());
      } catch (IllegalArgumentException ex) {
        throw new InvalidAuthTokenRequestException("Token should be null");
      }
    };
  }

  @Bean
  public TokenValidation<RefreshTokenRequest> refreshTokenValidation() {
    return request -> {
      try {
        Assert.notNull(request.getToken());
      } catch (IllegalArgumentException ex) {
        throw new InvalidRefreshTokenException("Username should not be null");
      }
    };
  }
}
