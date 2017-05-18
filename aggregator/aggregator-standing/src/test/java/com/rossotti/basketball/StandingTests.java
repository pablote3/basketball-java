package com.rossotti.basketball;

import com.rossotti.basketball.batch.Standing;
import com.rossotti.basketball.batch.StandingProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StandingTests {

    @Test
    public void processorCalculations() {
        StandingProcessor standingProcessor = new StandingProcessor();
        Standing standing = standingProcessor.process(createMockStanding());
        Assert.assertEquals(0, Float.compare(standing.getStrengthOfSchedule(), 0.3867f));
        Assert.assertEquals(0, Float.compare(standing.getRelativePercentageIndex(), 0.456725f));
        Assert.assertEquals(0, Float.compare(standing.getMarginOfVictory(), 10.5f));
        Assert.assertEquals(0, Float.compare(standing.getSimpleRatingSystem(), 10.1133f));
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