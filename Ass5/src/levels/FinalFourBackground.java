package levels;

import sprites.Sprite;
import biuoop.DrawSurface;

import java.awt.Color;

/**
 * FinalFourBackground.
 */
public class FinalFourBackground implements Sprite {
    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.fillRectangle(20, 40, 760, 560);
        Color azure = new Color(138, 185, 255);
        d.setColor(azure);
        d.fillRectangle(20, 40, 760, 560);
        // create lines that go out from the clouds
        d.setColor(Color.WHITE);
        int xValueE = 590;
        int xValueS = 600;
        for (int i = 0; i < 10; i++) {
            d.drawLine(xValueS, 520, xValueE, 600);
            xValueS += 10;
            xValueE += 10;
        }
        // create clouds
        Color grey1 = new Color(219, 219, 219);
        d.setColor(grey1);
        d.fillCircle(600, 500, 20);
        d.fillCircle(620, 520, 25);
        Color grey2 = new Color(190, 190, 190);
        d.setColor(grey2);
        d.fillCircle(640, 490, 30);
        Color grey3 = new Color(162, 162, 162);
        d.setColor(grey3);
        d.fillCircle(650, 510, 20);
        d.fillCircle(680, 495, 30);
        // create lines that go out from the clouds
        d.setColor(Color.WHITE);
        xValueE = 140;
        xValueS = 150;
        for (int i = 0; i < 10; i++) {
            d.drawLine(xValueS, 470, xValueE, 600);
            xValueS += 10;
            xValueE += 10;
        }
        // create more clouds
        d.setColor(grey1);
        d.fillCircle(150, 450, 20);
        d.fillCircle(170, 470, 25);
        d.setColor(grey2);
        d.fillCircle(190, 440, 30);
        d.setColor(grey3);
        d.fillCircle(200, 460, 20);
        d.fillCircle(230, 445, 30);
    }

    /**
     * notify the sprite that time has passed.
     */
    public void timePassed() {
    }
}
