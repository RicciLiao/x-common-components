package ricciliao.x.component.crypto.hash;

import ricciliao.x.component.crypto.AbstractCrypto;
import ricciliao.x.component.crypto.CryptoConstants;
import ricciliao.x.component.crypto.CryptoResult;

import java.util.Arrays;

public class HashDecryptor extends AbstractCrypto.AbstractDecryptor {

    public HashDecryptor(byte[] input) {
        super(input);
    }

    @Override
    public CryptoResult decrypt() {

        return CryptoResult.hash(this.getSalt(), Arrays.copyOfRange(input, CryptoConstants.SALT_BYTES_LENGTH, input.length));
    }

    @Override
    protected char[] getPassword() {

        return new char[0];
    }
}
