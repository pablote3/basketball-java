package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class StandingProcessor implements ItemProcessor<Standing, Standing> {

    private Standing standing;

    @Override
    public Standing process(Standing standingIn) {
        standing = standingIn;
        standing.setStrengthOfSchedule(calculateStrengthOfSchedule().floatValue());
        return standing;
    }

    private BigDecimal calculateStrengthOfSchedule() {
        return StandingCalculations.calculateStrengthOfSchedule(
            standing.getOpptGamesWon(), standing.getOpptGamesPlayed(), standing.getOpptOpptGamesWon(), standing.getOpptOpptGamesPlayed()
        );
    }
}