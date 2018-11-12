package listeners;

import sprites.Ball;
import animations.Counter;
import sprites.Block;

/**
 * listeners.ScoreTrackingListener.
 * <p>
 * Omer Zucker
 * 200876548
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Construct listeners.ScoreTrackingListener with scoreCounter.
     *
     * @param scoreCounter score of player in the game
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * add scores to game according to different hits.
     *
     * @param beingHit a block was benn hitted
     * @param hitter   a ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            this.currentScore.increase(10);
        }
        this.currentScore.increase(5);
    }
}
