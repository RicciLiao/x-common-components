package ricciliao.x.component.crypto;

public class CryptoConstants {
    public static final String SECRET_FACTORY_KEY_ALGO = "PBKDF2WithHmacSHA256";
    public static final String CIPHER_ALGO = "AES/GCM/NoPadding";
    public static final String SECRET_KEY_ALGO = "AES";
    public static final int SECRET_KEY_ITERATION_COUNT = 65536;
    public static final int SALT_BYTES_LENGTH = 16;
    public static final int IV_BYTES_LENGTH = 12;
    public static final int SECRET_KEY_BYTES_LENGTH = 256;
    public static final int TAG_BYTES_LENGTH = 16;

    private CryptoConstants() {
        throw new IllegalStateException("Utility class");
    }

}
