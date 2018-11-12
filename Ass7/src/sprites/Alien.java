package sprites;


import geometrics.Point;
import geometrics.Rectangle;

/**
 * Alien.
 */
public class Alien extends Block {
    private double xValue;
    private double yValue;

    /**
     * Construct Alien with x and y values.
     *
     * @param x x value
     * @param y y value
     */
    public Alien(double x, double y) {
        super(x, y);
        this.xValue = x;
        this.yValue = y;

    }

    /**
     * Moves an Alien in the game.
     *
     * @param dx change in x axe
     * @param dy change in y axe
     */
    public void moveAlien(double dx, double dy) {
        Point newUpperPoint = new Point(super.getCollisionRectangle().getUpperLeft().getX() + dx,
                super.getCollisionRectangle().getUpperLeft().getY() + dy);
        super.setRect(new Rectangle(newUpperPoint, super.getWidth(), super.getCollisionRectangle().getHeight()));
    }

    /**
     * Reposition army of aliens to the initial location.
     */
    public void goStartPosition() {
        Point startingPoint = new Point(xValue, yValue);
        super.setRect(new Rectangle(startingPoint, super.getWidth(), super.getCollisionRectangle().getHeight()));
    }
}

