package animations;

import levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.util.List;


/**
 * GameFlow.
 */
public class GameFlow {
    private GUI gui;
    private KeyboardSensor keyboardSensor;
    private AnimationRunner animationRunner;
    private Counter scores;
    private Counter lives;
    private boolean win;

    /**
     * Construct game flow.
     *
     * @param gui the gui
     * @param ks  keyboardSensor
     * @param ar  animation runner to run
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.scores = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
        this.win = true;
    }

    /**
     * Running levels.
     *
     * @param levels - array list of different levels.
     */
    public void runLevels(List<LevelInformation> levels) {
        // run through levels
        for (LevelInformation levelInfo : levels) {
            GameLevel level = new GameLevel(levelInfo, this.keyboardSensor, this.animationRunner, this.scores,
                    this.lives);
            level.initialize();
            while ((level.getNumOfBlocks() > 0) && (level.getLivesValue() > 0)) {
                level.run();
                if (level.getLivesValue() == 0) {
                    this.win = false;
                }
            }
        }
        this.animationRunner.run(new EndScreen(this.gui.getKeyboardSensor(), this.scores, win));
    }
}
