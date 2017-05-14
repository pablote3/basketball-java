package com.rossotti.basketball.batch;

import java.math.BigDecimal;
import java.math.RoundingMode;

class StandingCalculations {

    static BigDecimal calculateStrengthOfSchedule(Integer opptGamesWon, Integer opptGamesPlayed, Integer opptOpptGamesWon, Integer opptOpptGamesPlayed) {
        BigDecimal opptWinPct, opptOpptWinPct, strengthOfSchedule;

        if (opptGamesPlayed > 0 && opptOpptGamesPlayed > 0 && opptOpptGamesWon > 0) {
            opptWinPct = new BigDecimal(opptGamesWon)
                .divide(new BigDecimal(opptGamesPlayed), 4, RoundingMode.HALF_UP);
            opptOpptWinPct = new BigDecimal(opptOpptGamesWon)
                .divide(new BigDecimal(opptOpptGamesPlayed), 4, RoundingMode.HALF_UP);
            strengthOfSchedule = (opptWinPct.multiply(new BigDecimal(2)).add(opptOpptWinPct))
                .divide(new BigDecimal(3), 4, RoundingMode.HALF_UP);
        }
        else {
            strengthOfSchedule = new BigDecimal(0);
        }
        return strengthOfSchedule;
    }
}