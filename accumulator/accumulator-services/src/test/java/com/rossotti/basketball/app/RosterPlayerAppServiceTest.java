package com.rossotti.basketball.app;

import com.rossotti.basketball.app.service.RosterPlayerAppService;
import com.rossotti.basketball.client.dto.BoxScorePlayerDTO;
import com.rossotti.basketball.client.dto.RosterPlayerDTO;
import com.rossotti.basketball.jpa.exception.DuplicateEntityException;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.*;
import com.rossotti.basketball.jpa.service.RosterPlayerJpaService;
import com.rossotti.basketball.jpa.service.TeamJpaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class RosterPlayerAppServiceTest {
	@Mock
	private RosterPlayerJpaService rosterPlayerJpaService;

	@Mock
	private TeamJpaService teamJpaService;

	@InjectMocks
	private RosterPlayerAppService rosterPlayerAppService;

	@Test
	public void getBoxScorePlayers_notFound() {
		assertThrows(NoSuchEntityException.class,
			()->{
				when(rosterPlayerJpaService.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(anyString(), anyString(), anyString(), any()))
					.thenReturn(createMockRosterPlayer("", "", StatusCodeDAO.NotFound));
				List<BoxScorePlayer> boxScorePlayers = rosterPlayerAppService.getBoxScorePlayers(createMockBoxScorePlayerDTOs(), createMockBoxScore(), LocalDate.of(1995, 11, 26), "sacramento-hornets");
				assertEquals(0, boxScorePlayers.size());
			});
	}

	@Test
	public void getBoxScorePlayers_found() {
		when(rosterPlayerJpaService.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(anyString(), anyString(), anyString(), any()))
			.thenReturn(createMockRosterPlayer("Coors", "Adolph", StatusCodeDAO.Found));
		List<BoxScorePlayer> boxScorePlayers = rosterPlayerAppService.getBoxScorePlayers(createMockBoxScorePlayerDTOs(), createMockBoxScore(), LocalDate.of(1995, 11, 26), "sacramento-hornets");
		assertEquals(2, boxScorePlayers.size());
		assertEquals("Coors", boxScorePlayers.get(1).getRosterPlayer().getPlayer().getLastName());
		assertEquals("Adolph", boxScorePlayers.get(1).getRosterPlayer().getPlayer().getFirstName());
	}
	
	@Test
	public void getRosterPlayers_notFound() {
		assertThrows(NoSuchEntityException.class,
			()->{
				when(teamJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
					.thenReturn(createMockTeam("denver-mcnuggets", StatusCodeDAO.NotFound));
				List<RosterPlayer> rosterPlayers = rosterPlayerAppService.getRosterPlayers(createMockRosterPlayerDTOs(), LocalDate.of(1995, 11, 26), "sacramento-hornets");
				assertEquals(0, rosterPlayers.size());
			});
	}

	@Test
	public void getRosterPlayers_found() {
		when(teamJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
			.thenReturn(createMockTeam("denver-nuggets", StatusCodeDAO.Found));
		List<RosterPlayer> rosterPlayers = rosterPlayerAppService.getRosterPlayers(createMockRosterPlayerDTOs(), LocalDate.of(1995, 11, 26), "sacramento-hornets");
		assertEquals(2, rosterPlayers.size());
		assertEquals("Clayton", rosterPlayers.get(1).getPlayer().getLastName());
		assertEquals("Mark", rosterPlayers.get(1).getPlayer().getFirstName());
	}

	@Test
	public void findByPlayerNameTeamAsOfDate_notFound() {
		when(rosterPlayerJpaService.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(anyString(), anyString(), anyString(), any()))
			.thenReturn(createMockRosterPlayer("Simmons", "Richard", StatusCodeDAO.NotFound));
		RosterPlayer rosterPlayer = rosterPlayerAppService.findByPlayerNameTeamAsOfDate("Simmons", "Richard", "sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertTrue(rosterPlayer.isNotFound());
	}

	@Test
	public void findByPlayerNameTeamAsOfDate_found() {
		when(rosterPlayerJpaService.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate(anyString(), anyString(), anyString(), any()))
			.thenReturn(createMockRosterPlayer("Simmons", "Gene", StatusCodeDAO.Found));
		RosterPlayer rosterPlayer = rosterPlayerAppService.findByPlayerNameTeamAsOfDate("Simmons", "Gene", "sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertEquals("Gene", rosterPlayer.getPlayer().getFirstName());
		assertTrue(rosterPlayer.isFound());
	}

	@Test
	public void findByPlayerNameBirthdateAsOfDate_notFound() {
		when(rosterPlayerJpaService.findByLastNameAndFirstNameAndBirthdateAndAsOfDate(anyString(), anyString(), any(), any()))
			.thenReturn(createMockRosterPlayer("Simmons", "Richard", StatusCodeDAO.NotFound));
		RosterPlayer rosterPlayer = rosterPlayerAppService.findByPlayerNameBirthdateAsOfDate("Simmons", "Richard", LocalDate.of(1995, 11, 26), LocalDate.of(1995, 11, 26));
		assertTrue(rosterPlayer.isNotFound());
	}

	@Test
	public void findByPlayerNameBirthdateAsOfDate_found() {
		when(rosterPlayerJpaService.findByLastNameAndFirstNameAndBirthdateAndAsOfDate(anyString(), anyString(), any(), any()))
			.thenReturn(createMockRosterPlayer("Simmons", "Gene", StatusCodeDAO.Found));
		RosterPlayer rosterPlayer = rosterPlayerAppService.findByPlayerNameBirthdateAsOfDate("Simmons", "Richard", LocalDate.of(1995, 11, 26), LocalDate.of(1995, 11, 26));
		assertEquals("Gene", rosterPlayer.getPlayer().getFirstName());
		assertTrue(rosterPlayer.isFound());
	}

	@Test
	public void findByTeamKeyAsOfDate_notFound() {
		when(rosterPlayerJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
				.thenReturn(new ArrayList<>());
		List<RosterPlayer> rosterPlayers = rosterPlayerAppService.findByTeamKeyAsOfDate(LocalDate.of(1995, 11, 26), "sacramento-hornets");
		assertEquals(new ArrayList<RosterPlayer>(), rosterPlayers);
	}

	@Test
	public void findByTeamKeyAsOfDate_found() {
		when(rosterPlayerJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
				.thenReturn(createMockRosterPlayers());
		List<RosterPlayer> rosterPlayers = rosterPlayerAppService.findByTeamKeyAsOfDate(LocalDate.of(1995, 11, 26), "sacramento-hornets");
		assertEquals(2, rosterPlayers.size());
		assertEquals("Simpson", rosterPlayers.get(1).getPlayer().getLastName());
		assertEquals("Lisa", rosterPlayers.get(1).getPlayer().getFirstName());
	}

	@Test
	public void createRosterPlayer_alreadyExists() {
		assertThrows(DuplicateEntityException.class,
			()->{
				when(rosterPlayerJpaService.create(any()))
					.thenThrow(new DuplicateEntityException(RosterPlayer.class));
				RosterPlayer rosterPlayer = rosterPlayerAppService.createRosterPlayer(createMockRosterPlayer("Smith", "Emmitt", StatusCodeDAO.Found));
				assertTrue(rosterPlayer.isFound());
			});
	}

	@Test
	public void createRosterPlayer_created() {
		when(rosterPlayerJpaService.create(any()))
			.thenReturn(createMockRosterPlayer("Payton", "Walter", StatusCodeDAO.Created));
		RosterPlayer rosterPlayer = rosterPlayerAppService.createRosterPlayer(createMockRosterPlayer("Payton", "Walter", StatusCodeDAO.Created));
		assertEquals("Walter", rosterPlayer.getPlayer().getFirstName());
		assertTrue(rosterPlayer.isCreated());
	}

	@Test
	public void updateRosterPlayer_notFound() {
		when(rosterPlayerJpaService.update(any()))
			.thenReturn(createMockRosterPlayer("Lima", "Roger", StatusCodeDAO.NotFound));
		RosterPlayer rosterPlayer = rosterPlayerAppService.updateRosterPlayer(createMockRosterPlayer("Roger", "Lima", StatusCodeDAO.NotFound));
		assertEquals("Roger", rosterPlayer.getPlayer().getFirstName());
		assertTrue(rosterPlayer.isNotFound());
	}

	@Test
	public void updateRosterPlayer_updated() {
		when(rosterPlayerJpaService.update(any()))
			.thenReturn(createMockRosterPlayer("Schaub", "Buddy", StatusCodeDAO.Updated));
		RosterPlayer rosterPlayer = rosterPlayerAppService.updateRosterPlayer(createMockRosterPlayer("Schaub", "Buddy", StatusCodeDAO.Found));
		assertEquals("Buddy", rosterPlayer.getPlayer().getFirstName());
		assertTrue(rosterPlayer.isUpdated());
	}

	private BoxScorePlayerDTO[] createMockBoxScorePlayerDTOs() {
		BoxScorePlayerDTO[] boxScorePlayers = new BoxScorePlayerDTO[2];
		boxScorePlayers[0] = createMockBoxScorePlayerDTO("Adams", "Samuel");
		boxScorePlayers[1] = createMockBoxScorePlayerDTO("Coors", "Adolph");
		return boxScorePlayers;
	}

	private BoxScorePlayerDTO createMockBoxScorePlayerDTO(String lastName, String firstName) {
		BoxScorePlayerDTO boxScorePlayer = new BoxScorePlayerDTO();
		boxScorePlayer.setLast_name(lastName);
		boxScorePlayer.setFirst_name(firstName);
		boxScorePlayer.setPosition("C");
		boxScorePlayer.setMinutes((short)25);
		boxScorePlayer.setIs_starter(true);
		boxScorePlayer.setPoints((short)12);
		boxScorePlayer.setAssists((short)3);
		boxScorePlayer.setTurnovers((short)0);
		boxScorePlayer.setSteals((short)2);
		boxScorePlayer.setBlocks((short)15);
		boxScorePlayer.setField_goals_attempted((short)8);
		boxScorePlayer.setField_goals_made((short)4);
		boxScorePlayer.setField_goal_percentage((float).5);
		boxScorePlayer.setThree_point_field_goals_attempted((short)3);
		boxScorePlayer.setThree_point_field_goals_made((short)1);
		boxScorePlayer.setThree_point_percentage((float).333);
		boxScorePlayer.setFree_throws_attempted((short)10);
		boxScorePlayer.setFree_throws_made((short)1);
		boxScorePlayer.setFree_throw_percentage((float).1);
		boxScorePlayer.setOffensive_rebounds((short)0);
		boxScorePlayer.setDefensive_rebounds((short)10);
		boxScorePlayer.setPersonal_fouls((short)4);
		return boxScorePlayer;
	}

	private RosterPlayerDTO[] createMockRosterPlayerDTOs() {
		RosterPlayerDTO[] rosterPlayers = new RosterPlayerDTO[2];
		rosterPlayers[0] = createMockRosterPlayerDTO("Marino", "Dan");
		rosterPlayers[1] = createMockRosterPlayerDTO("Clayton", "Mark");
		return rosterPlayers;
	}

	private RosterPlayerDTO createMockRosterPlayerDTO(String lastName, String firstName) {
		RosterPlayerDTO rosterPlayer = new RosterPlayerDTO();
		rosterPlayer.setLast_name(lastName);
		rosterPlayer.setFirst_name(firstName);
		rosterPlayer.setDisplay_name(firstName + " " + lastName);
		rosterPlayer.setHeight_in((short)82);
		rosterPlayer.setWeight_lb((short)200);
		rosterPlayer.setBirthdate(LocalDate.of(1995, 11, 26));
		rosterPlayer.setBirthplace("Kalamazoo, KS");
		rosterPlayer.setUniform_number("25");
		rosterPlayer.setPosition("G");
		return rosterPlayer;
	}

	private List<RosterPlayer> createMockRosterPlayers() {
		return Arrays.asList(
			createMockRosterPlayer("Simpson", "Homer", StatusCodeDAO.Found),
			createMockRosterPlayer("Simpson", "Lisa", StatusCodeDAO.Found)
		);
	}

	private RosterPlayer createMockRosterPlayer(String lastName, String firstName, StatusCodeDAO statusCode) {
		RosterPlayer rosterPlayer = new RosterPlayer();
		Player player = new Player();
		rosterPlayer.setPlayer(player);
		rosterPlayer.setStatusCode(statusCode);
		rosterPlayer.setFromDate(LocalDate.of(2015, 11, 26));
		player.setLastName(lastName);
		player.setFirstName(firstName);
		player.setBirthdate(LocalDate.of(1995, 11, 26));
		return rosterPlayer;
	}

	private Team createMockTeam(String teamKey, StatusCodeDAO statusCode) {
		Team team = new Team();
		team.setTeamKey(teamKey);
		team.setStatusCode(statusCode);
		return team;
	}

	private BoxScore createMockBoxScore() {
		return new BoxScore();
	}
}