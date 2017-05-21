package com.rossotti.basketball;

import com.rossotti.basketball.batch.TeamBoxScore;
import com.rossotti.basketball.batch.TeamBoxScoreProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamBoxScoreTests {

    @Test
    public void processorCalculations() {
        TeamBoxScoreProcessor teamBoxScoreProcessor = new TeamBoxScoreProcessor();
        TeamBoxScore teamBoxScore = teamBoxScoreProcessor.process(createMockTeamBoxScore());
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointAttempts(), (short)69));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointMade(), (short)28));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointPct(), (float)0.4058));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointRate(), (float)0.8214));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamFieldGoalPct(), (float)0.4286));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamThreePointPct(), (float)0.5333));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamThreePointRate(), (float)0.1786));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamFreeThrowPct(), (float)0.6818));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamFreeThrowRate(), (float)0.2619));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointAttempts(), (short)65));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointMade(), (short)36));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointPct(), (float)0.5538));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointRate(), (float)0.7831));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptFieldGoalPct(), (float)0.5181));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptThreePointPct(), (float)0.3889));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptThreePointRate(), (float)0.2169));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptFreeThrowPct(), (float)0.72));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getOpptFreeThrowRate(), (float)0.3012));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPossessions(), 97.342f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPace(), 97.342f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTrueShootingPct(), 0.5284f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamEffectiveFieldGoalPct(), 0.4762f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamOffensiveReboundPct(), 21.0526f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamDefensiveReboundPct(), 78.2609f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTotalReboundPct(), 52.381f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamAssistedFieldGoalPct(), 0.6111f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamTurnoverPct(), 13.802f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamStealPct(), 11.3004f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamBlockPct(), 5.1365f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamBlockRate(), 7.2464f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamPointsPerShot(), 1.1786f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamFloorImpactCounter(), 76.75f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamFloorImpactCounterPer40(), 63.9583f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamOffensiveRating(), 101.7033f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamDefensiveRating(), 103.7579f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamEfficiencyDifferential(), -2.0546f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamPlayPct(), 0.3956f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamAssistRate(), 16.835f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamAssistToTurnoverRatio(), 1.4667f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getTeamStealToTurnoverRatio(), 73.3333f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanWinningPct_13_91(), 0.4309f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanWins_13_91(), 35.3338f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanLosses_13_91(), 46.6662f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanWinningPct_16_5(), 0.4182f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanWins_16_5(), 34.2924f));
        Assert.assertEquals(0, Float.compare(teamBoxScore.getPythagoreanLosses_16_5(), 47.7076f));
    }

    private TeamBoxScore createMockTeamBoxScore() {
        TeamBoxScore teamBoxScore = new TeamBoxScore();
        teamBoxScore.setGameDateTime("2017-03-30T00:00");
        teamBoxScore.setSeasonType("Regular");
        teamBoxScore.setTeamAbbr("LAL");
        teamBoxScore.setTeamConference("West");
        teamBoxScore.setTeamDivision("Pacific");
        teamBoxScore.setTeamLocation("Home");
        teamBoxScore.setTeamResult("Loss");
        teamBoxScore.setTeamMinutes((short)240);
        teamBoxScore.setTeamDaysOff((short)2);
        teamBoxScore.setTeamPoints((short)99);
        teamBoxScore.setTeamAssists((short)22);
        teamBoxScore.setTeamTurnovers((short)15);
        teamBoxScore.setTeamSteals((short)11);
        teamBoxScore.setTeamBlocks((short)5);
        teamBoxScore.setTeamPersonalFouls((short)18);
        teamBoxScore.setTeamFieldGoalAttempts((short)84);
        teamBoxScore.setTeamFieldGoalMade((short)36);
        teamBoxScore.setTeamThreePointAttempts((short)15);
        teamBoxScore.setTeamThreePointMade((short)8);
        teamBoxScore.setTeamFreeThrowAttempts((short)22);
        teamBoxScore.setTeamFreeThrowMade((short)15);
        teamBoxScore.setTeamReboundsOffense((short)8);
        teamBoxScore.setTeamReboundsDefense((short)36);
        teamBoxScore.setTeamPointsQ1((short)50);
        teamBoxScore.setTeamPointsQ2((short)50);
        teamBoxScore.setTeamPointsQ3((short)50);
        teamBoxScore.setTeamPointsQ4((short)49);
        teamBoxScore.setTeamPointsQ5((short)0);
        teamBoxScore.setTeamPointsQ6((short)0);
        teamBoxScore.setTeamPointsQ7((short)0);
        teamBoxScore.setTeamPointsQ8((short)0);
        teamBoxScore.setOpptAbbr("SAC");
        teamBoxScore.setOpptConference("West");
        teamBoxScore.setOpptDivision("Pacific");
        teamBoxScore.setOpptLocation("Home");
        teamBoxScore.setOpptResult("Win");
        teamBoxScore.setOpptMinutes((short)240);
        teamBoxScore.setOpptDaysOff((short)1);
        teamBoxScore.setOpptPoints((short)101);
        teamBoxScore.setOpptAssists((short)20);
        teamBoxScore.setOpptTurnovers((short)14);
        teamBoxScore.setOpptSteals((short)13);
        teamBoxScore.setOpptBlocks((short)4);
        teamBoxScore.setOpptPersonalFouls((short)19);
        teamBoxScore.setOpptFieldGoalAttempts((short)83);
        teamBoxScore.setOpptFieldGoalMade((short)43);
        teamBoxScore.setOpptThreePointAttempts((short)18);
        teamBoxScore.setOpptThreePointMade((short)7);
        teamBoxScore.setOpptFreeThrowAttempts((short)25);
        teamBoxScore.setOpptFreeThrowMade((short)18);
        teamBoxScore.setOpptReboundsOffense((short)10);
        teamBoxScore.setOpptReboundsDefense((short)30);
        teamBoxScore.setOpptPointsQ1((short)50);
        teamBoxScore.setOpptPointsQ2((short)50);
        teamBoxScore.setOpptPointsQ3((short)50);
        teamBoxScore.setOpptPointsQ4((short)51);
        teamBoxScore.setOpptPointsQ5((short)0);
        teamBoxScore.setOpptPointsQ6((short)0);
        teamBoxScore.setOpptPointsQ7((short)0);
        teamBoxScore.setOpptPointsQ8((short)0);
        return teamBoxScore;
    }
}