package game;

import animations.Animation;
import animations.AnimationRunner;
import animations.Counter;
import animations.HighScoreAnimation;
import animations.KeyPressStoppableAnimation;
import animations.ScoreInfo;
import animations.GameOver;
import animations.HighScoresTable;
import biuoop.DialogManager;
import biuoop.KeyboardSensor;

import java.io.File;
import java.io.IOException;


/**
 * Construct GameInvaders.
 */
public class GameInvaders {
    private AnimationRunner runner;
    private KeyboardSensor keyboardS;
    private DialogManager dialogM;
    private HighScoresTable table;
    private Counter lives = new Counter();
    private Counter scores = new Counter();
    private int width;
    private int height;

    /**
     * @param ar    Animation Runner
     * @param ks    Keyboard Sensor
     * @param dm    DialogManager
     * @param table HighScoresTable
     * @param width width of gui
     * @param height height of gui
     */
    public GameInvaders(AnimationRunner ar, KeyboardSensor ks, DialogManager dm, HighScoresTable table, int width,
                        int height) {
        this.runner = ar;
        this.keyboardS = ks;
        this.dialogM = dm;
        this.table = table;
        this.width = width;
        this.height = height;
        this.lives.increase(3);
        this.scores.increase(0);
    }

    /**
     * Runlevels.
     */
    public void runLevels() {
        int battleNum = 1;
        while (true) {
            GameLevelAlien gameLevelAlien = new GameLevelAlien(battleNum, this.runner, this.keyboardS, this.scores,
                    this.lives, this.width, this.height);
            gameLevelAlien.initialize();
            while (this.lives.getValue() != 0 && gameLevelAlien.getAliensNum() != 0) {
                gameLevelAlien.playOneTurn();
            }
            if (this.lives.getValue() == 0) {
                break;
            }
            battleNum++;
        }
        // if score of player can be sorted in table
        if (this.table.getRank(this.scores.getValue()) <= this.table.size()) {
            String name = dialogM.showQuestionDialog("Name", "What is your name?", "");
            this.table.add(new ScoreInfo(name, this.scores.getValue()));
            try {
                this.table.save(new File("highscores.ser"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        Animation gameOver = new GameOver(this.scores);
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardS, KeyboardSensor.SPACE_KEY, gameOver));

        // show high score table
        HighScoreAnimation highScore = new HighScoreAnimation(this.table);
        this.runner.run(new KeyPressStoppableAnimation(this.keyboardS, KeyboardSensor.SPACE_KEY,
                highScore));
    }
}
