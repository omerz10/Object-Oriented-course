/**
 * Omer Zucker
 * 200876548
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;
import java.awt.Color;

/**
 * Creates 6 bouncing balls that are bouncing in two rectangles.
 */
public class MultipleFramesBouncingBallsAnimation {

    /**
     * main function.
     *
     * @param args user's input
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation bouncingBalls = new MultipleBouncingBallsAnimation();
        GUI gui = new GUI("6 Balls in 2 windows", 600, 600);
        Sleeper sleeper = new Sleeper();
        // set boundaries of yellow & grey rectangles
        Point greyBoundary1 = new Point(50, 50);
        Point greyBoundary2 = new Point(500, 500);
        Point yellowBoundary1 = new Point(450, 450);
        Point yellowBoundary2 = new Point(600, 600);
        String[] greyRadius = new String[args.length / 2];
        String[] yellowRadius = new String[args.length / 2];
        // convert radius to integer
        for (int i = 0; i < args.length; i++) {
            int radius = 0;
            // turn input's parameters to radius (integer type)
            try {
                radius = bouncingBalls.convertToInt(args[i]);
            } catch (RuntimeException e) {
                System.out.println("Radius is invalid!");
                System.exit(1);
            }
        }
        // divides args to 2 arrays of String
        for (int i = 0; i < args.length / 2; i++) {
            greyRadius[i] = args[i];
            yellowRadius[i] = args[(args.length / 2) + i];
        }
        // balls for grey rectangle
        Ball[] balls = bouncingBalls.createBalls(greyRadius, greyBoundary1, greyBoundary2);
        // balls for yellow rectangle
        Ball[] balls2 = bouncingBalls.createBalls(yellowRadius, yellowBoundary1, yellowBoundary2);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.gray);
            d.fillRectangle(50, 50, 450, 450);
            d.setColor(Color.yellow);
            d.fillRectangle(450, 450, 150, 150);
            // run through balls in grey rectangle
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            // run through balls2 in yellow rectangle
            for (int j = 0; j < balls2.length; j++) {
                balls2[j].moveOneStep();
                balls2[j].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
