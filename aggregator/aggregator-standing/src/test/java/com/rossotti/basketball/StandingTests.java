package com.rossotti.basketball;

import com.rossotti.basketball.batch.Standing;
import com.rossotti.basketball.batch.StandingProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StandingTests {

    @Test
    public void processorCalculations() {
        StandingProcessor standingProcessor = new StandingProcessor();
        Standing standing = standingProcessor.process(createMockStanding());
        assertEquals(0, Float.compare(standing.getStrengthOfSchedule(), 0.3867f));
        assertEquals(0, Float.compare(standing.getRelativePercentageIndex(), 0.456725f));
        assertEquals(0, Float.compare(standing.getMarginOfVictory(), 10.5f));
        assertEquals(0, Float.compare(standing.getSimpleRatingSystem(), 10.1133f));
        assertEquals(0, Float.compare(standing.getProjectedWinningPct(),0.8457f));
        assertEquals(0, Float.compare(standing.getPythagoreanWinningPct_13_91(), 0.8072f));
        assertEquals(0, Float.compare(standing.getPythagoreanWins_13_91(), 66.1904f));
        assertEquals(0, Float.compare(standing.getPythagoreanLosses_13_91(), 15.8096f));
        assertEquals(0, Float.compare(standing.getPythagoreanWinningPct_16_5(), 0.8454f));
        assertEquals(0, Float.compare(standing.getPythagoreanWins_16_5(), 69.3228f));
        assertEquals(0, Float.compare(standing.getPythagoreanLosses_16_5(), 12.6772f));
    }

    private Standing createMockStanding() {
        Standing standing = new Standing();
        standing.setStandingDate("2016-11-07");
        standing.setTeamAbbr("ATL");
        standing.setRank("3");
        standing.setOrdinalRank("3rd");
        standing.setGamesWon((short)4);
        standing.setGamesLost((short)2);
        standing.setStreak("W1");
        standing.setStreakType("win");
        standing.setStreakTotal((short)1);
        standing.setGamesBack((float)2);
        standing.setPointsFor((short)644);
        standing.setPointsAgainst((short)581);
        standing.setHomeWins((short)3);
        standing.setHomeLosses((short)1);
        standing.setAwayWins((short)1);
        standing.setAwayLosses((short)1);
        standing.setConferenceWins((short)2);
        standing.setConferenceLosses((short)1);
        standing.setLastFive((short)3);
        standing.setLastTen((short)4);
        standing.setGamesPlayed((short)6);
        standing.setPointsScoredPerGame((float)107.3);
        standing.setPointsAllowedPerGame((float)96.8);
        standing.setPointDifferentialPerGame((float)10.5);
        standing.setOpptGamesPlayed(32);
        standing.setOpptGamesWon(10);
        standing.setOpptOpptGamesPlayed(213);
        standing.setOpptOpptGamesWon(114);
        return standing;
    }
}