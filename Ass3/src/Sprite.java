/**
 * Omer Zucker.
 * 200876548
 */

import biuoop.DrawSurface;

/**
 * Sprite.
 */

public interface Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    void drawOn(DrawSurface d);

    /**
     * notify the sprite that time has passed.
     */
    void timePassed();
}
