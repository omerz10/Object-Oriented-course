package animations; /**
 * Omer Zucker
 * 200876548
 */

import collisions.Collidable;
import collisions.CollisionInfo;
import geometrics.Line;
import geometrics.Point;
import geometrics.Rectangle;

import java.util.ArrayList;
import java.util.List;

/**
 * animations.GameEnvironment contains all collidable object might be in the game.
 */
public class GameEnvironment {
    private List<Collidable> collidables;

    /**
     * add the given collidable to the environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<Collidable>();
    }

    /**
     * Add a specific collidable object.
     *
     * @param c a collidable object
     */
    public void addCollidable(Collidable c) {
        this.collidables.add(c);
    }

    /**
     * Remove a specific collidable object.
     *
     * @param c a collidable object
     */
    public void removeCollidable(Collidable c) {
        this.collidables.remove(c);
    }

    /**
     * Assume an object moving from line.start() to line.end().
     * If this object will not collide with any of the collidables.
     * in this collection, return null. Else, return the information
     * about the closest collision that is going to occur.
     *
     * @param trajectory the "path" of an object until the collision
     * @return collision info - geometrics.Point and shape
     */
    public CollisionInfo getClosestCollision(Line trajectory) {
        Point collisionPoint = null;
        //set flag for first optional collidable object in array
        boolean flag = true;
        double minDistance = 0;
        int index = 0;
        // no collidables
        if (collidables.isEmpty()) {
            return null;
        }
        // run through collidables in collection
        for (int i = 0; i < this.collidables.size(); i++) {
            Rectangle collidableRect = this.collidables.get(i).getCollisionRectangle();
            collisionPoint = trajectory.closestIntersectionToStartOfLine(collidableRect);
            // check if collidableRect is the closest collision
            if (collisionPoint != null) {
                // saving minimum distance of first optional collision point
                if (flag) {
                    minDistance = trajectory.start().distance(collisionPoint);
                    flag = false;
                }
                double distance = trajectory.start().distance(collisionPoint);
                if (distance <= minDistance) {
                    minDistance = distance;
                    index = i;

                }
            }
        }
        Rectangle collidableRect = this.collidables.get(index).getCollisionRectangle();
        collisionPoint = trajectory.closestIntersectionToStartOfLine(collidableRect);
        // the closest collision point
        if (collisionPoint != null) {
            CollisionInfo collisionInfo = new CollisionInfo(collisionPoint, this.collidables.get(index));
            return collisionInfo;
        // object will not collide with any of the collidables
        } else {
            return null;
        }
    }
}


