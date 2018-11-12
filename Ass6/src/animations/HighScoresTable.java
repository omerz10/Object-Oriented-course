package animations;


import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * HighScoresTable.
 */
public class HighScoresTable implements Serializable {
    private int size;
    private static final long serialVersionUID = 1L;
    private List<ScoreInfo> table;

    /**
     * Create an empty high-scores table with the specified size.
     * The size means that the table holds up to size top scores.
     *
     * @param size size of table
     */
    public HighScoresTable(int size) {
        this.size = size;
        this.table = new ArrayList<ScoreInfo>();

    }

    /**
     * Add a high-score.
     *
     * @param score given score
     */
    public void add(ScoreInfo score) {
        // list is not full
        if (this.table.size() != this.size) {
            table.add(score);
            Collections.sort(this.table, new ScoreCompare());
        } else {
            // score is bigger then last score in table
            if (score.getScore() > this.table.get(this.size - 1).getScore()) {
                table.remove(table.get(table.size() - 1));
                table.add(score);
                Collections.sort(this.table, new ScoreCompare());
            }
        }
    }

    /**
     * Return table size.
     *
     * @return table size
     */
    public int size() {
        return this.size;
    }


    /**
     * Return the current high scores. The list is sorted such that the highest scores come first.
     *
     * @return current hight score
     */
    public List<ScoreInfo> getHighScores() {
        return this.table;
    }


    /**
     * Return the rank of the current score: where will it be on the list if added?
     * Rank 1 means the score will be highest on the list.
     * Rank `size` means the score will be lowest.
     * Rank > `size` means the score is too low and will not be added to the list.
     *
     * @param score score
     * @return rank of score
     */
    public int getRank(int score) {
        for (int i = 0; i < this.table.size(); i++) {
            if (this.table.get(i).getScore() < score) {
                return i + 1;
            }
        }
        // score is too low
        return this.table.size() + 1;
    }

    /**
     * Clears the table.
     */
    public void clear() {
        this.table.clear();
    }


    /**
     * Load table data from file. Current table data is cleared.
     *
     * @param filename name of file
     * @throws IOException exception
     */
    public void load(File filename) throws IOException {
        ObjectInputStream in = null;
        ScoreInfo score;
        try {
            in = new ObjectInputStream(new FileInputStream(filename.getAbsolutePath()));
            while ((score = (ScoreInfo) in.readObject()) != null) {
                table.add(score);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        } finally {
            // Exception might have happened at constructor
            if (in != null) {
                try {
                    in.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Save table data to the specified file.
     *
     * @param filename name of file
     * @throws IOException exception
     */
    public void save(File filename) throws IOException {
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(new FileOutputStream(filename.getAbsolutePath()));
            for (int i = 0; i < table.size(); i++) {
                out.writeObject(table.get(i));
            }
            out.writeObject(null);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            // Exception might have happened at constructor
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }

    /**
     * Read a table from file and return it. If the file does not exist, or there is a problem with
     * reading it, an empty table is returned.
     *
     * @param filename name of file
     * @return table
     */
    public static HighScoresTable loadFromFile(File filename)  {
            HighScoresTable newTable = new HighScoresTable(10);
            try {
                newTable.load(filename);
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
            return newTable;
    }
}
