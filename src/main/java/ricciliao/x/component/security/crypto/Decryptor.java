package ricciliao.x.component.security.crypto;

import ricciliao.x.component.exception.AbstractException;

public interface Decryptor {

    CryptoResult decrypt() throws AbstractException;

}
