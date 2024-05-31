package com.rossotti.basketball.business.service;

import com.rossotti.basketball.app.service.GameAppService;
import com.rossotti.basketball.app.service.OfficialAppService;
import com.rossotti.basketball.app.service.RosterPlayerAppService;
import com.rossotti.basketball.app.service.TeamAppService;
import com.rossotti.basketball.business.model.ClientSourceBusiness.ClientSource;
import com.rossotti.basketball.business.model.GameBusiness;
import com.rossotti.basketball.business.model.StatusCodeBusiness.StatusCode;
import com.rossotti.basketball.client.dto.GameDTO;
import com.rossotti.basketball.client.service.FileStatsService;
import com.rossotti.basketball.client.service.RestStatsService;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.*;
import com.rossotti.basketball.jpa.model.BoxScore.Result;
import com.rossotti.basketball.jpa.model.Game.GameStatus;
import com.rossotti.basketball.util.DateTimeConverter;
import com.rossotti.basketball.util.ThreadSleep;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class GameBusService {
	private final Environment env;

	private final RestStatsService restStatsService;

	private final FileStatsService fileStatsService;

	private final OfficialAppService officialAppService;

	private final RosterPlayerAppService rosterPlayerAppService;

	private final TeamAppService teamAppService;

	private final GameAppService gameAppService;

	private final Logger logger = LoggerFactory.getLogger(GameBusService.class);

	@Autowired
	public GameBusService(Environment env, FileStatsService fileStatsService, OfficialAppService officialAppService, RestStatsService restStatsService, TeamAppService teamAppService, RosterPlayerAppService rosterPlayerAppService, GameAppService gameAppService) {
		this.env = env;
		this.fileStatsService = fileStatsService;
		this.officialAppService = officialAppService;
		this.restStatsService = restStatsService;
		this.teamAppService = teamAppService;
		this.rosterPlayerAppService = rosterPlayerAppService;
		this.gameAppService = gameAppService;
	}

	private GameBusiness scoreGame(Game game, String previousUpdateTeam) {
		GameBusiness gameBusiness = new GameBusiness(game);
		try {
			BoxScore awayBoxScore = game.getBoxScoreAway();
			BoxScore homeBoxScore = game.getBoxScoreHome();
			String awayTeamKey = awayBoxScore.getTeam().getTeamKey();
			String homeTeamKey = homeBoxScore.getTeam().getTeamKey();
			LocalDateTime gameDateTime = game.getGameDateTime();
			LocalDate gameDate = DateTimeConverter.getLocalDate(gameDateTime);

			String event = DateTimeConverter.getStringDateNaked(gameDateTime) + "-" + awayTeamKey + "-at-" + homeTeamKey;

			if (game.isScheduled()) {
				logger.debug("Scheduled game ready to be scored: " + event);

				GameDTO gameDTO;
				ClientSource clientSource = ClientSource.valueOf(env.getProperty("accumulator.source.boxScore"));
				if (clientSource == ClientSource.File) {
					gameDTO = fileStatsService.retrieveBoxScore(event);
				}
				else if (clientSource == ClientSource.Api) {
					ThreadSleep.sleep(Integer.valueOf(env.getProperty("sleep.duration")));
					gameDTO = restStatsService.retrieveBoxScore(event, false);
				}
				else {
                    throw new IllegalStateException("property exception");
				}

				if (gameDTO == null || gameDTO.isNotFound()) {
					logger.info("Unable to find game");
					gameBusiness.setStatusCode(StatusCode.ClientError);
				}
				else if (gameDTO.isFound()) {
					awayBoxScore.updateTotals(gameDTO.away_totals);
					homeBoxScore.updateTotals(gameDTO.home_totals);
					awayBoxScore.updatePeriodScores(gameDTO.away_period_scores);
					homeBoxScore.updatePeriodScores(gameDTO.home_period_scores);
					gameBusiness.setRosterLastTeam(awayTeamKey);
					awayBoxScore.setBoxScorePlayers(rosterPlayerAppService.getBoxScorePlayers(gameDTO.away_stats, awayBoxScore, gameDate, awayTeamKey));
					gameBusiness.setRosterLastTeam(homeTeamKey);
					homeBoxScore.setBoxScorePlayers(rosterPlayerAppService.getBoxScorePlayers(gameDTO.home_stats, homeBoxScore, gameDate, homeTeamKey));
					game.setGameOfficials(officialAppService.getGameOfficials(gameDTO.officials, game, gameDate));
					awayBoxScore.setTeam(teamAppService.findTeamByTeamKey(awayTeamKey, gameDate));
					homeBoxScore.setTeam(teamAppService.findTeamByTeamKey(homeTeamKey, gameDate));

					if (gameDTO.away_totals.getPoints() > gameDTO.home_totals.getPoints()) {
						awayBoxScore.setResult(Result.Win);
						homeBoxScore.setResult(Result.Loss);
					}
					else {
						awayBoxScore.setResult(Result.Loss);
						homeBoxScore.setResult(Result.Win);
					}

					awayBoxScore.setDaysOff((short) DateTimeConverter.getDaysBetweenTwoDateTimes(gameAppService.findPreviousByTeamKeyAsOfDate(awayTeamKey, gameDate), gameDateTime));
					homeBoxScore.setDaysOff((short) DateTimeConverter.getDaysBetweenTwoDateTimes(gameAppService.findPreviousByTeamKeyAsOfDate(homeTeamKey, gameDate), gameDateTime));
					game.setStatus(GameStatus.Completed);
					Game updatedGame = gameAppService.updateGame(game);
					if (updatedGame.isUpdated()) {
						logger.info(gameBusiness.getGame().getBoxScoreAway().getTeam().getAbbr() + " " +
								gameBusiness.getGame().getBoxScoreAway().getBoxScoreStats().getPoints() + " " +
								gameBusiness.getGame().getBoxScoreHome().getTeam().getAbbr() + " " +
								gameBusiness.getGame().getBoxScoreHome().getBoxScoreStats().getPoints());
					}
					else {
						logger.info("Unable to update game : status = " + updatedGame.getStatus());
					}
					gameBusiness.setStatusCode(StatusCode.Completed);
				}
				else if (gameDTO.isClientException()) {
					logger.info("Client exception");
					gameBusiness.setStatusCode(StatusCode.ClientError);
				}
			}
			else {
				logger.info(game.getStatus() + " game not eligible to be scored: " + event);
				gameBusiness.setStatusCode(StatusCode.Completed);
			}
		}
		catch (NoSuchEntityException nse) {
			if (nse.getEntityClass().equals(Official.class)) {
				logger.info("Official not found - need to add official");
				gameBusiness.setStatusCode(StatusCode.OfficialError);
			}
			else if (nse.getEntityClass().equals(Team.class)) {
				logger.info("Team not found - need to add team");
				gameBusiness.setStatusCode(StatusCode.TeamError);
			}
			else if (nse.getEntityClass().equals(RosterPlayer.class)) {
				if (previousUpdateTeam == null || !previousUpdateTeam.equals(gameBusiness.getRosterLastTeam())) {
					logger.info("RosterPlayer not found - need to rebuild active roster");
					gameBusiness.setStatusCode(StatusCode.RosterUpdate);
				}
				else {
					logger.info("Roster Player not found - problem between box score and roster");
					gameBusiness.setStatusCode(StatusCode.RosterUpdate);
				}
			}
		}
		catch (IllegalStateException pe) {
			logger.info("Property exception = " + pe);
			gameBusiness.setStatusCode(StatusCode.ServerError);
		}
		catch (NumberFormatException nfe) {
			logger.info("NumberFormatException = " + nfe);
			gameBusiness.setStatusCode(StatusCode.ServerError);
		}
		catch (Exception e) {
			logger.info("Unexpected exception = " + e);
			gameBusiness.setStatusCode(StatusCode.ServerError);
		}
		return gameBusiness;
	}

	public GameBusiness scoreGame(GameBusiness gameBusiness) {
		if (gameBusiness.isServerError()) {
			return gameBusiness;
		}
		else if(gameBusiness.isRosterUpdate()) {
			return scoreGame(gameBusiness.getGame(), gameBusiness.getRosterLastTeam());
		}
		else {
			return scoreGame(gameBusiness.getGame(), null);
		}
	}
}
