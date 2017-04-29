package com.rossotti.basketball;

import com.rossotti.basketball.model.TeamBoxScore;
import com.rossotti.basketball.processor.TeamBoxScoreProcessor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.LocalDateTime;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TeamBoxScoreTests {

	@Test
	public void findTeamByTeamKey_notFound() {
		TeamBoxScoreProcessor teamBoxScoreProcessor = new TeamBoxScoreProcessor();
		TeamBoxScore team = teamBoxScoreProcessor.process(createMockTeamBoxScore());
		Assert.assertEquals(0, Float.compare(team.getPossessions(), 93.4f));
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
		teamBoxScore.setTeamMinutes((short)120);
		teamBoxScore.setTeamDaysOff((short)2);
		teamBoxScore.setTeamPoints((short)99);
		teamBoxScore.setTeamAssists((short)22);
		teamBoxScore.setTeamTurnovers((short)33);
		teamBoxScore.setTeamSteals((short)11);
		teamBoxScore.setTeamBlocks((short)9);
		teamBoxScore.setTeamPersonalFouls((short)18);
		teamBoxScore.setTeamFieldGoalAttempts((short)25);
		teamBoxScore.setTeamFieldGoalMade((short)20);
		teamBoxScore.setTeamThreePointAttempts((short)15);
		teamBoxScore.setTeamThreePointMade((short)10);
		teamBoxScore.setTeamFreeThrowAttempts((short)15);
		teamBoxScore.setTeamFreeThrowMade((short)10);
		teamBoxScore.setTeamReboundsOffense((short)15);
		teamBoxScore.setTeamReboundsDefense((short)10);
		teamBoxScore.setTeamPoints((short)50);
		teamBoxScore.setTeamPoints((short)50);
		teamBoxScore.setTeamPoints((short)50);
		teamBoxScore.setTeamPoints((short)49);
		teamBoxScore.setTeamPoints((short)0);
		teamBoxScore.setTeamPoints((short)0);
		teamBoxScore.setTeamPoints((short)0);
		teamBoxScore.setTeamPoints((short)0);
		teamBoxScore.setOpptAbbr("SAC");
		teamBoxScore.setOpptConference("West");
		teamBoxScore.setOpptDivision("Pacific");
		teamBoxScore.setOpptLocation("Home");
		teamBoxScore.setOpptResult("Win");
		teamBoxScore.setOpptMinutes((short)120);
		teamBoxScore.setOpptDaysOff((short)1);
		teamBoxScore.setOpptPoints((short)101);
		teamBoxScore.setOpptAssists((short)20);
		teamBoxScore.setOpptTurnovers((short)31);
		teamBoxScore.setOpptSteals((short)13);
		teamBoxScore.setOpptBlocks((short)13);
		teamBoxScore.setOpptPersonalFouls((short)19);
		teamBoxScore.setOpptFieldGoalAttempts((short)23);
		teamBoxScore.setOpptFieldGoalMade((short)15);
		teamBoxScore.setOpptThreePointAttempts((short)18);
		teamBoxScore.setOpptThreePointMade((short)14);
		teamBoxScore.setOpptFreeThrowAttempts((short)12);
		teamBoxScore.setOpptFreeThrowMade((short)8);
		teamBoxScore.setOpptReboundsOffense((short)14);
		teamBoxScore.setOpptReboundsDefense((short)17);
		teamBoxScore.setOpptPoints((short)50);
		teamBoxScore.setOpptPoints((short)50);
		teamBoxScore.setOpptPoints((short)50);
		teamBoxScore.setOpptPoints((short)51);
		teamBoxScore.setOpptPoints((short)0);
		teamBoxScore.setOpptPoints((short)0);
		teamBoxScore.setOpptPoints((short)0);
		teamBoxScore.setOpptPoints((short)0);
		return teamBoxScore;
	}

}
