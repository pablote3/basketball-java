package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.*;
import com.rossotti.basketball.jpa.model.RosterPlayer.Position;
import com.rossotti.basketball.util.DateTimeConverter;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class GameRepositoryTest {

	private GameRepository gameRepository;

	@Autowired
	public void setGameRepository(GameRepository gameRepository) {
		this.gameRepository = gameRepository;
	}

	@Test
	public void getById() {
		Game game = gameRepository.findById(5L);
		assertEquals(Game.GameStatus.Postponed, game.getStatus());
		assertEquals("Baltimore Bullets", game.getBoxScoreHome().getTeam().getFullName());
	}

	@Test
	public void findAll() {
		List<Game> games = gameRepository.findAll();
		assertEquals(17, games.size());
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDate_Found() {
		Game game = gameRepository.findByTeamKeyAndFromDateAndToDate("chicago-zephyr's", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 27)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 27)));
		assertEquals(LocalDateTime.of(2015, 10, 27, 20, 0), game.getGameDateTime());
		assertEquals("Harlem Globetrotter's", game.getBoxScoreAway().getTeam().getFullName());
		assertEquals(3, game.getGameOfficials().size());
		assertEquals("QuestionableCall", game.getGameOfficials().get(2).getOfficial().getLastName());
        assertEquals((short)98, (short) game.getBoxScoreAway().getBoxScoreStats().getPoints());
		assertEquals(1, game.getBoxScoreHome().getBoxScorePlayers().size());
		assertEquals(Position.SG, game.getBoxScoreHome().getBoxScorePlayers().get(0).getRosterPlayer().getPosition());
		assertEquals(2, game.getBoxScoreAway().getBoxScorePlayers().size());
		assertEquals((short)5, (short)game.getBoxScoreAway().getBoxScorePlayers().get(1).getBoxScoreStats().getPoints());
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDate_NotFound_TeamKey() {
		Game game = gameRepository.findByTeamKeyAndFromDateAndToDate("baltimore-bullys", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 29)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 29)));
		assertNull(game);
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDate_NotFound_AsOfDate() {
		Game game = gameRepository.findByTeamKeyAndFromDateAndToDate("baltimore-bullets", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2014, 10, 29)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2014, 10, 29)));
		assertNull(game);
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDateSeason_Found() {
		List<Game> games = gameRepository.findByTeamKeyAndFromDateAndToDateSeason("baltimore-bullets", DateTimeConverter.getLocalDateTimeSeasonMin(LocalDate.of(2015, 10, 30)), DateTimeConverter.getLocalDateTimeSeasonMax(LocalDate.of(2015, 10, 27)));
		assertEquals(3, games.size());
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDateSeason_NotFound_TeamKey() {
		List<Game> games = gameRepository.findByTeamKeyAndFromDateAndToDateSeason("baltimore-bullys", DateTimeConverter.getLocalDateTimeSeasonMin(LocalDate.of(2015, 10, 30)), DateTimeConverter.getLocalDateTimeSeasonMax(LocalDate.of(2015, 10, 27)));
		assertEquals(0, games.size());
	}

	@Test
	public void findByTeamKeyAndFromDateAndToDateSeason_NotFound_AsOfDate() {
		List<Game> games = gameRepository.findByTeamKeyAndFromDateAndToDateSeason("baltimore-bullets", DateTimeConverter.getLocalDateTimeSeasonMin(LocalDate.of(2014, 10, 30)), DateTimeConverter.getLocalDateTimeSeasonMax(LocalDate.of(2014, 10, 27)));
		assertEquals(0, games.size());
	}

	@Test
	public void findByFromDateAndToDate_Found() {
		List<Game> games = gameRepository.findByFromDateAndToDate(DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 27)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 27)));
		assertEquals(3, games.size());
		assertEquals(LocalDateTime.of(2015, 10, 27, 20, 30), games.get(0).getGameDateTime());
		assertTrue(games.get(0).isScheduled());
		assertEquals(LocalDateTime.of(2015, 10, 27, 21, 0), games.get(1).getGameDateTime());
		assertTrue(games.get(1).isScheduled());
		assertEquals(LocalDateTime.of(2015, 10, 27, 20, 0), games.get(2).getGameDateTime());
		assertTrue(games.get(2).isCompleted());
	}

	@Test
	public void findByFromDateAndToDate_NotFound() {
		List<Game> games = gameRepository.findByFromDateAndToDate(DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 26)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 26)));
		assertEquals(0, games.size());
	}

	@Test
	public void findCountByFromDateAndToDate_NotFound() {
		int count = gameRepository.findCountByFromDateAndToDate(DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 26)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 26)));
		assertEquals(0, count);
	}

	@Test
	public void findCountByFromDateAndToDate_Found() {
		int count = gameRepository.findCountByFromDateAndToDate(DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 27)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 27)));
		assertEquals(3, count);
	}

	@Test
	public void findPreviousByTeamKeyAndFromDateAndToDate_Found() {
		List<LocalDateTime> gameDates = gameRepository.findPreviousByTeamKeyAndAsOfDate("chicago-zephyr's", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 30)));
		assertEquals(2, gameDates.size());
		assertEquals(LocalDateTime.of(2015, 10, 28, 20, 0), gameDates.get(0));
		assertEquals(LocalDateTime.of(2015, 10, 27, 20, 0), gameDates.get(1));
	}

	@Test
	public void findPreviousByTeamKeyAndFromDateAndToDate_NotFound_TeamKey() {
		List<LocalDateTime> games = gameRepository.findPreviousByTeamKeyAndAsOfDate("chicago-zephyry", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 28)));
		assertEquals(0, games.size());
	}

	@Test
	public void findPreviousByTeamKeyAndFromDateAndToDate_NotFound_AsOfDate() {
		List<LocalDateTime> games = gameRepository.findPreviousByTeamKeyAndAsOfDate("chicago-zephyr's", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 27)));
		assertEquals(0, games.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		gameRepository.save(createMockGame(30L, LocalDateTime.of(2016, 10, 10, 21, 0), 21L, 1L, "chicago-zephyr's", 22L, 2L, "harlem-globetrotter's", Game.GameStatus.Scheduled));
		Game findGame = gameRepository.findByTeamKeyAndFromDateAndToDate("chicago-zephyr's", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2016, 10, 10)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2016, 10, 10)));
		assertEquals(2, findGame.getBoxScores().size());
		assertEquals("Harlem Globetrotter's", findGame.getBoxScoreAway().getTeam().getFullName());
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				gameRepository.save(createMockGame(31L, LocalDateTime.of(2015, 12, 27, 21, 0), 3L, 3L, "st-louis-bomber's", 4L, 4L, "salinas-cowboys", null));
			});
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void update_Updated() {
		gameRepository.save(updateMockGame(8L, LocalDateTime.of(2015, 10, 15, 10, 0), 15L, 6L, "cleveland-rebels", 16L, 5L, "baltimore-bullets", Game.GameStatus.Completed));
		Game findGame = gameRepository.findByTeamKeyAndFromDateAndToDate("cleveland-rebels", DateTimeConverter.getLocalDateTimeMin(LocalDate.of(2015, 10, 15)), DateTimeConverter.getLocalDateTimeMax(LocalDate.of(2015, 10, 15)));
		assertEquals(3, findGame.getGameOfficials().size());
		assertEquals("MissedCa'll", findGame.getGameOfficials().get(1).getOfficial().getLastName());
		assertEquals(2, findGame.getBoxScores().size());
		assertEquals("Baltimore Bullets", findGame.getBoxScoreAway().getTeam().getFullName());
		assertEquals((short)18, (short)findGame.getBoxScoreAway().getBoxScoreStats().getFreeThrowMade());
		assertEquals(1, findGame.getBoxScoreAway().getBoxScorePlayers().size());
		assertEquals((short)2, (short)findGame.getBoxScoreAway().getBoxScorePlayers().get(0).getBoxScoreStats().getFreeThrowMade());
		assertEquals(2, findGame.getBoxScoreHome().getBoxScorePlayers().size());
		assertEquals((short)4, (short)findGame.getBoxScoreHome().getBoxScorePlayers().get(0).getBoxScoreStats().getFreeThrowMade());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				gameRepository.save(updateMockGame(9L, LocalDateTime.of(2015, 11, 24, 10, 0), 17L, 9L, "detroit-pistons", 18L, 8L, "st-louis-bomber's", null));
			});
	}

	@Test
	public void delete_Deleted() {
		gameRepository.deleteById(11L);
		Game standing = gameRepository.findById(11L);
		assertNull(standing);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()->{
				gameRepository.deleteById(101L);
			});
	}

	private Game createMockGame(Long id, LocalDateTime gameDateTime, Long boxScoreIdHome, Long teamIdHome, String teamKeyHome, Long boxScoreIdAway, Long teamIdAway, String teamKeyAway, Game.GameStatus status) {
		Game game = new Game();
		game.setId(id);
		game.setGameDateTime(gameDateTime);
		game.setSeasonType(Game.SeasonType.Regular);
		game.setStatus(status);
		game.addBoxScore(createMockBoxScore(game, boxScoreIdHome, teamIdHome, teamKeyHome, BoxScore.Location.Home));
		game.addBoxScore(createMockBoxScore(game, boxScoreIdAway, teamIdAway, teamKeyAway, BoxScore.Location.Away));
		return game;
	}

	private BoxScore createMockBoxScore(Game game, Long boxScoreId, Long teamId, String teamKey, BoxScore.Location location) {
		BoxScore boxScore = new BoxScore();
		boxScore.setId(boxScoreId);
		boxScore.setGame(game);
		boxScore.setTeam(getMockTeam(teamId, teamKey));
		boxScore.setLocation(location);
		return boxScore;
	}

	private Team getMockTeam(Long teamId, String teamKey) {
		Team team = new Team();
		team.setId(teamId);
		team.setTeamKey(teamKey);
		return team;
	}

	private Game updateMockGame(Long id, LocalDateTime gameDateTime, Long boxScoreIdHome, Long teamIdHome, String teamKeyHome, Long boxScoreIdAway, Long teamIdAway, String teamKeyAway, Game.GameStatus status) {
		Game game = createMockGame(id, gameDateTime, boxScoreIdHome, teamIdHome, teamKeyHome, boxScoreIdAway, teamIdAway, teamKeyAway, status);
		game.addGameOfficial(createMockGameOfficial(game, 1L, "LateCall", "Joe"));
		game.addGameOfficial(createMockGameOfficial(game, 3L, "MissedCa'll", "Mike"));
		game.addGameOfficial(createMockGameOfficial(game, 4L, "QuestionableCall", "Hefe"));
		updateMockBoxScoreHome(game.getBoxScoreHome());
		updateMockBoxScoreAway(game.getBoxScoreAway());
		return game;
	}

	private GameOfficial createMockGameOfficial(Game game, Long officialId, String lastName, String firstName) {
		GameOfficial gameOfficial = new GameOfficial();
		gameOfficial.setGame(game);
		gameOfficial.setOfficial(getMockOfficial(officialId, lastName, firstName));
		return gameOfficial;
	}

	public static Official getMockOfficial(Long officialId, String lastName, String firstName) {
		Official official = new Official();
		official.setId(officialId);
		official.setLastName(lastName);
		official.setFirstName(firstName);
		return official;
	}

	private void updateMockBoxScoreHome(BoxScore homeBoxScore) {
		homeBoxScore.addBoxScorePlayer(createMockBoxScorePlayerHome_0(homeBoxScore));
		homeBoxScore.addBoxScorePlayer(createMockBoxScorePlayerHome_1(homeBoxScore));
		homeBoxScore.setBoxScoreStats(new BoxScoreStats());
		homeBoxScore.getBoxScoreStats().setMinutes((short)240);
		homeBoxScore.getBoxScoreStats().setPoints((short)98);
		homeBoxScore.getBoxScoreStats().setAssists((short)14);
		homeBoxScore.getBoxScoreStats().setTurnovers((short)5);
		homeBoxScore.getBoxScoreStats().setSteals((short)7);
		homeBoxScore.getBoxScoreStats().setBlocks((short)5);
		homeBoxScore.getBoxScoreStats().setFieldGoalAttempts((short)44);
		homeBoxScore.getBoxScoreStats().setFieldGoalMade((short)22);
		homeBoxScore.getBoxScoreStats().setFieldGoalPercent((float).500);
		homeBoxScore.getBoxScoreStats().setThreePointAttempts((short)10);
		homeBoxScore.getBoxScoreStats().setThreePointMade((short)6);
		homeBoxScore.getBoxScoreStats().setThreePointPercent((float).6);
		homeBoxScore.getBoxScoreStats().setFreeThrowAttempts((short)20);
		homeBoxScore.getBoxScoreStats().setFreeThrowMade((short)10);
		homeBoxScore.getBoxScoreStats().setFreeThrowPercent((float).500);
		homeBoxScore.getBoxScoreStats().setReboundsOffense((short)25);
		homeBoxScore.getBoxScoreStats().setReboundsDefense((short)5);
		homeBoxScore.getBoxScoreStats().setPersonalFouls((short)18);
	}

	private void updateMockBoxScoreAway(BoxScore awayBoxScore) {
		awayBoxScore.addBoxScorePlayer(createMockBoxScorePlayerAway(awayBoxScore));
		awayBoxScore.setBoxScoreStats(new BoxScoreStats());
		awayBoxScore.getBoxScoreStats().setMinutes((short)240);
		awayBoxScore.getBoxScoreStats().setPoints((short)98);
		awayBoxScore.getBoxScoreStats().setAssists((short)14);
		awayBoxScore.getBoxScoreStats().setTurnovers((short)5);
		awayBoxScore.getBoxScoreStats().setSteals((short)7);
		awayBoxScore.getBoxScoreStats().setBlocks((short)5);
		awayBoxScore.getBoxScoreStats().setFieldGoalAttempts((short)44);
		awayBoxScore.getBoxScoreStats().setFieldGoalMade((short)22);
		awayBoxScore.getBoxScoreStats().setFieldGoalPercent((float).500);
		awayBoxScore.getBoxScoreStats().setThreePointAttempts((short)10);
		awayBoxScore.getBoxScoreStats().setThreePointMade((short)6);
		awayBoxScore.getBoxScoreStats().setThreePointPercent((float).6);
		awayBoxScore.getBoxScoreStats().setFreeThrowAttempts((short)20);
		awayBoxScore.getBoxScoreStats().setFreeThrowMade((short)18);
		awayBoxScore.getBoxScoreStats().setFreeThrowPercent((float).500);
		awayBoxScore.getBoxScoreStats().setReboundsOffense((short)25);
		awayBoxScore.getBoxScoreStats().setReboundsDefense((short)5);
		awayBoxScore.getBoxScoreStats().setPersonalFouls((short)18);
	}

	private BoxScorePlayer createMockBoxScorePlayerHome_0(BoxScore boxScore) {
		BoxScorePlayer homeBoxScorePlayer = new BoxScorePlayer();
		homeBoxScorePlayer.setBoxScoreStats(new BoxScoreStats());
		homeBoxScorePlayer.setBoxScore(boxScore);
		homeBoxScorePlayer.setRosterPlayer(getMockRosterPlayer(2L, "Luke", LocalDate.of(2002, 2, 20), LocalDate.of(2009, 11, 30), LocalDate.of(9999, 12, 31)));
		homeBoxScorePlayer.setPosition(Position.F);
		homeBoxScorePlayer.getBoxScoreStats().setFreeThrowMade((short)4);
		return homeBoxScorePlayer;
	}

	private BoxScorePlayer createMockBoxScorePlayerHome_1(BoxScore boxScore) {
		BoxScorePlayer homeBoxScorePlayer = new BoxScorePlayer();
		homeBoxScorePlayer.setBoxScoreStats(new BoxScoreStats());
		homeBoxScorePlayer.setBoxScore(boxScore);
		homeBoxScorePlayer.setRosterPlayer(getMockRosterPlayer(3L, "Thad", LocalDate.of(1966, 6, 2), LocalDate.of(2009, 10, 30), LocalDate.of(2009, 11, 4)));
		homeBoxScorePlayer.setPosition(Position.C);
		homeBoxScorePlayer.getBoxScoreStats().setFreeThrowMade((short)0);
		return homeBoxScorePlayer;
	}

	private BoxScorePlayer createMockBoxScorePlayerAway(BoxScore boxScore) {
		BoxScorePlayer awayBoxScorePlayer = new BoxScorePlayer();
		awayBoxScorePlayer.setBoxScoreStats(new BoxScoreStats());
		awayBoxScorePlayer.setBoxScore(boxScore);
		awayBoxScorePlayer.setRosterPlayer(getMockRosterPlayer(5L, "Junior", LocalDate.of(1966, 6, 10), LocalDate.of(2009, 10, 30), LocalDate.of(9999, 12, 31)));
		awayBoxScorePlayer.setPosition(Position.SG);
		awayBoxScorePlayer.getBoxScoreStats().setFreeThrowMade((short)2);
		return awayBoxScorePlayer;
	}

	private RosterPlayer getMockRosterPlayer(Long rosterPlayerId, String firstName, LocalDate birthdate, LocalDate fromDate, LocalDate toDate) {
		RosterPlayer rosterPlayer = new RosterPlayer();
		rosterPlayer.setId(rosterPlayerId);
		rosterPlayer.setPlayer(getMockPlayer("Puzdrakiewicz", firstName, birthdate));
		rosterPlayer.setFromDate(fromDate);
		rosterPlayer.setToDate(toDate);
		rosterPlayer.setPosition(Position.C);
		rosterPlayer.setNumber("99");
		return rosterPlayer;
	}

	public static Player getMockPlayer(String lastName, String firstName, LocalDate birthdate) {
		Player player = new Player();
		player.setLastName(lastName);
		player.setFirstName(firstName);
		player.setBirthdate(birthdate);
		return player;
	}
}