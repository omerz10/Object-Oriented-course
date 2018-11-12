package sprites;

import geometrics.Point;
import geometrics.Rectangle;

import java.awt.image.BufferedImage;
import java.util.Map;

/**
 * BlockCreation.
 */
public class BlockCreation implements BlockCreator {
    private double width;
    private double height;
    private java.awt.Color color;
    private int hitPoints;
    private java.awt.Color stroke;
    private Map<String, String> fill;
    private String fillF;
    private BufferedImage img;


    /**
     * @param sizes     height anf width
     * @param color     color
     * @param hitPoints number of hits
     * @param stroke    frame of block
     * @param fill      maping color for block
     * @param fillF     name of the color of fill
     * @param img       an image
     */
    public BlockCreation(Double[] sizes, java.awt.Color color, int hitPoints, java.awt.Color stroke,
                         Map<String, String> fill, String fillF, BufferedImage img) {
        this.width = sizes[0];
        this.height = sizes[1];
        this.color = color;
        this.hitPoints = hitPoints;
        this.stroke = stroke;
        this.fill = fill;
        this.fillF = fillF;
        this.img = img;
    }

    /**
     * Returns new block.
     *
     * @param xpos x value
     * @param ypos y value
     * @return new block.
     */
    public Block create(int xpos, int ypos) {
        if (this.color == null) {
            return new Block(new Rectangle(new Point((double) xpos,
                    (double) ypos), (double) this.width, (double) this.height), this.img, this.hitPoints, this.stroke,
                    this.fill, this.fillF);
        } else if (this.img == null) {
            return new Block(new Rectangle(new Point((double) xpos,
                    (double) ypos), (double) this.width, (double) this.height), this.color, this.hitPoints,
                    this.stroke, this.fill, this.fillF);
        }
        return null;
    }

}
