package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * GameOver.
 */
public class GameOver implements Animation {
    private Counter score;

    /**
     * Construct GameOver animation with kebourd sensor, gui and table of scores.
     *
     * @param scores the scores
     */
    public GameOver(Counter scores) {
        this.score = scores;
    }

    /**
     * Creates one frame.
     *
     * @param d drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawText(200, d.getHeight() / 2, "Game Over. Your Score is "
                + Integer.toString(this.score.getValue()), 32);
    }

    /**
     * Returns boolean.
     *
     * @return boolean
     */
    public boolean shouldStop() {
        return false;
    }
}
