/**
 * Bowling Scorer
 *
 * Produce the total Score from a Bowling Game
 */
public class BowlingScorer {

    static final int SPARE_VALUE = 9;
    static final int STRIKE_VALUE = 10;
    static final int FIRST_ROLL = 1;
    static final int SECOND_ROLL = 2;

    protected int totalScore;
    protected int scoreAddedFrameBefore;
    protected boolean bonusForStrike = false;
    protected boolean bonusForSpare = false;
    protected int numRollOnFrame = 0;


    /**
     * Produce the total Score from a Bowling Game
     *
     * @param game
     * @return int
     */
    public int totalScoreFromAGame(String game) {
        for (int i=0; i<game.length(); i++) {
            char markRoll = game.charAt(i);
            setNewRollOnFrame();
            switch (markRoll) {
                case 'X':
                    processStrike();
                    break;
                case '/':
                    processSpare();
                    break;
                default:
                    processPinDowns(markRoll);
                    break;

            }
        }

        return this.totalScore;
    }

    /**
     * Process normal pin downs play.
     *
     * @param markRoll
     */
    protected void processPinDowns(char markRoll) {
        sumPinDownsToTotalScore(getPinDowns(markRoll), this.hasBonus());
        if (SECOND_ROLL == this.numRollOnFrame) {
            bonusForStrike = false;
        }
        this.bonusForSpare = false;
    }

    /**
     * Process spare play.
     */
    protected void processSpare() {
        sumSpareToTotalScore(this.hasBonus());
        this.bonusForStrike = false;
        this.bonusForSpare = true;
    }

    /**
     * Process Strike play.
     */
    protected void processStrike() {
        sumPinDownsToTotalScore(STRIKE_VALUE, this.hasBonus());
        this.bonusForStrike = true;
        this.bonusForSpare = false;
        this.numRollOnFrame = SECOND_ROLL;
    }

    /**
     * Get correct num of roll on frame.
     *
     */
    protected void setNewRollOnFrame() {
        if (this.numRollOnFrame == FIRST_ROLL) {
            this.numRollOnFrame = SECOND_ROLL;
        } else {
            this.numRollOnFrame = FIRST_ROLL;
        }
    }

    /**
     *
     * @param pinDowns
     * @param bonus
     *
     * @return
     */
    protected void sumPinDownsToTotalScore(int pinDowns, boolean bonus) {
        int scoreToAdd = pinDowns;
        if (bonus) {
            scoreToAdd += pinDowns;
        }
        this.totalScore += scoreToAdd;
        this.scoreAddedFrameBefore = scoreToAdd;
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
     * @param bonus
     */
    protected void sumSpareToTotalScore(boolean bonus) {
        this.totalScore -= this.scoreAddedFrameBefore;
        this.sumPinDownsToTotalScore(SPARE_VALUE, bonus);
    }

    /**
     * Calculate if has bonus in this roll
     *
     *
     * @return
     */
    protected boolean hasBonus() {
        if (this.bonusForStrike|| this.bonusForSpare) {
            return true;
        }
        return false;
    }
}
