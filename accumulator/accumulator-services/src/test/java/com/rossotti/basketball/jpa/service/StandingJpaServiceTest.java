package com.rossotti.basketball.jpa.service;

import com.rossotti.basketball.jpa.model.Standing;
import com.rossotti.basketball.jpa.model.Team;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class StandingJpaServiceTest {

	private StandingJpaService standingJpaService;

	@Autowired
	public void setStandingJpaService(StandingJpaService standingJpaService) {
		this.standingJpaService = standingJpaService;
	}

	@Test
	public void getById() {
		Standing standing = standingJpaService.getById(1L);
		assertEquals("1st", standing.getOrdinalRank());
		assertEquals("Chicago Zephyr\'s", standing.getTeam().getFullName());
		assertTrue(standing.isFound());
	}

	@Test
	public void listAll() {
		List<Standing> standings = (List<Standing>) standingJpaService.listAll();
		assertTrue(standings.size() >= 4);
	}

	@Test
	public void findByTeamKey_Found() {
		List<Standing> standings = standingJpaService.findByTeamKey("st-louis-bomber's");
		assertTrue(standings.size() >= 2);
	}

	@Test
	public void findByTeamKey_NotFound() {
		List<Standing> standings = standingJpaService.findByTeamKey("st-louis-bomber");
		assertEquals(0, standings.size());
	}

	@Test
	public void findByAsOfDate_Found() {
		List<Standing> standings = standingJpaService.findByAsOfDate(LocalDate.of(2015, 10, 30));
		assertEquals(2, standings.size());
	}

	@Test
	public void findByAsOfDate_NotFound() {
		List<Standing> standings = standingJpaService.findByAsOfDate(LocalDate.of(2015, 10, 29));
		assertEquals(0, standings.size());
	}

	@Test
	public void findByTeamKeyAsOfDate_Found() {
		Standing standing = standingJpaService.findByTeamKeyAndAsOfDate("chicago-zephyr's", LocalDate.of(2015, 10, 30));
		assertEquals("1st", standing.getOrdinalRank());
		assertTrue(standing.isFound());
	}

	@Test
	public void findByTeamKeyAsOfDate_NotFound_TeamKey() {
		Standing standing = standingJpaService.findByTeamKeyAndAsOfDate("chicago-zephyr", LocalDate.of(2015, 10, 30));
		assertTrue(standing.isNotFound());
	}

	@Test
	public void findByTeamKeyAsOfDate_NotFound_AsOfDate() {
		Standing standing = standingJpaService.findByTeamKeyAndAsOfDate("chicago-zephyr's", LocalDate.of(2015, 10, 29));
		assertTrue(standing.isNotFound());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		Standing createStanding = standingJpaService.create(createMockStanding(21L, "utah-jazz", LocalDate.of(2012, 7, 1), "10th"));
		Standing findStanding = standingJpaService.findByTeamKeyAndAsOfDate("utah-jazz", LocalDate.of(2012, 7, 1));
		assertTrue(createStanding.isCreated());
		assertEquals((short)7, (short)findStanding.getConferenceWins());
	}

	@Test
	public void create_Existing() {
		Standing createStanding = standingJpaService.create(createMockStanding(1L, "chicago-zephyr's", LocalDate.of(2015, 10, 30), "10th"));
		assertTrue(createStanding.isFound());
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				standingJpaService.create(createMockStanding(1L, "chicago-zephyr's", LocalDate.of(2016, 11, 1), null));
			});
	}

	@Test
	public void update_Updated() {
		Standing updateStanding = standingJpaService.update(createMockStanding(3L, "st-louis-bomber's", LocalDate.of(2015, 10, 31), "10th"));
		Standing standing = standingJpaService.findByTeamKeyAndAsOfDate("st-louis-bomber's", LocalDate.of(2015, 10, 31));
		assertEquals("10th", standing.getOrdinalRank());
		assertTrue(updateStanding.isUpdated());
	}

	@Test
	public void update_NotFound() {
		Standing standing = standingJpaService.update(createMockStanding(3L, "st-louis-bomber's", LocalDate.of(2015, 11, 11), "10th"));
		assertTrue(standing.isNotFound());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				standingJpaService.update(createMockStanding(3L, "st-louis-bomber's", LocalDate.of(2015, 10, 31), null));
		});
	}

	@Test
	public void delete_Deleted() {
		Standing deleteStanding = standingJpaService.delete(6L);
		Standing findStanding = standingJpaService.getById(6L);
		assertNull(findStanding);
		assertTrue(deleteStanding.isDeleted());
	}

	@Test
	public void delete_NotFound() {
		Standing deleteStanding = standingJpaService.delete(101L);
		assertTrue(deleteStanding.isNotFound());
	}

	private Standing createMockStanding(Long teamId, String teamKey, LocalDate asOfDate, String ordinalRank) {
		Standing standing = new Standing();
		standing.setTeam(createMockTeam(teamId, teamKey));
		standing.setStandingDate(asOfDate);
		standing.setRank((short)3);
		standing.setOrdinalRank(ordinalRank);
		standing.setGamesWon((short)15);
		standing.setGamesLost((short)25);
		standing.setStreak("L5");
		standing.setStreakType("loss");
		standing.setStreakTotal((short)5);
		standing.setGamesBack((float)3.5);
		standing.setPointsFor((short)1895);
		standing.setPointsAgainst((short)2116);
		standing.setHomeWins((short)10);
		standing.setHomeLosses((short)10);
		standing.setAwayWins((short)5);
		standing.setAwayLosses((short)15);
		standing.setConferenceWins((short)7);
		standing.setConferenceLosses((short)8);
		standing.setLastFive("0-5");
		standing.setLastTen("3-7");
		standing.setGamesPlayed((short)40);
		standing.setPointsScoredPerGame((float)95.5);
		standing.setPointsAllowedPerGame((float)102.5);
		standing.setWinPercentage((float)0.375);
		standing.setPointDifferential((short)221);
		standing.setPointDifferentialPerGame((float)7.0);
		standing.setOpptGamesWon(4);
		standing.setOpptGamesPlayed(5);
		standing.setOpptOpptGamesWon(15);
		standing.setOpptOpptGamesPlayed(20);
		return standing;
	}

	private Team createMockTeam(Long id, String teamKey) {
		Team team = new Team();
		team.setId(id);
		team.setTeamKey(teamKey);
		return team;
	}
}
