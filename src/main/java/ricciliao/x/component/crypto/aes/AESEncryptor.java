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
import java.nio.ByteBuffer;
import java.security.GeneralSecurityException;

public class AESEncryptor extends AbstractCrypto.AbstractEncryptor {

    public AESEncryptor(byte[] input) {
        super(input);
    }

    @Override
    protected char[] getPassword() {

        return SpringBeanUtils.getBean(CommonProperties.class).getCryptoPassword().toCharArray();
    }

    @Override
    public CryptoResult encrypt() throws AbstractException {
        byte[] iv = CryptoUtils.randomBytes(new byte[CryptoConstants.IV_BYTES_LENGTH]);
        try {
            Cipher cipher = Cipher.getInstance(CryptoConstants.CIPHER_ALGO);
            cipher.init(
                    Cipher.ENCRYPT_MODE,
                    CryptoUtils.generateSecretKeySpec(this.generateSecretKey().getEncoded()),
                    CryptoUtils.generateGCMParameterSpec(iv)
            );
            byte[] bytes = cipher.doFinal(input);
            byte[] data = ByteBuffer.allocate(this.getSalt().length + iv.length + bytes.length)
                    .put(this.getSalt())
                    .put(iv)
                    .put(bytes)
                    .array();

            return CryptoResult.aes(this.getSalt(), iv, data);
        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

}
