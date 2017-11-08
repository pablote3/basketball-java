package com.rossotti.basketball.batch;

import com.rossotti.basketball.model.BoxScore;
import org.springframework.batch.item.ItemProcessor;

public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScore) {
        BoxScore.process(teamBoxScore);
        return teamBoxScore;
    }
}