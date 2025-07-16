package ricciliao.x.component.challenge;

import ricciliao.x.component.challenge.background.Gradient;
import ricciliao.x.component.challenge.background.Transparent;

import java.awt.image.BufferedImage;
import java.util.function.Function;

public enum ChallengeBgStrategy {

    TRANSPARENT(imageSize -> new Transparent().generate(imageSize)),
    GRADIENT(imageSize -> new Gradient().generate(imageSize));

    private final Function<ChallengeCanvasSize, BufferedImage> background;

    ChallengeBgStrategy(Function<ChallengeCanvasSize, BufferedImage> background) {
        this.background = background;
    }

    public BufferedImage get(ChallengeCanvasSize size) {

        return background.apply(size);
    }
}
