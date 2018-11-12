package levels;

import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * DirectHitBackground.
 */
public class DirectHitBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.fillRectangle(20, 40, 760, 560);
        d.setColor(Color.BLUE);
        d.drawCircle(400, 160, 40);
        d.drawCircle(400, 160, 80);
        d.drawCircle(400, 160, 120);
        d.drawLine(260, 160, 380, 160);
        d.drawLine(420, 160, 540, 160);
        d.drawLine(400, 40, 400, 140);
        d.drawLine(400, 180, 400, 300);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt parameter
     */
    public void timePassed(double dt) {
    }
}
