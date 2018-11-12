package animations;

import biuoop.DrawSurface;

/**
 * Animation.
 */
public interface Animation {

    /**
     * Creates one Frame.
     *
     * @param d a given drawSurface
     * @param dt parameter
     */
    void doOneFrame(DrawSurface d, double dt);

    /**
     * Returns boolean.
     *
     * @return boolean
     */
    boolean shouldStop();
}
