package ricciliao.x.component.crypto.aes;

import ricciliao.x.component.crypto.AbstractCrypto;
import ricciliao.x.component.crypto.CryptoConstants;
import ricciliao.x.component.crypto.CryptoResult;
import ricciliao.x.component.crypto.CryptoUtils;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.props.CommonProperties;
import ricciliao.x.component.utils.SpringBeanUtils;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;
import java.util.Arrays;

public class AESDecryptor extends AbstractCrypto.AbstractDecryptor {

    private final byte[] iv;

    public AESDecryptor(byte[] input) {
        super(input);
        this.iv = Arrays.copyOfRange(input, CryptoConstants.SALT_BYTES_LENGTH, CryptoConstants.SALT_BYTES_LENGTH + CryptoConstants.IV_BYTES_LENGTH);
    }

    @Override
    protected char[] getPassword() {

        return SpringBeanUtils.getBean(CommonProperties.class).getCryptoPassword().toCharArray();
    }

    @Override
    public CryptoResult decrypt() throws AbstractException {
        try {
            Cipher cipher = Cipher.getInstance(CryptoConstants.CIPHER_ALGO);
            cipher.init(
                    Cipher.DECRYPT_MODE,
                    CryptoUtils.generateSecretKeySpec(this.generateSecretKey().getEncoded()),
                    CryptoUtils.generateGCMParameterSpec(iv)
            );
            byte[] data = cipher.doFinal(Arrays.copyOfRange(input, CryptoConstants.SALT_BYTES_LENGTH + CryptoConstants.IV_BYTES_LENGTH, input.length));

            return CryptoResult.aes(this.getSalt(), this.iv, data);

        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }


}
