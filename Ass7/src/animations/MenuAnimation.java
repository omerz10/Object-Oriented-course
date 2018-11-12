package animations;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import game.Menu;

/**
 * MenuAnimation<T>.
 *
 * @param <T>
 * @author Yaheli Salina (yahelisalina@gmail.com)
 * @author Adi Shua (Adishu90@gmail.com)
 */
public class MenuAnimation<T> implements Menu<T> {
    private String title;
    private boolean stop;
    private KeyboardSensor ks;
    private AnimationRunner animationR;
    private List<Boolean> statusSubMenu;
    private List<String> key;
    private List<String> massage;
    private List<T> returnVals;
    private T status;
    private List<Menu<T>> subMenuList;

    /**
     * Construct MenuAnimation.
     *
     * @param k     the keyboard.
     * @param title a string
     * @param ar    AnimationRunner
     */
    public MenuAnimation(String title, KeyboardSensor k, AnimationRunner ar) {
        this.title = title;
        this.ks = k;
        this.stop = false;
        this.statusSubMenu = new ArrayList<Boolean>();
        this.massage = new ArrayList<String>();
        this.key = new ArrayList<String>();
        this.returnVals = new ArrayList<T>();
        this.status = null;
        this.subMenuList = new ArrayList<Menu<T>>();
        this.animationR = ar;

    }

    /**
     * Add selection to menu.
     *
     * @param keyS      key
     * @param massageS  massage
     * @param returnVal returned value
     */
    public void addSelection(String keyS, String massageS, T returnVal) {
        this.key.add(keyS);
        this.massage.add(massageS);
        this.returnVals.add(returnVal);
        this.subMenuList.add(null);
        this.statusSubMenu.add(false);
    }


    /**
     * Creates one frame.
     *
     * @param d  a given drawSurface
     * @param dt parameter
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.black);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(Color.GREEN);
        d.drawText(18, 178, this.title, 180);
        for (int i = 0; i < massage.size(); i++) {
            if (i == 0) {
                d.setColor(Color.WHITE);
            } else if (i == 1) {
                d.setColor(Color.BLUE);
            } else {
                d.setColor(Color.RED);
            }
            d.drawText(29, 280 + i * 80, "(" + key.get(i) + ")" + " " + massage.get(i), 60);
        }
        for (int i = 0; i < this.key.size(); i++) {
            if (ks.isPressed(this.key.get(i))) {
                if (!statusSubMenu.get(i)) {
                    status = returnVals.get(i);
                    stop = true;
                    break;
                }
                Menu<T> subMenu = subMenuList.get(i);
                animationR.run(subMenu);
                status = subMenu.getStatus();
                stop = true;
                subMenu.clear();
                break;
            }
        }
    }


    /**
     * Return true if game should stop.
     *
     * @return true if game should stop.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * Continue running game.
     */
    public void clear() {
        this.stop = false;
    }

    /**
     * @return T get status.
     */
    public T getStatus() {
        return this.status;
    }


    /**
     * Add sub menu.
     *
     * @param keyS    the key that end
     * @param message the massage to the level
     * @param subMenu the submenu
     */
    public void addSubMenu(String keyS, String message, Menu<T> subMenu) {
        this.key.add(keyS);
        this.massage.add(message);
        this.subMenuList.add(subMenu);
        this.statusSubMenu.add(true);
        this.returnVals.add(null);
    }
}