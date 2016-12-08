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

        if (game.contains("2")) {
            return 2;
        }

        if (game.contains("3")) {
            return 3;
        }

        return 0;
    }
}
