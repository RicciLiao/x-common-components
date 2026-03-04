package ricciliao.x.component.security.crypto.rsa;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;
import ricciliao.x.component.security.crypto.CryptoResult;
import ricciliao.x.component.security.crypto.Decryptor;

import javax.crypto.Cipher;
import java.security.GeneralSecurityException;

public abstract class AbstractRSADecryptor extends AbstractRSA implements Decryptor {

    protected AbstractRSADecryptor(byte[] input) {
        super(input);
    }

    @Override
    public CryptoResult decrypt() throws AbstractException {
        try {
            Cipher cipher = Cipher.getInstance(AbstractRSA.CIPHER_ALGO);
            cipher.init(Cipher.DECRYPT_MODE, this.getKey());

            return CryptoResult.rsa(cipher.doFinal(this.input));
        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }

    }

}
