/**
 * collisions.
 * <p>
 * Omer Zucker
 * 200876548
 */
package collisions;

import listeners.HitListener;
import geometrics.Point;
import geometrics.Rectangle;
import animations.GameLevel;
import animations.GameEnvironment;
import sprites.Sprite;
import biuoop.DrawSurface;
import geometrics.Line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * collisions.Ball comprise of center (geometrics.Point), size (radius), color.
 */
public class Ball implements Sprite {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int upperLeftX, upperLeftY, lowerRightX, lowerRightY;
    private GameEnvironment objects;
    private List<HitListener> hitListeners;

    /**
     * Construct a ball given 2 Coordinates (x & y values), radius and color.
     *
     * @param x     is a coordinates of center point.
     * @param y     is a coordinates of center point.
     * @param r     is a length of radius
     * @param color is a color of the ball.
     */
    public Ball(int x, int y, int r, java.awt.Color color) {
        this.center = new Point((double) x, (double) y);
        this.radius = r;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * Construct a ball given a center point, radius, color.
     *
     * @param center specific center point
     * @param r      length of radius
     * @param color  the color of the ball
     */
    public Ball(Point center, int r, java.awt.Color color) {
        this.center = center;
        this.radius = r;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
    }

    /**
     * return  x value of center point.
     *
     * @return the value of coordinate x.
     */
    public int getX() {
        return (int) this.center.getX();
    }

    /**
     * return y value of center point.
     *
     * @return the value of coordinate y.
     */
    public int getY() {
        return (int) this.center.getY();
    }

    /**
     * return a y coordinate of center point.
     *
     * @return the length .
     */
    public int getSize() {
        return this.radius;
    }

    /**
     * Returns upperLeftX geometrics.Point.
     *
     * @return upperLeftX geometrics.Point
     */
    public int getUpperLeftX() {
        return this.upperLeftX;
    }

    /**
     * Returns upperLeftY geometrics.Point.
     *
     * @return upperLeftY geometrics.Point
     */
    public int getUpperLeftY() {
        return this.upperLeftY;
    }

    /**
     * Returns lowerRightX geometrics.Point.
     *
     * @return lowerRightX geometrics.Point
     */
    public int getLowerRightX() {
        return this.lowerRightX;
    }

    /**
     * Returns lowerRightY geometrics.Point.
     *
     * @return lowerRightY geometrics.Point
     */
    public int getLowerRightY() {
        return this.lowerRightY;
    }

    /**
     * Set all objects of game to environment.
     *
     * @param environment contains all objects in the game
     */
    public void setGameEnvironment(GameEnvironment environment) {
        this.objects = environment;
    }

    /**
     * @return color of the ball.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * draw the ball on the given DrawSurface.
     *
     * @param d is a given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.getColor());
        d.fillCircle(this.getX(), this.getY(), this.radius);
        d.setColor(Color.black);
        d.drawCircle(this.getX(), this.getY(), this.radius);
    }

    /**
     * Updating the velocity as it given.
     *
     * @param v the velocity of the ball
     */
    public void setVelocity(Velocity v) {
        this.velocity = v;
    }

    /**
     * Updating the velocity as it given (by dx & dy).
     *
     * @param dx variable of velocity
     * @param dy variable of velocity
     */
    public void setVelocity(double dx, double dy) {
        this.velocity = new Velocity(dx, dy);

    }

    /**
     * return the velocity of the ball.
     *
     * @return the velocity of the ball
     */
    public Velocity getVelocity() {
        return this.velocity;
    }

    /**
     * collisions.Ball is moving one step according to it's velocity.
     */
    public void moveOneStep() {
        Line trajectory = new Line(this.center, this.getVelocity().applyToPoint(this.center));
        CollisionInfo colInfo = this.objects.getClosestCollision(trajectory);
        // collisions.Ball will not hit anything
        if (colInfo == null) {
            this.center = this.getVelocity().applyToPoint(this.center);
            // there is a collision
        } else {
            Point collisionPoint = colInfo.collisionPoint();
            double newColPointX = collisionPoint.getX();
            double newColPointY = collisionPoint.getY();
            // move collision point a bit
            if (this.velocity.getX() > 0) {
                newColPointX -= 0.000001;
            }
            if (this.velocity.getX() < 0) {
                newColPointX += 0.000001;
            }
            if (this.velocity.getY() > 0) {
                newColPointY -= 0.000001;
            }
            if (this.velocity.getY() < 0) {
                newColPointY += 0.000001;
            }

            Rectangle rect = colInfo.collisionObject().getCollisionRectangle();
            if ((this.center.getX() >= rect.getUpperLeft().getX() && this.center.getX() <= rect.getUpperLeft().getX()
                    + rect.getWidth()) && (this.center.getY() >= rect.getUpperLeft().getY()
                    && this.center.getY() <= rect.getUpperLeft().getY() + rect.getHeight())) {
                if (this.velocity.getX() > 0) {
                    newColPointX = collisionPoint.getX() + this.velocity.getX() / 2;
                } else if (this.velocity.getX() < 0) {
                    newColPointX = collisionPoint.getX() - this.velocity.getX() / 2;
                }
                if (this.velocity.getY() > 0) {
                    newColPointY = collisionPoint.getY() - this.velocity.getY() / 2;
                } else if (this.velocity.getY() < 0) {
                    newColPointY = collisionPoint.getY() + this.velocity.getY() / 2;
                }
            }
            this.center = new Point(newColPointX, newColPointY);
            Velocity newVelocity = colInfo.collisionObject().hit(this, collisionPoint, this.velocity);
            this.velocity = newVelocity;
        }
    }

    /**
     * Cheks if ball penetrates the block.
     *
     * @param rect           a the collidable object the ball collide with
     * @param collisionPoint point of collision between ball and block
     */
    public void penetrateToBlock(Rectangle rect, Point collisionPoint) {
        double newColPointX = collisionPoint.getX();
        double newColPointY = collisionPoint.getY();

    }


    /**
     * Moves the collision geometrics.Point a bit.
     *
     * @param collisionPoint a point that the ball is going to collide
     */
    public void moveCollisionPoint(Point collisionPoint) {

    }

    /**
     * @param x1 x value of left horizontal limit
     * @param y1 y value of left vertical limit
     * @param x2 x value of right horizontal limit
     * @param y2 y value of right vertical limit
     */
    public void setBoundary(int x1, int y1, int x2, int y2) {
        this.upperLeftX = x1;
        this.upperLeftY = y1;
        this.lowerRightX = x2;
        this.lowerRightY = y2;
        this.startPosition();
    }

    /**
     * Initializing the position if ball is out of boundaries.
     */
    public void startPosition() {
        // radius is deviating from left
        if ((this.center.getX() < this.upperLeftX + this.radius)) {
            this.center = new Point((double) (this.upperLeftX + this.radius), (double) this.getY());
        }
        // radius is deviating from right
        if (this.center.getX() > this.lowerRightX - this.radius) {
            this.center = new Point((double) (this.lowerRightX - this.radius), (double) this.getY());
        }
        // radius is deviating from upperline
        if (this.center.getY() < this.upperLeftY + this.radius) {
            this.center = new Point((double) this.getX(), (double) (this.upperLeftY + this.radius));
        }

        // radius is deviating from underline
        if ((this.center.getY()) > this.lowerRightY - this.radius) {
            this.center = new Point((double) this.getX(), (double) (this.lowerRightY - this.radius));
        }
    }

    /**
     * Add this collisions.Ball to game.
     *
     * @param g a givven game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }

    /**
     * The function remove from game.
     * @param g - given gameLevel.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
    }

    /**
     * The function add hl as a listener to hit events.
     *
     * @param hl listener to add
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * The function Remove hl from the list of listeners to hit events.
     *
     * @param hl listener to remove
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }

    /**
     * Move the ball one step.
     */
    public void timePassed() {
        this.moveOneStep();
    }

}

