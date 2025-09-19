package ricciliao.x.component.crypto;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.GCMParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class XCipher {

    private XCipher(int optMode, byte[] iv) throws AbstractException {
        this.optMode = optMode;
        this.iv = iv;
        this.cipher = buildCipher();
    }

    private final int optMode;
    private final byte[] iv;
    private static String secretKey;
    private static String salt;
    public final Cipher cipher;
    public static final int GCM_IV_LENGTH = 12;
    public static final int GCM_TAG_LENGTH = 16;

    public static void setSecretKey(String secretKey) {
        XCipher.secretKey = secretKey;
    }

    public static void setSalt(String salt) {
        XCipher.salt = salt;
    }

    private Cipher buildCipher() throws AbstractException {
        KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
        try {
            Cipher result = Cipher.getInstance("AES/GCM/NoPadding");
            result.init(
                    optMode,
                    new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(spec).getEncoded(), "AES"),
                    new GCMParameterSpec(GCM_TAG_LENGTH * Byte.SIZE, iv)
            );

            return result;
        } catch (NoSuchAlgorithmException
                 | InvalidKeyException
                 | InvalidAlgorithmParameterException
                 | NoSuchPaddingException
                 | InvalidKeySpecException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

    static class XCipherBuilder {

        private int optMode;
        private byte[] iv;

        public XCipherBuilder iv(byte[] iv) {
            this.iv = iv;

            return this;
        }

        public XCipherBuilder optMode(int optMode) {
            this.optMode = optMode;

            return this;
        }

        public static XCipherBuilder builder() {

            return new XCipherBuilder();

        }

        public Cipher build() throws AbstractException {

            return new XCipher(optMode, iv).cipher;
        }
    }

}
