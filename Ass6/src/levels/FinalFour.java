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
 * FinalFour - level 4.
 */
public class FinalFour implements LevelInformation {
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
        vList.add(Velocity.fromAngleAndSpeed(0, 360));
        //vList.add(Velocity.fromAngleAndSpeed(45, 360));
       // vList.add(Velocity.fromAngleAndSpeed(315, 360));
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
        return 100;
    }
    /**
     * The function return the name of this level.
     * @return the the level name.
     */
    public String levelName() {
        return "Final Four";
    }
    /**
     * The function return the background of this level.
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return new FinalFourBackground();

    }
    /**
     * The function return all blocks of his level.
     * @return array of the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        List<Block> bList = new ArrayList<Block>();
        Color[] blocksColor = new Color[7];
        blocksColor[0] = Color.GRAY;
        blocksColor[1] = Color.RED;
        blocksColor[2] = Color.YELLOW;
        blocksColor[3] = Color.BLUE;
        blocksColor[4] = Color.WHITE;
        blocksColor[5] = Color.PINK;
        blocksColor[6] = Color.CYAN;
        // create 7 lines
        for (int i = 0; i < 7; i++) {
            // create blocks in each line
            for (int j = 0; j < 15; j++) {
                bList.add(new Block(new Rectangle(new Point(800 - (60 + (39 * j)), 100 + (i * 20)),
                            39, 20), blocksColor[i], 1));
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
