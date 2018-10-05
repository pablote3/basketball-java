package com.rossotti.basketball.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossotti.basketball.app.service.GameAppService;
import com.rossotti.basketball.app.service.OfficialAppService;
import com.rossotti.basketball.app.service.RosterPlayerAppService;
import com.rossotti.basketball.app.service.TeamAppService;
import com.rossotti.basketball.business.model.GameBusiness;
import com.rossotti.basketball.business.model.StatusCodeBusiness;
import com.rossotti.basketball.business.service.GameBusService;
import com.rossotti.basketball.client.dto.GameDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO.StatusCode;
import com.rossotti.basketball.client.service.FileStatsService;
import com.rossotti.basketball.client.service.RestStatsService;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.*;
import com.rossotti.basketball.jpa.model.BoxScore.Location;
import com.rossotti.basketball.jpa.model.Game.GameStatus;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GameBusServiceTest {
	@Mock
	private Environment env;

	@Mock
	private FileStatsService fileStatsService;

	@Mock
	private RestStatsService restStatsService;

	@Mock
	private RosterPlayerAppService rosterPlayerAppService;

	@Mock
	private OfficialAppService officialAppService;

	@Mock
	private TeamAppService teamAppService;

	@Mock
	private GameAppService gameAppService;

	@InjectMocks
	private GameBusService gameBusService;

	private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

	@Test
	public void propertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isServerError());
	}

	@Test
	public void propertyNull() {
		when(env.getProperty(anyString()))
			.thenReturn(null);
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isServerError());
	}

	@Test
	public void fileClientService_gameNotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveBoxScore(anyString()))
			.thenReturn(createMockGameDTO_StatusCode(StatusCode.NotFound));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isClientError());
	}

	@Test
	public void fileClientService_clientException() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveBoxScore(anyString()))
			.thenReturn(createMockGameDTO_StatusCode(StatusCode.ClientException));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isClientError());
	}

	@Test
	public void restClientService_gameNotFound() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_StatusCode(StatusCode.NotFound));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isClientError());
	}

	@Test
	public void restClientService_clientException() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_StatusCode(StatusCode.ClientException));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isClientError());
	}

	@Test
	public void rosterPlayerService_getBoxScorePlayers_appRosterUpdate() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_Found());
		when(rosterPlayerAppService.getBoxScorePlayers(any(), any(), any(), anyString()))
			.thenThrow(new NoSuchEntityException(RosterPlayer.class));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isRosterUpdate());
	}

	@Test
	public void officialService_getGameOfficials_appOfficialError() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_Found());
		when(rosterPlayerAppService.getBoxScorePlayers(any(), any(), any(), anyString()))
			.thenReturn(createMockBoxScorePlayers_Found());
		when(officialAppService.getGameOfficials(any(), any(), any()))
			.thenThrow(new NoSuchEntityException(Official.class));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isOfficialError());
	}

	@Test
	public void teamService_findTeam_appTeamError() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_Found());
		when(rosterPlayerAppService.getBoxScorePlayers(any(), any(), any(), anyString()))
			.thenReturn(createMockBoxScorePlayers_Found());
		when(officialAppService.getGameOfficials(any(), any(), any()))
			.thenReturn(createMockGameOfficials_Found());
		when(teamAppService.findTeamByTeamKey(anyString(), any()))
			.thenThrow(new NoSuchEntityException(Team.class));
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isTeamError());
	}

	@Test
	public void gameService_updateGame_complete() {
		when(env.getProperty("accumulator.source.boxScore"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveBoxScore(anyString(), anyBoolean()))
			.thenReturn(createMockGameDTO_Found());
		when(rosterPlayerAppService.getBoxScorePlayers(any(), any(), any(), anyString()))
			.thenReturn(createMockBoxScorePlayers_Found());
		when(officialAppService.getGameOfficials(any(), any(), any()))
			.thenReturn(createMockGameOfficials_Found());
		when(teamAppService.findTeamByTeamKey(anyString(), any()))
			.thenReturn(createMockTeam_Found());
		when(gameAppService.updateGame(any()))
			.thenReturn(createMockGame_Updated());
		GameBusiness game = gameBusService.scoreGame(createMockGame_Scheduled());
		Assert.assertTrue(game.isCompleted());
	}

	private GameBusiness createMockGame_Scheduled() {
		Game game = new Game();
		game.setGameDateTime(LocalDateTime.of(2015, 11, 26, 10, 0));
		game.setStatus(GameStatus.Scheduled);
		Team teamHome = new Team();
		teamHome.setTeamKey("brooklyn-nets");
		BoxScore boxScoreHome = new BoxScore();
		boxScoreHome.setLocation(Location.Home);
		boxScoreHome.setTeam(teamHome);
		game.addBoxScore(boxScoreHome);
		Team teamAway = new Team();
		teamAway.setTeamKey("detroit-pistons");
		BoxScore boxScoreAway = new BoxScore();
		boxScoreAway.setLocation(Location.Away);
		boxScoreAway.setTeam(teamAway);
		game.addBoxScore(boxScoreAway);
		return new GameBusiness(game, StatusCodeBusiness.StatusCode.Initial);
	}

	private Game createMockGame_Updated() {
		Game game = new Game();
		game.setGameDateTime(LocalDateTime.of(2015, 11, 24, 10, 0));
		game.setStatusCode(StatusCodeDAO.Updated);
		return game;
	}

	private GameDTO createMockGameDTO_Found() {
		GameDTO game;
		try {
			InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json");
			game = objectMapper.readValue(baseJson, GameDTO.class);
			game.setStatusCode(StatusCode.Found);
		}
		catch (IOException e) {
			game = new GameDTO();
			game.setStatusCode(StatusCode.ClientException);
		}
		return game;
	}

	private GameDTO createMockGameDTO_StatusCode(StatusCode statusCode) {
		GameDTO game = new GameDTO();
		game.setStatusCode(statusCode);
		return game;
	}

	private List<BoxScorePlayer> createMockBoxScorePlayers_Found() {
		List<BoxScorePlayer> boxScorePlayers = new ArrayList<>();
		boxScorePlayers.add(createMockBoxScorePlayer(1L, "Bogdanović", "Bojan"));
		boxScorePlayers.add(createMockBoxScorePlayer(2L, "Larkin", "DeShane"));
		boxScorePlayers.add(createMockBoxScorePlayer(3L, "Robinson", "Thomas"));
		boxScorePlayers.add(createMockBoxScorePlayer(4L, "Karasev", "Sergey"));
		return boxScorePlayers;
	}

	private BoxScorePlayer createMockBoxScorePlayer(Long id, String lastName, String firstName) {
		BoxScorePlayer boxScorePlayer = new BoxScorePlayer();
		boxScorePlayer.setId(id);
		Player player = new Player();
		player.setLastName(lastName);
		player.setFirstName(firstName);
		RosterPlayer rosterPlayer = new RosterPlayer();
		rosterPlayer.setPlayer(player);
		boxScorePlayer.setRosterPlayer(rosterPlayer);
		return boxScorePlayer;
	}

	private List<GameOfficial> createMockGameOfficials_Found() {
		List<GameOfficial> gameOfficials = new ArrayList<>();
		gameOfficials.add(createMockGameOfficial(1L, "Zarba", "Zach"));
		gameOfficials.add(createMockGameOfficial(2L, "Forte", "Brian"));
		gameOfficials.add(createMockGameOfficial(3L, "Roe", "Eli"));
		return gameOfficials;
	}

	private GameOfficial createMockGameOfficial(Long id, String lastName, String firstName) {
		GameOfficial gameOfficial = new GameOfficial();
		Official official = new Official();
		official.setId(id);
		official.setLastName(lastName);
		official.setFirstName(firstName);
		gameOfficial.setOfficial(official);
		return gameOfficial;
	}

	private Team createMockTeam_Found() {
		Team team = new Team();
		team.setId(1L);
		team.setTeamKey("brooklyn-nets");
		return team;
	}
}