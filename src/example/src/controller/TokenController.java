package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import example.src.service.TokenService;
import example.src.token.AuthenticateTokenRequest;
import example.src.token.RefreshTokenRequest;

@RestController
@RequestMapping("/token/*")
public class TokenController {

  @Autowired
  private TokenService tokenService;

  @PostMapping(path = "create", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> getToken(@RequestBody AuthenticateTokenRequest tokenRequest) {
    return ResponseEntity.ok(tokenService.createToken(tokenRequest));
  }

  @PutMapping(path = "refresh", consumes = "application/json", produces = "application/json")
  public ResponseEntity<String> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
    return ResponseEntity.ok(tokenService.refreshToken(tokenRequest));
  }
}
