package ricciliao.x.component.security.encode.argon2;

import org.bouncycastle.crypto.generators.Argon2BytesGenerator;
import org.bouncycastle.crypto.params.Argon2Parameters;
import ricciliao.x.component.random.RandomGenerator;
import ricciliao.x.component.security.encode.AbstractEncoder;

import java.util.Base64;

public class Argon2Encoder extends AbstractEncoder {

    public static final int ITERATIONS = 3;
    public static final int MEMORY_COST = 65536;
    public static final int LANES = 4;
    public static final int HASH_LENGTH = 32;
    public static final int SALT_BYTES_LENGTH = 16;

    public Argon2Encoder(byte[] input) {
        super(input);
    }

    private static boolean constantTimeArrayEquals(byte[] expected, byte[] actual) {
        if (expected.length != actual.length) {
            return false;
        }
        int result = 0;
        for (int i = 0; i < expected.length; i++) {
            result |= expected[i] ^ actual[i];
        }
        return result == 0;
    }

    @Override
    public String encode() {
        byte[] hash = new byte[Argon2Encoder.HASH_LENGTH];
        Argon2Parameters argon2Parameters =
                new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id)
                        .withVersion(Argon2Parameters.ARGON2_VERSION_13)
                        .withIterations(Argon2Encoder.ITERATIONS)
                        .withMemoryAsKB(Argon2Encoder.MEMORY_COST)
                        .withParallelism(Argon2Encoder.LANES)
                        .withSalt(RandomGenerator.nextBytes(new byte[Argon2Encoder.SALT_BYTES_LENGTH]))
                        .build();
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(argon2Parameters);
        generator.generateBytes(input, hash);

        return "$argon2id" +
                "$v=" +
                argon2Parameters.getVersion() +
                "$m=" +
                argon2Parameters.getMemory() +
                ",t=" +
                argon2Parameters.getIterations() +
                ",p=" +
                argon2Parameters.getLanes() +
                "$" + Base64.getEncoder().withoutPadding().encodeToString(argon2Parameters.getSalt()) +
                "$" + Base64.getEncoder().withoutPadding().encodeToString(hash);
    }

    @Override
    public boolean matches(String encoded) {
        String[] parts = encoded.split("\\$");
        if (parts.length < 4) {

            return false;
        }
        Argon2Parameters.Builder paramsBuilder = new Argon2Parameters.Builder(Argon2Parameters.ARGON2_id);
        int currentPart = 2;
        if (parts[currentPart].startsWith("v=")) {
            paramsBuilder.withVersion(Integer.parseInt(parts[currentPart].substring(2)));
            currentPart++;
        }
        String[] performanceParams = parts[currentPart++].split(",");
        if (performanceParams.length != 3) {

            return false;
        }
        if (!performanceParams[0].startsWith("m=")) {

            return false;
        }
        paramsBuilder.withMemoryAsKB(Integer.parseInt(performanceParams[0].substring(2)));
        if (!performanceParams[1].startsWith("t=")) {

            return false;
        }
        paramsBuilder.withIterations(Integer.parseInt(performanceParams[1].substring(2)));
        if (!performanceParams[2].startsWith("p=")) {

            return false;
        }
        paramsBuilder.withParallelism(Integer.parseInt(performanceParams[2].substring(2)));
        paramsBuilder.withSalt(Base64.getDecoder().decode(parts[currentPart++]));
        byte[] hash = Base64.getDecoder().decode(parts[currentPart]);
        byte[] inputHash = new byte[hash.length];
        Argon2BytesGenerator generator = new Argon2BytesGenerator();
        generator.init(paramsBuilder.build());
        generator.generateBytes(input, inputHash);

        return constantTimeArrayEquals(hash, inputHash);
    }

}
