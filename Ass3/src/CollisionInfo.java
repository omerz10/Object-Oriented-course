/**
 * Omer Zucker.
 * 200876548
 * CollisionInfo contains information about a specific object and it's collision point.
 */
public class CollisionInfo {
    private Point collisionPoint;
    private Collidable collidableObject;

    /**
     * Cunstruct collisionInfo from an object and it's collision point.
     *
     * @param collisionPoint object's collision point
     * @param c              a given object
     */
    public CollisionInfo(Point collisionPoint, Collidable c) {
        this.collisionPoint = collisionPoint;
        this.collidableObject = c;
    }
    //

    /**
     * Returns the point at which the collision occurs.
     *
     * @return point at which the collision occurs
     */
    public Point collisionPoint() {
        return this.collisionPoint;
    }

    /**
     * the collidable object involved in the collision.
     *
     * @return collidable object involved in the collision
     */
    public Collidable collisionObject() {
        return this.collidableObject;
    }
}
