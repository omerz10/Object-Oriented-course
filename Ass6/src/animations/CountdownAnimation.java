package animations;

import sprites.SpriteCollection;
import biuoop.DrawSurface;

import java.awt.Color;


/**
 * CountdownAnimation.
 */
public class CountdownAnimation implements Animation {
    private double seconds;
    private int countF;
    private SpriteCollection gScreen;
    private boolean running;

    /**
     * Construct CountdownAnimation with numOfSeconds, countFrom, gameScreen.
     *
     * @param numOfSeconds number of seconds to show the counting numbers
     * @param countFrom    the number we count from (backwards)
     * @param gameScreen   a given screen of the game
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.seconds = numOfSeconds;
        this.countF = countFrom;
        this.gScreen = gameScreen;
        this.running = true;
    }

    /**
     * Creates one frame.
     *
     * @param d a given drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        this.gScreen.drawAllOn(d);
        biuoop.Sleeper sleeper = new biuoop.Sleeper();
        d.setColor(Color.BLACK);
        int millisecondsPerFrame =  1000 / (int) this.seconds;
        if (this.countF >= 0) {
            if (this.countF == 0) {
                d.drawText(d.getWidth() / 2 - 8, (d.getHeight() / 2) + 40, "Go!", 50);
            } else if ((this.countF > 0)) {
                d.drawText(d.getWidth() / 2, (d.getHeight() / 2) + 40, Integer.toString(this.countF), 50);
            }
            this.countF -= 1;
        } else {
            this.running = false;
        }
        sleeper.sleepFor(millisecondsPerFrame);
    }


    /**
     * Return true if game should stop.
     *
     * @return true if game should stop.
     */
    public boolean shouldStop() {
        return !this.running;
    }
}
