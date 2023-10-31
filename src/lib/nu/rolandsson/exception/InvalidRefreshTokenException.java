package lib.nu.exception;

public class InvalidRefreshTokenException extends InvalidTokenRequestException {

  public InvalidRefreshTokenException(String string) {
    super(string);
  }

}
