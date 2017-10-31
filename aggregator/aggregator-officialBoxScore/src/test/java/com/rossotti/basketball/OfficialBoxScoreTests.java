package com.rossotti.basketball;

import com.rossotti.basketball.batch.OfficialBoxScore;
import com.rossotti.basketball.batch.OfficialBoxScoreProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OfficialBoxScoreTests {

    @Test
    public void processorCalculations() {
        OfficialBoxScoreProcessor officialBoxScoreProcessor = new OfficialBoxScoreProcessor();
        OfficialBoxScore officialBoxScore = officialBoxScoreProcessor.process(createMockOfficialBoxScore());
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTwoPointAttempts(), (short)69));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTwoPointMade(), (short)28));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTwoPointPct(), (float)0.4058));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTwoPointRate(), (float)0.8214));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamFieldGoalPct(), (float)0.4286));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamThreePointPct(), (float)0.5333));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamThreePointRate(), (float)0.1786));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamFreeThrowPct(), (float)0.6818));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamFreeThrowRate(), (float)0.2619));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamReboundsTotal(), (short)44));

        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTrueShootingPct(), 0.5284f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamEffectiveFieldGoalPct(), 0.4762f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamOffensiveReboundPct(), 21.0526f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamDefensiveReboundPct(), 78.2609f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTotalReboundPct(), 52.381f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamAssistedFieldGoalPct(), 61.1111f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamTurnoverPct(), 13.802f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamStealPct(), 11.3004f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamBlockPct(), 5.1365f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamBlockRate(), 7.2464f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamPointsPerShot(), 1.1786f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamFloorImpactCounter(), 76.75f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamFloorImpactCounterPer40(), 63.9583f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamOffensiveRating(), 101.7033f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamDefensiveRating(), 103.7579f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamEfficiencyDifferential(), -2.0546f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamPlayPct(), 0.3956f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamAssistRate(), 16.835f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamAssistToTurnoverRatio(), 1.4667f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getTeamStealToTurnoverRatio(), 73.3333f));

        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTwoPointAttempts(), (short)65));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTwoPointMade(), (short)36));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTwoPointPct(), (float)0.5538));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTwoPointRate(), (float)0.7831));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptFieldGoalPct(), (float)0.5181));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptThreePointPct(), (float)0.3889));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptThreePointRate(), (float)0.2169));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptFreeThrowPct(), (float)0.72));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptFreeThrowRate(), (float)0.3012));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptReboundsTotal(), (short)40));

        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTrueShootingPct(), 0.5372f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptEffectiveFieldGoalPct(), 0.5602f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptOffensiveReboundPct(), 25.0f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptDefensiveReboundPct(), 75.0f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTotalReboundPct(), 47.619f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptAssistedFieldGoalPct(), 46.5116f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptTurnoverPct(), 12.963f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptStealPct(), 11.3004f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptBlockPct(), 4.1092f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptBlockRate(), 6.1538f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptPointsPerShot(), 1.2169f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptFloorImpactCounter(), 73.375f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptFloorImpactCounterPer40(), 61.1458f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptOffensiveRating(), 103.7579f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptDefensiveRating(), 101.7033f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptEfficiencyDifferential(), 2.0546f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptPlayPct(), 0.4943f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptAssistRate(), 15.625f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptAssistToTurnoverRatio(), 1.4286f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getOpptStealToTurnoverRatio(), 78.5714f));

        Assert.assertEquals(0, Float.compare(officialBoxScore.getPossessions(), 97.342f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPace(), 97.342f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanWinningPct_13_91(), 0.4309f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanWins_13_91(), 35.3338f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanLosses_13_91(), 46.6662f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanWinningPct_16_5(), 0.4182f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanWins_16_5(), 34.2924f));
        Assert.assertEquals(0, Float.compare(officialBoxScore.getPythagoreanLosses_16_5(), 47.7076f));
    }

    private OfficialBoxScore createMockOfficialBoxScore() {
        OfficialBoxScore officialBoxScore = new OfficialBoxScore();
        officialBoxScore.setGameDateTime("2017-03-30T00:00");
        officialBoxScore.setSeasonType("Regular");
        officialBoxScore.setOfficialLastName("Tiven");
        officialBoxScore.setOfficialFirstName("Josh");
        officialBoxScore.setTeamAbbr("LAL");
        officialBoxScore.setTeamConference("West");
        officialBoxScore.setTeamDivision("Pacific");
        officialBoxScore.setTeamLocation("Home");
        officialBoxScore.setTeamResult("Loss");
        officialBoxScore.setTeamMinutes((short)240);
        officialBoxScore.setTeamDaysOff((short)2);
        officialBoxScore.setTeamPoints((short)99);
        officialBoxScore.setTeamAssists((short)22);
        officialBoxScore.setTeamTurnovers((short)15);
        officialBoxScore.setTeamSteals((short)11);
        officialBoxScore.setTeamBlocks((short)5);
        officialBoxScore.setTeamPersonalFouls((short)18);
        officialBoxScore.setTeamFieldGoalAttempts((short)84);
        officialBoxScore.setTeamFieldGoalMade((short)36);
        officialBoxScore.setTeamThreePointAttempts((short)15);
        officialBoxScore.setTeamThreePointMade((short)8);
        officialBoxScore.setTeamFreeThrowAttempts((short)22);
        officialBoxScore.setTeamFreeThrowMade((short)15);
        officialBoxScore.setTeamReboundsOffense((short)8);
        officialBoxScore.setTeamReboundsDefense((short)36);
        officialBoxScore.setTeamPointsQ1((short)50);
        officialBoxScore.setTeamPointsQ2((short)50);
        officialBoxScore.setTeamPointsQ3((short)50);
        officialBoxScore.setTeamPointsQ4((short)49);
        officialBoxScore.setTeamPointsQ5((short)0);
        officialBoxScore.setTeamPointsQ6((short)0);
        officialBoxScore.setTeamPointsQ7((short)0);
        officialBoxScore.setTeamPointsQ8((short)0);
        officialBoxScore.setOpptAbbr("SAC");
        officialBoxScore.setOpptConference("West");
        officialBoxScore.setOpptDivision("Pacific");
        officialBoxScore.setOpptLocation("Home");
        officialBoxScore.setOpptResult("Win");
        officialBoxScore.setOpptMinutes((short)240);
        officialBoxScore.setOpptDaysOff((short)1);
        officialBoxScore.setOpptPoints((short)101);
        officialBoxScore.setOpptAssists((short)20);
        officialBoxScore.setOpptTurnovers((short)14);
        officialBoxScore.setOpptSteals((short)11);
        officialBoxScore.setOpptBlocks((short)4);
        officialBoxScore.setOpptPersonalFouls((short)19);
        officialBoxScore.setOpptFieldGoalAttempts((short)83);
        officialBoxScore.setOpptFieldGoalMade((short)43);
        officialBoxScore.setOpptThreePointAttempts((short)18);
        officialBoxScore.setOpptThreePointMade((short)7);
        officialBoxScore.setOpptFreeThrowAttempts((short)25);
        officialBoxScore.setOpptFreeThrowMade((short)18);
        officialBoxScore.setOpptReboundsOffense((short)10);
        officialBoxScore.setOpptReboundsDefense((short)30);
        officialBoxScore.setOpptPointsQ1((short)50);
        officialBoxScore.setOpptPointsQ2((short)50);
        officialBoxScore.setOpptPointsQ3((short)50);
        officialBoxScore.setOpptPointsQ4((short)51);
        officialBoxScore.setOpptPointsQ5((short)0);
        officialBoxScore.setOpptPointsQ6((short)0);
        officialBoxScore.setOpptPointsQ7((short)0);
        officialBoxScore.setOpptPointsQ8((short)0);
        return officialBoxScore;
    }
}