
package sprites;

import geometrics.Rectangle;
import biuoop.DrawSurface;

/**
 * LevelName.
 */
public class LevelName implements Sprite {
    private Rectangle scoreWindow;
    private String levelName;

    /**
     * Construct LevelName with window (Block) and given name of the level.
     *
     * @param scoreWindow a block
     * @param levelName   given game
     */
    public LevelName(Rectangle scoreWindow, String levelName) {
        this.scoreWindow = scoreWindow;
        this.levelName = levelName;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(java.awt.Color.BLACK);
        d.drawText((int) (this.scoreWindow.getUpperLeft().getX() + this.scoreWindow.getWidth() / 1.5),
                (int) (this.scoreWindow.getUpperLeft().getY() + this.scoreWindow.getHeight() / 1.2),
                "Level Name: " + this.levelName, 15);
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
