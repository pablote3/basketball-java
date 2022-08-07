package com.rossotti.basketball.app;

import com.rossotti.basketball.app.service.GameAppService;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.BoxScore;
import com.rossotti.basketball.jpa.model.Game;
import com.rossotti.basketball.jpa.model.Game.GameStatus;
import com.rossotti.basketball.jpa.model.Team;
import com.rossotti.basketball.jpa.service.GameJpaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class GameAppServiceTest {
	@Mock
	private GameJpaService gameJpaService;

	@InjectMocks
	private GameAppService gameAppService;

	@Test
	public void findByAsOfDate_notFound() {
		when(gameJpaService.findByAsOfDate(any()))
			.thenReturn(new ArrayList<>());
		List<Game> games = gameAppService.findByAsOfDate(LocalDate.of(1995, 11, 26));
		assertEquals(0, games.size());
	}

	@Test
	public void findByAsOfDate_found() {
		when(gameJpaService.findByAsOfDate(any()))
			.thenReturn(createMockGames());
		List<Game> games = gameAppService.findByAsOfDate(LocalDate.of(1995, 11, 26));
		assertEquals(2, games.size());
	}

	@Test
	public void findByTeamKeyAsOfDate_notFound() {
		when(gameJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
			.thenReturn(null);
		Game game = gameAppService.findByTeamKeyAsOfDate("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertNull(game);
	}

	@Test
	public void findByTeamKeyAsOfDate_found() {
		when(gameJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
			.thenReturn(createMockGame_Scheduled());
		Game game = gameAppService.findByTeamKeyAsOfDate("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertEquals(LocalDateTime.of(2015, 11, 26, 10, 0), game.getGameDateTime());
	}

	@Test
	public void findPreviousByTeamKeyAsOfDate_notFound() {
		when(gameJpaService.findPreviousByTeamKeyAsOfDate(anyString(), any()))
			.thenReturn(null);
		LocalDateTime previousGameDate = gameAppService.findPreviousByTeamKeyAsOfDate("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertNull(previousGameDate);
	}

	@Test
	public void findPreviousByTeamKeyAsOfDate_found() {
		when(gameJpaService.findPreviousByTeamKeyAsOfDate(anyString(), any()))
			.thenReturn(LocalDateTime.of(2015, 11, 26, 10, 0));
		LocalDateTime previousGameDate = gameAppService.findPreviousByTeamKeyAsOfDate("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertEquals(LocalDateTime.of(2015, 11, 26, 10, 0), previousGameDate);
	}

	@Test
	public void findByDateTeamSeason_notFound() {
		when(gameJpaService.findByTeamKeyAndAsOfDateSeason(anyString(), any()))
			.thenReturn(new ArrayList<>());
		List<Game> games = gameAppService.findByTeamKeyAsOfDateSeason("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertEquals(0, games.size());
	}

	@Test
	public void findByDateTeamSeason_found() {
		when(gameJpaService.findByTeamKeyAndAsOfDateSeason(anyString(), any()))
			.thenReturn(createMockGames());
		List<Game> games = gameAppService.findByTeamKeyAsOfDateSeason("sacramento-hornets", LocalDate.of(1995, 11, 26));
		assertEquals(2, games.size());
	}

	@Test
	public void updateGame_notFound() {
		when(gameJpaService.update(any()))
			.thenReturn(createMockGame_StatusCode(StatusCodeDAO.NotFound));
		Game game = gameAppService.updateGame(createMockGame_Scheduled());
		assertTrue(game.isNotFound());
	}

	@Test
	public void updateGame_updated() {
		when(gameJpaService.update(any()))
			.thenReturn(createMockGame_StatusCode(StatusCodeDAO.Updated));
		Game game = gameAppService.updateGame(createMockGame_Scheduled());
		assertTrue(game.isUpdated());
	}

	@Test
	public void createGame_found() {
		when(gameJpaService.create(any()))
			.thenReturn(createMockGame_StatusCode(StatusCodeDAO.Found));
		Game game = gameAppService.createGame(createMockGame_Scheduled());
		assertTrue(game.isFound());
	}

	@Test
	public void createGame_created() {
		when(gameJpaService.create(any()))
			.thenReturn(createMockGame_StatusCode(StatusCodeDAO.Created));
		Game game = gameAppService.createGame(createMockGame_Scheduled());
		assertTrue(game.isCreated());
	}

	private List<Game> createMockGames() {
		return Arrays.asList(
			createMockGame_Completed(),
			createMockGame_Scheduled()
		);
	}

	private Game createMockGame_Scheduled() {
		Game game = new Game();
		game.setGameDateTime(LocalDateTime.of(2015, 11, 26, 10, 0));
		game.setStatus(GameStatus.Scheduled);
		Team teamHome = new Team();
		teamHome.setTeamKey("brooklyn-nets");
		BoxScore boxScoreHome = new BoxScore();
		boxScoreHome.setLocation(BoxScore.Location.Home);
		boxScoreHome.setTeam(teamHome);
		game.addBoxScore(boxScoreHome);
		Team teamAway = new Team();
		teamAway.setTeamKey("detroit-pistons");
		BoxScore boxScoreAway = new BoxScore();
		boxScoreAway.setLocation(BoxScore.Location.Away);
		boxScoreAway.setTeam(teamAway);
		game.addBoxScore(boxScoreAway);
		return game;
	}

	private Game createMockGame_Completed() {
		Game game = new Game();
		game.setGameDateTime(LocalDateTime.of(2015, 11, 26, 10, 0));
		game.setStatus(GameStatus.Completed);
		return game;
	}

	private Game createMockGame_StatusCode(StatusCodeDAO status) {
		Game game = new Game();
		game.setGameDateTime(LocalDateTime.of(2015, 11, 26, 10, 0));
		game.setStatusCode(status);
		return game;
	}
}