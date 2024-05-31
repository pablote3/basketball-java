package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Team;
import com.rossotti.basketball.jpa.model.Team.Conference;
import com.rossotti.basketball.jpa.model.Team.Division;
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
public class TeamRepositoryTest {

	private TeamRepository teamRepository;

	@Autowired
	public void setTeamRepository(TeamRepository teamRepository) {
		this.teamRepository = teamRepository;
	}

	@Test
	public void getById() {
		Team team = teamRepository.findById(1L);
		assertEquals("Chicago Zephyr's", team.getFullName());
		assertTrue(team.getStandings().size() >= 1);
	}

	@Test
	public void findAll() {
		List<Team> teams = teamRepository.findAll();
		assertTrue(teams.size() >= 12);
	}

	@Test
	public void findByKey_Found_FromDate() {
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("harlem-globetrotter's", LocalDate.of(2009, 7, 1), LocalDate.of(2009, 7, 1));
		assertEquals("Harlem Globetrotter's", team.getFullName());
	}

	@Test
	public void findByKey_Found_ToDate() {
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("harlem-globetrotter's", LocalDate.of(2010, 6, 30), LocalDate.of(2010, 6, 30));
		assertEquals("Harlem Globetrotter's", team.getFullName());
	}

	@Test
	public void findByKey_NotFound_TeamKey() {
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("harlem-hoopers", LocalDate.of(2009, 7, 2), LocalDate.of(2009, 7, 2));
		assertNull(team);
	}

	@Test
	public void findByKey_NotFound_BeforeAsOfDate() {
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("harlem-globetrotter's", LocalDate.of(2009, 6, 30), LocalDate.of(2009, 6, 30));
		assertNull(team);
	}

	@Test
	public void findByKey_NotFound_AfterAsOfDate() {
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("harlem-globetrotter's", LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 1));
		assertNull(team);
	}

	@Test
	public void findByLastName_Found_FromDate() {
		Team team = teamRepository.findByLastNameAndFromDateAndToDate("Globetrotter's", LocalDate.of(2009, 7, 2), LocalDate.of(2009, 7, 2));
		assertEquals("Harlem Globetrotter's", team.getFullName());
	}

	@Test
	public void findByLastName_Found_ToDate() {
		Team team = teamRepository.findByLastNameAndFromDateAndToDate("Globetrotter's", LocalDate.of(2010, 6, 29), LocalDate.of(2010, 6, 29));
		assertEquals("Harlem Globetrotter's", team.getFullName());
	}

	@Test
	public void findByLastName_NotFound_TeamKey() {
		Team team = teamRepository.findByLastNameAndFromDateAndToDate("Globetreker's", LocalDate.of(2009, 7, 2), LocalDate.of(2009, 7, 2));
		assertNull(team);
	}

	@Test
	public void findByLastName_NotFound_BeforeAsOfDate() {
		Team team = teamRepository.findByLastNameAndFromDateAndToDate("Globetrotter's", LocalDate.of(2009, 6, 30), LocalDate.of(2009, 6, 30));
		assertNull(team);
	}

	@Test
	public void findByLastName_NotFound_AfterAsOfDate() {
		Team team = teamRepository.findByLastNameAndFromDateAndToDate("Globetrotter's", LocalDate.of(2016, 7, 1), LocalDate.of(2016, 7, 1));
		assertNull(team);
	}

	@Test
	public void findByKey_Found() {
		List<Team> teams = teamRepository.findByTeamKey("st-louis-bomber's");
		assertEquals(2, teams.size());
	}

	@Test
	public void findByKey_NotFound() {
		List<Team> teams = teamRepository.findByTeamKey("st-louis-bombber's");
		assertEquals(0, teams.size());
	}

	@Test
	public void findByDate_Found() {
		List<Team> teams = teamRepository.findByFromDateAndToDate(LocalDate.of(2009, 10, 30), LocalDate.of(2009, 10, 30));
		assertTrue(teams.size() >= 3);
	}

	@Test
	public void findByDate_NotFound() {
		List<Team> teams = teamRepository.findByFromDateAndToDate(LocalDate.of(1909, 10, 30), LocalDate.of(1909, 10, 30));
		assertEquals(0, teams.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		teamRepository.save(createMockTeam("baltimore-bullets", LocalDate.of(2006, 7, 1), LocalDate.of(9999, 12, 31), "Baltimore Bullets2"));
		Team findTeam = teamRepository.findByTeamKeyAndFromDateAndToDate("baltimore-bullets", LocalDate.of(2006, 7, 2), LocalDate.of(2006, 7, 20));
		assertEquals("Baltimore Bullets2", findTeam.getFullName());
	}

	@Test
	public void create_Existing() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				teamRepository.save(createMockTeam("baltimore-bullets", LocalDate.of(2005, 7, 1), LocalDate.of(2006, 6, 30), "Baltimore Bullets"));
			});
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				teamRepository.save(createMockTeam("baltimore-bullets", LocalDate.of(2005, 7, 1), LocalDate.of(2006, 6, 30), null));
			});
	}

	@Test
	public void update_Updated() {
		teamRepository.save(updateMockTeam(LocalDate.of(2009, 7, 1), LocalDate.of(2010, 6, 30), "St. Louis Bombier's"));
		Team team = teamRepository.findByTeamKeyAndFromDateAndToDate("st-louis-bomber's", LocalDate.of(2010, 5, 30), LocalDate.of(2010, 5, 30));
		assertEquals("St. Louis Bombier's", team.getFullName());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				teamRepository.save(updateMockTeam(LocalDate.of(2009, 7, 1), LocalDate.of(2010, 6, 30), null));
			});
	}

	@Test
	public void delete_Deleted() {
		teamRepository.deleteById(10L);
		Team findTeam = teamRepository.findById(10L);
		assertNull(findTeam);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()->{
				teamRepository.deleteById(101L);
			});
	}

	public static Team createMockTeam(String key, LocalDate fromDate, LocalDate toDate, String fullName) {
		Team team = new Team();
		team.setTeamKey(key);
		team.setFromDate(fromDate);
		team.setToDate(toDate);
		team.setAbbr("SEA");
		team.setFirstName("Seattle");
		team.setLastName("Supersonics");
		team.setConference(Conference.West);
		team.setDivision(Division.Pacific);
		team.setSiteName("Key Arena");
		team.setCity("Seattle");
		team.setState("WA");
		team.setFullName(fullName);
		return team;
	}

	private Team updateMockTeam(LocalDate fromDate, LocalDate toDate, String fullName) {
		Team team = createMockTeam("st-louis-bomber's", fromDate, toDate, fullName);
		team.setId(3L);
		return team;
	}
}