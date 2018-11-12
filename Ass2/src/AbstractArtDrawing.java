/**
 * Omer Zucker
 * 200876548
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import java.util.Random;
import java.awt.Color;

/**
 * Draws 10 lines on window, middle points of lines and intersections of all lines.
 */
public class AbstractArtDrawing {
    /**
     * The function creates 10 random lines and draw them on window
        as well as, drawing middle and intersection points.
     */
    public void draw10Lines() {
        // Create a window with the title "Random Circles Example"
        // which is 400 pixels wide and 300 pixels high.
        GUI gui = new GUI("Create 10 Lines", 500, 500);
        DrawSurface d = gui.getDrawSurface();
        Line[] lines = new Line[10];
        for (int i = 0; i < 10; i++) {
            lines[i] = this.generateRandomLine();
            this.drawLine(lines[i], d);
            this.drawMiddleP(lines[i], d);
            // do not check intersection when there is only 1 line in lines
            if (i > 0) {
                this.drawIntersectionP(lines, i, d);
            }
        }
        // show lines and points on window
        gui.show(d);
    }

    /**
     * Generates random line.
     * @return line
     */
    public Line generateRandomLine() {
        Random rand = new Random(); // create a random-number generator
        double x1 = (double) rand.nextInt(500) + 1; // get integer in range 1-500
        double x2 = (double) rand.nextInt(500) + 1; // get integer in range 1-500
        double y1 = (double) rand.nextInt(500) + 1; // get integer in range 1-500
        double y2 = (double) rand.nextInt(500) + 1; // get integer in range 1-500
        Line line = new Line(x1, y1, x2, y2);
        return line;
    }

    /**
     * Draws l on window.
     * @param l A specific line
     * @param d calls DrawSurface functions
     */
    public void drawLine(Line l, DrawSurface d) {
        d.setColor(Color.black);
        int x1 = (int) l.start().getX();
        int y1 = (int) l.start().getY();
        int x2 = (int) l.end().getX();
        int y2 = (int) l.end().getY();
        d.drawLine(x1, y1, x2, y2);
    }

    /**
     * Draws middle point of l on window.
     * @param l A specific line
     * @param d calls DrawSurface functions
     */
    public void drawMiddleP(Line l, DrawSurface d) {
        d.setColor(Color.blue);
        Point middle = l.middle();
        d.fillCircle((int) middle.getX(), (int) middle.getY(), 3);
    }

    /**
     * Draws intersections points of all lines with each other.
     * @param lines An array of lines
     * @param i index
     * @param d calls DrawSurface functions
     */
    public void drawIntersectionP(Line[] lines, int i, DrawSurface d) {
        d.setColor(Color.red);
        for (int j = i - 1; j >= 0; j--) {
            if (lines[i].isIntersecting(lines[j])) {
                Point newPoint = lines[i].intersectionWith(lines[j]);
                d.fillCircle((int) newPoint.getX(), (int) newPoint.getY(), 3);
            }
        }
    }

    /**
     * The main function operating AbstractArtDrawing func for drawing lines and points on window.
     * @param args user's input
     */
    public static void main(String[] args) {
        AbstractArtDrawing makeLines = new AbstractArtDrawing();
        makeLines.draw10Lines();
    }
}

