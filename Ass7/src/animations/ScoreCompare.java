package animations;

import java.util.Comparator;

/**
 * ScoreCompare.
 */
public class ScoreCompare implements Comparator<ScoreInfo> {
    /**
     * Compare between 2 scores.
     *
     * @param arg1 argument
     * @param arg2 argument
     * @return 1 if arg1 has higher score.
     *  -1 if arg2 has the higher score.
     *  0 if they equals.
     */
    public int compare(ScoreInfo arg1, ScoreInfo arg2) {
        if (arg1.getScore() < arg2.getScore()) {
            return 1;
        } else if (arg1.getScore() > arg2.getScore()) {
            return -1;
        } else {
            return 0;
        }
    }

}
