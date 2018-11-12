package sprites; /**
 * Omer Zucker
 * 200876548
 */

import game.GameLevel;
import listeners.HitListener;
import listeners.HitNotifier;
import collisions.Collidable;
import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import biuoop.DrawSurface;

import javax.imageio.ImageIO;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * sprites.Block comprise of shape (rect) and color.
 */
public class Block implements Collidable, Sprite, HitNotifier {
    private Rectangle rect;
    private Color color;
    private int numberOfHits;
    private List<HitListener> hitListeners;
    private BufferedImage img;
    private java.awt.Color frame;
    private String fillF;
    private Map<String, String> fill;


    /**
     * Constuct Alien Block.
     *
     * @param x upper left point x value
     * @param y upper left point y value
     */
    public Block(double x, double y) {
        this.rect = new Rectangle(new Point(x, y), 40, 30);
        this.numberOfHits = 1;
        this.hitListeners = new ArrayList<HitListener>();
        this.frame = null;
        this.fillF = null;
        this.fill = null;
        try {
            this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream("enemy.png"));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Construct a block given rect (type of shape) and color.
     *
     * @param rect  shape of block
     * @param color color of block
     */
    public Block(Rectangle rect, Color color) {
        this.rect = rect;
        this.color = color;
        this.numberOfHits = 0;
        this.hitListeners = new ArrayList<HitListener>();
        this.frame = color;
        this.fill = new TreeMap<String, String>();
        this.fill.put("fill", "color(RGB(0,0,0))");
        this.fillF = "string";
    }

    /**
     * Construct a block given rect (type of shape) and color.
     *
     * @param rect         shape of block
     * @param color        color of block
     * @param numberOfHits number of hiths of this block
     */
    public Block(Rectangle rect, Color color, int numberOfHits) {
        this.rect = rect;
        this.color = color;
        this.hitListeners = new ArrayList<HitListener>();
        this.numberOfHits = numberOfHits;
        this.frame = java.awt.Color.BLACK;
        this.fill = new TreeMap<String, String>();
        this.fill.put("fill", "color(RGB(0,0,0))");
        this.fillF = "string";
    }

    /**
     * Construct a block.
     *
     * @param newRect shape of block
     * @param hits    number of hits
     * @param img     an image
     * @param frame   stroke
     * @param fill    fill with some color
     * @param current a string
     **/
    public Block(Rectangle newRect, BufferedImage img, int hits, java.awt.Color frame, Map<String, String> fill,
                 String current) {
        this.rect = newRect;
        this.color = null;
        this.numberOfHits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.img = img;
        this.frame = frame;
        this.fill = fill;
        this.fillF = current;
    }

    /**
     * Construct a block.
     *
     * @param newRect shape of block
     * @param hits    number of hits
     * @param color   color
     * @param frame   stroke
     * @param fill    fill with some color
     * @param string  a string
     **/
    public Block(Rectangle newRect, java.awt.Color color, int hits, java.awt.Color frame,
                 Map<String, String> fill, String string) {
        this.rect = newRect;
        this.numberOfHits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.img = null;
        this.frame = frame;
        this.color = color;
        this.fill = fill;
        this.fillF = string;
    }

    /**
     * Construct Block with point, widht, height and color.
     *
     * @param upperLeftPoint point
     * @param width          width
     * @param height         height
     * @param color          color
     */
    public Block(Point upperLeftPoint, int width, int height, Color color) {
        this.rect = new Rectangle(upperLeftPoint, width, height);
        this.numberOfHits = -1;
        this.hitListeners = new ArrayList<HitListener>();
        this.img = null;
        this.frame = null;
        this.color = color;
        this.fill = null;
        this.fillF = null;
    }

    /**
     * Construct Block with point, widht, height and color.
     *
     * @param upperLeftPoint point
     * @param width          width
     * @param height         height
     * @param color          color
     * @param hits           num of hits
     */
    public Block(Point upperLeftPoint, int width, int height, Color color, int hits) {
        this.rect = new Rectangle(upperLeftPoint, width, height);
        this.numberOfHits = hits;
        this.hitListeners = new ArrayList<HitListener>();
        this.img = null;
        this.frame = null;
        this.color = color;
        this.fill = null;
        this.fillF = null;
    }


    /**
     * @return color of the block.
     */
    public java.awt.Color getColor() {
        return this.color;
    }

    /**
     * Return the shape of rectangle.
     *
     * @return rect (shape of rectangle)
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Returns number of hit in this block.
     *
     * @return numer of hits
     */
    public int getHitPoints() {
        return this.numberOfHits;
    }

    /**
     * Return the new velocity right after collision.
     *
     * @param collisionPoint  a specific point that the ball is going to collide with
     * @param currentVelocity velocity of the ball before the collision
     * @param hitter          a ball
     * @return new collisions.Velocity
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double newDx = currentVelocity.getX();
        double newDy = currentVelocity.getY();

        // change number of hits on a specific block
        if (this.numberOfHits > 0) {
            this.numberOfHits -= 1;
        }
        // collision with leftFrame or rightFrame of block
        if ((collisionPoint.getX() == this.rect.getUpperLeft().getX())
                || ((collisionPoint.getX() == this.rect.getUpperLeft().getX() + this.rect.getWidth()))) {
            if ((collisionPoint.getY() >= this.rect.getUpperLeft().getY())
                    && (collisionPoint.getY() <= this.rect.getUpperLeft().getY() + this.rect.getHeight())) {
                newDx *= -1;
            }
        }
        // collision with upperFrame or lowerFrame of block
        if ((collisionPoint.getY() == this.rect.getUpperLeft().getY())
                || ((collisionPoint.getY() == this.rect.getUpperLeft().getY() + this.rect.getHeight()))) {
            if ((collisionPoint.getX() >= this.rect.getUpperLeft().getX())
                    && (collisionPoint.getX() <= (this.rect.getUpperLeft().getX() + this.rect.getWidth()))) {
                newDy *= -1;
            }
        }

        Velocity newVelocity = new Velocity(newDx, newDy);
        this.notifyHit(hitter);
        if (this.numberOfHits != 0) {
            this.setFill();
        }
        return newVelocity;
    }

    /**
     * draw the block on the given DrawSurface.
     *
     * @param d is a given DrawSurface.
     */
    public void drawOn(DrawSurface d) {
        if (this.color != null) {
            d.setColor(this.color);
            d.fillRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
         // draw image
        } else {
            d.drawImage((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(), this.img);
        }
        // draw block
        if (this.frame != null) {
            d.setColor(this.frame);
            d.drawRectangle((int) rect.getUpperLeft().getX(), (int) rect.getUpperLeft().getY(),
                    (int) rect.getWidth(), (int) rect.getHeight());
        }
    }

    /**
     * TimePassed.
     *
     * @param dt parameter
     */
    public void timePassed(double dt) {
    }

    /**
     * Add this block to game.
     *
     * @param g a givven game
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Add this block to game.
     *
     * @param game a givven game
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
        game.removeCollidable(this);
    }


    /**
     * Notify when ball hit the block.
     *
     * @param hitter a ball
     */
    public void notifyHit(Ball hitter) {
        // Make a copy of the hitListeners before iterating over them.
        List<HitListener> listeners = new ArrayList<HitListener>(this.hitListeners);
        // Notify all listeners about a hit event:
        for (HitListener hl : listeners) {
            hl.hitEvent(this, hitter);
        }
    }

    /**
     * Add hl to list of listeners to hit events.
     *
     * @param hl a listeners.HitListener
     */
    public void addHitListener(HitListener hl) {
        this.hitListeners.add(hl);
    }

    /**
     * Remove hl from the list of listeners to hit events.
     *
     * @param hl a listeners.HitListener
     */
    public void removeHitListener(HitListener hl) {
        this.hitListeners.remove(hl);
    }


    /**
     * Return the width of the Block.
     *
     * @return width of the Block
     */
    public double getWidth() {
        return this.rect.getWidth();
    }

    /**
     * Set fill according to given different fills.
     */
    public void setFill() {
        if (this.fill.containsKey("fill-" + Integer.toString(this.numberOfHits))) {
            this.fillF = this.fill.get("fill-" + this.numberOfHits);
            if (this.fillF.contains("image")) {
                this.img = null;
                try {
                    String s = this.fillF.replace("image(", "").replace(")", "");
                    this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s));
                } catch (IOException e) {
                    System.out.print("Error while reading image!");
                }
                this.color = null;
            } else {
                if (this.fillF.contains("RGB")) {
                    String sp = (this.fillF.replace("color(RGB(", ""));
                    sp = sp.replace(")", "");
                    String[] args = sp.split(",");
                    this.color = new Color(Integer.parseInt(args[0]),
                            Integer.parseInt(args[1]),
                            Integer.parseInt(args[2]));
                } else {
                    try {
                        Field field = Class.forName("java.awt.Color").getField(
                                this.fillF.replace("color(", "").replace(")", ""));
                        this.color = (Color) field.get(null);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Unknown color");
                    }
                }
                this.img = null;
            }
        } else if (!this.fillF.equals(this.fill.get("fill"))) {
            this.fillF = this.fill.get("fill");
            if (this.fillF.contains("image")) {
                this.img = null;
                try {
                    String s1 = this.fillF.replace("image(", "").replace(")", "");
                    this.img = ImageIO.read(ClassLoader.getSystemClassLoader().getResourceAsStream(s1));
                } catch (IOException e) {
                    System.out.print("Error while reading image!");
                }
                this.color = null;
            } else {
                if (this.fillF.contains("RGB")) {
                    String sp = this.fillF.replace("color(RGB(", "");
                    sp = sp.replace(")", "");
                    String[] numbers = sp.split(",");
                    this.color = new Color(Integer.parseInt(numbers[0]),
                            Integer.parseInt(numbers[1]),
                            Integer.parseInt(numbers[2]));
                } else {
                    try {
                        Field field = Class.forName("java.awt.Color").getField(
                                this.fillF.replace("color(", "").replace(")", ""));
                        this.color = (Color) field.get(null);
                    } catch (Exception e) {
                        throw new IllegalArgumentException("Invalid color!");
                    }
                }
                this.img = null;
            }
        }

    }

    /**
     * Get x value.
     * @return x value
     */
    public double getX() {
        return this.rect.getUpperLeft().getX();
    }

    /**
     * Get y value.
     * @return y value
     */
    public double getY() {
        return this.rect.getUpperLeft().getY();
    }

    /**
     * Set new Rectangle.
     * @param r a Rectangle.
     */
    public void setRect(Rectangle r) {
        this.rect = new Rectangle(r.getUpperLeft(), r.getWidth(), r.getHeight());
    }
}
