package ricciliao.x.component.security.crypto.aes;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.security.crypto.CryptoResult;
import ricciliao.x.component.security.crypto.Decryptor;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public abstract class AbstractAESDecryptor extends AbstractAES implements Decryptor {

    private final byte[] iv;

    protected AbstractAESDecryptor(byte[] input) {
        super(input, Arrays.copyOfRange(input, 0, AbstractAES.SALT_BYTES_LENGTH));
        this.iv =
                Arrays.copyOfRange(
                        input,
                        AbstractAES.SALT_BYTES_LENGTH,
                        AbstractAES.SALT_BYTES_LENGTH + AbstractAES.IV_BYTES_LENGTH
                );
    }

    @Override
    public CryptoResult decrypt() throws AbstractException {
        try {
            Cipher cipher = Cipher.getInstance(AbstractAES.CIPHER_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, this.getKey(), this.generateGCMParameterSpec(iv));
            byte[] data =
                    cipher.doFinal(
                            Arrays.copyOfRange(
                                    input,
                                    AbstractAES.SALT_BYTES_LENGTH + AbstractAES.IV_BYTES_LENGTH, input.length
                            )
                    );

            return CryptoResult.aes(this.salt, this.iv, data);

        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

}
