package nu.rolandsson.jwt.facade;

import java.security.Key;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import nu.rolandsson.rsa.config.RsaDetails;
import nu.rolandsson.rsa.factory.RsaProviderFactory;

@Component
@Slf4j
public class JwtFacade {

  @Autowired
  private RsaDetails rsaDetails;

  @Autowired
  private RsaProviderFactory rsaFactory;

  public String createToken(String subject, Claims claims) {
    Optional<Key> key = rsaFactory.createPrivateKey(rsaDetails.getPrivateKey());

    Date issuedAt = new Date();
    Date expiresAt = new Date(issuedAt.getTime() + (1000l * 60 * 30));
    String token = Jwts.builder()
        .setSubject(subject)
        .setClaims(claims)
        .setIssuedAt(issuedAt)
        .setExpiration(expiresAt)
        .signWith(SignatureAlgorithm.RS256, key.get())
        .compact();

    log.debug("Created - " + token);
    return verifyToken(token) ? token : "";
  }

  private boolean verifyToken(String token) {
    Optional<Key> key = rsaFactory.createPublicKey(rsaDetails.getPublicKey());

    return Jwts.parser().setSigningKey(key.get()).isSigned(token);
  }

  public String refreshToken(String subject, String token) {
    if (!verifyToken(token)) {
      return "";
    }
    Optional<Key> key = rsaFactory.createPublicKey(rsaDetails.getPublicKey());

    JwtParser jwtParser = Jwts.parser().setSigningKey(key.get());

    Claims claims = jwtParser.parseClaimsJws(token).getBody();

    return createToken(subject, claims);
  }

}
