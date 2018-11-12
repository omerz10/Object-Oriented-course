package levels;

import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.IOException;
import java.util.TreeMap;

/**
 * LevelSets.
 */
public class LevelSets {

    /**
     *
     * @param reader a given text file
     * @return a tree map
     */
    public static TreeMap<String, String> setsReader(InputStream reader) {
        TreeMap<String, String> map = new TreeMap<String, String>();
        int lines;
        String key = "";
        String value = "";
        LineNumberReader ln = new LineNumberReader(new BufferedReader(new InputStreamReader(reader)));
        try {
            String line;
            while ((line = ln.readLine()) != null) {
                lines = ln.getLineNumber();
                if (lines % 2 == 1) {
                    key = line;
                } else {
                    value = line;
                    map.put(key, value);
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (ln != null) {
                try {
                    ln.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return map;
    }
}
