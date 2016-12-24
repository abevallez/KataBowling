/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    static final int SPARE_VALUE = 9;
    static final int STRIKE_VALUE = 10;

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
        boolean bonus;
        boolean strike = false;
        boolean spare = false;


        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            switch (roll) {
                case 'X':
                    this.totalScore += STRIKE_VALUE;
                    strike = true;
                    break;
                case '/':
                    sumSpareToTotalScore(pinDowns);
                    strike = false;
                    spare = true;
                    break;
                default:
                    bonus = this.hasBonus(strike, spare);
                    pinDowns = getPinDowns(roll);
                    sumPinDownsToTotalScore(pinDowns, bonus);
                    strike = false;
                    spare = false;
                    break;

            }
        }

        return this.totalScore;
    }

    /**
     *
     * @param pinDowns
     * @param bonus
     *
     * @return
     */
    protected void sumPinDownsToTotalScore(int pinDowns, boolean bonus) {
        if (bonus) {
            this.totalScore += pinDowns;
        }
        this.totalScore += pinDowns;
    }

    protected int getPinDowns(char roll) {
        if ('-' == roll) {
            return 0;
        }

        return Character.getNumericValue(roll);
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

    /**
     * Calculate if has bonus in this roll
     *
     * @param strike
     * @param spare
     *
     * @return
     */
    protected boolean hasBonus(boolean strike, boolean spare) {
        if (strike || spare) {
            return true;
        }
        return false;
    }
}
