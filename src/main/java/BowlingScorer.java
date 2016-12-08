/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    static final int SPARE_VALUE = 9;

    protected int totalScore;

    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game
     * @return int
     */
    public int totalScoreFromAGame(String game) {
        int i;
        char roll;
        int pinDowns = 0;
        boolean bonus = false;

        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            switch (roll) {
                case 'X':
                    this.totalScore = 10;
                case '-':
                    pinDowns = 0;
                    bonus = false;
                    break;
                case '/':
                    sumSpareToTotalScore(pinDowns);
                    bonus = true;
                    break;
                case '1':
                case '2':
                case '3':
                case '4':
                case '5':
                case '6':
                case '7':
                case '8':
                case '9':
                    pinDowns = Character.getNumericValue(roll);
                    sumPinDownsToTotalScore(pinDowns, bonus);
                    bonus = false;
                    break;

            }
        }

        return this.totalScore;
    }

    /**
     *
     * @param pinDowns
     * @param bonus
     * @return
     */
    protected void sumPinDownsToTotalScore(int pinDowns, boolean bonus) {
        if (bonus) {
            this.totalScore += pinDowns;
        }
        this.totalScore += pinDowns;
    }

    /**
     * Sum Spare to totalScore correctly.
     *
     * @param previousSum
     */
    protected void sumSpareToTotalScore(int previousSum) {
        this.totalScore -= previousSum;
        this.totalScore += SPARE_VALUE;
    }
}
