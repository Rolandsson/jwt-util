package nu.rolandsson.jwt.exception;

public class InvalidRefreshTokenException extends InvalidTokenRequestException {

  public InvalidRefreshTokenException(String string) {
    super(string);
  }

}
