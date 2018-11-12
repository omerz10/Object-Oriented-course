/**
 * Omer Zucker.
 * 200876548
 */

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.Sleeper;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;
import java.util.List;

/**
 * Game comprise of GUI, Sprite collection and environment.
 */
public class Game {
    private GUI gui;
    private int guiWidth;
    private int guiHeight;
    private SpriteCollection sprites;
    private GameEnvironment environment;

    /**
     * Construct Game with environment (collidables), gui's size,  and sprites.
     */
    public Game() {
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.guiWidth = 900;
        this.guiHeight = 500;
        gui = new GUI("Game", this.guiWidth, this.guiHeight);
    }

    /**
     * Add collidable.
     *
     * @param c a specific Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Add a Sprite.
     *
     * @param s a specific Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and Ball (and Paddle) and add them to the game.
     */
    public void initialize() {
        // creates up and down layouts (blocks)
        for (int i = 0; i < 2; i++) {
            Point point1 = new Point(i * 20, i * (this.guiHeight - 20));
            Rectangle layOutRect1 = new Rectangle(point1, this.guiWidth - (i * 40), 20);
            Block upAndDown = new Block(layOutRect1, Color.lightGray);
            upAndDown.setNumberOfHits(0);
            upAndDown.addToGame(this);
        }
        // creates left and right layouts (blocks)
        for (int j = 0; j < 2; j++) {
            Point point2 = new Point(j * (this.guiWidth - 20), 20);
            Rectangle layOutRect2 = new Rectangle(point2, 20, this.guiHeight - 20);
            Block leftAndRight = new Block(layOutRect2, Color.lightGray);
            leftAndRight.setNumberOfHits(0);
            leftAndRight.addToGame(this);
        }
        Random rand = new Random();
        List<Block> blocks = new ArrayList<Block>();
        // create the blocks
        blocks = createBlocks(rand);
        // Create paddle
        int paddleWidth = 100;
        int paddleHeight = 20;
        biuoop.KeyboardSensor newKeyboard = gui.getKeyboardSensor();
        Point upperLeftP = new Point((this.guiWidth / 2) - (paddleWidth / 2), this.guiHeight - paddleHeight - 20);
        int paddleRightBound = this.guiWidth - 20;
        Rectangle paddleRect = new Rectangle(upperLeftP, paddleWidth, paddleHeight);
        Paddle paddle = new Paddle(upperLeftP, paddleWidth, paddleHeight, paddleRightBound,
                newKeyboard, Color.YELLOW);
        paddle.addToGame(this);


        // Create 2 balls
        for (int n = 0; n < 2; n++) {
            Ball ball = new Ball(this.guiWidth / 2, this.guiHeight - paddleHeight - 20, 5, Color.WHITE);
            ball.setBoundary(0, 0, 900, 500);
            Velocity velocity = Velocity.fromAngleAndSpeed(rand.nextInt(120) + 300,
                    rand.nextInt(6) + 2);
            ball.setVelocity(velocity);
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
    }

    /**
     * Creates and returns ArrayList of block.
     *
     * @param rand       Random func
     * @return list of blocks
     */
    public List<Block> createBlocks(Random rand) {
        List<Block> blocks = new ArrayList<Block>();
        // creates "lines" of block
        for (int i = 0; i < 6; i++) {
            // set random color for each line
            float r = rand.nextFloat();
            float g = rand.nextFloat();
            float b = rand.nextFloat();
            Color lineColor = Color.getHSBColor(r, g, b);
            // creates 12 blocks in first line and than removes one block for each next line
            for (int j = 0; j < 12 - i; j++) {
                // creates blocks with a spaces between them
                Point blockPoint = new Point(this.guiWidth - (80 + (60 * j)), 100 + (i * 20));
                Rectangle rect = new Rectangle(blockPoint, 60, 20);
                //give each line a random color
                Block newBlock = new Block(rect, lineColor);
                if (i == 0) {
                    newBlock.setNumberOfHits(2);
                } else {
                    newBlock.setNumberOfHits(1);
                }
                newBlock.addToGame(this);
                blocks.add(newBlock);
            }
        }
        return blocks;
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void run() {
        Sleeper sleeper = new Sleeper();
        int framesPerSecond = 60;
        int millisecondsPerFrame = 1000 / framesPerSecond;
        while (true) {
            long startTime = System.currentTimeMillis(); // timing
            DrawSurface d = gui.getDrawSurface();
            d.setColor(Color.blue);
            d.fillRectangle(0, 0, this.guiWidth, this.guiHeight);
            this.sprites.drawAllOn(d);
            gui.show(d);
            this.sprites.notifyAllTimePassed();
            // timing
            long usedTime = System.currentTimeMillis() - startTime;
            long milliSecondLeftToSleep = millisecondsPerFrame - usedTime;
            if (milliSecondLeftToSleep > 0) {
                sleeper.sleepFor(milliSecondLeftToSleep);
            }
        }
    }
}

