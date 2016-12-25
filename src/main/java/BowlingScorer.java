/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    static final int STRIKE_VALUE = 10;

    protected int totalScore;
    protected int pinsDownBefore;
    protected int rollsWithBonusForStrike = 0;
    protected boolean rollHasBonusForSpare = false;


    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game
     * @return int
     */
    public int totalScoreFromAGame(String game) {
        for (int i=0; i<game.length(); i++) {
            char markRoll = game.charAt(i);
            switch (markRoll) {
                case 'X':
                    processStrike();
                    break;
                case '/':
                    processSpare();
                    break;
                default:
                    sumPinDownsToTotalScore(getPinDowns(markRoll));
                    break;

            }
        }

        return this.totalScore;
    }

    /**
     * Process spare play.
     */
    protected void processSpare() {
        this.sumPinDownsToTotalScore(STRIKE_VALUE - this.pinsDownBefore);
        this.rollHasBonusForSpare = true;
    }

    /**
     * Process Strike play.
     */
    protected void processStrike() {
        sumPinDownsToTotalScore(STRIKE_VALUE);
        this.rollsWithBonusForStrike = this.rollsWithBonusForStrike + 2;
    }

    /**
     *
     * @param pinDowns
     *
     * @return
     */
    protected void sumPinDownsToTotalScore(int pinDowns) {
        int scoreToAdd = pinDowns;
        this.pinsDownBefore = pinDowns;

        scoreToAdd = addBonusForSpare(pinDowns, scoreToAdd);
        scoreToAdd = addBonusForStrike(pinDowns, scoreToAdd);

        this.totalScore += scoreToAdd;
    }

    /**
     * Add bonus for spare
     *
     * @param pinDowns
     * @param scoreToAdd
     * @return
     */
    private int addBonusForSpare(int pinDowns, int scoreToAdd) {
        if (this.rollHasBonusForSpare) {
            scoreToAdd += pinDowns;
            this.rollHasBonusForSpare = false;
        }
        return scoreToAdd;
    }

    /**
     * Add Bonus for Strike
     *
     * @param pinDowns
     * @param scoreToAdd
     * @return
     */
    private int addBonusForStrike(int pinDowns, int scoreToAdd) {
        if (this.rollsWithBonusForStrike > 2) {
            scoreToAdd += pinDowns * 2;
            this.rollsWithBonusForStrike = this.rollsWithBonusForStrike - 2;
        } else if (this.rollsWithBonusForStrike > 0 && this.rollsWithBonusForStrike <=2) {
            scoreToAdd += pinDowns;
            this.rollsWithBonusForStrike--;
        }
        return scoreToAdd;
    }

    /**
     * Get Pins downs
     *
     * @param roll
     * @return
     */
    protected int getPinDowns(char roll) {
        if ('-' == roll) {
            return 0;
        }

        return Character.getNumericValue(roll);
    }
}
