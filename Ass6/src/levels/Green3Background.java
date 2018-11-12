package levels;

import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Green3Background.
 */
public class Green3Background implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        Color green = new Color(58, 102, 9);
        d.setColor(green);
        d.fillRectangle(20, 40, 760, 560);
        // Create tower
        d.setColor(Color.BLACK);
        d.fillRectangle(100, 400, 80, 200);
        d.setColor(Color.WHITE);
        int xValue = 105, yValue = 405;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                d.fillRectangle(xValue, yValue, 10, 20);
                xValue = xValue + 15;
            }
            xValue = 105;
            yValue = yValue + 30;
        }
        d.setColor(Color.DARK_GRAY);
        d.fillRectangle(130, 360, 20, 40);
        d.setColor(Color.GRAY);
        d.fillRectangle(138, 260, 5, 100);
        d.setColor(Color.orange);
        d.fillCircle(140, 250, 12);
        d.setColor(Color.RED);
        d.fillCircle(140, 250, 9);
        d.setColor(Color.WHITE);
        d.fillCircle(140, 250, 5);
    }

    /**
     * notify the sprite that time has passed.
     *
     * @param dt parameter
     */
    public void timePassed(double dt) {
    }
}
