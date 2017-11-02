package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;

public class PlayerBoxScoreProcessor implements ItemProcessor<PlayerBoxScore, PlayerBoxScore> {

    @Override
    public PlayerBoxScore process(PlayerBoxScore playerBoxScoreIn) {
        PlayerBoxScore playerBoxScore = playerBoxScoreIn;
        playerBoxScore.setTwoPointAttempts(playerBoxScore.calculateTwoPointAttempt());
        playerBoxScore.setTwoPointMade(playerBoxScore.calculateTwoPointMade());
        playerBoxScore.setTwoPointPct(playerBoxScore.calculateTwoPointPct().floatValue());
        playerBoxScore.setThreePointPct(playerBoxScore.calculateThreePointPct().floatValue());
        playerBoxScore.setFieldGoalPct(playerBoxScore.calculateFieldGoalPct().floatValue());
        playerBoxScore.setFreeThrowPct(playerBoxScore.calculateFreeThrowPct().floatValue());
        playerBoxScore.setReboundsTotal(playerBoxScore.calculateReboundTotal());
        return playerBoxScore;
    }
}
