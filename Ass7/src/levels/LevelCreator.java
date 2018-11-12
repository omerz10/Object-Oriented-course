package levels;

import collisions.Velocity;
import sprites.Block;
import sprites.Sprite;

import java.util.List;

/**
 * LevelCreator.
 */
public class LevelCreator implements LevelInformation {
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private List<Velocity> ballVel;


    /**
     * @param name       level's name
     * @param background background
     * @param velocities list of velocities of all balls
     * @param paddleS    speed of paddle
     * @param paddleW    width of the paddle
     * @param blocks     list of al blocks
     */
    public LevelCreator(String name, Sprite background, List<Velocity> velocities, int paddleS, int paddleW,
                        List<Block> blocks) {
        this.levelName = name;
        this.background = background;
        this.ballVel = velocities;
        this.paddleSpeed = paddleS;
        this.paddleWidth = paddleW;
        this.blocks = blocks;
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
        return this.ballVel;

    }

    /**
     * The function return the paddle speed in this level.
     *
     * @return the paddle speed in this level.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * The function return the paddle width in this level.
     *
     * @return the paddle width in this level.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * The function return the name of this level.
     *
     * @return the the level name.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * The function return the background of this level.
     *
     * @return a sprite with the background of the level.
     */
    public Sprite getBackground() {
        return this.background;

    }

    /**
     * The function return all blocks of his level.
     *
     * @return array of the Blocks that make up this level, each block contains
     * its size, color and location.
     */
    public List<Block> blocks() {
        return this.blocks;

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
