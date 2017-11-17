package com.rossotti.basketball.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class StandingCalculations {

    public static BigDecimal calculateStrengthOfSchedule(Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed) {
        if (opptGamesPlayed > 0 && opptOpptGamesPlayed > 0 && opptOpptGamesWon > 0) {
            BigDecimal opptWinPct = new BigDecimal(opptGamesWon)
                .divide(new BigDecimal(opptGamesPlayed), 4, RoundingMode.HALF_UP);
            BigDecimal opptOpptWinPct = new BigDecimal(opptOpptGamesWon)
                .divide(new BigDecimal(opptOpptGamesPlayed), 4, RoundingMode.HALF_UP);
            return (opptWinPct.multiply(new BigDecimal(2)).add(opptOpptWinPct))
                .divide(new BigDecimal(3), 4, RoundingMode.HALF_UP);
        }
        else {
            return new BigDecimal(0);
        }
    }

    public static BigDecimal calculateRelativePercentageIndex(Short teamGamesWon, Short teamGamesPlayed, Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed) {
        if (teamGamesPlayed > 0 && opptGamesPlayed > 0 && opptOpptGamesPlayed > 0 && opptOpptGamesWon > 0) {
            BigDecimal teamWinPct = new BigDecimal(teamGamesWon)
                .divide(new BigDecimal(teamGamesPlayed), 4, RoundingMode.HALF_UP);
            BigDecimal opptWinPct = new BigDecimal(opptGamesWon)
                .divide(new BigDecimal(opptGamesPlayed), 4, RoundingMode.HALF_UP);
            BigDecimal opptOpptWinPct = new BigDecimal(opptOpptGamesWon)
                .divide(new BigDecimal(opptOpptGamesPlayed), 4, RoundingMode.HALF_UP);
            return teamWinPct.multiply(new BigDecimal(0.25))
                .add(opptWinPct.multiply(new BigDecimal(0.5)))
                .add(opptOpptWinPct.multiply(new BigDecimal(0.25)));
        }
        else {
            return new BigDecimal(0);
        }
    }

    public static BigDecimal calculateMarginOfVictory(Short pointsFor, Short pointsAgainst, Short gamesPlayed) {
        if (gamesPlayed > 0) {
            return new BigDecimal(pointsFor)
                .subtract(new BigDecimal(pointsAgainst))
                .divide(new BigDecimal(gamesPlayed), 4, RoundingMode.HALF_UP);
        }
        else {
            return new BigDecimal(0);
        }
    }

    public static BigDecimal calculateSimpleRatingSystem(Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed, Short pointsFor, Short pointsAgainst, Short teamGamesPlayed) {
        BigDecimal strengthOfSchedule = calculateStrengthOfSchedule(opptGamesWon, opptGamesPlayed, opptOpptGamesWon, opptOpptGamesPlayed);
        BigDecimal marginOfVictory = calculateMarginOfVictory(pointsFor, pointsAgainst, teamGamesPlayed);
        return marginOfVictory
            .subtract(strengthOfSchedule);
    }

    public static BigDecimal calculateProjectedWinningPct(Short pointsFor, Short pointsAgainst, Short gamesPlayed) {
        BigDecimal marginOfVictory = calculateMarginOfVictory(pointsFor, pointsAgainst, gamesPlayed);
        BigDecimal top =  (marginOfVictory
            .multiply(new BigDecimal(2.7)))
            .add(new BigDecimal(41));
        return top
            .divide(new BigDecimal(82), 4, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculatePythagoreanWinningPct_13_91(Short teamPoints, Short opptPoints) {
        BigDecimal teamPoints_13_91 = new BigDecimal(Math.pow(new Double(teamPoints), 13.91));
        BigDecimal opptPoints_13_91 = new BigDecimal(Math.pow(new Double(opptPoints), 13.91));
        BigDecimal bottom = teamPoints_13_91
                .add(opptPoints_13_91);
        return CommonCalculations.calculatePercent(teamPoints_13_91, bottom);
    }

    public static BigDecimal calculatePythagoreanWins_13_91(Short teamPoints, Short opptPoints) {
        return calculatePythagoreanWinningPct_13_91(teamPoints, opptPoints)
                .multiply(new BigDecimal(82));
    }

    public static BigDecimal calculatePythagoreanLosses_13_91(Short teamPoints, Short opptPoints) {
        return new BigDecimal(82)
                .subtract(calculatePythagoreanWins_13_91(teamPoints, opptPoints));
    }

    public static BigDecimal calculatePythagoreanWinningPct_16_5(Short teamPoints, Short opptPoints) {
        BigDecimal teamPoints_16_5 = new BigDecimal(Math.pow(new Double(teamPoints), 16.5));
        BigDecimal opptPoints_16_5 = new BigDecimal(Math.pow(new Double(opptPoints), 16.5));
        BigDecimal bottom = teamPoints_16_5
                .add(opptPoints_16_5);
        return CommonCalculations.calculatePercent(teamPoints_16_5, bottom);
    }

    public static BigDecimal calculatePythagoreanWins_16_5(Short teamPoints, Short opptPoints) {
        return calculatePythagoreanWinningPct_16_5(teamPoints, opptPoints)
                .multiply(new BigDecimal(82));
    }

    public static BigDecimal calculatePythagoreanLosses_16_5(Short teamPoints, Short opptPoints) {
        return new BigDecimal(82)
                .subtract(calculatePythagoreanWins_16_5(teamPoints, opptPoints));
    }
}