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
        boolean bonusForStrike = false;
        boolean bonusForSpare = false;


        for (i=0; i<game.length(); i++) {
            roll = game.charAt(i);
            switch (roll) {
                case 'X':
                    this.totalScore += STRIKE_VALUE;
                    bonusForStrike = true;
                    bonusForSpare = false;
                    break;
                case '/':
                    sumSpareToTotalScore(pinDowns);
                    bonusForStrike = false;
                    bonusForSpare = true;
                    break;
                default:
                    bonus = this.hasBonus(bonusForStrike, bonusForSpare);
                    pinDowns = getPinDowns(roll);
                    sumPinDownsToTotalScore(pinDowns, bonus);
                    bonusForStrike = false;
                    bonusForSpare = false;
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
     * @param BonusForStrike
     * @param BonusForSpare
     *
     * @return
     */
    protected boolean hasBonus(boolean BonusForStrike, boolean BonusForSpare) {
        if (BonusForStrike || BonusForSpare) {
            return true;
        }
        return false;
    }
}
