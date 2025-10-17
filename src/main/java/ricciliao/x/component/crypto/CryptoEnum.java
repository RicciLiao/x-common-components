package ricciliao.x.component.crypto;

import ricciliao.x.component.crypto.aes.AESDecryptor;
import ricciliao.x.component.crypto.aes.AESEncryptor;
import ricciliao.x.component.crypto.hash.HashDecryptor;
import ricciliao.x.component.crypto.hash.HashEncryptor;
import ricciliao.x.component.sneaky.SneakyThrowUtils;

import java.util.function.Function;

public enum CryptoEnum {
    HASH(
            bytes -> new HashDecryptor(bytes).decrypt(),
            bytes -> SneakyThrowUtils.get(() -> new HashEncryptor(bytes).encrypt())
    ),
    AES(
            bytes -> SneakyThrowUtils.get(() -> new AESEncryptor(bytes).encrypt()),
            bytes -> SneakyThrowUtils.get(() -> new AESDecryptor(bytes).decrypt())
    );

    private final Function<byte[], CryptoResult> decryptFunction;
    private final Function<byte[], CryptoResult> encryptFunction;

    CryptoEnum(Function<byte[], CryptoResult> decryptFunction,
               Function<byte[], CryptoResult> encryptFunction) {

        this.decryptFunction = decryptFunction;
        this.encryptFunction = encryptFunction;
    }

    public CryptoResult decrypt(byte[] bytes) {

        return decryptFunction.apply(bytes);
    }

    public CryptoResult encrypt(byte[] bytes) {

        return encryptFunction.apply(bytes);
    }

}
