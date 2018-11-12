package sprites;

import geometrics.Rectangle;
import animations.Counter;
import biuoop.DrawSurface;

/**
 * LivesIndicator.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class LivesIndicator implements Sprite {
    private Rectangle scoreWindow;
    private Counter lives;

    /**
     * Construct ScoreIndicator with window (Block) and givven game.
     *
     * @param scoreWindow a block
     * @param lives       given game
     */
    public LivesIndicator(Rectangle scoreWindow, Counter lives) {
        this.scoreWindow = scoreWindow;
        this.lives = lives;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText((int) (this.scoreWindow.getUpperLeft().getX() + this.scoreWindow.getWidth() / 12),
                (int) (this.scoreWindow.getUpperLeft().getY() + this.scoreWindow.getHeight() / 1.2),
                "Number Of Lives:" + Integer.toString(this.lives.getValue()), 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }

    /**
     * Add new sprite to game.
     *
     * @param s the array list s is adding to.
     */
    public void addToGame(SpriteCollection s) {
        s.addSprite(this);
    }
}
