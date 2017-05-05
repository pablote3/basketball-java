package com.rossotti.basketball.processor;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StandingCalculations {

    static BigDecimal calculateStrengthOfSchedule(Short opptGamesWon, Short opptGamesPlayed, Short opptOpptGamesWon, Short opptOpptGamesPlayed) {
        BigDecimal opptWinPct, opptOpptWinPct;

        opptWinPct = new BigDecimal(opptGamesWon)
                .divide(new BigDecimal(opptGamesPlayed), 4, RoundingMode.HALF_UP);
        opptOpptWinPct = new BigDecimal(opptOpptGamesWon)
                .divide(new BigDecimal(opptOpptGamesPlayed), 4, RoundingMode.HALF_UP);
        return (opptWinPct.multiply(new BigDecimal(2)).add(opptOpptWinPct)).divide(new BigDecimal(2), 4, RoundingMode.HALF_UP);
    }
}