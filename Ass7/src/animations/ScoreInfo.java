package animations;

import java.io.Serializable;

/**
 * CScoreInfo.
 */
public class ScoreInfo implements Serializable {
    private String name;
    private int score;

    /**
     * Construct ScoreInfo with name and score.
     *
     * @param name name of player
     * @param score score
     */
    public ScoreInfo(String name, int score) {
        this.name = name;
        this.score = score;
    }

    /**
     * Return name.
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     * Return score.
     *
     * @return score
     */
    public int getScore() {
        return this.score;
    }
}
