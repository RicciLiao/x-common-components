package ricciliao.x.component.crypto;

import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.SecureRandom;

public class CryptoUtils {
    private CryptoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static byte[] randomBytes(byte[] bytes) {
        new SecureRandom().nextBytes(bytes);

        return bytes;
    }

    public static SecretKeySpec generateSecretKeySpec(byte[] salt) {

        return new SecretKeySpec(salt, CryptoConstants.SECRET_KEY_ALGO);
    }

    public static GCMParameterSpec generateGCMParameterSpec(byte[] iv) {

        return new GCMParameterSpec(CryptoConstants.TAG_BYTES_LENGTH * Byte.SIZE, iv);
    }

}
