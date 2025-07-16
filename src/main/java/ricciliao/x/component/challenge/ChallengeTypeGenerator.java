package ricciliao.x.component.challenge;

import org.apache.commons.lang3.tuple.Pair;
import ricciliao.x.component.random.RandomGenerator;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Random;

public abstract class ChallengeTypeGenerator {
    public static final int CANVAS_PADDING = 4;
    public static final int CANVAS_MAX_HEIGHT = 55;
    public static final float GRAIN_INTENSITY = 0.23f;

    private final String code;
    private final ChallengeBgStrategy bgStrategy;

    protected ChallengeTypeGenerator(int codeLength, ChallengeBgStrategy bgStrategy) {
        this.code = this.getCode(codeLength);
        this.bgStrategy = bgStrategy;
    }

    protected ChallengeTypeGenerator(int codeLength) {
        this.code = this.getCode(codeLength);
        this.bgStrategy = null;
    }

    protected abstract String getCode(int codeLength);

    protected abstract ChallengeTypeStrategy getType();

    public Challenge generate() throws IOException {
        if (Objects.nonNull(bgStrategy)) {

            return
                    new Challenge(
                            this.getType(),
                            this.code,
                            Base64.getEncoder().encodeToString(this.getImage())
                    );
        }

        return new Challenge(this.getType(), this.code);
    }

    private byte[] getImage() throws IOException {
        Pair<ChallengeCanvasSize, List<CharPattern>> codeSize = this.codePattern();
        BufferedImage image = bgStrategy.get(codeSize.getLeft());
        Graphics2D graphics2D = image.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int x = CANVAS_PADDING / 2;
        int y = image.getHeight() / 2;
        int test = 0;
        // captcha code
        for (CharPattern charPattern : codeSize.getRight()) {
            graphics2D.setColor(charPattern.getColor());
            graphics2D.setFont(charPattern.getFont());
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            graphics2D.translate(x + charPattern.width / 2.0, y);
            graphics2D.rotate(Math.toRadians(charPattern.rotate));
            graphics2D.drawString(
                    String.valueOf(charPattern.character),
                    -charPattern.width / 2,
                    (fontMetrics.getAscent() - charPattern.getHeight() / 2) + RandomGenerator.randomLength(-6, 6)
            );

            graphics2D.rotate(-Math.toRadians(charPattern.rotate));
            graphics2D.translate(-(x + charPattern.width / 2.0), -y);
            x += charPattern.width + charPattern.paddingNext;
            test++;
        }
        this.addInterference(image, graphics2D);
        graphics2D.dispose();

        byte[] imageBytes;
        try (ByteArrayOutputStream b = new ByteArrayOutputStream()) {
            ImageIO.setUseCache(false);
            ImageIO.write(image, "png", b);
            imageBytes = b.toByteArray();
        }

        return imageBytes;
    }

    private Pair<ChallengeCanvasSize, List<CharPattern>> codePattern() {
        List<CharPattern> charPatternList = new ArrayList<>();

        int width = 0;
        int height = 0;
        for (int i = 0; i < this.code.length(); i++) {
            BufferedImage image = new BufferedImage(1, 1, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            CharPattern charPattern = new CharPattern(this.code.charAt(i), graphics);
            graphics.dispose();
            charPatternList.add(charPattern);
            width += charPattern.width + charPattern.paddingNext;
            height = Math.max(height, charPattern.height);
        }
        width -= charPatternList.getLast().paddingNext;

        return Pair.of(new ChallengeCanvasSize(width, height), charPatternList);
    }

    private void addInterference(BufferedImage image, Graphics2D graphics2D) {
        this.addLines(image, graphics2D);
        this.addGrains(image);
        graphics2D.dispose();
    }

    private void addLines(BufferedImage image, Graphics2D graphics2D){
        for (int i = 0; i < 15; i++) {
            int x1 = RandomGenerator.randomLength(-CANVAS_PADDING, image.getWidth() + CANVAS_PADDING);
            int y1 = RandomGenerator.randomLength(-CANVAS_PADDING, image.getHeight() + CANVAS_PADDING);
            int x2 = RandomGenerator.randomLength(-CANVAS_PADDING, image.getWidth() + CANVAS_PADDING);
            int y2 = RandomGenerator.randomLength(-CANVAS_PADDING, image.getHeight() + CANVAS_PADDING);
            graphics2D.setColor(new Color(RandomGenerator.randomLength(0, 120), RandomGenerator.randomLength(0, 120), RandomGenerator.randomLength(0, 120)));
            graphics2D.drawLine(x1, y1, x2, y2);
        }
    }

    private void addGrains(BufferedImage image) {
        Random random = new SecureRandom();
        for (int y = 0; y < image.getHeight(); y++) {
            for (int x = 0; x < image.getWidth(); x++) {
                if (random.nextFloat() < GRAIN_INTENSITY) {
                    int alpha = RandomGenerator.randomLength(80, 120);
                    Color color = new Color(RandomGenerator.randomLength(0, 255), RandomGenerator.randomLength(0, 255), RandomGenerator.randomLength(0, 255), alpha);
                    image.setRGB(x, y, blendPixel(image.getRGB(x, y), color.getRGB()));
                }
            }
        }
    }

    private int blendPixel(int bgColor, int fgColor) {
        Color bg = new Color(bgColor, true);
        Color fg = new Color(fgColor, true);

        float alpha = fg.getAlpha() / 255f;

        int r = (int) (fg.getRed() * alpha + bg.getRed() * (1 - alpha));
        int g = (int) (fg.getGreen() * alpha + bg.getGreen() * (1 - alpha));
        int b = (int) (fg.getBlue() * alpha + bg.getBlue() * (1 - alpha));

        return new Color(r, g, b).getRGB();
    }

    static class CharPattern {
        private final char character;
        private final int width;
        private final int height;
        private final int paddingNext;
        private final double rotate;
        private final Color color;
        private final Font font;

        CharPattern(char character, Graphics2D graphics2D) {
            this.character = character;
            this.rotate = RandomGenerator.randomLength(-45, 45);
            this.color = new Color(RandomGenerator.randomLength(0, 120), RandomGenerator.randomLength(0, 120), RandomGenerator.randomLength(0, 120));
            this.font = new Font("Arial", Font.BOLD, RandomGenerator.randomLength(40, 45));
            this.paddingNext = /*RandomGenerator.randomLength(5, 10)*/10;
            graphics2D.setFont(this.font);
            FontMetrics fontMetrics = graphics2D.getFontMetrics();
            this.width = fontMetrics.charWidth(this.character);
            this.height = fontMetrics.getAscent() + fontMetrics.getDescent();
        }

        public char getCharacter() {
            return character;
        }

        public int getWidth() {
            return width;
        }

        public Color getColor() {
            return color;
        }

        public Font getFont() {
            return font;
        }

        public int getPaddingNext() {
            return paddingNext;
        }

        public int getHeight() {
            return height;
        }

        public double getRotate() {
            return rotate;
        }
    }

}
