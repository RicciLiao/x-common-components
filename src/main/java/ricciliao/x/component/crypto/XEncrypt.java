package ricciliao.x.component.crypto;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.ByteBuffer;
import java.security.SecureRandom;
import java.util.concurrent.Callable;

public class XEncrypt implements Callable<byte[]> {

    public XEncrypt(byte[] data) {
        this.data = data;
    }

    private final byte[] data;

    @Override
    public byte[] call() throws AbstractException {
        byte[] iv = new byte[XCipher.GCM_IV_LENGTH];
        (new SecureRandom()).nextBytes(iv);
        Cipher cipher = XCipher.XCipherBuilder.builder().optMode(Cipher.ENCRYPT_MODE).iv(iv).build();
        byte[] encryptedData;
        try {
            encryptedData = cipher.doFinal(data);
        } catch (IllegalBlockSizeException | BadPaddingException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
        ByteBuffer byteBuffer = ByteBuffer.allocate(Integer.BYTES + iv.length + encryptedData.length);
        byteBuffer.putInt(iv.length);
        byteBuffer.put(iv);
        byteBuffer.put(encryptedData);

        return byteBuffer.array();
    }

}
