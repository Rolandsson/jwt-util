package nu.rolandsson.rsa.type;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annontation to mark fields to be injected with RSA key values 
 * Used for both private and public key fields
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface RsaDecoder {}
