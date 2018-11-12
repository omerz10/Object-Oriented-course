/**
 * Omer Zucker
 * 200876548
 */

package geometrics;

import java.util.List;

/**
 * Line comprise of 2 points (defined by geometrics.Point class),
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
        if (this.start.getX() > this.end.getX()) {
            // swap points because end is left then start
            Point temp = this.start;
            this.start = this.end;
            this.end = temp;
        }
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
        this(new Point(x1, y1), new Point(x2, y2));
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
        if (m1 == m2) {
            return null;
        // this line is vertical
        } else if (m1 == Double.POSITIVE_INFINITY || m1 == Double.NEGATIVE_INFINITY) {
            interX = this.start.getX();
            if ((Math.min(other.start().getX(), other.end().getX()) <= interX
                    && interX <= Math.max(other.end().getX(), other.start().getX()))) {
                // y value of intersection point
                interY = interX * m2 + b2;
                if (Math.min(this.start().getY(), this.end().getY()) <= interY
                        && interY <= Math.max(this.start().getY(), this.end().getY())) {
                    Point interPoint = new Point(Math.round(interX), Math.round(interY));
                    return interPoint;
                }
            }
            return null;
        // other line is vertical
        } else if (m2 == Double.POSITIVE_INFINITY || m2 == Double.NEGATIVE_INFINITY) {
            interX = other.start.getX();
            if ((Math.min(this.start().getX(), this.end().getX()) <= interX
                    && interX <= Math.max(this.end().getX(), this.start().getX()))) {
                // y value of intersection point
                interY = interX * m1 + b1;
                if (Math.min(other.start().getY(), other.end().getY()) <= interY
                        && interY <= Math.max(other.start().getY(), other.end().getY())) {
                    Point interPoint = new Point(Math.round(interX), Math.round(interY));
                    return interPoint;
                }
            }
            return null;
        // this line is horizontal
        } else if (m1 == 0) {
            // y value of potential intersection point
            interY = this.start.getY();
            if ((Math.min(other.start().getY(), other.end().getY()) <= interY
                    && interY <= Math.max(other.end().getY(), other.start().getY()))) {
                interX = ((interY - other.start.getY()) / other.lineSlope()) + other.start.getX();
                Point interPoint = new Point(Math.round(interX), Math.round(interY));
                if (Math.min(this.start().getX(), this.end().getX()) <= interX
                        && interX <= Math.max(this.start().getX(), this.end().getX())) {
                    return interPoint;
                }
            }
            return null;
            // other line is horizontal
        } else if (m2 == 0) {
            // y value of potential intersection point
            interY = this.start.getY();
            if ((Math.min(this.start().getY(), this.end().getY()) <= interY
                    && interY <= Math.max(this.end().getY(), this.start().getY()))) {
                interX = ((interY - this.start.getY()) / this.lineSlope()) + this.start.getX();
                Point interPoint = new Point(Math.round(interX), Math.round(interY));
                if (Math.min(other.start().getX(), other.end().getX()) <= interX
                        && interX <= Math.max(other.start().getX(), other.end().getX())) {
                    return interPoint;
                }
            }
            return null;
            // lines are not horizontal or vertical
        } else {
            interX = (b1 - b2) / (m2 - m1);
            interY = (m1 * (interX - this.start.getX())) + this.start.getY();
            Point interPoint = new Point(Math.round(interX), Math.round(interY));
            if (Math.min(other.start().getX(), other.end().getX()) <= interX
                    && interX <= Math.max(other.start().getX(), other.end().getX())
                    || (Math.min(this.start().getX(), this.end().getX()) <= interX
                    && interX <= Math.max(this.start().getX(), this.end().getX()))) {
                return interPoint;
            }
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

    /**
     * If this line does not intersect with the rectangle, return null.
     * Otherwise, return the closest intersection point to the start of the line.
     *
     * @param rect a given rectangle
     * @return geometrics.Point
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> list = rect.intersectionPoints(this);
        if (list.isEmpty()) {
            return null;
        // there is only one point in list
        } else if (list.size() == 1) {
            return list.get(0);
        // first point in list closer than the second one
        } else if (this.start.distance(list.get(0)) < this.start.distance(list.get(1))) {
            return list.get(0);
        // second point in list closer than the first one
        } else {
            return list.get(1);
        }
    }

}