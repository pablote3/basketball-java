package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.Team;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class TeamProcessor implements ItemProcessor<Team, Team> {

    private final Logger logger = LoggerFactory.getLogger(TeamProcessor.class);

    @Override
    public Team process(Team team) throws Exception {
        logger.info("Team: " + team.getTeamKey());
        return team;
    }
}
