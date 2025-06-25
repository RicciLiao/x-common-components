package ricciliao.x.component.challenge;

import ricciliao.x.component.exception.CmnException;
import ricciliao.x.component.exception.CmnServiceException;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Random;

public abstract class ChallengeGenerator {

    protected final int imageWidth;
    protected final int imageHeight;
    protected final int codeLength;

    protected ChallengeGenerator(int imageWidth, int imageHeight, int codeLength) {
        this.imageWidth = imageWidth;
        this.imageHeight = imageHeight;
        this.codeLength = codeLength;
    }

    abstract String generate();

    protected byte[] generate(String code) throws CmnException {

        BufferedImage image = new BufferedImage(this.imageWidth, this.imageHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = image.createGraphics();
        // background color
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, this.imageWidth, this.imageHeight);
        // font style
        graphics.setFont(new Font("Arial", Font.BOLD, 25));
        // lines
        Random random = new SecureRandom();
        for (int i = 0; i < 10; i++) {
            int x1 = random.nextInt(this.imageWidth);
            int y1 = random.nextInt(this.imageHeight);
            int x2 = random.nextInt(this.imageWidth);
            int y2 = random.nextInt(this.imageHeight);
            graphics.setColor(new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255)));
            graphics.drawLine(x1, y1, x2, y2);
        }
        // captcha code
        for (int i = 0; i < code.length(); i++) {
            graphics.setColor(new Color(random.nextInt(100), random.nextInt(100), random.nextInt(100)));
            graphics.drawString(String.valueOf(code.charAt(i)), 25 * i + 10, 25);
        }
        graphics.dispose();

        byte[] imageBytes;
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            ImageIO.setUseCache(false);
            ImageIO.write(image, "png", b);
            imageBytes = b.toByteArray();
        } catch (IOException e) {

            throw new CmnServiceException(e);
        }

        return imageBytes;
    }

}
