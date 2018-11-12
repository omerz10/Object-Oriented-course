package listeners;

import sprites.Ball;


/**
 * HitListener.
 *
 * @param <T> An object - Block/Paddle/etc
 */
public interface HitListenerG<T> {

    /**
     * This method is called whenever the beingHit object is hit.
     * The hitter parameter is the sprites.Ball that's doing the hitting.
     *
     * @param beingHit the block being hitted
     * @param hitter   a ball
     */
    void hitEvent(T beingHit, Ball hitter);
}
