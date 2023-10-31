package nu.rolandsson.rsa.facade;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import nu.rolandsson.rsa.config.RsaDetails;
import nu.rolandsson.rsa.facade.RsaDetailsFacade;

@Component
@Slf4j
public class JwtFacade {

  @Autowired
  private RsaDetails rsaDetails;

  public String createToken(String subject, Claims claims) {

    Key key = new RsaDetailsFacade.Factory(rsaDetails.getPrivateKey()).key("private");

    Date issuedAt = new Date();
    Date expiresAt = new Date(issuedAt.getTime() + (1000l * 60 * 30));
    String token = Jwts.builder()
        .setSubject(subject)
        .setClaims(claims)
        .setIssuedAt(issuedAt)
        .setIssuer("nu.rolandsson.noted.v1")
        .setExpiration(expiresAt)
        .signWith(SignatureAlgorithm.RS256, key)
        .compact();

    log.debug("Created - " + token);
    return verifyToken(token) ? token : "";
  }

  private boolean verifyToken(String token) {
    Key key = new RsaDetailsFacade.Factory(rsaDetails.getPublicKey()).key("public");
    return Jwts.parser().setSigningKey(key).isSigned(token);
  }

  public String refreshToken(String subject, String token) {
    if (!verifyToken(token)) {
      return "";
    }

    Key key = new RsaDetailsFacade.Factory(rsaDetails.getPublicKey()).key("public");

    JwtParser jwtParser = Jwts.parser().setSigningKey(key);

    Claims claims = jwtParser.parseClaimsJws(token).getBody();

    return createToken(subject, claims);
  }
}
