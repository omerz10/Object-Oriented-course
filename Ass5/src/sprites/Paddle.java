package sprites; /**
 * Omer Zucker.
 * 200876548
 */

import animations.GameLevel;
import collisions.Ball;
import collisions.Collidable;
import collisions.Velocity;
import geometrics.Point;
import geometrics.Rectangle;
import biuoop.DrawSurface;
import biuoop.KeyboardSensor;

import java.awt.Color;

/**
 * sprites.Paddle comprise of upperLeft (geometrics.Point), width, height, right bound, color, ball velocity
 * move sprites.Paddle (sprites.Block) and keyboard (keboardSensor).
 */

public class Paddle implements Sprite, Collidable {
    private biuoop.KeyboardSensor keyboard;
    private Block movePaddle;
    private Point paddleUpperLeft;
    private int paddleWidth;
    private int paddleHeight;
    private int paddleRightBound;
    private int paddleSpeed;

    /**
     * Construct a sprites.Paddle from upperLeft geometrics.Point, width, height, right bound,
     * color and keyboard (keyboard sensor).
     *
     * @param paddleUpperLeft  geometrics.Point of the upper left corner ofthe paddle
     * @param paddleWidth      width of the paddle
     * @param paddleHeight     height of the paddle
     * @param paddleRightBound width of GUI's screen minus the right layout block
     * @param keyboard         keyboard sensor
     * @param color            color of paddle
     * @param paddleSpeed      speed of paddle
     */
    public Paddle(Point paddleUpperLeft, int paddleWidth, int paddleHeight, int paddleRightBound,
                  KeyboardSensor keyboard, Color color, int paddleSpeed) {
        this.paddleUpperLeft = paddleUpperLeft;
        this.paddleWidth = paddleWidth;
        this.paddleHeight = paddleHeight;
        this.keyboard = keyboard;
        this.movePaddle = new Block(paddleUpperLeft, paddleWidth, paddleHeight, color);
        this.paddleRightBound = paddleRightBound;
        this.paddleSpeed = paddleSpeed;
    }


    /**
     * Move the ball to left side until it touches left layout block.
     */
    public void moveLeft() {
        this.paddleUpperLeft = new Point(this.paddleUpperLeft.getX() - this.paddleSpeed,
                this.paddleUpperLeft.getY());
        if (this.paddleUpperLeft.getX() <= 20) {
            this.paddleUpperLeft = new Point(20, this.paddleUpperLeft.getY());
        }
        this.movePaddle = new Block(this.paddleUpperLeft, this.paddleWidth, this.paddleHeight, Color.YELLOW);

    }

    /**
     * Move the ball to right side until it touches right layout block.
     */
    public void moveRight() {
        this.paddleUpperLeft = new Point(this.paddleUpperLeft.getX() + this.paddleSpeed,
                this.paddleUpperLeft.getY());
        if (this.paddleUpperLeft.getX() + this.paddleWidth >= this.paddleRightBound) {
            this.paddleUpperLeft = new Point(this.paddleRightBound - this.paddleWidth, this.paddleUpperLeft.getY());
        }
        this.movePaddle = new Block(this.paddleUpperLeft, this.paddleWidth, this.paddleHeight, Color.YELLOW);
    }

    /**
     * Moving the paddle until time passed.
     */
    public void timePassed() {
        if (keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {
            moveRight();
        }
    }

    /**
     * Draw on the paddle.
     *
     * @param d DrawSurface
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.movePaddle.getColor());
        d.fillRectangle((int) this.paddleUpperLeft.getX(), (int) this.paddleUpperLeft.getY(),
                this.paddleWidth, this.paddleHeight);
        // draw frame
        d.setColor(Color.BLACK);
        d.drawRectangle((int) this.paddleUpperLeft.getX(), (int) this.paddleUpperLeft.getY(),
                this.paddleWidth, this.paddleHeight);
    }

    /**
     * Return the shape of the paddle.
     *
     * @return the shape of the paddle (geometrics.Rectangle)
     */
    public Rectangle getCollisionRectangle() {
        return this.movePaddle.getCollisionRectangle();
    }

    /**
     * Returns the new velocity of the ball after it hit's the paddle.
     *
     * @param collisionPoint  a specific point that the ball is going to collide with
     * @param currentVelocity velocity of the ball before the collision
     * @param hitter a ball
     * @return new velocity of the ball
     */
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        Velocity newBallVelocity = new Velocity(currentVelocity.getX(), currentVelocity.getY());
        for (int i = 0; i < 6; i++) {
            if ((collisionPoint.getX() >= this.paddleUpperLeft.getX() + (i * (this.paddleWidth / 5)))
                    && (collisionPoint.getX() < this.paddleUpperLeft.getX() + (i + 1) * (this.paddleWidth / 5))) {
                double speed = Math.sqrt(Math.pow(currentVelocity.getX(), 2) + Math.pow(currentVelocity.getY(), 2));
                newBallVelocity = Velocity.fromAngleAndSpeed(300 + (i * 30), speed);
            }
            if (collisionPoint.getY() > this.paddleUpperLeft.getY()) {
                return new Velocity(-currentVelocity.getX(), currentVelocity.getY());
            }
        }
        return newBallVelocity;
    }


    /**
     * Add this paddle to the game.
     *
     * @param g animations.GameLevel
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Add this paddle to the game.
     *
     * @param g animations.GameLevel
     */
    public void removeFromGame(GameLevel g) {
        g.removeSprite(this);
        g.removeCollidable(this);
    }

}

