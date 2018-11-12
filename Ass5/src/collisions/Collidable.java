package collisions;

import geometrics.Point;
import geometrics.Rectangle;

/**
 * Omer Zucker.
 * 200876548
 */
public interface Collidable {
    /**
     * Return the "collision shape" of the object.
     *
     * @return "collision shape" of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * Notify the object that we collided with it at collisionPoint with a given velocity.
     * The return is the new velocity expected after the hit (based on the force the object inflicted on us).
     *
     * @param collisionPoint  a specific point that the ball is going to collide with
     * @param currentVelocity velocity of the ball before the collision
     * @param hitter a ball
     * @return new velocity expected after the hit (based on the force the object inflicted on us)
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);
}
