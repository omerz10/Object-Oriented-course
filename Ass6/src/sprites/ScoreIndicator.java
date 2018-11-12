package sprites;

import animations.Counter;
import geometrics.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * ScoreIndicator.
 */
public class ScoreIndicator implements Sprite {
    private Rectangle scoreWindow;
    private Counter score;

    /**
     * Construct ScoreIndicator with window (Block) and givven game.
     *
     * @param scoreWindow  a block
     * @param counter given game
     */
    public ScoreIndicator(Rectangle scoreWindow, Counter counter) {
        this.scoreWindow = scoreWindow;
        this.score = counter;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle((int) this.scoreWindow.getUpperLeft().getX(), (int) this.scoreWindow.getUpperLeft().getY(),
                (int) this.scoreWindow.getWidth(), (int) this.scoreWindow.getHeight());
        d.setColor(java.awt.Color.BLACK);
        d.drawText((int) (this.scoreWindow.getUpperLeft().getX() + this.scoreWindow.getWidth() / 2.3),
                (int) (this.scoreWindow.getUpperLeft().getY() + this.scoreWindow.getHeight() / 1.2),
                "Score:" + Integer.toString(this.score.getValue()), 15);
    }

    /**
     * TimePassed.
     *
     * @param dt parameter
     */
    public void timePassed(double dt) {
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
