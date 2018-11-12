package geometrics;

/**
 * Omer Zucker
 * 200876548
 * geometrics.Point comprise of x and y values, the Class defines point's characteristics.
 */
public class Point {
    private double x;
    private double y;

    /**
     * construct point given x & y values.
     *
     * @param x x value of point
     * @param y value of point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * distance of this point to the other point.
     *
     * @param other other point
     * @return distance between points
     */
    public double distance(Point other) {
        return Math.sqrt(((this.x - other.x) * (this.x - other.x)) + ((this.y - other.y) * (this.y - other.y)));
    }

    /**
     * true is the points are equal, false otherwise.
     *
     * @param other other point
     * @return boolean
     */
    public boolean equals(Point other) {
        return (this.x == other.x && this.y == other.y);
    }

    /**
     * Return the x and y values of this point.
     *
     * @return x value of x
     */
    public double getX() {
        return this.x;
    }

    /**
     * Return the x and y values of this point.
     *
     * @return x value of x
     */
    public double getY() {
        return this.y;
    }

    /**
     * Set x value.
     * @param x1 x value of point
     */
    public void setX(double x1) {
        this.x = x1;
    }

    /**
     * Set y value.
     * @param y1 y value of point
     */
    public void setY(double y1) {
        this.y = y1;
    }
}
