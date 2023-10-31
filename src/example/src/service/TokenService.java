package service;

import org.springframework.stereotype.Service;

import example.src.token.AuthenticateTokenRequest;
import example.src.token.RefreshTokenRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import nu.rolandsson.jwt.facade.JwtFacade;
import nu.rolandsson.jwt.validation.TokenValidation;

@Service
public class TokenService {

  private JwtFacade jwtFacade;
  private TokenValidation<AuthenticateTokenRequest> authTokenValidation;
  private TokenValidation<RefreshTokenRequest> refreshTokenValidation;

  public TokenService(JwtFacade jwtFacade, TokenValidation<AuthenticateTokenRequest> authTokenValidation,
      TokenValidation<RefreshTokenRequest> refreshTokenValidation) {
    this.authTokenValidation = authTokenValidation;
    this.refreshTokenValidation = refreshTokenValidation;
    this.jwtFacade = jwtFacade;
  }

  public String createToken(AuthenticateTokenRequest request) {
    authTokenValidation.validate(request);

    Claims claims = Jwts.claims();
    claims.put("username", request.getUsername());

    String token = jwtFacade.createToken("authentication - username", claims);
    request.setToken(token);

    return token;
  }

  public String refreshToken(RefreshTokenRequest refreshTokenRequest) {
    refreshTokenValidation.validate(refreshTokenRequest);
    String token = refreshTokenRequest.getToken();
    token = jwtFacade.refreshToken("authentication - refresh", token);

    refreshTokenRequest.setToken(token);

    return token;
  }
}
