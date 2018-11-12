package animations;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import levels.DirectHit;
import levels.Green3;
import levels.LevelInformation;
import levels.WideEasy;
import levels.FinalFour;

import java.util.ArrayList;


/**
 * Omer Zucker.
 * 200876548
 */
public class Ass5Game {

    /**
     * main.
     *
     * @param args args
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arknoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(gui);
        GameFlow game = new GameFlow(runner, keyboard, gui);
        ArrayList<LevelInformation> levels = new ArrayList<LevelInformation>();
        if (args.length != 0) {
            for (String arg : args) {
                try {
                    int input = Integer.parseInt(arg);
                    if (input == 1) {
                        levels.add(new DirectHit());
                    } else if (input == 2) {
                        levels.add(new WideEasy());
                    } else if (input == 3) {
                        levels.add(new Green3());
                    } else if (input == 4) {
                        levels.add(new FinalFour());
                    }
                } catch (Exception e) {
                    System.out.println("wrong input or level doesn't exist!");
                }
            }
            game.runLevels(levels);
        } else {
            levels.add(new DirectHit());
            levels.add(new WideEasy());
            levels.add(new Green3());
            levels.add(new FinalFour());
            game.runLevels(levels);
        }
        gui.close();
    }
}
