package sprites;


import biuoop.DrawSurface;
import geometrics.Rectangle;
import geometrics.Point;

import java.awt.Image;
import java.awt.Color;

/**
 * BackgroundSprite.
 */
public class BackgroundSprite implements Sprite {
    private Rectangle rect;
    private Color color;
    private Image image;

    /**
     * Construct background with x nd y values, width, height and image.
     *
     * @param point  point
     * @param width  width
     * @param height height
     * @param image  imported image
     */
    public BackgroundSprite(Point point, double width, double height, Image image) {
        this.rect = new Rectangle(point, width, height);
        this.color = null;
        this.image = image;
    }

    /**
     * Construct background with x nd y values, width, height and color.
     *
     * @param point  point
     * @param width  width
     * @param height height
     * @param color  color
     */
    public BackgroundSprite(Point point, double width, double height, Color color) {
        this.rect = new Rectangle(point, width, height);
        this.color = color;
        this.image = null;
    }

    /**
     * draw the sprite to the screen.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        if (this.color == null) {
            d.drawImage((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(), this.image);
        } else {
            d.setColor(this.color);
            d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
        }
    }

    /**
     * Move the ball one step.
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
