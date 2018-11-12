package listeners;

import game.GameLevel;
import sprites.Ball;
import sprites.Paddle;

/**
 * PaddleRemover.
 */
public class PaddleRemover implements HitListenerG<Paddle> {
    private GameLevel game;

    /**
     * Construct Paddle Remover.
     * @param g GameLevelAlien
     */
    public PaddleRemover(GameLevel g) {
        this.game = g;
    }


    @Override
    public void hitEvent(Paddle beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        beingHit.removeFromGame(this.game);
        beingHit.removeHitListener(this);
        this.game.resetGameLevel();
    }
}
