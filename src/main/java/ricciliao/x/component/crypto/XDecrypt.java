package ricciliao.x.component.crypto;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.exception.UnexpectedException;
import ricciliao.x.component.response.code.impl.SecondaryCodeEnum;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import java.nio.ByteBuffer;
import java.util.concurrent.Callable;

public class XDecrypt implements Callable<byte[]> {

    public XDecrypt(byte[] data) {
        this.data = data;
    }

    private final byte[] data;

    @Override
    public byte[] call() throws AbstractException {
        ByteBuffer byteBuffer = ByteBuffer.wrap(data);
        int position = byteBuffer.getInt();
        //Make sure that the file was encrypted properly
        if (position < XCipher.GCM_IV_LENGTH || position >= XCipher.GCM_IV_LENGTH + Integer.BYTES) {
            throw new IllegalArgumentException("Nonce size is incorrect. Make sure that the incoming data is an AES encrypted file.");
        }
        byte[] iv = new byte[position];
        byteBuffer.get(iv);
        byte[] encrypted = new byte[byteBuffer.remaining()];
        byteBuffer.get(encrypted);
        try {
            Cipher cipher = XCipher.XCipherBuilder.builder()
                    .optMode(Cipher.DECRYPT_MODE)
                    .iv(iv)
                    .build();

            return cipher.doFinal(encrypted);
        } catch (BadPaddingException | IllegalBlockSizeException e) {

            throw new UnexpectedException(SecondaryCodeEnum.BLANK, e);
        }
    }

}
