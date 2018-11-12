package game;

import animations.AnimationRunner;
import animations.Counter;
import animations.HighScoresTable;
import animations.Animation;
import animations.GameOver;
import animations.KeyPressStoppableAnimation;
import animations.EndScreen;
import animations.ScoreInfo;
import animations.HighScoreAnimation;
import biuoop.DialogManager;
import levels.LevelInformation;
import biuoop.GUI;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;
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
    private HighScoresTable table;

    /**
     * Construct game flow.
     *
     * @param gui   the gui
     * @param ks    keyboardSensor
     * @param ar    animation runner to run
     * @param table table of scores
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui, HighScoresTable table) {
        this.animationRunner = ar;
        this.keyboardSensor = ks;
        this.gui = gui;
        this.scores = new Counter();
        this.lives = new Counter();
        this.lives.increase(7);
        this.win = true;
        this.table = table;
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
            }
            if (level.getLivesValue() == 0) {
                break;
            }
        }
        // if score of player can be sorted in table
        if (this.table.getRank(this.scores.getValue()) <= this.table.size()) {
            DialogManager dialog = this.gui.getDialogManager();
            String name = dialog.showQuestionDialog("Name", "What is your name?", "");
            table.add(new ScoreInfo(name, this.scores.getValue()));
            try {
                table.save(new File("highscores.ser"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        // Game Over - player lose
        if (lives.getValue() == 0) {
            this.win = false;
            Animation gameOver = new GameOver(this.scores);
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor,
                    KeyboardSensor.SPACE_KEY,
                    gameOver));
        }
        // player won
        if (this.lives.getValue() > 0) {
            Animation endScreen = new EndScreen(this.scores);
            this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                    endScreen));
        }
        // show high score table
        HighScoreAnimation highScore = new HighScoreAnimation(this.table);
        this.animationRunner.run(new KeyPressStoppableAnimation(this.keyboardSensor, KeyboardSensor.SPACE_KEY,
                highScore));
    }
}
