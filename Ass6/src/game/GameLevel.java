package game; /**
 * Omer Zucker.
 * 200876548
 */


import animations.Animation;
import animations.AnimationRunner;
import animations.CountdownAnimation;
import animations.KeyPressStoppableAnimation;
import animations.PauseScreen;

import animations.Counter;
import levels.LevelInformation;
import listeners.BlockRemover;

import sprites.Ball;
import listeners.BallRemover;
import collisions.Collidable;
import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import listeners.ScoreTrackingListener;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import sprites.Paddle;
import sprites.ScoreIndicator;
import sprites.Sprite;
import sprites.SpriteCollection;
import sprites.LivesIndicator;
import sprites.LevelName;
import sprites.Block;

import java.awt.Color;
import java.util.List;


/**
 * game.GameLevel comprise of GUI, sprites.Sprite collection and environment.
 */
public class GameLevel implements Animation {
    private static final int WIDTH = 800;
    private static final int HEIGHT = 600;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter remainingBlocks;
    private Counter remainingBalls;
    private Paddle paddle;
    private Counter score;
    private Counter lives;
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private LevelInformation levelInfo;

    /**
     * Construct Game level.
     *
     * @param levelInfo      level information
     * @param keyboardSensor ks
     * @param runner         animation runner
     * @param scores         scores
     * @param lives          lives
     */
    public GameLevel(LevelInformation levelInfo, KeyboardSensor keyboardSensor, AnimationRunner runner,
                     Counter scores, Counter lives) {
        this.levelInfo = levelInfo;
        this.keyboard = keyboardSensor;
        this.sprites = new SpriteCollection();
        this.environment = new GameEnvironment();
        this.remainingBlocks = new Counter();
        this.remainingBlocks.increase(this.levelInfo.numberOfBlocksToRemove());
        this.remainingBalls = new Counter();
        this.score = scores;
        this.lives = lives;
        this.running = false;
        this.runner = runner;
    }

    /**
     * Add collidable.
     *
     * @param c a specific collisions.Collidable
     */
    public void addCollidable(Collidable c) {
        this.environment.addCollidable(c);
    }

    /**
     * Reomve collisions.Collidable.
     *
     * @param c a specific collisions.Collidable
     */
    public void removeCollidable(Collidable c) {
        this.environment.removeCollidable(c);
    }

    /**
     * Add a sprites.Sprite.
     *
     * @param s a specific sprites.Sprite
     */
    public void addSprite(Sprite s) {
        this.sprites.addSprite(s);
    }

    /**
     * Remove a sprites.Sprite.
     *
     * @param s a specific sprites.Sprite
     */
    public void removeSprite(Sprite s) {
        this.sprites.removeSprite(s);
    }

    /**
     * Initialize a new game: create the Blocks and sprites.Ball (and sprites.Paddle) and add them to the game.
     */
    public void initialize() {
        this.createDeathRegion();
        this.addSprite(this.levelInfo.getBackground());
        // create window - lives, score and level's name
        ScoreIndicator scoreInd = new ScoreIndicator(new Rectangle(new Point(0, 0), WIDTH, 20),
                this.score);
        scoreInd.addToGame(this.sprites);
        LivesIndicator livesInd = new LivesIndicator(new Rectangle(new Point(0, 0), WIDTH, 20),
                this.lives);
        livesInd.addToGame(this.sprites);
        LevelName levelN = new LevelName(new Rectangle(new Point(0, 0), WIDTH, 20),
                this.levelInfo.levelName());
        levelN.addToGame(this.sprites);
        // creates upper layout (blocks)
        Block upAndDown = new Block(new Rectangle(new Point(0, 20), WIDTH, 20), Color.lightGray,
                0);
        upAndDown.addToGame(this);
        // creates left and right layouts (blocks)
        for (int j = 0; j < 2; j++) {
            Block leftAndRight = new Block(new Rectangle(new Point(j * (WIDTH - 20), 40), 20,
                    HEIGHT - 20), Color.lightGray, 0);
            leftAndRight.addToGame(this);
        }
        this.createListenresBlocks();
    }

    /**
     * Run the game -- start the animation loop.
     */
    public void playOneTurn() {
        this.createBallsOnTopOfPaddle();
        this.runner.run(new CountdownAnimation(2, 3, this.sprites));
        this.running = true;
        this.runner.run(this);
        this.paddle.removeFromGame(this);
    }

    /**
     * Creates one Frame.
     *
     * @param d a given drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.levelInfo.getBackground().drawOn(d);
        this.sprites.notifyAllTimePassed(dt);
        this.sprites.drawAllOn(d);
        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            this.runner.run(new KeyPressStoppableAnimation(this.keyboard, KeyboardSensor.SPACE_KEY, pauseScreen));
        }
        if (this.remainingBlocks.getValue() == 0) {
            this.score.increase(100);
            this.running = false;
        }
        if (this.remainingBalls.getValue() == 0) {
            this.lives.decrease(1);
            this.running = false;
        }
    }

    /**
     * Returns false if the game is running and true if it does not.
     *
     * @return true or false
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Runs one turn.
     */
    public void run() {
        playOneTurn();
    }

    /**
     * Create the paddle and the balls in each level.
     */
    public void createBallsOnTopOfPaddle() {
        // Create paddle
        int paddleWidth = this.levelInfo.paddleWidth();
        int paddleHeight = 20;
        Point upperLeftP = new Point((WIDTH / 2) - (paddleWidth / 2), HEIGHT - paddleHeight - 20);
        int paddleRightBound = WIDTH - 20;
        this.paddle = new Paddle(upperLeftP, paddleWidth, paddleHeight, paddleRightBound, this.keyboard, Color.YELLOW,
                this.levelInfo.paddleSpeed());
        this.paddle.addToGame(this);
        // Create balls
        List<Velocity> velList = this.levelInfo.initialBallVelocities();
        for (int n = 0; n < this.levelInfo.numberOfBalls(); n++) {
            Ball ball = new Ball(WIDTH / 2, HEIGHT - paddleHeight - 30, 5, Color.WHITE);
            ball.setVelocity(velList.get(n));
            ball.addToGame(this);
            ball.setGameEnvironment(this.environment);
        }
        this.remainingBalls.increase(this.levelInfo.numberOfBalls());
    }

    /**
     * Create death region (lower layout).
     */
    public void createDeathRegion() {
        Block deathBlock = new Block(new Rectangle(new Point(20, HEIGHT - 30), WIDTH - 40, 20),
                null);
        deathBlock.addToGame(this);
        BallRemover ballR = new BallRemover(this, this.remainingBalls);
        deathBlock.addHitListener(ballR);
    }

    /**
     * Create each block in level as a listener.
     *
     */
    public void createListenresBlocks() {
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.score);
        BlockRemover blockR = new BlockRemover(this, this.remainingBlocks);
        for (int i = 0; i < this.levelInfo.blocks().size(); i++) {
            Block newBlock = this.levelInfo.blocks().get(i);
            newBlock.addHitListener(blockR);
            newBlock.addHitListener(scoreListener);
            newBlock.addToGame(this);
        }
    }

    /**
     * Returns lives value.
     *
     * @return lives value
     */
    public int getLivesValue() {
        return this.lives.getValue();
    }

    /**
     * Returns number of blocks.
     *
     * @return number of blocks
     */
    public int getNumOfBlocks() {
        return this.remainingBlocks.getValue();
    }
}

