package com.rossotti.basketball;

import com.rossotti.basketball.batch.PlayerBoxScore;
import com.rossotti.basketball.batch.PlayerBoxScoreProcessor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PlayerBoxScoreTests {

    @Test
    public void processorCalculations() {
        PlayerBoxScoreProcessor playerBoxScoreProcessor = new PlayerBoxScoreProcessor();
        PlayerBoxScore playerBoxScore = playerBoxScoreProcessor.process(createMockPlayerBoxScore());
        assertEquals(0, Float.compare(playerBoxScore.getTwoPointAttempts(), (short)69));
        assertEquals(0, Float.compare(playerBoxScore.getTwoPointMade(), (short)28));
        assertEquals(0, Float.compare(playerBoxScore.getTwoPointPct(), (float)0.4058));
        assertEquals(0, Float.compare(playerBoxScore.getFieldGoalPct(), (float)0.4286));
        assertEquals(0, Float.compare(playerBoxScore.getThreePointPct(), (float)0.5333));
        assertEquals(0, Float.compare(playerBoxScore.getFreeThrowPct(), (float)0.6818));
        assertEquals(0, Float.compare(playerBoxScore.getReboundsTotal(), (short)44));
    }

    private PlayerBoxScore createMockPlayerBoxScore() {
        PlayerBoxScore playerBoxScore = new PlayerBoxScore();
        playerBoxScore.setGameDateTime("2017-03-30T00:00");
        playerBoxScore.setPlayerLastName("Toesome");
        playerBoxScore.setPlayerFirstName("Biff");
        playerBoxScore.setSeasonType("Regular");
        playerBoxScore.setTeamAbbr("LAL");
        playerBoxScore.setTeamConference("West");
        playerBoxScore.setTeamDivision("Pacific");
        playerBoxScore.setTeamLocation("Home");
        playerBoxScore.setTeamResult("Loss");
        playerBoxScore.setTeamDaysOff((short)2);
        playerBoxScore.setStatus("1");

        playerBoxScore.setPoints((short)99);
        playerBoxScore.setAssists((short)22);
        playerBoxScore.setTurnovers((short)15);
        playerBoxScore.setSteals((short)11);
        playerBoxScore.setBlocks((short)5);
        playerBoxScore.setPersonalFouls((short)18);
        playerBoxScore.setFieldGoalAttempts((short)84);
        playerBoxScore.setFieldGoalMade((short)36);
        playerBoxScore.setThreePointAttempts((short)15);
        playerBoxScore.setThreePointMade((short)8);
        playerBoxScore.setFreeThrowAttempts((short)22);
        playerBoxScore.setFreeThrowMade((short)15);
        playerBoxScore.setReboundsOffense((short)8);
        playerBoxScore.setReboundsDefense((short)36);

        playerBoxScore.setOpptAbbr("SAC");
        playerBoxScore.setOpptConference("West");
        playerBoxScore.setOpptDivision("Pacific");
        playerBoxScore.setOpptLocation("Home");
        playerBoxScore.setOpptResult("Win");
        playerBoxScore.setOpptDaysOff((short)1);

        return playerBoxScore;
    }
}