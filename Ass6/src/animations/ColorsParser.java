package animations;

import java.awt.Color;
import java.lang.reflect.Field;

/**
 * ColorsParser.
 */
public class ColorsParser {
    /**
     * Parse color definition and return the specified color.
     *
     * @param s a sting
     * @return a color
     */
    public java.awt.Color colorFromString(String s) {
        Color newColor;
        if (s.contains("RGB")) {
            String replacer = s.replace(")", "");
            replacer = replacer.replace("color(RGB(", "");
            String[] rgb = replacer.split(",");
            newColor = new Color(Integer.parseInt(rgb[0]), Integer.parseInt(rgb[1]), Integer.parseInt(rgb[2]));
        } else {
            try {
                Field field = Class.forName("java.awt.Color").getField(s.replace("color(",
                        "").replace(")", ""));
                newColor = (Color) field.get(null);
            } catch (Exception e) {
                throw new IllegalArgumentException();
            }
        }
        return newColor;
    }
}
