package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.TeamBoxScore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class TeamProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private final Logger logger = LoggerFactory.getLogger(TeamProcessor.class);

    @Override
    public TeamBoxScore process(TeamBoxScore team) throws Exception {
        logger.info("TeamBoxScore: " + team.getTeamKey());
        return team;
    }
}
