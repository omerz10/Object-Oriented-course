package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Block;

/**
 * ShieldRemover.
 */
public class ShieldRemover implements HitListener {
    private GameLevel game;
    /**
     * Construct ShieldRemover.
     *
     * @param game          a given game
     */
    public ShieldRemover(GameLevel game) {
        this.game = game;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            hitter.removeFromGame(this.game);
        }
    }
}
