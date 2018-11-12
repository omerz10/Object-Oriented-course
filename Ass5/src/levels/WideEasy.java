package levels;

import collisions.Velocity;
import geometrics.Point;
import sprites.Block;
import sprites.Sprite;


import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * WideEasy - level 2.
 */
public class WideEasy implements LevelInformation {

    /**
     * Returns number of balls for level.
     *
     * @return number of balls
     */
    public int numberOfBalls() {
        return this.initialBallVelocities().size();
    }

    /**
     * Returns a list of the initial velocity of each ball.
     *
     * @return list of the initial velocity of each ball
     */
    public List<Velocity> initialBallVelocities() {
        List<Velocity> vList = new ArrayList<Velocity>();
        double velDelta = 330;
        double velDelta2 = 30;
        for (int i = 0; i < 5; i++) {
            vList.add(Velocity.fromAngleAndSpeed(velDelta, 5));
            vList.add(Velocity.fromAngleAndSpeed(velDelta2, 5));
            velDelta -= 10;
            velDelta2 += 10;
        }
        return vList;
    }

    /**
     * Returns speed of the paddle.
     *
     * @return speed of the paddle
     */
    public int paddleSpeed() {
        return 5;
    }

    /**
     * Returns width of the paddle.
     *
     * @return width of the paddle
     */
    public int paddleWidth() {
        return 600;
    }

    /**
     * Returns level's name.
     *
     * @return level's name
     */
    public String levelName() {
        return "Wide Easy";
    }

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprite with the background of the level
     */
    public Sprite getBackground() {
        return new WideEasyBackground();
    }

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level
     */
    public List<Block> blocks() {
        List<Block> bList = new ArrayList<Block>();
        Color[] blocksColor = new Color[15];
        blocksColor[0] = Color.RED;
        blocksColor[1] = Color.RED;
        blocksColor[2] = Color.ORANGE;
        blocksColor[3] = Color.ORANGE;
        blocksColor[4] = Color.YELLOW;
        blocksColor[5] = Color.YELLOW;
        blocksColor[6] = Color.GREEN;
        blocksColor[7] = Color.GREEN;
        blocksColor[8] = Color.GREEN;
        blocksColor[9] = Color.BLUE;
        blocksColor[10] = Color.BLUE;
        blocksColor[11] = Color.PINK;
        blocksColor[12] = Color.PINK;
        blocksColor[13] = Color.CYAN;
        blocksColor[14] = Color.CYAN;
        double nextBlock = 0;
        for (int i = 0; i < 15; i++) {
            bList.add(new Block(new Point(20 + nextBlock, 200), 51, 20, blocksColor[i],
                    1));
            nextBlock += 50.6;
        }
        return bList;
    }

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return Number of blocks that should be removed before the level is considered to be "cleared"
     */
    public int numberOfBlocksToRemove() {
        return this.blocks().size();
    }
}
