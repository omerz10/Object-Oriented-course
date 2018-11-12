/**
 * Omer Zucker
 * 200876548
 * Line comprise of 2 points (defined by Point class),
 * the Class defines line's characteristics.
 */
public class Line {
    private Point start;
    private Point end;

    /**
     * construct line given start and end points.
     *
     * @param start start point of line
     * @param end   end point of line
     */
    public Line(Point start, Point end) {
        this.start = new Point(start.getX(), start.getY());
        this.end = new Point(end.getX(), end.getY());
    }

    /**
     * construct line given x & y values for points.
     *
     * @param x1 x value of start
     * @param y1 y value of start
     * @param x2 x value of end
     * @param y2 y value of end
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * @return length of line
     */
    public double length() {
        return this.start.distance(end);
    }

    /**
     * Returns the middle point of the line.
     *
     * @return middle point of line
     */
    public Point middle() {
        Point middlePoint = new Point(((this.end.getX() + this.start.getX()) / 2),
                ((this.end.getY() + this.start.getY()) / 2));
        return middlePoint;
    }

    /**
     * Returns the start point of the line.
     *
     * @return start point
     */
    public Point start() {
        return this.start;
    }

    /**
     * Returns the end point of the line.
     *
     * @return end point
     */
    public Point end() {
        return this.end;
    }

    /**
     * Returns true if the lines intersect, false otherwise.
     *
     * @param other other line
     * @return boolean
     */
    public boolean isIntersecting(Line other) {
        if (this.intersectionWith(other) != null) {
            return true;
        }
        return false;
    }

    /**
     * Returns slope value of the equation.
     *
     * @return Returns slope value of the equation
     */
    public double lineSlope() {
        //The formula to calculate the gradient.
        double slope;
        double dy = this.start.getY() - this.end.getY();
        double dx = this.start.getX() - this.end.getX();
        // line is vertical
        if (dx == 0 && dy != 0) {
            slope = Double.POSITIVE_INFINITY;
            return slope;
        }
        // line is horizontal
        if (dy == 0 && dx != 0) {
            slope = 0;
            return slope;
        }
        slope = dy / dx;
        return slope;
    }

    /**
     * Return intercept value of equation.
     *
     * @return intercept value of equation
     */
    public double intercept() {
        return (this.start.getY() - (this.lineSlope() * this.start.getX()));
    }

    /**
     * Checks if interX between range of other.
     *
     * @param interX potential x value for intersection
     * @return true or false
     */
    public boolean checkIntersection(double interX) {
        return ((interX >= this.start.getX() && interX <= this.end.getX())
                || (interX <= this.start.getX() && interX >= this.end.getX()));
    }
// && ((interX >= other.start.getX() && interX <= other.end.getX())
              //  || (interX <= other.start.getX() && interX >= other.end.getX()))

    /**
     * Returns the intersection point if the lines intersect, and null otherwise.
     *
     * @param other other line
     * @return the intersection point if the lines intersect, and null otherwise
     */
    public Point intersectionWith(Line other) {
        double m1 = this.lineSlope();
        double m2 = other.lineSlope();
        double b1 = this.intercept();
        double b2 = other.intercept();
        double interX, interY;
        double infintyLine = Double.POSITIVE_INFINITY;
        if (m1 == m2) {
            return null;
        }
        // this line is vertical
        if (m1 == infintyLine) {
            interX = this.start.getX();
            if (interX > other.start.getX() && interX < other.end.getX()) {
                // y value of intersection point
                interY = (m2 * interX) + b2;
                Point interPoint = new Point(interX, interY);
                if (this.checkIntersection(interX) && other.checkIntersection(interX)) {
                    return interPoint;
                }
            }
            return null;
        }
        // other line is vertical
        if (m2 == infintyLine) {
            interX = other.start.getX();
            if (interX > this.start.getX() && interX < this.end.getX()) {
                // y value of intersection point
                interY = (m1 * interX) + b1;
                Point interPoint = new Point(interX, interY);
                if (this.checkIntersection(interX) && other.checkIntersection(interX)) {
                    return interPoint;
                }
            }
        return null;
        }
        // this line is horizontal
        if (m1 == 0) {
            // y value of potential intersection point
            interY = this.start.getY();
            if ((interY < other.start.getY() && interY > other.end.getY())
                    || other.start.getY() < interY && other.end.getY() > interY) {
                interX = ((interY - other.start.getY()) / other.lineSlope()) + other.start.getX();
                Point interPoint = new Point(interX, interY);
                if (this.checkIntersection(interX) && other.checkIntersection(interX)) {
                    return interPoint;
                }
            }
            return null;
        }
        // other line is horizontal
        if (m2 == 0) {
            // y value of potential intersection point
            interY = other.start.getY();
            if ((interY < this.start.getY() && interY > this.end.getY())
                    || this.start.getY() < interY && this.end.getY() > interY) {
                interX = ((interY - this.start.getY()) / this.lineSlope()) + this.start.getX();
                Point interPoint = new Point(interX, interY);
                if (this.checkIntersection(interX) && other.checkIntersection(interX)) {
                    return interPoint;
                }
            }
            return null;
        }
        interX = (b1 - b2) / (m2 - m1);
        interY = (m1 * (interX - this.start.getX())) + this.start.getY();
        Point interPoint = new Point(interX, interY);
        if (this.checkIntersection(interX) && other.checkIntersection(interX)) {
            return interPoint;
        }
        return null;
    }

    /**
     * Return true is the lines are equal, false otherwise.
     *
     * @param other value of point
     * @return Return true is the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        // exactly the same line
        if (this.start.equals(other.start) && this.end.equals(other.end)) {
            return true;
        }
        // opossite lines
        if (this.start().equals(other.end) && this.end().equals(other.start)) {
            return true;
        }
        return false;
    }
}