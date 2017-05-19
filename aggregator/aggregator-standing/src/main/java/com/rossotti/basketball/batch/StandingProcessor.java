package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class StandingProcessor implements ItemProcessor<Standing, Standing> {

    private Standing standing;

    @Override
    public Standing process(Standing standingIn) {
        standing = standingIn;
        standing.setStrengthOfSchedule(calculateStrengthOfSchedule().floatValue());
        standing.setRelativePercentageIndex(calculateRelativePercentageIndex().floatValue());
        standing.setMarginOfVictory(calculateMarginOfVictory().floatValue());
        standing.setSimpleRatingSystem(calculateSimpleRatingSystem().floatValue());
        standing.setProjectedWinningPct(calculateProjectedWinningPercentage().floatValue());
        return standing;
    }

    private BigDecimal calculateStrengthOfSchedule() {
        return StandingCalculations.calculateStrengthOfSchedule(
            standing.getOpptGamesWon(), standing.getOpptGamesPlayed(), standing.getOpptOpptGamesWon(), standing.getOpptOpptGamesPlayed()
        );
    }

    private BigDecimal calculateRelativePercentageIndex() {
        return StandingCalculations.calculateRelativePercentageIndex(
            standing.getGamesWon(), standing.getGamesPlayed(), standing.getOpptGamesWon(), standing.getOpptGamesPlayed(), standing.getOpptOpptGamesWon(), standing.getOpptOpptGamesPlayed()
        );
    }

    private BigDecimal calculateMarginOfVictory() {
        return StandingCalculations.calculateMarginOfVictory(
            standing.getPointsFor(), standing.getPointsAgainst(), standing.getGamesPlayed()
        );
    }

    private BigDecimal calculateSimpleRatingSystem() {
        return StandingCalculations.calculateSimpleRatingSystem(
            standing.getOpptGamesWon(), standing.getOpptGamesPlayed(), standing.getOpptOpptGamesWon(), standing.getOpptOpptGamesPlayed(),
            standing.getPointsFor(), standing.getPointsAgainst(), standing.getGamesPlayed()
        );
    }

    private BigDecimal calculateProjectedWinningPercentage() {
        return StandingCalculations.calculateProjectedWinningPct(
            standing.getPointsFor(), standing.getPointsAgainst(), standing.getGamesPlayed()
        );
    }
}