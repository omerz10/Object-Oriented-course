package listeners;

import animations.Counter;
import game.GameLevel;
import sprites.Alien;
import sprites.AliensArmy;
import sprites.Ball;
import sprites.Block;

/**
 * AlienRemover.
 */
public class AlienRemover implements HitListener {
    private GameLevel game;
    private Counter aliens;
    private AliensArmy army;

    /**
     * Construct AlienRemover.
     *
     * @param game          a given game
     * @param aliens removed blocks
     * @param army a given army
     */
    public AlienRemover(GameLevel game, Counter aliens, AliensArmy army) {
        this.game = game;
        this.aliens = aliens;
        this.army = army;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            // Alien balls
            if (hitter.getSize() > 3) {
                hitter.removeFromGame(this.game);
            } else {
                beingHit.removeHitListener(this);
                beingHit.removeFromGame(this.game);
                aliens.decrease(1);
                hitter.removeFromGame(this.game);
                this.army.clearColumns((Alien) beingHit);
            }

        }
    }
}
