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

    @DataProvider
    public static Object[][] spareInGameMissingBonus() {
        return new Object[][] {
                {"11-----------/------", 12},
                {"-----/------1-------", 11},
                {"--25-/-----7--------", 24},
                {"-------2-------8-/--", 20}
        };
    }

    @Test
    public void scoreIs9WhenOnlyOneSpare() {
        int totalScore = this.bowlingScorer.totalScoreFromAGame("-/------------------");
        assertEquals("When only one spare score must be 10", 10, totalScore);
    }

    @Test
    public void scoreIs18WhenTwoSparesNotConsecutive() {
        int totalScore = this.bowlingScorer.totalScoreFromAGame("-/-/----------------");
        assertEquals("When two spares not consecutive score must be 20", 20, totalScore);
    }

    @Test
    @UseDataProvider("spareInGameMissingBonus")
    public void spareSum9ToTotalScoreWithBonusRollMissed(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals("spare sum 10 to total score with bonus roll missed", totalScoreExpected, totalScore);
    }

    @DataProvider
    public static Object[][] spareInGameMissingBonusWithPinDownInSameFrame() {
        return new Object[][] {
                {"11-----------/------", 12},
                {"-----/------1-------", 11},
                {"--25-/-----7--------", 24},
                {"-------2-------8-/--", 20}
        };
    }

    @Test
    @UseDataProvider("spareInGameMissingBonusWithPinDownInSameFrame")
    public void spareSumOnly9WithPinDownsBeforeInSameFrame(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals("spare sum 10 to total score with bonus roll missed", totalScoreExpected, totalScore);
    }

    @DataProvider
    public static Object[][] spareWithBonusInNextRoll() {
        return new Object[][] {
                {"11-----------/4-----", 19},
                {"-----/9-----1-------", 28},
                {"--25-/2----7--------", 28},
                {"-------2-------8-/1-", 22},
                {"5/5/5/5/5/5/5/5/5/5/5", 150}
        };
    }

    @Test
    @UseDataProvider("spareInGameMissingBonusWithPinDownInSameFrame")
    public void spareGiveNextRollWithBonus(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals("next roll pin downs counts twice when spare", totalScoreExpected, totalScore);
    }

    @Test
    public void scoreIs10WhenOnlyOneStrike()
    {
        int totalScore = this.bowlingScorer.totalScoreFromAGame("X------------------");
        assertEquals("Score is 10 when only one Strike", 10, totalScore);
    }

    @DataProvider
    public static Object[][] strikeWithBonusMissed() {
        return new Object[][] {
                {"X-----------X-----", 20},
                {"-----X-----1-------", 11},
                {"--X--X--7--------", 27},
                {"-------2-------8X--", 20}
        };
    }

    @Test
    @UseDataProvider("strikeWithBonusMissed")
    public void strikeWithBonusMissedSum10ToTotalScore(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals("strike without bonus missed sum 10 to total score", totalScoreExpected, totalScore);
    }

    @DataProvider
    public static Object[][] consecutiveStrikeAndBonus() {
        return new Object[][] {
                {"X1-----------------", 12},
                {"X-1----------------", 12},
                {"XX----------------", 30},
                {"X1/----------------", 30},
                {"XXX---------------", 60},
                {"XX1/---------------", 51}
        };
    }

    @Test
    @UseDataProvider("consecutiveStrikeAndBonus")
    public void strikeGiveTwoRollsWithBonus(String game, int totalScoreExpected) {
        int totalScore = this.bowlingScorer.totalScoreFromAGame(game);
        assertEquals("strike give two next roll with bonus", totalScoreExpected, totalScore);
    }
}
