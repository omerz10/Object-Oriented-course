package animations;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

/**
 * KeyPressStoppableAnimation.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor ks;
    private String key;
    private Animation animation;
    private boolean stop;
    private boolean isAlreadyPressed;

    /**
     * Construct KeyPressStoppableAnimation with ks, key and animation.
     *
     * @param sensor    keyboardSensor
     * @param key       a string
     * @param animation given Animation
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.animation = animation;
        this.ks = sensor;
        this.key = key;
        this.stop = false;
        this.isAlreadyPressed = true;
    }

    /**
     * Creates one frame.
     *
     * @param d  a given drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.animation.doOneFrame(d, dt);
        if (this.ks.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.stop = true;
            }
        } else {
            this.isAlreadyPressed = false;
        }

    }

    /**
     * Return true if game should stop.
     *
     * @return true if game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

}
