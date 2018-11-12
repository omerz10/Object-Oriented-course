package sprites; /**
 * Omer Zucker
 * 200876548
 */

import animations.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import collisions.Ball;
import collisions.Collidable;
import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import biuoop.DrawSurface;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * sprites.Block comprise of shape (rect) and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private int numberOfHits;
    private List<HitListener> hitListeners;

    /**
     * Construct a block given rect (type of shape) and color.
     *
     * @param rect  shape of block
     * @param color color of block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a block given rect (type of shape) and color.
     *
     * @param rect         shape of block
     * @param color        color of block
     * @param numberOfHits number of hiths of this block
     */
    public Block(Rectangle rect, Color color, int numberOfHits) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.numberOfHits = numberOfHits;
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
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a block given upper Left point, width, height and color.
     *
     * @param upperLeft point of upper left corner of rectangle
     * @param width     width of rectangle
     * @param height    height of rectangle
     * @param color     color of rectangle
     * @param numberOfHits number of hiths of this block
     */
    public Block(Point upperLeft, double width, double height, Color color, int numberOfHits) {
        this.rect = new Rectangle(upperLeft, width, height);
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.numberOfHits = numberOfHits;
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
     * Returns number of hit in this block.
     *
     * @return numer of hits
     */
    public int getHitPoints() {
        return this.numberOfHits;
    }

    /**
     * Return the new velocity right after collision.
     *
     * @param collisionPoint  a specific point that the ball is going to collide with
     * @param currentVelocity velocity of the ball before the collision
     * @param hitter          a ball
     * @return new collisions.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
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
        this.notifyHit(hitter);
        return newVelocity;
    }

    /**
     * @return rectangle object of this block
     */
    public Rectangle getRect() {
        return rect;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param d is a given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                    (int) this.rect.getWidth(), (int) this.rect.getHeight());
            // draw frame of block
            if (this.numberOfHits != 0) {
                d.setColor(Color.BLACK);
                d.drawRectangle((int) this.rect.getUpperLeft().getX(), (int) this.rect.getUpperLeft().getY(),
                        (int) this.rect.getWidth(), (int) this.rect.getHeight());
            }
        }
    }

    /**
     * TimePassed.
     */
    public void timePassed(double dt) {
        
    }

    /**
     * Add this block to game.
     *
     * @param g a givven game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Add this block to game.
     *
     * @param game a givven game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }

    /**
     * Notify when ball hit the block.
     *
     * @param hitter a ball
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl as a listener to hit events.
     *
     * @param hl a listeners.HitListener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }


    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a listeners.HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

}
