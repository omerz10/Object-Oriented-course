package sprites;

/**
 * BlockCreator.
 */
public interface BlockCreator {

    /**
     * Create a block at the specified location.
     *
     * @param xpos x value
     * @param ypos y value
     * @return a block
     */
    Block create(int xpos, int ypos);
}
