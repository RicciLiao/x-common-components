package ricciliao.x.component.security.crypto.aes;

import ricciliao.x.component.security.crypto.Encryptor;

public abstract class AbstractAESWithPBEEncryptor extends AbstractAESEncryptor implements Encryptor {

    protected AbstractAESWithPBEEncryptor(byte[] input) {
        super(input);
    }

}
