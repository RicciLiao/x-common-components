package ricciliao.x.component.gzip;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtils {

    private GzipUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static String gzip(String primStr) throws IOException {
        if (StringUtils.isBlank(primStr)) {
            return primStr;
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
            gzip.write(primStr.getBytes());
        }

        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    public static String gunzip(String compressedStr) throws IOException {
        if (StringUtils.isBlank(compressedStr)) {
            return null;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream();
             ByteArrayInputStream in = new ByteArrayInputStream(Base64.getDecoder().decode(compressedStr));
             GZIPInputStream ginzip = new GZIPInputStream(in)) {
            byte[] buffer = new byte[1024];
            int offset = -1;
            while ((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }

            return out.toString();
        }
    }

}
