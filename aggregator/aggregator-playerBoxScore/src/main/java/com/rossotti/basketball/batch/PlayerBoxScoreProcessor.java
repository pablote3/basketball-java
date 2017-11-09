package com.rossotti.basketball.batch;

import com.rossotti.basketball.util.DateTimeConverter;
import org.springframework.batch.item.ItemProcessor;

public class PlayerBoxScoreProcessor implements ItemProcessor<PlayerBoxScore, PlayerBoxScore> {

    @Override
    public PlayerBoxScore process(PlayerBoxScore playerBoxScore) {
        playerBoxScore.setGameDate(DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTime(playerBoxScore.getGameDateTime())));
        playerBoxScore.setGameTime(DateTimeConverter.getStringTime(DateTimeConverter.getLocalDateTime(playerBoxScore.getGameDateTime())));
        playerBoxScore.setTwoPointAttempts(playerBoxScore.calculateTwoPointAttempt());
        playerBoxScore.setTwoPointMade(playerBoxScore.calculateTwoPointMade());
        playerBoxScore.setTwoPointPct(playerBoxScore.calculateTwoPointPct().floatValue());
        playerBoxScore.setThreePointPct(playerBoxScore.calculateThreePointPct().floatValue());
        playerBoxScore.setFieldGoalPct(playerBoxScore.calculateFieldGoalPct().floatValue());
        playerBoxScore.setFreeThrowPct(playerBoxScore.calculateFreeThrowPct().floatValue());
        playerBoxScore.setReboundsTotal(playerBoxScore.calculateReboundTotal());
        playerBoxScore.setStatus(playerBoxScore.calculateStatus());
        return playerBoxScore;
    }
}
