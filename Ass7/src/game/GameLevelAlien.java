package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.Counter;
import animations.CountdownAnimation;
import animations.PauseScreen;
import animations.KeyPressStoppableAnimation;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import listeners.ShieldRemover;

import listeners.PaddleRemover;
import listeners.AlienRemover;
import listeners.ScoreTrackingListener;
import listeners.BallRemover;

import sprites.AliensArmy;
import sprites.Shield;
import sprites.Block;
import sprites.Paddle;
import sprites.Ball;
import sprites.BackgroundSprite;
import sprites.LevelName;
import sprites.LivesIndicator;
import sprites.ScoreIndicator;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * GameLevelAlien.
 */
public class GameLevelAlien extends GameLevel {
    public static final int PADDLE_WIDTH = 100;
    public static final int PADDLE_HEIGHT = 20;
    private BackgroundSprite background;
    private Paddle paddle;
    private Counter numOfAliens;
    private Counter numOfBalls = new Counter();
    private Counter scores = new Counter();
    private Counter lives = new Counter();
    private int levelNumber;
    private int width;
    private int height;
    private double shootingPaddle;
    private double shootingAlien;
    private boolean running;
    private AliensArmy army;
    private List<Shield> shields;
    private List<Ball> ballsOfPlayer;
    private List<Ball> ballsOfArmy;

    /**
     * Construct GameLevelAlien.
     *
     * @param levelN battle number
     * @param ar     Animation Runner
     * @param ks     Keyboard Sensor
     * @param scores scores
     * @param lives  lives
     * @param width  width
     * @param height height
     */
    public GameLevelAlien(int levelN, AnimationRunner ar, KeyboardSensor ks, Counter scores, Counter lives, int width,
                          int height) {
        super(ar, ks, scores, lives, width, height);
        this.width = width;
        this.height = height;
        this.height = height;
        this.levelNumber = levelN;
        this.running = false;
        this.scores = scores;
        this.lives = lives;
        this.background = new BackgroundSprite(new Point(0, 0), width, height, Color.black);
        this.numOfAliens = new Counter();
        this.numOfAliens.increase(50);
        this.shootingPaddle = 0.35;
        this.shootingAlien = 0.5;
        this.army = new AliensArmy(0, width, 40 + (10 * this.levelNumber));
        this.shields = new ArrayList<Shield>();
        this.ballsOfPlayer = new ArrayList<Ball>();
        this.ballsOfArmy = new ArrayList<Ball>();

    }

    @Override
    public void doOneFrame(DrawSurface d, double dt) {
        this.shootingPaddle -= dt;
        this.shootingAlien -= dt;
        super.getSprites().notifyAllTimePassed(dt);
        if (this.numOfAliens.getValue() == 0) {
            this.running = false;
        } else {
            this.army.timePassed(dt);
            super.getSprites().drawAllOn(d);
            if (super.getKeyboardS().isPressed("p")) {
                Animation pauseScreen = new PauseScreen();
                super.getRunner().run(new KeyPressStoppableAnimation(super.getKeyboardS(), KeyboardSensor.SPACE_KEY,
                        pauseScreen));
            }
            if (super.getKeyboardS().isPressed(KeyboardSensor.SPACE_KEY)) {
                if (this.shootingPaddle < 0) {
                    createBallsOnTopOfPaddle();
                }
            }
            if (this.army.getLowestValueY() > 450) {
                running = false;
                this.removeSprite(paddle);
                this.removeCollidable(paddle);
                this.resetGameLevel();
            }
            if (this.shootingAlien < 0) {
                this.shotAliensBalls();
            }
        }

    }

    @Override
    public boolean shouldStop() {
        return !this.running;
    }


    /**
     * Return number of aliens.
     *
     * @return number of aliens
     */
    public int getAliensNum() {
        return this.numOfAliens.getValue();
    }

    /**
     *
     */
    public void playOneTurn() {
        // create new level properties
        createPaddle();
        this.createBallsOnTopOfPaddle();

        // run
        super.getRunner().run(new CountdownAnimation(2, 3, super.getSprites()));
        this.running = true;
        super.getRunner().run(this);
        if (this.numOfAliens.getValue() != 0) {
            this.lives.decrease(1);
        }
    }

    @Override
    public void initialize() {
        this.background.addToGame(super.getSprites());
        createDeathRegion();

        // board on top of window (scores, lives, name)
        ScoreTrackingListener scoreListener = new ScoreTrackingListener(this.scores);
        ScoreIndicator scoreInd = new ScoreIndicator(new Rectangle(new Point(0, 0), this.width, 20),
                this.scores);
        scoreInd.addToGame(this.getSprites());
        LivesIndicator livesInd = new LivesIndicator(new Rectangle(new Point(0, 0), this.width, 20),
                this.lives);
        livesInd.addToGame(this.getSprites());
        LevelName levelN = new LevelName(new Rectangle(new Point(0, 0), this.width, 20),
                "Battle No. " + Integer.toString(this.levelNumber));
        levelN.addToGame(this.getSprites());

        // set Removers
        AlienRemover alienR = new AlienRemover(this, this.numOfAliens, army);
        BallRemover ballR = new BallRemover(this, this.numOfBalls);
        ShieldRemover shieldR = new ShieldRemover(this);
        this.army.createArmy();
        this.army.addToGame(this, alienR, scoreListener);

        // shields
        for (int i = 0; i < 3; i++) {
            Shield shield = new Shield(50 + i * 250, 460);
            shield.createShields(this, shieldR, ballR);
            shields.add(shield);
        }
    }

    @Override
    public void resetGameLevel() {
        for (Ball b : this.ballsOfArmy) {
            b.removeFromGame(this);
        }
        for (Ball b : this.ballsOfPlayer) {
            b.removeFromGame(this);
        }
        this.army.resetPosition();
        this.running = false;
        this.ballsOfPlayer.clear();
        this.ballsOfArmy.clear();
    }

    /**
     *
     */
    public void createPaddle() {
        int paddleRightBound = super.getWidth() - 20;
        int paddleSpeed = 500;
        Point upperLeftP = new Point((super.getWidth() / 2) - (PADDLE_WIDTH / 2),
                super.getHeight() - PADDLE_HEIGHT - 20);
        this.paddle = new Paddle(upperLeftP, PADDLE_WIDTH, 20, paddleRightBound, super.getKeyboardS(),
                Color.YELLOW, paddleSpeed);
        PaddleRemover paddleR = new PaddleRemover(this);
        this.paddle.addHitListener(paddleR);
        this.paddle.addToGame(this);
    }

    /**
     *
     */
    public void createBallsOnTopOfPaddle() {
        this.shootingPaddle = 0.35;
        int startX = (int) paddle.getCollisionRectangle().getUpperLeft().getX() + (PADDLE_WIDTH / 2);
        Ball ball = new Ball(startX, super.getHeight() - PADDLE_HEIGHT - 25, 3, Color.WHITE);
        Velocity v = Velocity.fromAngleAndSpeed(0, 600);
        ball.setVelocity(v);
        ball.addToGame(this);
        ball.setGameEnvironment(super.getEnvironment());
        this.ballsOfPlayer.add(ball);
        this.numOfBalls.increase(1);
    }

    /**
     * Return a shoted Ball from Alien.
     *
     * @return a Ball
     */
    public Ball createShotBallFromAlien() {
        this.shootingAlien = 0.5;
        Velocity vel = Velocity.fromAngleAndSpeed(180, 80);
        Ball ball = new Ball(this.army.chooseRandomAlien(), 4, Color.RED);
        ball.setVelocity(vel);
        this.ballsOfArmy.add(ball);
        return ball;
    }

    /**
     * Shot ball from random alien.
     */
    public void shotAliensBalls() {
        Ball shotedBall = createShotBallFromAlien();
        shotedBall.setGameEnvironment(this.getEnvironment());
        shotedBall.addToGame(this);
        this.ballsOfArmy.add(shotedBall);
    }

    /**
     * Create death region (lower layout).
     */
    public void createDeathRegion() {
        Block deathBlock = new Block(new Rectangle(new Point(0, 0), this.width, 20),
                null);
        deathBlock.addToGame(this);
        BallRemover ballR = new BallRemover(this, this.numOfBalls);
        deathBlock.addHitListener(ballR);
    }


}
