package ricciliao.x.component.challenge.background;

import ricciliao.x.component.challenge.ChallengeBgGenerator;
import ricciliao.x.component.challenge.ChallengeCanvasSize;
import ricciliao.x.component.challenge.ChallengeTypeGenerator;
import ricciliao.x.component.random.RandomGenerator;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class Gradient extends ChallengeBgGenerator {

    @Override
    public BufferedImage generate(ChallengeCanvasSize imageSize) {
        BufferedImage image =
                new BufferedImage(
                        imageSize.getImageWidth() + ChallengeTypeGenerator.CANVAS_PADDING,
                        Math.max(imageSize.getImageHeight() + ChallengeTypeGenerator.CANVAS_PADDING, ChallengeTypeGenerator.CANVAS_MAX_HEIGHT),
                        BufferedImage.TYPE_INT_RGB
                );
        Graphics2D graphics = image.createGraphics();
        GradientPaint gradient = new GradientPaint(
                0, 0, new Color(RandomGenerator.randomLength(50, 140), RandomGenerator.randomLength(50, 140), RandomGenerator.randomLength(50, 140)),
                image.getWidth(), image.getHeight(), new Color(RandomGenerator.randomLength(140, 210), RandomGenerator.randomLength(140, 210), RandomGenerator.randomLength(140, 210))
        );
        graphics.setPaint(gradient);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.dispose();

        return image;
    }

}
