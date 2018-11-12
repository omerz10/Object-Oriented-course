package listeners;

import sprites.Ball;
import sprites.Block;

/**
 * HitListener.
 *
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     *
     * @param beingHit the block being hitted
     * @param hitter   a ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
