package nu.rolandsson.rsa.facade;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import nu.rolandsson.rsa.type.RsaDecoder;

/**
 * Currently only tested for RSA but could potentionally represent a veraity of keys
 * Used in conjunction with RsaDecoder annontation to provide key injected values
 */
public class RsaDetailsFacade {
  /**
   * Read annotation field and injects the value from resourece file
   * @param object to hold injected values
   * @throws IllegalAccessException should not happen, check accessibility of field .e.g final/static modifiers
   */
  public static void decodeRsa(Object object) throws IllegalAccessException {
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

}
