package levels;

import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * WideEasyBackground.
 */
public class WideEasyBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(Color.WHITE);
        d.fillRectangle(20, 40, 760, 560);
        // Create the lines that go out from the sun
        double angle = 100;
        int xValueE = 780;
        d.setColor(Color.YELLOW);
        for (int i = 0; i < 85; i++) {
            int xValueS =  100 + 40 * (int) Math.sin(Math.toRadians(angle));
            int yValueS = 100 + 40 * ((int) Math.cos(Math.toRadians(angle) * (-1)));
            d.drawLine(xValueS, yValueS, xValueE, 200);
            angle = angle + 3;
            xValueE -= 10;
        }
        // Create Sun
        d.setColor(Color.YELLOW);
        d.fillCircle(120, 100, 35);
        d.setColor(Color.ORANGE);
        d.fillCircle(120, 100, 25);
        d.setColor(Color.red);
        d.fillCircle(120, 100, 15);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
