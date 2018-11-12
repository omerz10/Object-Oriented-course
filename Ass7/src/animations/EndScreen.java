package animations;

import biuoop.DrawSurface;

import java.awt.Color;


/**
 * EndScreen.
 */
public class EndScreen implements Animation {
    private boolean stop;
    private Counter scores;

    /**
     * Construct EndScreen - player won.
     *
     * @param scores scores
     */
    public EndScreen(Counter scores) {
        this.stop = false;
        this.scores = scores;
    }

    /**
     * Creates one frame.
     *
     * @param d  a given drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.BLUE);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.YELLOW);
            d.drawText(200, d.getHeight() / 2, "You Win! Your Score is "
                    + Integer.toString(scores.getValue()), 32);
    }

    /**
     * Return true if the user pressed pause.
     *
     * @return true if the user pressed pause.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
