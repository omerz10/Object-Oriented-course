package game;

import animations.AnimationRunner;
import animations.HighScoresTable;
import animations.MenuAnimation;
import animations.KeyPressStoppableAnimation;
import animations.HighScoreAnimation;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.LevelInformation;
import levels.LevelSets;
import levels.LevelSpecificationReader;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Map;
import java.util.TreeMap;


/**
 * Omer Zucker.
 * 200876548
 */
public class Ass6Game {
    /**
     * main.
     *
     * @param args args
     * @throws IOException exception
     */
    public void statGame(String[] args) throws IOException {
        // Initialize properties and parameters
        String gameName = "Arkanoid";
        GUI gui = new GUI(gameName, 800, 600);
        KeyboardSensor ks = gui.getKeyboardSensor();
        final AnimationRunner runner = new AnimationRunner(gui);
        TreeMap<String, String> map = new TreeMap<>();
        String levelSetsPath = "level_sets.txt";
        String symbol, difficulty;

        // import files
        File highscores = new File("highscores.ser");
        final HighScoresTable table = HighScoresTable.loadFromFile(highscores);
        if (args.length > 0) {
            levelSetsPath = args[0];
        }
        try {
            InputStream reader = ClassLoader.getSystemClassLoader().getResourceAsStream(levelSetsPath);
            map = LevelSets.setsReader(reader);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        // Set general menu and sub menu
        Menu<Task<Void>> subMenu = new MenuAnimation<Task<Void>>(gameName, ks, runner);
        Menu<Task<Void>> generalMenu = new MenuAnimation<Task<Void>>(gameName, ks, runner);
        final LevelSpecificationReader levelSpecReader = new LevelSpecificationReader();
        for (final Map.Entry<String, String> s : map.entrySet()) {
            final String key = s.getKey();
            final String value = s.getValue();
            symbol = key.split(":")[0];
            difficulty = key.split(":")[1];
            subMenu.addSelection(symbol, difficulty, new Task<Void>() {
                public Void run() {
                    InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream(value);
                    InputStreamReader isr = new InputStreamReader(is);
                    ArrayList<LevelInformation> levels;
                    try {
                        levels = (ArrayList<LevelInformation>) levelSpecReader.fromReader(isr);
                        GameFlow game = new GameFlow(runner, ks, gui, table);
                        game.runLevels(levels);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    return null;
                }
            });
        }
        generalMenu.addSubMenu("s", "Start Game", subMenu);
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
        Ass6Game arkanoid = new Ass6Game();
        arkanoid.statGame(args);
    }
}

