package animations;

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Animation.
 */
public class PauseScreen implements Animation {
    private boolean stop;

    /**
     * Construct PauseScreen with KeyboardSensor.
     *
     */
    public PauseScreen() {
        this.stop = false;
    }

    /**
     * Creates one frame.
     *
     * @param d a given drawSurface
     *
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(Color.YELLOW);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.BLUE);
        d.drawText(160, d.getHeight() / 2, "Paused -- press space to continue", 32);
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
