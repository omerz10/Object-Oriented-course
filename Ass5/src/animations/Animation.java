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
     */
    void doOneFrame(DrawSurface d);

    /**
     * Returns boolean.
     *
     * @return boolean
     */
    boolean shouldStop();
}
