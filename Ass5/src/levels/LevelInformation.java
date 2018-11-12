package levels;

import collisions.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * LevelInformation.
 */
public interface LevelInformation {

    /**
     * Returns number of balls for level.
     *
     * @return number of balls
     */
    int numberOfBalls();


    /**
     * Returns a list of the initial velocity of each ball.
     *
     * @return list of the initial velocity of each ball
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns speed of the paddle.
     *
     * @return speed of the paddle
     */
    int paddleSpeed();

    /**
     * Returns width of the paddle.
     *
     * @return width of the paddle
     */
    int paddleWidth();



    /**
     * The level name will be displayed at the top of the screen.
     *
     * @return level's name
     */
    String levelName();

    /**
     * Returns a sprite with the background of the level.
     *
     * @return sprite with the background of the level
     */
    Sprite getBackground();

    /**
     * The Blocks that make up this level, each block contains its size, color and location.
     *
     * @return the Blocks that make up this level
     */
    List<Block> blocks();

    /**
     * Number of blocks that should be removed before the level is considered to be "cleared".
     *
     * @return Number of blocks that should be removed before the level is considered to be "cleared"
     */
    int numberOfBlocksToRemove();
}
