package listeners;

import collisions.Ball;
import sprites.Block;

/**
 * listeners.HitListener.
 *
 * Omer Zucker
 * 20087658
 */
public interface HitListener {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the collisions.Ball that's doing the hitting.
     *
     * @param beingHit the block being hitted
     * @param hitter   a ball
     */
    void hitEvent(Block beingHit, Ball hitter);
}
