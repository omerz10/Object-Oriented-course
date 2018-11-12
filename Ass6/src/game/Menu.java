package game;

import animations.Animation;

/**
 * Menu<T>.
 *
 * @param <T> - unknown type
 * @author Yaheli Salina (yahelisalina@gmail.com)
 * @author Adi Shua (Adishu90@gmail.com)
 */
public interface Menu<T> extends Animation {
    /**
     * Adds new selection to the menu.
     *
     * @param key       - key to add
     * @param message   - message to add
     * @param returnVal - T to add
     */
    void addSelection(String key, String message, T returnVal);

    /**
     * Return the current menu status.
     *
     * @return T an object
     */
    T getStatus();

    /**
     * Add sub menu.
     *
     * @param key1     the key that end
     * @param message1 the massage to the level
     * @param subMenu  the submenu
     */
    void addSubMenu(String key1, String message1, Menu<T> subMenu);


    /**
     * Continue running game.
     */
    void clear();

}