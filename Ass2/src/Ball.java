/**
 * Omer Zucker
 * 200876548
 */

import biuoop.DrawSurface;

/**
 * Ball composed of center (Point), size (radius), color.
 */
public class Ball {
    private Point center;
    private int radius;
    private java.awt.Color color;
    private Velocity velocity;
    private int leftHorizontal, rightHorizontal, leftVertical, rightVertical;

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
     * Ball is moving one step according to it's velocity.
     */
    public void moveOneStep() {
        this.center = this.getVelocity().applyToPoint(this.center);
        Velocity prevVelocity = this.getVelocity();
        double dxNew = prevVelocity.getX();
        double dyNew = prevVelocity.getY();
        // check if ball is deviating from window
        if (this.center.getX() > this.rightHorizontal - this.radius) {
            // change dx direction
            dxNew *= -1;
            this.center = new Point(this.rightHorizontal - this.radius, this.getY());
        }
        if (this.center.getX() < this.leftHorizontal + this.radius) {
            dxNew *= -1;
            this.center = new Point(this.leftHorizontal + this.radius, this.getY());
        }
        if (this.center.getY() > this.rightVertical - this.radius) {
            // change dy direction
            dyNew *= -1;
            this.center = new Point(this.getX(), this.rightVertical - this.radius);
        }
        if (this.center.getY() < this.leftVertical + this.radius) {
            // change dy direction
            dyNew *= -1;
            this.center = new Point(this.getX(), this.leftVertical + this.radius);
        }
        this.setVelocity(dxNew, dyNew);

    }

    /**
     * @param x1 x value of left horizontal limit
     * @param y1 y value of left vertical limit
     * @param x2 x value of right horizontal limit
     * @param y2 y value of right vertical limit
     */
    public void setBoundary(int x1, int y1, int x2, int y2) {
        this.leftHorizontal = x1;
        this.leftVertical = y1;
        this.rightHorizontal = x2;
        this.rightVertical = y2;
        //if ((this.radius >= (this.width1 / 2)) || (this.radius >= (this.height1 / 2))
        //  || (this.radius >= (this.width2 / 2)) || (this.radius >= (this.height2 / 2))) {
        //System.out.println("Radius is to big!");
        //System.exit(1);
        //}
        this.startPosition();
    }

    /**
     * Initializing the position if ball is out of boundaries.
     */
    public void startPosition() {
        // radius is deviating from left
        if ((this.center.getX() < this.leftHorizontal + this.radius)) {
            this.center = new Point((double) (this.leftHorizontal + this.radius), (double) this.getY());
        }
        // radius is deviating from right
        if (this.center.getX() > this.rightHorizontal - this.radius) {
            this.center = new Point((double) (this.rightHorizontal - this.radius), (double) this.getY());
        }
        // radius is deviating from upperline
        if (this.center.getY() < this.leftVertical + this.radius) {
            this.center = new Point((double) this.getX(), (double) (this.leftVertical + this.radius));
        }

        // radius is deviating from underline
        if ((this.center.getY()) > this.rightVertical - this.radius) {
            this.center = new Point((double) this.getX(), (double) (this.rightVertical - this.radius));
        }
    }
}
