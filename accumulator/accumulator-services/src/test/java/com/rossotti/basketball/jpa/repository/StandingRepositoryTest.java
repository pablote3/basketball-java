package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Standing;
import com.rossotti.basketball.jpa.model.Team;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class StandingRepositoryTest {

	private StandingRepository standingRepository;

	@Autowired
	public void setStandingRepository(StandingRepository standingRepository) {
		this.standingRepository = standingRepository;
	}

	@Test
	public void getById() {
		Standing standing = standingRepository.findById(1L);
		assertEquals("1st", standing.getOrdinalRank());
		assertEquals("Chicago Zephyr\'s", standing.getTeam().getFullName());
	}

	@Test
	public void findAll() {
		List<Standing> standings = standingRepository.findAll();
		assertTrue(standings.size() >= 6);
	}

	@Test
	public void findByTeamKeyAsOfDate_Found() {
		Standing standing = standingRepository.findByTeamKeyAndStandingDate("chicago-zephyr's", LocalDate.of(2015, 10, 30));
		assertEquals("1st", standing.getOrdinalRank());
	}

	@Test
	public void findByTeamKeyAsOfDate_NotFound_TeamKey() {
		Standing standing = standingRepository.findByTeamKeyAndStandingDate("chicago-zephyr", LocalDate.of(2015, 10, 30));
		assertNull(standing);
	}

	@Test
	public void findByTeamKeyAsOfDate_NotFound_AsOfDate() {
		Standing standing = standingRepository.findByTeamKeyAndStandingDate("chicago-zephyr's", LocalDate.of(2015, 10, 29));
		assertNull(standing);
	}

	@Test
	public void findByAsOfDate_Found() {
		List<Standing> standings = standingRepository.findByStandingDate(LocalDate.of(2015, 10, 30));
		assertEquals(2, standings.size());
	}

	@Test
	public void findByAsOfDate_NotFound() {
		List<Standing> standings = standingRepository.findByStandingDate(LocalDate.of(2015, 10, 29));
		assertEquals(0, standings.size());
	}

	@Test
	public void findByTeamKey_Found() {
		List<Standing> standings = standingRepository.findByTeamKey("st-louis-bomber's");
		assertTrue(standings.size() >= 2);
	}

	@Test
	public void findByTeamKey_NotFound() {
		List<Standing> standings = standingRepository.findByTeamKey("st-louis-bomber");
		assertEquals(0, standings.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		standingRepository.save(createMockStanding(20L, LocalDate.of(2012, 7, 1), "10th"));
		Standing findStanding = standingRepository.findByTeamKeyAndStandingDate("chicago-bulls", LocalDate.of(2012, 7, 1));
		assertEquals("10th", findStanding.getOrdinalRank());
	}
	@Test
	public void create_Existing() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				standingRepository.save(createMockStanding(1L, LocalDate.of(2015, 10, 30), "10th"));
			});
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				standingRepository.save(createMockStanding(20L, LocalDate.of(2012, 7, 1), null));
			});
	}

	@Test
	public void update_Updated() {
		standingRepository.save(updateMockStanding(LocalDate.of(2015, 10, 31), "10th"));
		Standing standing = standingRepository.findByTeamKeyAndStandingDate("salinas-cowboys", LocalDate.of(2015, 10, 31));
		assertEquals("10th", standing.getOrdinalRank());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				standingRepository.save(updateMockStanding(LocalDate.of(2015, 10, 31), null));
			});
	}

	@Test
	public void delete_Deleted() {
		standingRepository.deleteById(5L);
		Standing standing = standingRepository.findById(5L);
		assertNull(standing);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()-> {
				standingRepository.deleteById(101L);
			});
	}

	private Standing createMockStanding(Long teamId, LocalDate asOfDate, String ordinalRank) {
		Standing standing = new Standing();
		standing.setTeam(getMockTeam(teamId));
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

	private Team getMockTeam(Long id) {
		Team team = new Team();
		team.setId(id);
		return team;
	}

	private Standing updateMockStanding(LocalDate asOfDate, String ordinalRank) {
		Standing standing = createMockStanding(4L, asOfDate, ordinalRank);
		standing.setId(4L);
		return standing;
	}
}