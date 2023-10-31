package lib.nu.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import nu.rolandsson.type.RsaDecoder;

public class RsaDetailsFacade {
  public static void decodeRsa(Object object) throws IllegalArgumentException, IllegalAccessException {
    for (Field field : object.getClass().getDeclaredFields()) {
      field.setAccessible(true);
      if (field.isAnnotationPresent(RsaDecoder.class)) {
        Resource keyResource = new ClassPathResource((String) field.get(object));
        field.set(object, decodeResource(keyResource));
      }
    }
  }

  private static String decodeResource(Resource resource) {
    try (InputStream stream = resource.getInputStream()) {
      StringBuilder content = new StringBuilder();
      String line = "";
      BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
      while ((line = reader.readLine()) != null) {
        if (!(line.contains("BEGIN") || line.contains("END"))) {
          content.append(line).append('\n');
        }
      }
      return content.toString();
    } catch (IOException ex) {
      ex.printStackTrace();
    }
    return "";
  }

  public static class Factory {
    private String sequence;
    private String algorithm;

    public Factory(String sequence) {
      this.sequence = sequence;
      this.algorithm = "RSA";
    }

    public Key key(String keyType) {
      Key key = null;
      try {
        KeyFactory kf = KeyFactory.getInstance(this.algorithm);
        byte[] decodedKey = Base64.decodeBase64(this.sequence);

        if (keyType.equals("private")) {
          key = kf.generatePrivate(new PKCS8EncodedKeySpec(decodedKey));
        } else {
          key = kf.generatePublic(new X509EncodedKeySpec(decodedKey));
        }
      } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
        ex.printStackTrace();
      }

      return key;
    }
  }
}
