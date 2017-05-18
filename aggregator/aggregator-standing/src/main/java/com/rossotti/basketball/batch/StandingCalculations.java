package com.rossotti.basketball.batch;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StandingCalculations {

    static BigDecimal calculateStrengthOfSchedule(Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed) {
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

    static BigDecimal calculateRelativePercentageIndex(Short teamGamesWon, Short teamGamesPlayed, Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed) {
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

    static BigDecimal calculateMarginOfVictory(Short pointsFor, Short pointsAgainst, Short gamesPlayed) {
        return new BigDecimal(pointsFor)
            .subtract(new BigDecimal(pointsAgainst))
            .divide(new BigDecimal(gamesPlayed), 4, RoundingMode.HALF_UP);
    }

    static BigDecimal calculateSimpleRatingSystem(Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed, Short pointsFor, Short pointsAgainst, Short teamGamesPlayed) {
        BigDecimal strenthOfSchedule = calculateStrengthOfSchedule(opptGamesWon, opptGamesPlayed, opptOpptGamesWon, opptOpptGamesPlayed);
        BigDecimal marginOfVictory = calculateMarginOfVictory(pointsFor, pointsAgainst, teamGamesPlayed);
        return marginOfVictory
            .subtract(strenthOfSchedule);
    }
}