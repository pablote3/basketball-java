package com.rossotti.basketball.calc;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CommonCalculations {

    static public BigDecimal calculatePercent(BigDecimal top, BigDecimal bottom) {
        if (top.compareTo(BigDecimal.ZERO) == 0 && bottom.compareTo(BigDecimal.ZERO) == 0) {
            return BigDecimal.ZERO;
        }
        else {
            return top.divide(bottom, 4, RoundingMode.HALF_UP);
        }
    }

    static public BigDecimal calculatePercent(Short top, Short bottom) {
        return calculatePercent(new BigDecimal(top), new BigDecimal(bottom));
    }

}
