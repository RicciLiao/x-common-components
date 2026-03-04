package ricciliao.x.component.security;

import ricciliao.x.component.security.crypto.aes.AbstractAESDecryptor;
import ricciliao.x.component.security.crypto.aes.AbstractAESEncryptor;
import ricciliao.x.component.security.crypto.rsa.AbstractRSADecryptor;
import ricciliao.x.component.security.crypto.rsa.AbstractRSAEncryptor;
import ricciliao.x.component.security.encode.AbstractEncoder;
import ricciliao.x.component.security.encode.argon2.Argon2Encoder;

import java.util.function.Function;

public interface SecurityProvider {

    Function<byte[], AbstractAESDecryptor> aesDecryptor();

    Function<byte[], AbstractAESEncryptor> aesEncryptor();

    Function<byte[], AbstractRSADecryptor> rsaDecryptor();

    Function<byte[], AbstractRSAEncryptor> rsaEncryptor();

    default Function<byte[], AbstractEncoder> argon2Encoder() {

        return Argon2Encoder::new;
    }

}
