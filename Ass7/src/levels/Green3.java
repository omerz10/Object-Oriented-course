package levels;

import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import sprites.Block;
import sprites.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;



/**
 * Green3 - level 3.
 */
public class Green3 implements LevelInformation {
    /**
     * The function return number of ball in this level.
     * @return the number of balls in this level.
     */
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * The function do The initial velocity of each ball.
     * @return list of velocity to all the balls.
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<Velocity>();
        double velDelta = 270;
        for (int i = 0; i < 2; i++) {
                vList.add(Velocity.fromAngleAndSpeed(45 + i * velDelta, 360));
            }
        return vList;
    }
    /**
     * The function return the paddle speed in this level.
     * @return the paddle speed in this level.
     */
    public int paddleSpeed() {
        return 600;
    }
    /**
     * The function return the paddle width in this level.
     * @return the paddle width in this level.
     */
    public int paddleWidth() {
        return 120;
    }
    /**
     * The function return the name of this level.
     * @return the the level name.
     */
    public String levelName() {
        return "Green 3";
    }
    /**
     * The function return the background of this level.
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new Green3Background();

    }
    /**
     * The function return all blocks of his level.
     * @return array of the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> bList = new ArrayList<Block>();
        Color[] blocksColor = new Color[5];
        blocksColor[0] = Color.GRAY;
        blocksColor[1] = Color.RED;
        blocksColor[2] = Color.YELLOW;
        blocksColor[3] = Color.BLUE;
        blocksColor[4] = Color.WHITE;
        // create 5 lines
        for (int i = 0; i < 5; i++) {
            // create blocks in each line
            for (int j = 0; j < 10 - i; j++) {
                // first line
                if (i == 0) {
                    bList.add(new Block(new Rectangle(new Point(800 - (71 + (50 * j)), 100 + (i * 20)),
                            50, 20), blocksColor[i], 2));
                    // lines 2-5
                } else {
                    bList.add(new Block(new Rectangle(new Point(800 - (71 + (50 * j)), 100 + (i * 20)),
                            50, 20), blocksColor[i], 1));
                }
            }
        }
        return bList;
    }

    /**
     * The function return number of blocks.
     * @return Number of levels that should be removed.
     * before the level is considered to be "cleared".
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
