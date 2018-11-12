/**
 * Omer Zucker
 * 200876548
 */

import biuoop.DrawSurface;

import java.awt.Color;

/**
 * Block comprise of shape (rect) and color.
 */
public class Block implements Collidable, Sprite {
    private Rectangle rect;
    private Color color;
    private int numberOfHits;

    /**
     * Construct a block given rect (type of shape) and color.
     *
     * @param rect  shape of block
     * @param color color of block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
    }

    /**
     * Construct a block given upper Left point, width, height and color.
     *
     * @param upperLeft point of upper left corner of rectangle
     * @param width     width of rectangle
     * @param height    height of rectangle
     * @param color     color of rectangle
     */
    public Block(Point upperLeft, double width, double height, Color color) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
    }

    /**
     * Set nuber of hits of this block.
     *
     * @param numOfHits number of hits that the ball hit the block
     */
    public void setNumberOfHits(int numOfHits) {
        this.numberOfHits = numOfHits;
    }

    /**
     * @return color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Return the shape of rectangle.
     *
     * @return rect (shape of rectangle)
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Return the new velocity right after collision.
     *
     * @param collisionPoint  a specific point that the ball is going to collide with
     * @param currentVelocity velocity of the ball before the collision
     * @return new Velocity
     */
    public Velocity hit(Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getX();
        double newDy = currentVelocity.getY();

        // change number of hits on a specific block
        if (this.numberOfHits > 0) {
            this.numberOfHits -= 1;
        }
        // collision with leftFrame or rightFrame of block
        if ((collisionPoint.getX() == this.rect.getUpperLeft().getX())
                || ((collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()))) {
            if ((collisionPoint.getY() >= this.rect.getUpperLeft().getY())
                    && (collisionPoint.getY() <= this.rect.getUpperLeft().getY() + this.rect.getHeight())) {
                newDx *= -1;
            }
        }
        // collision with upperFrame or lowerFrame of block
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY())
                || ((collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()))) {
            if ((collisionPoint.getX() >= this.rect.getUpperLeft().getX())
                    && (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + this.rect.getWidth()))) {
                newDy *= -1;
            }
        }

        Velocity newVelocity = new Velocity(newDx, newDy);
        return newVelocity;
    }

    /**
     * @return rectangle object of this block
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * Number of hits of the object.
     * @return number of hits of the object
     */
    public int getNumberOfHits() {
        return numberOfHits;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param d is a given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // draw frame of block
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                (int) this.rect.getWidth(), (int) this.rect.getHeight());
        // draw number of hits on block
        d.setColor(Color.WHITE);
        int middlePointX = (int) (this.rect.getUpperLeft().getX() + (this.rect.getWidth() / 2));
        int middlePointY = (int) (this.rect.getUpperLeft().getY() + (this.rect.getHeight() / 2));
        // draw number of hits on the block
        if (this.numberOfHits >= 1) {
            d.drawText(middlePointX, middlePointY, Integer.toString(this.numberOfHits), 10);
        } else if (this.numberOfHits == 0) {
            d.drawText(middlePointX, middlePointY, " X ", 10);
        }
    }

    /**
     * TimePassed.
     */
    public void timePassed() {
    }

    /**
     * Add this block to game.
     *
     * @param g a givven game
     */
    public void addToGame(Game g) {
        g.addSprite(this);
        g.addCollidable(this);
    }
}