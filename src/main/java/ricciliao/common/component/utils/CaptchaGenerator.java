package ricciliao.common.component.utils;

import ricciliao.common.component.exception.CmnException;
import ricciliao.common.component.exception.ServiceException;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Random;

public class CaptchaGenerator {

    private static final int WIDTH = 135;
    private static final int HEIGHT = 35;

    public static CaptchaResult generateCaptchaImage(String captchaCode) throws CmnException {

        BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        // background color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, WIDTH, HEIGHT);
        // font style
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        // lines
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(WIDTH);
            int y1 = random.nextInt(HEIGHT);
            int x2 = random.nextInt(WIDTH);
            int y2 = random.nextInt(HEIGHT);
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.drawLine(x1, y1, x2, y2);
        }
        // captcha code
        for (int i = 0; i < captchaCode.length(); i++) {
            graphics.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
            graphics.drawString(String.valueOf(captchaCode.charAt(i)), 25 * i + 10, 25);
        }
        graphics.dispose();

        byte[] imageBytes;
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            ImageIO.setUseCache(false);
            ImageIO.write(image, "png", b);
            imageBytes = b.toByteArray();
        } catch (IOException e) {

            throw new ServiceException(e);
        }

        return new CaptchaResult(captchaCode, Base64.getEncoder().encodeToString(imageBytes));
    }

    public record CaptchaResult(String code, String captchaBase64) {
    }

}
