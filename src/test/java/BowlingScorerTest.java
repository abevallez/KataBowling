import static org.junit.Assert.*;

import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Bowling Scorer Test
 */
@RunWith(DataProviderRunner.class)
public class BowlingScorerTest {

    @Test
    public void scorerIs0WhenNoPinsDown() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("--------------------");
        assertEquals("When no pins downs score must be 0", 0, totalScore);
    }

    @DataProvider
    public static Object[][] only1PinDownInTheWholeGameDataProvider() {
        return new Object[][] {
                {"1-------------------"},
                {"--1-----------------"},
                {"-------------------1"},
                {"------------1-------"},
        };
    }

    @Test
    @UseDataProvider("only1PinDownInTheWholeGameDataProvider")
    public void scorerIs1WhenOnly1PinDownInTheWholeGame(String game) {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame(game);
        assertEquals("When only 1 pin down in the whole game must be 1", 1, totalScore);
    }

    @Test
    public void scorerIs2WhenOnlyTwoPinDownInOneRoll() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("--2-----------------");
        assertEquals("When only 2 pin down in one roll must be 2", 2, totalScore);
    }
}
