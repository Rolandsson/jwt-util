package nu.rolandsson.jwt.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public abstract class TokenRequest {

  private String token;
}
