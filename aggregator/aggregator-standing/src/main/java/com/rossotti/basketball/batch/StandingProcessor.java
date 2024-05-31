package com.rossotti.basketball.batch;

import com.rossotti.basketball.calc.StandingCalculations;
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
        standing.setPythagoreanWinningPct_13_91(calculatePythagoreanWinningPct_13_91().floatValue());
        standing.setPythagoreanWins_13_91(calculatePythagoreanWins_13_91().floatValue());
        standing.setPythagoreanLosses_13_91(calculatePythagoreanLosses_13_91().floatValue());
        standing.setPythagoreanWinningPct_16_5(calculatePythagoreanWinningPct_16_5().floatValue());
        standing.setPythagoreanWins_16_5(calculatePythagoreanWins_16_5().floatValue());
        standing.setPythagoreanLosses_16_5(calculatePythagoreanLosses_16_5().floatValue());
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


    private BigDecimal calculatePythagoreanWinningPct_13_91() {
        return StandingCalculations.calculatePythagoreanWinningPct_13_91(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }

    private BigDecimal calculatePythagoreanWins_13_91() {
        return StandingCalculations.calculatePythagoreanWins_13_91(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }

    private BigDecimal calculatePythagoreanLosses_13_91() {
        return StandingCalculations.calculatePythagoreanLosses_13_91(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }

    private BigDecimal calculatePythagoreanWinningPct_16_5() {
        return StandingCalculations.calculatePythagoreanWinningPct_16_5(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }

    private BigDecimal calculatePythagoreanWins_16_5() {
        return StandingCalculations.calculatePythagoreanWins_16_5(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }

    private BigDecimal calculatePythagoreanLosses_16_5() {
        return StandingCalculations.calculatePythagoreanLosses_16_5(
            standing.getPointsFor(), standing.getPointsAgainst()
        );
    }
}