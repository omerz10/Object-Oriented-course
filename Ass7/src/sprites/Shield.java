package sprites;


import game.GameLevelAlien;
import geometrics.Point;
import geometrics.Rectangle;
import listeners.BallRemover;
import listeners.ShieldRemover;

import java.awt.Color;

/**
 * Shield.
 */
public class Shield {
    private double x;
    private double y;
    private Color color;

    /**
     * Contruct a Shield with x and y values.
     * @param x x value
     * @param y y value
     */
    public Shield(double x, double y) {
        this.x = x;
        this.y = y;
        this.color = new Color(16, 252, 255);
    }

    /**
     * Create 45 small Blocks in a Shield.
     * @param g GameLevelAlien
     * @param shieldR ShieldRemover
     * @param ballR BallRemover
     */
    public void createShields(GameLevelAlien g, ShieldRemover shieldR, BallRemover ballR) {
        for (int i = 1; i <= 15; i++) {
            for (int j = 1; j <= 3; j++) {
                Point newP = new Point(x + (10 * i), y + (3 * j));
                Rectangle rect = new Rectangle(newP, 10, 3);
                Block newBlock = new Block(rect, color);
                newBlock.addToGame(g);
                newBlock.addHitListener(shieldR);
                newBlock.addHitListener(ballR);
            }
        }
    }
}
