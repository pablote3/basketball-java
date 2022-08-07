package com.rossotti.basketball.jpa.service;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import com.rossotti.basketball.jpa.model.Team;
import com.rossotti.basketball.jpa.repository.TeamRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class TeamJpaServiceTest {

	private TeamJpaService teamJpaService;

	@Autowired
	public void setTeamJpaService(TeamJpaService teamJpaService) {
		this.teamJpaService = teamJpaService;
	}

	@Test
	public void getById() {
		Team team = teamJpaService.getById(1L);
		assertEquals("Chicago Zephyr's", team.getFullName());
		assertTrue(team.getStandings().size() >= 1);
	}

	@Test
	public void listAll() {
		List<Team> teams = (List<Team>) teamJpaService.listAll();
		assertTrue(teams.size() >= 10);
	}

	@Test
	public void findByKey_Found_FromDate() {
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("harlem-globetrotter's", LocalDate.of(2009, 7, 1));
		assertEquals("Harlem Globetrotter's", team.getFullName());
		assertTrue(team.isFound());
	}

	@Test
	public void findByKey_Found_ToDate() {
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("harlem-globetrotter's", LocalDate.of(2010, 6, 30));
		assertEquals("Harlem Globetrotter's", team.getFullName());
		assertTrue(team.isFound());
	}

	@Test
	public void findByKey_NotFound_TeamKey() {
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("harlem-hooper's", LocalDate.of(2009, 7, 1));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByKey_NotFound_BeforeAsOfDate() {
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("harlem-globetrotter's", LocalDate.of(2009, 6, 30));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByKey_NotFound_AfterAsOfDate() {
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("harlem-globetrotter's", LocalDate.of(2016, 7, 1));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByLastName_Found_FromDate() {
		Team team = teamJpaService.findByLastNameAndAsOfDate("Globetrotter's", LocalDate.of(2009, 7, 1));
		assertEquals("Harlem Globetrotter's", team.getFullName());
		assertTrue(team.isFound());
	}

	@Test
	public void findByLastName_Found_ToDate() {
		Team team = teamJpaService.findByLastNameAndAsOfDate("Globetrotter's", LocalDate.of(2010, 6, 30));
		assertEquals("Harlem Globetrotter's", team.getFullName());
		assertTrue(team.isFound());
	}

	@Test
	public void findByLastName_NotFound_TeamKey() {
		Team team = teamJpaService.findByLastNameAndAsOfDate("Globetreker's", LocalDate.of(2009, 7, 1));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByLastName_NotFound_BeforeAsOfDate() {
		Team team = teamJpaService.findByLastNameAndAsOfDate("Globetrotter's", LocalDate.of(2009, 6, 30));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByLastName_NotFound_AfterAsOfDate() {
		Team team = teamJpaService.findByLastNameAndAsOfDate("Globetrotter's", LocalDate.of(2016, 7, 1));
		assertTrue(team.isNotFound());
	}

	@Test
	public void findByTeamKey() {
		List<Team> teams = teamJpaService.findByTeamKey("salinas-cowboys");
		assertEquals("Salinas Cowboys", teams.get(0).getFullName());
	}

	@Test
	public void findByKey_Found() {
		List<Team> teams = teamJpaService.findByTeamKey("st-louis-bomber's");
		assertEquals(2, teams.size());
	}

	@Test
	public void findByKey_NotFound() {
		List<Team> teams = teamJpaService.findByTeamKey("st-louis-bombber's");
		assertEquals(0, teams.size());
	}

	@Test
	public void findByDateRange_Found() {
		List<Team> teams = teamJpaService.findByDate(LocalDate.of(2009, 10, 30));
		assertTrue(teams.size() >= 3);
	}

	@Test
	public void findByDateRange_NotFound() {
		List<Team> teams = teamJpaService.findByDate(LocalDate.of(1909, 10, 30));
		assertEquals(0, teams.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created_AsOfDate() {
		Team createTeam = teamJpaService.create(TeamRepositoryTest.createMockTeam("sacramento-hornets", LocalDate.of(2012, 7, 1), LocalDate.of(9999, 12, 31), "Sacramento Hornets"));
		Team findTeam = teamJpaService.findByTeamKeyAndAsOfDate("sacramento-hornets", LocalDate.of(2012, 7, 1));
		assertTrue(createTeam.isCreated());
		assertEquals("Sacramento Hornets", findTeam.getFullName());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created_DateRange() {
		Team createTeam = teamJpaService.create(TeamRepositoryTest.createMockTeam("sacramento-rivercats", LocalDate.of(2006, 7, 1), LocalDate.of(2012, 7, 2), "Sacramento Rivercats"));
		Team findTeam = teamJpaService.findByTeamKeyAndAsOfDate("sacramento-rivercats", LocalDate.of(2006, 7, 1));
		assertTrue(createTeam.isCreated());
		assertEquals("Sacramento Rivercats", findTeam.getFullName());
	}

	@Test
	public void create_OverlappingDates() {
		Team createTeam = teamJpaService.create(TeamRepositoryTest.createMockTeam("cleveland-rebels", LocalDate.of(2010, 7, 1), LocalDate.of(2010, 7, 1), "Cleveland Rebels"));
		assertTrue(createTeam.isFound());
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				teamJpaService.create(TeamRepositoryTest.createMockTeam("chavo-del-ocho", LocalDate.of(2010, 7, 1), LocalDate.of(2010, 7, 1), null));
			});
	}

	@Test
	public void update_Updated() {
		Team updateTeam = teamJpaService.update(TeamRepositoryTest.createMockTeam("st-louis-bomber's", LocalDate.of(2009, 7, 1), LocalDate.of(9999, 12, 31), "St. Louis Bombier's"));
		Team team = teamJpaService.findByTeamKeyAndAsOfDate("st-louis-bomber's", LocalDate.of(9999, 12, 31));
		assertEquals("St. Louis Bombier's", team.getFullName());
		assertTrue(updateTeam.isUpdated());
	}

	@Test
	public void update_NotFound() {
		Team team = teamJpaService.update(TeamRepositoryTest.createMockTeam("st-louis-bomb's", LocalDate.of(2009, 7, 1), LocalDate.of(2010, 7, 1), "St. Louis Bombier's"));
		assertTrue(team.isNotFound());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				teamJpaService.update(TeamRepositoryTest.createMockTeam("st-louis-bomber's", LocalDate.of(2009, 7, 1), LocalDate.of(2010, 6, 30), null));
			});
	}

	@Test
	public void delete_Deleted() {
		Team deleteTeam = teamJpaService.delete(7L);
		Team findTeam = teamJpaService.getById(7L);
		assertNull(findTeam);
		assertTrue(deleteTeam.isDeleted());
	}

	@Test
	public void delete_NotFound() {
		Team deleteTeam = teamJpaService.delete(101L);
		assertTrue(deleteTeam.isNotFound());
	}
}
