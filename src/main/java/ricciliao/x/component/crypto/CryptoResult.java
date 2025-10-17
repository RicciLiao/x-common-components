package ricciliao.x.component.crypto;

public class CryptoResult {

    private final byte[] salt;
    private final byte[] iv;
    private final byte[] data;

    private CryptoResult(byte[] salt, byte[] iv, byte[] data) {
        this.salt = salt;
        this.iv = iv;
        this.data = data;
    }

    public static CryptoResult aes(byte[] salt, byte[] iv, byte[] data) {

        return new CryptoResult(salt, iv, data);
    }

    public static CryptoResult hash(byte[] salt, byte[] data) {

        return new CryptoResult(salt, new byte[0], data);
    }

    public byte[] getSalt() {
        return salt;
    }

    public byte[] getData() {
        return data;
    }

    public byte[] getIv() {
        return iv;
    }

}
