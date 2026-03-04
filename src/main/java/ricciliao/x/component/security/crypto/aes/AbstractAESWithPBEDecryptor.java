package ricciliao.x.component.security.crypto.aes;

import ricciliao.x.component.security.crypto.Decryptor;

public abstract class AbstractAESWithPBEDecryptor extends AbstractAESDecryptor implements Decryptor {

    protected AbstractAESWithPBEDecryptor(byte[] input) {
        super(input);
    }

}
