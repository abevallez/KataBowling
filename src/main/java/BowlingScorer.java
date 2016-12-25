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
    protected int frame = 1;
    protected int roll = 1;


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
                    this.frame++;
                    this.roll = 1;
                    break;
                case '/':
                    processSpare();
                    this.frame++;
                    this.roll = 1;
                    break;
                default:
                    sumPinDownsToTotalScore(getPinDowns(markRoll));
                    setRollAndFrame();
                    break;

            }
        }

        return this.totalScore;
    }

    /**
     * Set new roll and frame
     */
    protected void setRollAndFrame() {
        if (this.roll == 2) {
            this.frame++;
            this.roll = 1;
        } else {
            this.roll++;
        }
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
        if (frame <= 10) {
            this.rollsWithBonusForStrike = this.rollsWithBonusForStrike + 2;
        }
    }

    /**
     *
     * @param pinDowns
     *
     * @return
     */
    protected void sumPinDownsToTotalScore(int pinDowns) {
        int scoreToAdd = 0;

        scoreToAdd += addBonusForSpare(pinDowns);
        scoreToAdd += addBonusForStrike(pinDowns);

        scoreToAdd += pinDowns;
        this.pinsDownBefore = pinDowns;

        this.totalScore += scoreToAdd;
    }

    /**
     * Add bonus for spare
     *
     * @param pinDowns
     *
     * @return
     */
    private int addBonusForSpare(int pinDowns) {
        int bonusScore = 0;

        if (this.rollHasBonusForSpare && this.frame <= 10) {
            bonusScore = pinDowns;
            this.rollHasBonusForSpare = false;
        }
        return bonusScore;
    }

    /**
     * Add Bonus for Strike
     *
     * @param pinDowns
     *
     * @return
     */
    private int addBonusForStrike(int pinDowns) {
        int bonusScore = 0;

        if (this.frame == 12) {
            return bonusScore;
        }

        if ((this.rollsWithBonusForStrike > 0 && this.rollsWithBonusForStrike <=2) || this.frame == 11) {
            bonusScore = pinDowns;
            this.rollsWithBonusForStrike--;
        } else if (this.rollsWithBonusForStrike > 2) {
            bonusScore =  pinDowns * 2;
            this.rollsWithBonusForStrike = this.rollsWithBonusForStrike - 2;
        }

        return bonusScore;
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
