package nu.rolandsson.jwt.exception;

/**
 * Base class for invalid token requests exceptions
 */
public abstract class InvalidTokenRequestException extends RuntimeException {

  public InvalidTokenRequestException(String string) {
    super(string);
  }

}
