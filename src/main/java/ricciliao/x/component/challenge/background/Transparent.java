package ricciliao.x.component.challenge.background;

import ricciliao.x.component.challenge.ChallengeBgGenerator;
import ricciliao.x.component.challenge.ChallengeCanvasSize;
import ricciliao.x.component.challenge.ChallengeTypeGenerator;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public final class Transparent extends ChallengeBgGenerator {

    @Override
    public BufferedImage generate(ChallengeCanvasSize imageSize) {
        BufferedImage image =
                new BufferedImage(
                        imageSize.getImageWidth() + ChallengeTypeGenerator.CANVAS_PADDING,
                        Math.max(imageSize.getImageHeight() + ChallengeTypeGenerator.CANVAS_PADDING, ChallengeTypeGenerator.CANVAS_MAX_HEIGHT),
                        BufferedImage.TYPE_4BYTE_ABGR
                );
        Graphics2D graphics = image.createGraphics();
        graphics.setComposite(AlphaComposite.Clear);
        graphics.fillRect(0, 0, image.getWidth(), image.getHeight());
        graphics.setComposite(AlphaComposite.SrcOver);
        graphics.dispose();

        return image;
    }

}
