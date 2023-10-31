package nu.rolandsson.jwt.exception;

/**
 * Cast when refresh requests fails due to missing or invalid parameters
 * E.g. when username not properly provided or if token field is null
 */
public class InvalidRefreshTokenException extends InvalidTokenRequestException {

  public InvalidRefreshTokenException(String string) {
    super(string);
  }

}
