/**
 * Omer Zucker.
 * 200876548
 */

import java.util.ArrayList;
import java.util.List;

/**
 * Rectangle comprise of upper left point (frame's corner), width, height and frame (list of lines).
 */
public class Rectangle {
    private Point upperLeft;
    private double width;
    private double height;
    private Line[] rectangleFrame = new Line[4];
    private int upperFrame = 0;
    private int lowerFrame = 1;
    private int leftFrame = 2;
    private int rightFrame = 3;

    /**
     * Construct a new rectangle with location and width/height.
     *
     * @param upperLeft point of upper left corner of rectangle
     * @param width     width of rectangle
     * @param height    height of rectangle
     */
    public Rectangle(Point upperLeft, double width, double height) {
        this.upperLeft = upperLeft;
        this.width = width;
        this.height = height;
        this.rectangleFrame[this.upperFrame] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY());
        this.rectangleFrame[this.lowerFrame] = new Line(this.upperLeft.getX(), this.upperLeft.getY() + this.height,
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
        this.rectangleFrame[this.leftFrame] = new Line(this.upperLeft.getX(), this.upperLeft.getY(),
                this.upperLeft.getX(), this.upperLeft.getY() + this.height);
        this.rectangleFrame[this.rightFrame] = new Line(this.upperLeft.getX() + this.width, this.upperLeft.getY(),
                this.upperLeft.getX() + this.width, this.upperLeft.getY() + this.height);
    }

    /**
     * Return a (possibly empty) List of intersection points with the specified line.
     *
     * @param line a line
     * @return List of intersection points with line
     */
    public java.util.List intersectionPoints(Line line) {
        List<Point> intersectionPointsList = new ArrayList<Point>();
        Point intersecting;
        for (int i = 0; i < rectangleFrame.length; i++) {
            intersecting = this.rectangleFrame[i].intersectionWith(line);
            // line is intersecting with frame
            if (intersecting != null) {
                intersectionPointsList.add(intersecting);
            }
        }
        return intersectionPointsList;
    }

    /**
     * Return the width of the rectangle.
     *
     * @return width of the rectangle
     */
    public double getWidth() {
        return (this.width);
    }

    /**
     * Return the height of the rectangle.
     *
     * @return height of the rectangle
     */
    public double getHeight() {
        return (this.height);
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return (this.upperLeft);
    }
}