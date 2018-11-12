/**
 * collisions.
 * <p>
 * Omer Zucker
 * 200876548
 */
package sprites;

import collisions.CollisionInfo;
import collisions.Velocity;
import game.GameLevel;
import listeners.HitListener;
import geometrics.Point;
import game.GameEnvironment;
import biuoop.DrawSurface;
import geometrics.Line;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

/**
 * sprites.Ball comprise of center (geometrics.Point), size (radius), color.
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
     * sprites.Ball is moving one step according to it's velocity.
     *
     * @param dt parameter
     */
    public void moveOneStep(double dt) {
        Velocity dtVel = new Velocity(this.getVelocity().getX() * dt, this.getVelocity().getY() * dt);
        Line trajectory = new Line(this.center, dtVel.applyToPoint(this.center));
        CollisionInfo colInfo = this.objects.getClosestCollision(trajectory);
        // sprites.Ball will not hit anything
        if (colInfo == null) {
            this.center = dtVel.applyToPoint(this.center);
            // there is a collision
        } else {
            Point collisionPoint = colInfo.collisionPoint();
            double newColPointX = collisionPoint.getX();
            double newColPointY = collisionPoint.getY();
            // move collision point a bit
            if (dtVel.getX() > 0) {
                newColPointX -= 0.000001;
            }
            if (dtVel.getX() < 0) {
                newColPointX += 0.000001;
            }
            if (dtVel.getY() > 0) {
                newColPointY -= 0.000001;
            }
            if (dtVel.getY() < 0) {
                newColPointY += 0.000001;
            }
            this.center = new Point(newColPointX, newColPointY);
            Velocity newVelocity = colInfo.collisionObject().hit(this, collisionPoint, velocity);
            this.velocity = newVelocity;
        }
    }

    /**
     * Add sprite to game.
     *
     * @param g game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * The function remove from game.
     *
     * @param g - given gameLevel.
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
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
     *
     * @param dt parameter
     */
    public void timePassed(double dt) {
        this.moveOneStep(dt);
    }
}

