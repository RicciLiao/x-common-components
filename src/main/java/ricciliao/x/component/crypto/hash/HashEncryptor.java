package ricciliao.x.component.crypto.hash;

import ricciliao.x.component.crypto.AbstractCrypto;
import ricciliao.x.component.crypto.CryptoResult;
import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.payload.response.code.impl.SecondaryCodeEnum;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.security.GeneralSecurityException;

public class HashEncryptor extends AbstractCrypto.AbstractEncryptor {

    public HashEncryptor(byte[] input) {
        super(input);
    }

    @Override
    protected char[] getPassword() {
        Charset charset = StandardCharsets.UTF_8;
        ByteBuffer byteBuffer = ByteBuffer.wrap(input);
        CharBuffer charBuffer = charset.decode(byteBuffer);
        char[] chars = new char[charBuffer.remaining()];
        charBuffer.get(chars);

        return chars;
    }

    @Override
    public CryptoResult encrypt() throws AbstractException {
        try {
            byte[] bytes = this.generateSecretKey().getEncoded();
            byte[] data = ByteBuffer.allocate(this.getSalt().length + bytes.length)
                    .put(this.getSalt())
                    .put(bytes)
                    .array();

            return CryptoResult.hash(this.getSalt(), data);
        } catch (GeneralSecurityException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

}
