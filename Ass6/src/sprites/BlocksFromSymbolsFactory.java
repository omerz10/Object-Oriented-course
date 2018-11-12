package sprites;

import java.util.Map;
import java.util.TreeMap;

/**
 * BlocksFromSymbolsFactory.
 */
public class BlocksFromSymbolsFactory {
    private Map<String, Integer> spacerWidths;
    private Map<String, BlockCreator> blocksSymbols;

    /**
     * Constructor.
     */
    public BlocksFromSymbolsFactory() {
        this.spacerWidths = new TreeMap<String, Integer>();
        this.blocksSymbols = new TreeMap<String, BlockCreator>();
    }

    /**
     * @param s      symbol of block
     * @param blockC blockCreator of this block
     */
    public void addBlockCreator(String s, BlockCreator blockC) {
        this.blocksSymbols.put(s, blockC);
    }

    /**
     * Returns true if 's' is block symbol.
     *
     * @param s symbol block
     * @return symbol exist or not.
     */
    public boolean isBlockSymbol(String s) {
        return this.blocksSymbols.containsKey(s);
    }

    /**
     * Returns a block.
     *
     * @param s block symbol
     * @param x x value location
     * @param y y value location
     * @return Block
     */
    public Block getBlock(String s, int x, int y) {
        return this.blocksSymbols.get(s).create(x, y);
    }

    /**
     * @param s   ymbol spacer
     * @param num width of  spacer
     */
    public void addSpacer(String s, int num) {
        this.spacerWidths.put(s, num);
    }


    /**
     * Returns true if 's' is space symbol.
     *
     * @param s symbol spacer
     * @return symbol exist or not
     */
    public boolean isSpaceSymbol(String s) {
        return this.spacerWidths.containsKey(s);
    }


    /**
     * Returns width.
     *
     * @param s Spaces symbol
     * @return width of symbol
     */
    public int getSpaceWidth(String s) {
        return this.spacerWidths.get(s);
    }
}
