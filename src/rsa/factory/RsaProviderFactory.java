package nu.rolandsson.rsa.factory;

import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Optional;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Component;

@Component
public class RsaProviderFactory {

    public Optional<Key> createPrivateKey(String keyValue) {
        return createKey(keyValue, "RSA", "private");
    }

    public Optional<Key> createPublicKey(String keyValue) {
        return createKey(keyValue, "RSA", "public");
    }


    private Optional<Key> createKey(String sequence, String algorithm, String keyType) {
      try {
        KeyFactory kf = KeyFactory.getInstance(algorithm);
        byte[] decodedKey = Base64.decodeBase64(sequence);

        Key key = switch(keyType) {
          case "private" -> kf.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
          case "public" -> kf.generatePublic(new X509EncodedKeySpec(decodedKey));
          default -> throw new IllegalArgumentException("Expected public private key modifier");
        };

        return Optional.of(key);

      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        ex.printStackTrace();
      }

      return Optional.empty();
    }
}
