/**
 * Omer Zucker
 * 200876548
 */

import biuoop.GUI;
import biuoop.DrawSurface;
import biuoop.Sleeper;

/**
 * Creates bouncing ball in GUI window.
 */
public class BouncingBallAnimation {
    /**
     * @param args input
     */
    public static void main(String[] args) {
        GUI gui = new GUI("title", 250, 250);
        Sleeper sleeper = new Sleeper();
        Ball ball = new Ball(0, 0, 30, java.awt.Color.BLACK);
        ball.setBoundary(0, 0, 250, 250);
        Velocity v = Velocity.fromAngleAndSpeed(75, 7);
        ball.setVelocity(v);
        while (true) {
            ball.moveOneStep();
            DrawSurface d = gui.getDrawSurface();
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}
