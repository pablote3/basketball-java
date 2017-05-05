package com.rossotti.basketball.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;

class BoxScoreCalculations {

    static BigDecimal calculatePossessions(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                           Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts) {
        BigDecimal bdTeam1, bdTeam2, bdTeam3;
        bdTeam1 = new BigDecimal(teamFieldGoalAttempts);
        bdTeam2 = new BigDecimal(teamReboundsOffense)
                .divide(new BigDecimal(teamReboundsOffense + opptReboundsDefense), 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(teamFieldGoalAttempts - teamFieldGoalMade))
                .multiply(new BigDecimal(1.07));
        bdTeam3 = bdTeam1.subtract(bdTeam2)
                .add(new BigDecimal(teamTurnovers))
                .add(new BigDecimal(.4 * teamFreeThrowAttempts));

        BigDecimal bdOppt1, bdOppt2, bdOppt3;
        bdOppt1 = new BigDecimal(opptFieldGoalAttempts);
        bdOppt2 = new BigDecimal(opptReboundsOffense)
                .divide(new BigDecimal(opptReboundsOffense + teamReboundsDefense), 4, RoundingMode.HALF_UP)
                .multiply(new BigDecimal(opptFieldGoalAttempts - opptFieldGoalMade))
                .multiply(new BigDecimal(1.07));
        bdOppt3 = bdOppt1.subtract(bdOppt2)
                .add(new BigDecimal(opptTurnovers))
                .add(new BigDecimal(.4 * opptFreeThrowAttempts));

        return (bdTeam3.add(bdOppt3)).divide(new BigDecimal(2), 4, RoundingMode.HALF_UP);
    }

    static BigDecimal calculatePace(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                    Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts,
                                    Short teamMinutes) {
        return calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts)
                .multiply(new BigDecimal(48 * 5))
                .divide(new BigDecimal(teamMinutes), 4, RoundingMode.HALF_UP);
    }

    static BigDecimal calculateTrueShootingPercentage(Short opptGamesWon, Short opptGamesPlayed, Short opptOpptGamesWon, Short opptOpptGamesPlayed) {
        BigDecimal opptWinPct, opptOpptWinPct;

        opptWinPct = new BigDecimal(opptGamesWon)
                .divide(new BigDecimal(opptGamesPlayed), 4, RoundingMode.HALF_UP);
        opptOpptWinPct = new BigDecimal(opptOpptGamesWon)
                .divide(new BigDecimal(opptOpptGamesPlayed), 4, RoundingMode.HALF_UP);
        return (opptWinPct.multiply(new BigDecimal(2)).add(opptOpptWinPct)).divide(new BigDecimal(2), 4, RoundingMode.HALF_UP);
    }
}
