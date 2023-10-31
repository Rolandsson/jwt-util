package token;

import lombok.Getter;
import lombok.NoArgsConstructor;
import nu.rolandsson.jwt.model.TokenRequest;

@NoArgsConstructor
@Getter
public class AuthenticateTokenRequest extends TokenRequest {

  private String password;
  private String username;
}
