import static org.junit.Assert.*;
import org.junit.Test;

/**
 * Bowling Scorer Test
 */
public class BowlingScorerTest {

    @Test
    public void scorerIs0WhenNoPinDowns() {
        BowlingScorer bowlingScorer = new BowlingScorer();
        int totalScore = bowlingScorer.totalScoreFromAGame("--------------------");
        assertEquals(0, totalScore);
    }
}
