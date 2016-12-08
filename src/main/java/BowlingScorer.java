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

        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            if (Character.isDigit(roll)) {
                return Character.getNumericValue(roll);
            }
        }

        return 0;
    }
}
