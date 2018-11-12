package listeners;

import animations.Counter;
import animations.GameLevel;
import collisions.Ball;
import sprites.Block;

/**
 * Removes a ball from game when it reach to "death" region.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Construct BallRemover with game and remaining Balls.
     *
     * @param game           a given game
     * @param remainingBalls remaining balls in the game
     */
    public BallRemover(GameLevel game, Counter remainingBalls) {
        this.game = game;
        this.remainingBalls = remainingBalls;
    }

    /**
     * Moves ball when reach to death region Block.
     *
     * @param beingHit death region
     * @param hitter   a ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeHitListener(this);
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }

    /**
     * Returns remaining balls.
     *
     * @return remaining balls
     */
    public Counter getRemainingBalls() {
        return this.remainingBalls;
    }
}
