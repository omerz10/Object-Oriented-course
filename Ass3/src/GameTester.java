/**
 * Created by עומר on 01/05/2017.
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.Random;

/**
 * Created by Eilon on 29/04/2017.
 */
public class GameTester {
/*
    public static void main(String[] args) {
        //Set board.
        GUI gui = new GUI("title", 600, 600);
        Point lim1 = new Point(0, 0);
        Point lim2 = new Point(600, 600);
        Random rand = new Random();
        Sleeper sleeper = new Sleeper();
        //Set limits.
        Point limPoint1 = new Point(0, 0);
        Point limPoint2 = new Point(0, 570);
        Point limPoint3 = new Point(570, 0);
        Block limLeftBlock = new Block(limPoint1, 30, 600, Color.BLACK);
        Block limUpperBlock = new Block(limPoint1, 600, 30, Color.BLACK);
        Block limLowBlock = new Block(limPoint2, 600, 30, Color.BLACK);
        Block limRightBlock = new Block(limPoint3, 30, 600, Color.BLACK);
        Block b = new Block(new Point(100, 100), 200, 400, Color.RED);
        GameEnvironment game = new GameEnvironment();
        game.addCollidable(b);
        game.addCollidable(limLeftBlock);
        game.addCollidable(limLowBlock);
        game.addCollidable(limRightBlock);
        game.addCollidable(limUpperBlock);
        //Location, size and color of the ball.
        Ball ball = new Ball(400, 400, 5, Color.BLUE);
        ball.setGameEnvironment(game);
        ball.setBoundary(0 , 0, 600, 600);
        //Set velocity of ball.
        Velocity v = Velocity.fromAngleAndSpeed(180, 10);
        ball.setVelocity(v);
        //Draw.
        while (true) {
            DrawSurface d = gui.getDrawSurface();
            ball.moveOneStep();
            b.drawOn(d);
            limLeftBlock.drawOn(d);
            limLowBlock.drawOn(d);
            limRightBlock.drawOn(d);
            limUpperBlock.drawOn(d);
            ball.drawOn(d);
            gui.show(d);
            sleeper.sleepFor(50);  // wait for 50 milliseconds.
        }
    }
*/
}
