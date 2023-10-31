package nu.rolandsson.jwt.validation;
import nu.rolandsson.jwt.model.TokenRequest;

@FunctionalInterface
public interface TokenValidation<T extends TokenRequest> {
  void validate(T type);
}
