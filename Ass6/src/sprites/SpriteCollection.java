package sprites; /**
 * Omer Zucker.
 * 200876548
 */

import biuoop.DrawSurface;

import java.util.ArrayList;
import java.util.List;

/**
 * sprites.SpriteCollection contains all sprites.Sprite types in the game.
 */
public class SpriteCollection {
    private List<Sprite> sprites;

    /**
     * Construct collection of sprite's type.
     */
    public SpriteCollection() {
        this.sprites = new ArrayList<Sprite>();
    }

    /**
     * Add a sprites.Sprite.
     *
     * @param s specific sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.add(s);
    }

    /**
     * Remove a sprites.Sprite.
     *
     * @param s specific sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.remove(s);
    }


    /**
     * Call timePassed() on all sprites.
     *
     * @param dt parameter
     */
    public void notifyAllTimePassed(double dt) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).timePassed(dt);
        }
    }

    /**
     * Call drawOn(d) on all sprites.
     *
     * @param d DrawSurface
     */
    public void drawAllOn(DrawSurface d) {
        for (int i = 0; i < this.sprites.size(); i++) {
            this.sprites.get(i).drawOn(d);
        }
    }
}
