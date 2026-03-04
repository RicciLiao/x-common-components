package ricciliao.x.component.security.crypto.aes;

import ricciliao.x.component.sneaky.SneakyThrowUtils;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public abstract class AbstractAESWithPBE extends AbstractAES {

    protected AbstractAESWithPBE(byte[] input, byte[] salt) {
        super(input, salt);
    }

    protected abstract char[] getPassword();

    @Override
    protected SecretKey getKey() {

        return SneakyThrowUtils.get(
                () ->
                        new SecretKeySpec(
                                SecretKeyFactory
                                        .getInstance(AbstractAES.SECRET_FACTORY_KEY_ALGO)
                                        .generateSecret(
                                                new PBEKeySpec(
                                                        this.getPassword(),
                                                        this.salt,
                                                        AbstractAES.SECRET_KEY_ITERATION_COUNT,
                                                        AbstractAES.SECRET_KEY_BYTES_LENGTH
                                                )
                                        )
                                        .getEncoded(),
                                AbstractAES.SECRET_KEY_ALGO
                        )
        );
    }
}
