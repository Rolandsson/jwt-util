package nu.rolandsson.jwt.exception;

/**
 * Cast when authentication fails due to missing or invalid parameters
 * E.g. when username or password is not properly provided or if token field is null
 */
public class InvalidAuthTokenRequestException extends InvalidTokenRequestException {

  public InvalidAuthTokenRequestException(String string) {
    super(string);
  }

}
