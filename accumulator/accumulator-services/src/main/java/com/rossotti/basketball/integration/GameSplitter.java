package com.rossotti.basketball.integration;

import com.rossotti.basketball.business.model.GameBusiness;
import com.rossotti.basketball.business.model.StatusCodeBusiness;
import com.rossotti.basketball.jpa.model.Game;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.Splitter;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class GameSplitter {
	private final Logger logger = LoggerFactory.getLogger(GameSplitter.class);

	@Splitter(inputChannel = "gameSplitterChannel", outputChannel = "gameRouterChannel")
	public List<Message<?>> splitMessage(List<Game> games) {
		List<Message<?>> messages = new ArrayList<>();
		for (int i = 0; i < games.size(); i++) {
			Game game = games.get(i);
			Message<?> msg = MessageBuilder
				.withPayload(new GameBusiness(game, StatusCodeBusiness.StatusCode.Initial))
				.setCorrelationId(game.getGameDateTime())
				.setSequenceNumber(i)
				.setSequenceSize(games.size())
				.build();
			messages.add(msg);
		}
		logger.info("gameCount: " + games.size() + ": route to gameRouterChannel");
		return messages;
	}
}