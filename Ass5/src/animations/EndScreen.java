package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;


/**
 * EndScreen.
 */
public class EndScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean win;
    private boolean stop;
    private Counter scores;

    /**
     * Construct EndScreen.
     *
     * @param k      KeyboardSensor
     * @param scores scores
     * @param win    boolean
     */
    public EndScreen(KeyboardSensor k, Counter scores, boolean win) {
        this.keyboard = k;
        this.win = win;
        this.stop = false;
        this.scores = scores;
    }

    /**
     * Creates one frame.
     *
     * @param d a given drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        // player won
        if (this.win) {
            d.drawText(200, d.getHeight() / 2, "You Win! Your Score is "
                    + Integer.toString(scores.getValue()), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
            // game over - player lose
        } else {
            d.drawText(200, d.getHeight() / 2, "Game Over. Your Score is "
                    + Integer.toString(scores.getValue()), 32);
            if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
                this.stop = true;
            }
        }
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
