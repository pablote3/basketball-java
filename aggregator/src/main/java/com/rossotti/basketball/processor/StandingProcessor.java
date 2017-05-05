package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.Standing;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

public class StandingProcessor implements ItemProcessor<Standing, Standing> {

    private Standing standing;

    @Override
    public Standing process(Standing standing) {
        standing = this.standing;
        standing.setStrengthOfSchedule(calculateStrengthOfSchedule().floatValue());
        return standing;
    }

    private BigDecimal calculateStrengthOfSchedule() {
        return StandingCalculations.calculateStrengthOfSchedule(
            standing.getOpptGamesWon(), standing.getOpptGamesPlayed(), standing.getOpptOpptGamesWon(), standing.getOpptOpptGamesPlayed()
        );
    }
}
