package com.rossotti.basketball;

import com.rossotti.basketball.batch.TeamBoxScore;
import com.rossotti.basketball.batch.TeamBoxScoreProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class TeamBoxScoreTests {

    @Test
    public void processorCalculations() {
        TeamBoxScoreProcessor teamBoxScoreProcessor = new TeamBoxScoreProcessor();
        TeamBoxScore teamBoxScore = teamBoxScoreProcessor.process(createMockTeamBoxScore());
        assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointAttempts(), (short)69));
        assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointMade(), (short)28));
        assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointPct(), (float)0.4058));
        assertEquals(0, Float.compare(teamBoxScore.getTeamTwoPointRate(), (float)0.8214));
        assertEquals(0, Float.compare(teamBoxScore.getTeamFieldGoalPct(), (float)0.4286));
        assertEquals(0, Float.compare(teamBoxScore.getTeamThreePointPct(), (float)0.5333));
        assertEquals(0, Float.compare(teamBoxScore.getTeamThreePointRate(), (float)0.1786));
        assertEquals(0, Float.compare(teamBoxScore.getTeamFreeThrowPct(), (float)0.6818));
        assertEquals(0, Float.compare(teamBoxScore.getTeamFreeThrowRate(), (float)0.2619));
        assertEquals(0, Float.compare(teamBoxScore.getTeamReboundsTotal(), (short)44));

        assertEquals(0, Float.compare(teamBoxScore.getTeamTrueShootingPct(), 0.5284f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamEffectiveFieldGoalPct(), 0.4762f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamOffensiveReboundPct(), 21.0526f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamDefensiveReboundPct(), 78.2609f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamTotalReboundPct(), 52.381f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamAssistedFieldGoalPct(), 61.1111f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamTurnoverPct(), 13.802f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamStealPct(), 11.3004f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamBlockPct(), 5.1365f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamBlockRate(), 7.2464f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamPointsPerShot(), 1.1786f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamFloorImpactCounter(), 76.75f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamFloorImpactCounterPer40(), 63.9583f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamOffensiveRating(), 101.7033f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamDefensiveRating(), 103.7579f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamEfficiencyDifferential(), -2.0546f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamPlayPct(), 0.3956f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamAssistRate(), 16.835f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamAssistToTurnoverRatio(), 1.4667f));
        assertEquals(0, Float.compare(teamBoxScore.getTeamStealToTurnoverRatio(), 73.3333f));

        assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointAttempts(), (short)65));
        assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointMade(), (short)36));
        assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointPct(), (float)0.5538));
        assertEquals(0, Float.compare(teamBoxScore.getOpptTwoPointRate(), (float)0.7831));
        assertEquals(0, Float.compare(teamBoxScore.getOpptFieldGoalPct(), (float)0.5181));
        assertEquals(0, Float.compare(teamBoxScore.getOpptThreePointPct(), (float)0.3889));
        assertEquals(0, Float.compare(teamBoxScore.getOpptThreePointRate(), (float)0.2169));
        assertEquals(0, Float.compare(teamBoxScore.getOpptFreeThrowPct(), (float)0.72));
        assertEquals(0, Float.compare(teamBoxScore.getOpptFreeThrowRate(), (float)0.3012));
        assertEquals(0, Float.compare(teamBoxScore.getOpptReboundsTotal(), (short)40));

        assertEquals(0, Float.compare(teamBoxScore.getOpptTrueShootingPct(), 0.5372f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptEffectiveFieldGoalPct(), 0.5602f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptOffensiveReboundPct(), 25.0f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptDefensiveReboundPct(), 75.0f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptTotalReboundPct(), 47.619f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptAssistedFieldGoalPct(), 46.5116f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptTurnoverPct(), 12.963f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptStealPct(), 11.3004f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptBlockPct(), 4.1092f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptBlockRate(), 6.1538f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptPointsPerShot(), 1.2169f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptFloorImpactCounter(), 73.375f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptFloorImpactCounterPer40(), 61.1458f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptOffensiveRating(), 103.7579f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptDefensiveRating(), 101.7033f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptEfficiencyDifferential(), 2.0546f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptPlayPct(), 0.4943f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptAssistRate(), 15.625f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptAssistToTurnoverRatio(), 1.4286f));
        assertEquals(0, Float.compare(teamBoxScore.getOpptStealToTurnoverRatio(), 78.5714f));

        assertEquals(0, Float.compare(teamBoxScore.getPossessions(), 97.342f));
        assertEquals(0, Float.compare(teamBoxScore.getPace(), 97.342f));
    }

    private TeamBoxScore createMockTeamBoxScore() {
        TeamBoxScore teamBoxScore = new TeamBoxScore();
        teamBoxScore.setGameDateTime("2017-03-30T00:00");
        teamBoxScore.setSeasonType("Regular");
        teamBoxScore.setOfficialLastName1("Tiven");
        teamBoxScore.setOfficialFirstName1("Josh");
        teamBoxScore.setOfficialLastName2("Ford");
        teamBoxScore.setOfficialFirstName2("Tyler");
        teamBoxScore.setOfficialLastName3("Kennedy");
        teamBoxScore.setOfficialFirstName3("Bill");
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
        teamBoxScore.setOpptSteals((short)11);
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