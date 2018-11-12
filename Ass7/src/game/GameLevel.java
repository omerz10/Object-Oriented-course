package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.Counter;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import collisions.Collidable;
import sprites.SpriteCollection;
import sprites.Sprite;

/**
 * GameLevel.
 */
public abstract class GameLevel implements Animation {
    private int width;
    private int height;
    private AnimationRunner runner;
    private KeyboardSensor keyboardS;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private Counter lives;
    private Counter scores;

    /**
     * Construct abstract GameLevel.
     *
     * @param ar     Animation Runner
     * @param ks     Keyboard Sensor
     * @param scores scores
     * @param lives  lives
     * @param width  width
     * @param height height
     */
    public GameLevel(AnimationRunner ar, KeyboardSensor ks, Counter scores, Counter lives, int width,
                     int height) {
        this.runner = ar;
        this.keyboardS = ks;
        this.width = width;
        this.height = height;
        this.scores = scores;
        this.lives = lives;
        this.environment = new GameEnvironment();
        this.sprites = new SpriteCollection();
    }

    @Override
    public abstract void doOneFrame(DrawSurface d, double dt);

    @Override
    public abstract boolean shouldStop();

    /**
     * Initializiing GameLevel.
     */
    public abstract void initialize();

    /**
     * Clear sprites and collidabels.
     * Reposition army of Aliens.
     */
    public abstract void resetGameLevel();

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
     * Return width.
     *
     * @return width
     */
    public int getWidth() {
        return this.width;
    }

    /**
     * Return height.
     *
     * @return height
     */
    public int getHeight() {
        return this.height;
    }

    /**
     * Return SpriteCollection of all sprites in the game.
     *
     * @return sprites
     */
    public SpriteCollection getSprites() {
        return this.sprites;
    }

    /**
     * Get Keyboard Sensor.
     *
     * @return Keyboard Sensor
     */
    public KeyboardSensor getKeyboardS() {
        return this.keyboardS;
    }

    /**
     * Get AnimationRunner.
     *
     * @return AnimationRunner
     */
    public AnimationRunner getRunner() {
        return this.runner;
    }

    /**
     * Get GameEnvironment.
     *
     * @return GameEnvironment
     */
    public GameEnvironment getEnvironment() {
        return this.environment;
    }
}
