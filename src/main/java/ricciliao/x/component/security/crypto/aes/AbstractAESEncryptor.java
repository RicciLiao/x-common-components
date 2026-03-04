package ricciliao.x.component.security.crypto.aes;

import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.random.RandomGenerator;
import ricciliao.x.component.security.crypto.CryptoResult;
import ricciliao.x.component.security.crypto.Encryptor;

import javax.crypto.Cipher;
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public abstract class AbstractAESEncryptor extends AbstractAES implements Encryptor {

    protected AbstractAESEncryptor(byte[] input) {
        super(input, RandomGenerator.nextBytes(new byte[AbstractAES.SALT_BYTES_LENGTH]));
    }

    @Override
    public CryptoResult encrypt() throws UnexpectedException {
        byte[] iv = RandomGenerator.nextBytes(new byte[AbstractAES.IV_BYTES_LENGTH]);
        try {
            Cipher cipher = Cipher.getInstance(AbstractAES.CIPHER_ALGO);
            cipher.init(Cipher.ENCRYPT_MODE, this.getKey(), this.generateGCMParameterSpec(iv));
            byte[] bytes = cipher.doFinal(input);
            byte[] data = ByteBuffer.allocate(this.salt.length + iv.length + bytes.length)
                    .put(this.salt)
                    .put(iv)
                    .put(bytes)
                    .array();

            return CryptoResult.aes(this.salt, iv, data);
        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

}
