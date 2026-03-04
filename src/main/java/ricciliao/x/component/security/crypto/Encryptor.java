package ricciliao.x.component.security.crypto;

import ricciliao.x.component.exception.AbstractException;

public interface Encryptor {

    CryptoResult encrypt() throws AbstractException;

}
