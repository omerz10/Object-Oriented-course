package game;

import animations.AnimationRunner;
import animations.HighScoresTable;
import animations.MenuAnimation;
import animations.KeyPressStoppableAnimation;
import animations.HighScoreAnimation;
import biuoop.DialogManager;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import java.io.File;
import java.io.IOException;

/**
 * Omer Zucker.
 * 200876548
 */
public class Ass7Game {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;
    /**
     * main.
     *
     * @param args args
     * @throws IOException exception
     */
    public void statGame(String[] args) throws IOException {
        // Initialize properties and parameters
        String gameName = "Arkanoid";
        GUI gui = new GUI(gameName, WIDTH, HEIGHT);
        KeyboardSensor ks = gui.getKeyboardSensor();
        final AnimationRunner runner = new AnimationRunner(gui);
        Menu<Task<Void>> generalMenu = new MenuAnimation<Task<Void>>(gameName, ks, runner);
        DialogManager dialogManager = gui.getDialogManager();

        // import high score table
        File highscores = new File("highscores.ser");
        final HighScoresTable table = HighScoresTable.loadFromFile(highscores);


        generalMenu.addSelection("s", "Start", new Task<Void>() {
            public Void run() {
                GameInvaders gInvaders = new GameInvaders(runner, ks, dialogManager, table, WIDTH, HEIGHT);
                gInvaders.runLevels();
                return null;
            }
        });
        generalMenu.addSelection("h", "High Score Table", new Task<Void>() {
            public Void run() {
                runner.run(new KeyPressStoppableAnimation(ks, "space", new HighScoreAnimation(table)));
                return null;
            }
        });
        generalMenu.addSelection("e", "Exit", new Task<Void>() {
            public Void run() {
                gui.close();
                System.exit(1);
                return null;
            }
        });
        while (true) {
            runner.run(generalMenu);
            Task<Void> task = generalMenu.getStatus();
            task.run();
            generalMenu.clear();
        }
    }

    /**
     * Main.
     *
     * @param args args
     * @throws IOException exception
     */
    public static void main(String[] args) throws IOException {
        Ass7Game arkanoid = new Ass7Game();
        arkanoid.statGame(args);
    }
}

