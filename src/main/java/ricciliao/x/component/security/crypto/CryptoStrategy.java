package ricciliao.x.component.security.crypto;

import ricciliao.x.component.exception.AbstractException;
import ricciliao.x.component.security.SecurityProvider;
import ricciliao.x.component.security.SecurityStrategy;

import java.util.function.BiFunction;

public enum CryptoStrategy implements SecurityStrategy {

    RSA(
            (securityProvider, bytes) -> securityProvider.rsaDecryptor().apply(bytes),
            (securityProvider, bytes) -> securityProvider.rsaEncryptor().apply(bytes)
    ),
    AES(
            (securityProvider, bytes) -> securityProvider.aesDecryptor().apply(bytes),
            (securityProvider, bytes) -> securityProvider.aesEncryptor().apply(bytes)
    ),
    ;

    private final BiFunction<SecurityProvider, byte[], Decryptor> decryptor;
    private final BiFunction<SecurityProvider, byte[], Encryptor> encryptor;

    CryptoStrategy(BiFunction<SecurityProvider, byte[], Decryptor> decryptor,
                   BiFunction<SecurityProvider, byte[], Encryptor> encryptor) {

        this.decryptor = decryptor;
        this.encryptor = encryptor;
    }

    public CryptoResult decrypt(byte[] input) throws AbstractException {

        return decryptor.apply(this.getSecurityProvider(), input).decrypt();
    }

    public CryptoResult encrypt(byte[] input) throws AbstractException {

        return encryptor.apply(this.getSecurityProvider(), input).encrypt();
    }

}
