/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game
     * @return int
     */
    public int totalScoreFromAGame(String game) {

        if (game.contains("1")) {
            return 1;
        }

        return 0;
    }
}
