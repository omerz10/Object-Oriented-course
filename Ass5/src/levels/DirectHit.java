package levels;

import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import sprites.Block;
import sprites.Sprite;

import java.util.ArrayList;
import java.util.List;

/**
 * DirectHit - level 1.
 */
public class DirectHit implements LevelInformation {

    /**
     * Construct DirectHit.
     */
    public DirectHit() {
    }

    /**
     * The function return number of ball in this level.
     *
     * @return the number of balls in this level.
     */
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * The function do The initial velocity of each ball.
     *
     * @return list of velocity to all the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<Velocity>();
        vList.add(Velocity.fromAngleAndSpeed(180, 4));
        return vList;
    }

    /**
     * The function return the paddle speed in this level.
     *
     * @return the paddle speed in this level.
     */
    public int paddleSpeed() {
        return 10;
    }

    /**
     * The function return the paddle width in this level.
     *
     * @return the paddle width in this level.
     */
    public int paddleWidth() {
        return 100;
    }

    /**
     * The function return the name of this level.
     *
     * @return the the level name.
     */
    public String levelName() {
        return "Direct Hit";
    }

    /**
     * The function return the background of this level.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new DirectHitBackground();

    }

    /**
     * The function return all blocks of his level.
     *
     * @return array of the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> bList = new ArrayList<Block>();
        java.awt.Color red = new java.awt.Color(210, 20, 14);
        Block newblock = new Block(new Rectangle(new Point(385, 145), 30, 30), red, 1);
        bList.add(newblock);
        return bList;
    }

    /**
     * The function return number of blocks.
     *
     * @return Number of levels that should be removed.
     * before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
