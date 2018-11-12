package collisions;

import geometrics.Point;

/**
 * Omer Zucker
 * 200876548
 * collisions.Velocity specifies the change in position on the `x` and the `y` axes.
 */
public class Velocity {
    private double dx;
    private double dy;

    /**
     * Construct the velocity given a center dx and dy.
     *
     * @param dx differential distance in x.
     * @param dy differential distance in y.
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * calculates the velocity - angle and speed.
     *
     * @param angle ball's angle (vector)
     * @param speed ball's speed (units)
     * @return velocity in dx/dy values
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        // convert the angle to radians in order to calculate dx & dy
        double dx = Math.sin(Math.toRadians(angle)) * speed;
        double dy = Math.cos(Math.toRadians(angle)) * (-1) * speed;
        Velocity newVelocity = new Velocity(dx, dy);
        return newVelocity;
    }

    /**
     * return the dx of the velocity.
     *
     * @return the dx of the velocity
     */
    public double getX() {
        return this.dx;
    }

    /**
     * return the dy of the velocity.
     *
     * @return the dy of the velocity
     */
    public double getY() {
        return this.dy;
    }

    /**
     * Take a point with position (x,y) and return a new point with position.
     * (x+dx, y+dy
     *
     * @param p is the current point.
     * @return newP is the new point (the new movement)
     */
    public Point applyToPoint(Point p) {
        // summing up the current center with the new change (dx,dy)
        Point newPoint = new Point(p.getX() + this.dx, p.getY() + this.dy);
        return newPoint;
    }

    /**
     * Set dx.
     * @param dx1 dx
     */
    public void setX(double dx1) {
        this.dx = dx1;
    }

    /**
     * Set dy.
     * @param dy1 dy
     */
    public void setY(double dy1) {
        this.dy = dy1;
    }
}
