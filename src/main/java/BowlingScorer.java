/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game String
     * @return int
     */
    public int totalScoreFromAGame(String game) {
        int i;
        char roll;
        char previousRoll;
        int totalScore = 0;

        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            if (Character.isDigit(roll)) {
                totalScore += Character.getNumericValue(roll);
            } else if (roll == '/') {
                previousRoll = game.charAt(i-1);
                if (Character.isDigit(previousRoll)) {
                    totalScore -= Character.getNumericValue(previousRoll);
                }
                totalScore += 9;
            }

        }

        return totalScore;
    }
}
