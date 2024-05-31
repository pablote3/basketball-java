package com.rossotti.basketball.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossotti.basketball.app.service.PlayerAppService;
import com.rossotti.basketball.app.service.RosterPlayerAppService;
import com.rossotti.basketball.business.model.RosterPlayerBusiness;
import com.rossotti.basketball.business.service.RosterPlayerBusService;
import com.rossotti.basketball.client.dto.RosterDTO;
import com.rossotti.basketball.client.dto.RosterPlayerDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO.StatusCode;
import com.rossotti.basketball.client.service.FileStatsService;
import com.rossotti.basketball.client.service.RestStatsService;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.Player;
import com.rossotti.basketball.jpa.model.RosterPlayer;
import com.rossotti.basketball.jpa.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class RosterPlayerBusServiceTest {
	@Mock
	private Environment env;

	@Mock
	private FileStatsService fileStatsService;

	@Mock
	private RestStatsService restStatsService;

	@Mock
	private RosterPlayerAppService rosterPlayerAppService;

	@Mock
	private PlayerAppService playerAppService;

	@InjectMocks
	private RosterPlayerBusService rosterPlayerBusService;

	private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

	@Test
	public void propertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isServerError());
	}

	@Test
	public void propertyNull() {
		when(env.getProperty(anyString()))
			.thenReturn(null);
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isServerError());
	}

	@Test
	public void fileClientService_rosterNotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveRoster(anyString(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.NotFound));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void fileClientService_clientException() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveRoster(anyString(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.ClientException));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void fileClientService_emptyList() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveRoster(anyString(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.Found));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void restClientService_rosterNotFound() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.NotFound));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void restClientService_clientException() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.ClientException));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void restClientService_emptyList() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_StatusCode(StatusCode.Found));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void rosterPlayerService_noSuchEntity_team() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_Found());
		when(rosterPlayerAppService.getRosterPlayers(any(), any(), anyString()))
			.thenThrow(new NoSuchEntityException(Team.class));
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isClientError());
	}

	@Test
	public void rosterPlayerService_getRosterPlayers_emptyList() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_Found());
		when(rosterPlayerAppService.getRosterPlayers(any(), any(), anyString()))
			.thenReturn(new ArrayList<>());
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isServerError());
	}

	@Test
	public void rosterPlayerService_findRosterPlayers_emptyList() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_Found());
		when(rosterPlayerAppService.getRosterPlayers(any(), any(), anyString()))
			.thenReturn(createMockRosterPlayers());
		when(rosterPlayerAppService.findByPlayerNameTeamAsOfDate(anyString(), anyString(), anyString(), any()))
			.thenReturn(new RosterPlayer(StatusCodeDAO.NotFound));
		when(rosterPlayerAppService.findByPlayerNameBirthdateAsOfDate(anyString(), anyString(), any(), any()))
			.thenReturn(new RosterPlayer(StatusCodeDAO.NotFound));
		when(playerAppService.findByPlayerNameBirthdate(anyString(), anyString(), any()))
			.thenReturn(new Player(StatusCodeDAO.NotFound));
		when(playerAppService.createPlayer(any()))
			.thenReturn(createMockPlayer("Jones", "Basketball"));
		when(rosterPlayerAppService.findByTeamKeyAsOfDate(any(), anyString()))
			.thenReturn(new ArrayList<>());
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isServerError());
	}

	@Test
	public void loadRoster_rosterUpdated() {
		when(env.getProperty("accumulator.source.roster"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveRoster(anyString(), anyBoolean(), any()))
			.thenReturn(createMockRosterDTO_Found());
		when(rosterPlayerAppService.getRosterPlayers(any(), any(), anyString()))
			.thenReturn(createMockRosterPlayers());
		when(rosterPlayerAppService.findByPlayerNameTeamAsOfDate(anyString(), anyString(), anyString(), any()))
			.thenReturn(new RosterPlayer(StatusCodeDAO.NotFound));
		when(rosterPlayerAppService.findByPlayerNameBirthdateAsOfDate(anyString(), anyString(), any(), any()))
			.thenReturn(new RosterPlayer(StatusCodeDAO.NotFound));
		when(playerAppService.findByPlayerNameBirthdate(anyString(), anyString(), any()))
			.thenReturn(new Player(StatusCodeDAO.NotFound));
		when(playerAppService.createPlayer(any()))
			.thenReturn(createMockPlayer("Jones", "Basketball"));
		when(rosterPlayerAppService.findByTeamKeyAsOfDate(any(), anyString()))
			.thenReturn(createMockRosterPlayers());
		RosterPlayerBusiness roster = rosterPlayerBusService.loadRoster("2014-10-28", "detroit-pistons");
		assertTrue(roster.isCompleted());
	}

	private RosterDTO createMockRosterDTO_Found() {
		RosterDTO roster;
		try {
			InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json");
			roster = objectMapper.readValue(baseJson, RosterDTO.class);
			roster.setStatusCode(StatusCode.Found);
		}
		catch (IOException e) {
			roster = new RosterDTO();
			roster.setStatusCode(StatusCode.ClientException);
		}
		return roster;
	}

	private RosterDTO createMockRosterDTO_StatusCode(StatusCode statusCode) {
		RosterDTO roster = new RosterDTO();
		roster.setStatusCode(statusCode);
		roster.players = new RosterPlayerDTO[0];
		return roster;
	}

	private List<RosterPlayer> createMockRosterPlayers() {
		List<RosterPlayer> rosterPlayers = new ArrayList<>();
		rosterPlayers.add(createMockRosterPlayer("Morris", "Marcus"));
		rosterPlayers.add(createMockRosterPlayer("Drummond", "Andre"));
		return rosterPlayers;
	}

	private RosterPlayer createMockRosterPlayer(String lastName, String firstName) {
		RosterPlayer rosterPlayer = new RosterPlayer();
		rosterPlayer.setFromDate(LocalDate.of(2015, 10, 27));
		rosterPlayer.setToDate(LocalDate.of(2015, 12, 27));
		rosterPlayer.setTeam(createMockTeam());
		rosterPlayer.setPlayer(createMockPlayer(lastName, firstName));
		return rosterPlayer;
	}

	private Player createMockPlayer(String lastName, String firstName) {
		Player player = new Player();
		player.setLastName(lastName);
		player.setFirstName(firstName);
		player.setBirthdate(LocalDate.of(1995, 12, 27));
		return player;
	}

	private Team createMockTeam() {
		Team team = new Team();
		team.setTeamKey("brooklyn-nets");
		return team;
	}
}