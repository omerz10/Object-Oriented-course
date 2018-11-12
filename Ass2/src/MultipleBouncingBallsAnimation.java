/**
 * Omer Zucker
 * 200876548
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.util.Random;


/**
 * Creates number of balls according to user's input (radius).
 * balls are bouncing in window.
 */
public class MultipleBouncingBallsAnimation {
    /**
     * Checks if argument is a letter.
     *
     * @param argument argument from args
     * @return true or false
     *
     */
    public boolean checkInputIsLetter(String argument) {
        try {
            // throws exception if the argument is a string (not int)
            Double.parseDouble(argument);
        } catch (NumberFormatException e) {
            return false;
        }
        // it is possible to convert int to double
        return true;
    }

    /**
     * Convert argument to integer.
     *
     * @param argument argument from args (user's input)
     * @return argument in integer type
     * @throws RuntimeException if radius is not valid
     */
    public int convertToInt(String argument) throws RuntimeException {
        // argument is a string
        if (!checkInputIsLetter(argument)) {
            throw new RuntimeException("Radius is invalid!");
        }
        // argument is negative number (radius can not be negative)
        if (Double.parseDouble(argument) < 0) {
            throw new RuntimeException("Radius is invalid!");
        }
        return ((int) Double.parseDouble(argument));
    }

    /**
     * @param radius    radius of specific ball
     * @param boundary1 boundary of window's upper left corner
     * @param boundary2 boundary of window's lower right corner
     * @return radius
     * @throws RuntimeException if radius is not valid
     */
    public int radiusCheck(int radius, Point boundary1, Point boundary2) throws RuntimeException {
        int diameter = 2 * radius;
        int horBoundary = (int) (boundary2.getX() - boundary1.getX());
        int verBoundary = (int) (boundary2.getY() - boundary1.getY());
        if (diameter > horBoundary || diameter > verBoundary) {
            throw new RuntimeException("Radius is invalid!");
        }
        return radius;
    }

    /**
     * generates random center point and return it.
     *
     * @param rand      known function for random
     * @param boundary1 boundary of window's upper left corner
     * @param boundary2 boundary of window's lower right corner
     * @return center of ball
     */
    public Point generateRandomBall(Random rand, Point boundary1, Point boundary2) {
        double x = (double) rand.nextInt((int) boundary2.getX()) + (int) boundary1.getX(); // get integer in range 1-500
        double y = (double) rand.nextInt((int) boundary2.getY()) + (int) boundary1.getY(); // get integer in range 1-500
        Point point = new Point(x, y);
        return point;
    }

    /**
     * sets a random velocity for specific ball according to it's radius.
     *
     * @param radius radius of ball
     * @param ball   specific ball
     * @param rand   known function for random
     */
    public void setRandomVelocity(int radius, Ball ball, Random rand) {
        // size fo ball is bigger than 50
        if (radius > 50) {
            ball.setVelocity(1, 2);
            // as ball's size is smaller - speed is faster
        } else {
            int angle = rand.nextInt(360) + 1; // get integer in range 1-360
            // there is no ball to create
            if (radius == 0) {
                ball.setVelocity(0, 0);
            } else {
                int speed = 200 / radius;
                Velocity v = Velocity.fromAngleAndSpeed(angle, speed);
                ball.setVelocity(v);
            }
        }
    }

    /**
     * Creates 6 ball- center and velocity of each ball are random.
     * note: small balls are faster than big balls (greater than 50).
     *
     * @param args      input from user- radius of balls
     * @param boundary1 boundary of window's upper left corner
     * @param boundary2 boundary of window's lower right corner
     * @return balls - array of Ball type
     */
    public Ball[] createBalls(String[] args, Point boundary1, Point boundary2) {
        Random rand = new Random();
        Ball[] balls = new Ball[args.length];
        // create 6 balls
        for (int i = 0; i < args.length; i++) {
            int radius = 0;
            // turn input's parameters to radius (integer type)
            try {
                radius = this.convertToInt(args[i]);
            } catch (RuntimeException e) {
                System.out.println("Radius is invalid!");
                System.exit(1);
            }
            // checks if ball is smaller than window
            try {
                radius = this.radiusCheck(radius, boundary1, boundary2);
            } catch (RuntimeException e) {
                System.out.println("Radius is invalid!");
                System.exit(1);
            }
            // sets center coordinates
            Point center = this.generateRandomBall(rand, boundary1, boundary2);
            Ball newBall = new Ball(center, radius, java.awt.Color.BLACK);
            // boundary of window
            newBall.setBoundary((int) boundary1.getX(), (int) boundary1.getY(),
                    (int) boundary2.getX(), (int) boundary2.getY());
            this.setRandomVelocity(radius, newBall, rand);
            balls[i] = newBall;
        }
        return balls;
    }

    /**
     * main function.
     *
     * @param args user's input
     */
    public static void main(String[] args) {
        MultipleBouncingBallsAnimation bouncingBalls = new MultipleBouncingBallsAnimation();
        GUI gui = new GUI("Create 6 Balls", 300, 300);
        Sleeper sleeper = new Sleeper();
        Point boundary1 = new Point(0, 0);
        Point boundary2 = new Point(300, 300);
        Ball[] balls = bouncingBalls.createBalls(args, boundary1, boundary2);
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            for (int i = 0; i < balls.length; i++) {
                balls[i].moveOneStep();
                balls[i].drawOn(d);
            }
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
}