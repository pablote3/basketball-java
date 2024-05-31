package com.rossotti.basketball.integration;

import com.rossotti.basketball.business.model.GameBusiness;
import com.rossotti.basketball.business.service.GameBusService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;

@Configuration
public class GameScorerActivator {
	private final GameBusService gameBusService;
	private final Logger logger = LoggerFactory.getLogger(GameScorerActivator.class);

	@Autowired
	public GameScorerActivator(GameBusService gameBusService) {
		this.gameBusService = gameBusService;
	}

	@ServiceActivator(inputChannel = "gameScoreChannel", outputChannel = "gameResultsChannel")
	public GameBusiness scoreGame(GameBusiness gameBusiness) {
		gameBusiness = gameBusService.scoreGame(gameBusiness);
		logger.info(gameBusiness.getStatusCode() + ": route to gameResultsChannel");
		return gameBusiness;
	}
}