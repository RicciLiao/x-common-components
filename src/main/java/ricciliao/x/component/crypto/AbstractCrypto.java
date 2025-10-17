package ricciliao.x.component.crypto;

import ricciliao.x.component.exception.AbstractException;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public abstract class AbstractCrypto {

    protected final byte[] input;

    protected AbstractCrypto(byte[] input) {
        this.input = input;
    }

    protected final SecretKey generateSecretKey() throws GeneralSecurityException {

        return SecretKeyFactory
                .getInstance(CryptoConstants.SECRET_FACTORY_KEY_ALGO)
                .generateSecret(
                        new PBEKeySpec(
                                this.getPassword(),
                                this.getSalt(),
                                CryptoConstants.SECRET_KEY_ITERATION_COUNT,
                                CryptoConstants.SECRET_KEY_BYTES_LENGTH
                        )
                );
    }

    protected abstract char[] getPassword();

    protected abstract byte[] getSalt();

    public abstract static class AbstractDecryptor extends AbstractCrypto {

        protected AbstractDecryptor(byte[] input) {
            super(input);
        }

        public abstract CryptoResult decrypt() throws AbstractException;

        @Override
        protected byte[] getSalt() {

            return Arrays.copyOfRange(input, 0, CryptoConstants.SALT_BYTES_LENGTH);
        }
    }

    public abstract static class AbstractEncryptor extends AbstractCrypto {

        private final byte[] salt;

        protected AbstractEncryptor(byte[] input) {
            super(input);
            this.salt = CryptoUtils.randomBytes(new byte[CryptoConstants.SALT_BYTES_LENGTH]);
        }

        public abstract CryptoResult encrypt() throws AbstractException;

        @Override
        protected byte[] getSalt() {

            return salt;
        }

    }

}
