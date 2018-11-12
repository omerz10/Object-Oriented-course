package animations;

import biuoop.DrawSurface;

import java.util.ArrayList;

/**
 * HighScoreAnimation.
 */
public class HighScoreAnimation implements Animation {
    private HighScoresTable table;
    private boolean stop;

    /**
     * Construct HighScoreAnimation with table, endKey, gui, ks.
     *
     * @param scores scores
     */
    public HighScoreAnimation(HighScoresTable scores) {
        this.table = scores;
        this.stop = false;
    }


    /**
     * @param d  - the surface.
     * @param dt - the speed control.
     */
    public void doOneFrame(DrawSurface d, double dt) {
        d.setColor(java.awt.Color.BLACK);
        d.fillRectangle(0, 0, 800, 600);
        d.setColor(java.awt.Color.white);
        d.drawText(170, 100, "Top 10 Players", 65);
        d.setColor(java.awt.Color.white);
        d.drawText(100, 200, "Player Name", 35);
        d.drawText(400, 200, "Score", 35);
        ArrayList<ScoreInfo> scoresList = (ArrayList<ScoreInfo>) this.table.getHighScores();
        d.setColor(java.awt.Color.WHITE);
        for (int i = 0; i < scoresList.size(); i++) {
            d.drawText(100, 240 + (i * 30), scoresList.get(i).getName(), 25);
            d.drawText(400, 240 + (i * 30),
                    Integer.toString(scoresList.get(i).getScore()), 25);
        }
        d.setColor(java.awt.Color.white);
        d.drawText(260, 570, "Press space to continue", 20);
    }

    /**
     * @return if stop the runner.
     */
    public boolean shouldStop() {
        return this.stop;
    }

    /**
     * The function initiate the ShoulsStop paramater.
     */
    public void initShouldStop() {
        this.stop = false;
    }
}
