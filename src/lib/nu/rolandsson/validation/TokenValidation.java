package lib.nu.validation;
import nu.rolandsson.model.TokenRequest;

@FunctionalInterface
public interface TokenValidation<T extends TokenRequest> {
  void validate(T type);
}
