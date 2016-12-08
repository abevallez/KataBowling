import static org.junit.Assert.*;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Bowling Scorer Test
 */
@RunWith(DataProviderRunner.class)
public class BowlingScorerTest {

    public BowlingScorer bowlingScorer;

    @Before
    public void setUp() {
        this.bowlingScorer = new BowlingScorer();
    }

    @Test
    public void scoreIs0WhenNoPinsDown() {
        int totalScore = this.bowlingScorer.totalScoreFromAGame("--------------------");
        assertEquals("When no pins downs score must be 0", 0, totalScore);
    }

    @DataProvider
    public static Object[][] onlyOneRollWithPinsDownInTheWholeGameDataProvider() {
        return new Object[][] {
                {"1-------------------", 1},
                {"------------1-------", 1},
                {"--2-----------------", 2},
                {"-------2------------", 2},
                {"-------------------3", 3},
                {"------------3-------", 3},
        };
    }

    @Test
    @UseDataProvider("onlyOneRollWithPinsDownInTheWholeGameDataProvider")
    public void scoreIsSameThanPinWhenOneRollWithPinDowns(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals(
                "Scorer is " + totalScore + " when game is " + game,
                totalScoreExpected,
                totalScore
        );
    }

    @DataProvider
    public static Object[][] twoOrMoreRollsWithPinDownsNoSpareNorStrike() {
        return new Object[][] {
                {"11------------------", 2},
                {"-----5------1-------", 6},
                {"--25-------7--------", 14},
                {"-------2-------8----", 10},
                {"----6-----7--------3", 16},
                {"----3--5----3-------", 11},
                {"9-9-9-9-9-9-9-9-9-9-", 90},
        };
    }

    @Test
    @UseDataProvider("twoOrMoreRollsWithPinDownsNoSpareNorStrike")
    public void totalScoreIsSumOfPinDownsTwoOrMoreRollsNoStrikesNorSpare(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals(
                "Sum of pin downs must be " + totalScore + "when game is " + game,
                totalScoreExpected,
                totalScore
        );
    }
}
