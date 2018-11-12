package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * Animation.
 */
public class PauseScreen implements Animation {
    private KeyboardSensor keyboard;
    private boolean stop;

    /**
     * Construct PuseScreen with KeyboardSensor.
     *
     * @param k a KeyboardSensor
     */
    public PauseScreen(KeyboardSensor k) {
        this.keyboard = k;
        this.stop = false;
    }

    /**
     * Creates one frame.
     *
     * @param d a given drawSurface
     */
    public void doOneFrame(DrawSurface d) {
        d.drawText(160, d.getHeight() / 2, "Paused -- press space to continue", 32);
        if (this.keyboard.isPressed(KeyboardSensor.SPACE_KEY)) {
            this.stop = true;
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
