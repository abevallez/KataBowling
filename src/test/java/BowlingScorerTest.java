import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Bowling Scorer Test
 */
public class BowlingScorerTest {

    @Test
    public void scorerIs0WhenNoPinsDown() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("--------------------");
        assertEquals("When no pins downs score must be 0", 0, totalScore);
    }

    @Test
    public void scorerIs1WhenOnly1PinDownInFirstFrame() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("1-------------------");
        assertEquals("When only 1 pin down in first frame score must be 1", 1, totalScore);
    }

    @Test
    public void scorerIs1WhenOnly1PinDownInSecondFrame() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("---1----------------");
        assertEquals("When only 1 pin down in second frame score must be 1", 1, totalScore);
    }
}
