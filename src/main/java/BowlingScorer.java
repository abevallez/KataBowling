/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    static final int SPARE_VALUE = 9;

    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game
     * @return int
     */
    public int totalScoreFromAGame(String game) {
        int i;
        char roll;
        int totalScore = 0;
        int previousSum = 0;
        int pinDowns;

        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            if (roll == '/') {
                totalScore = sumSpare(totalScore, previousSum);
                previousSum = 0;
            }else if (Character.isDigit(roll)) {
                pinDowns = Character.getNumericValue(roll);
                totalScore += pinDowns;
                previousSum = pinDowns;
            } else {
                previousSum = 0;
            }
        }

        return totalScore;
    }

    /**
     * Sum Spare to totalScore correctly.
     *
     * @param totalScore
     * @param previousSum
     *
     * @return totalScore
     */
    protected int sumSpare(int totalScore, int previousSum) {
        totalScore -= previousSum;
        totalScore += SPARE_VALUE;
        return totalScore;
    }
}
