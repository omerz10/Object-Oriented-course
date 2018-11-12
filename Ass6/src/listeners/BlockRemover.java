package listeners;

import game.GameLevel;
import sprites.Ball;
import animations.Counter;
import sprites.Block;

/**
 * BlockRemover is in charge of removing blocks from the game, as well as keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Construct Blockremover with a game and given removed blocks.
     *
     * @param game          a given game
     * @param removedBlocks removed blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Blocks that are hit and reach 0 hit-points should be removed from the game.
     * Remember to remove this listener from the block that is being removed from the game.
     *
     * @param beingHit the block being hitted
     * @param hitter   a ball
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        if (beingHit.getHitPoints() == 0) {
            beingHit.removeHitListener(this);
            beingHit.removeFromGame(this.game);
            remainingBlocks.decrease(1);
        }
    }

    /**
     * Returns remaining blocks.
     * @return remaining blocks
     */
    public Counter getRemainingBlocks() {
        return this.remainingBlocks;
    }

}
