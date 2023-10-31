package nu.rolandsson.jwt.exception;

public class InvalidTokenRequestException extends RuntimeException {

  public InvalidTokenRequestException(String string) {
    super(string);
  }

}
