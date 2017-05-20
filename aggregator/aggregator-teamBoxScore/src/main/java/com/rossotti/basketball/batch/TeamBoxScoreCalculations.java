package com.rossotti.basketball.batch;

import java.math.BigDecimal;
import java.math.RoundingMode;

class TeamBoxScoreCalculations {

    static Short calculateTwoPointAttempt(Short fieldGoalAttempt, Short threePointAttempt) {
        return (short)(fieldGoalAttempt - threePointAttempt);
    }

    static Short calculateTwoPointMade(Short fieldGoalMade, Short threePointMade) {
        return (short)(fieldGoalMade - threePointMade);
    }

    static BigDecimal calculatePercentShort(Short top, Short bottom) {
        return calculatePercentBd(new BigDecimal(top), new BigDecimal(bottom));
    }

    static BigDecimal calculatePercentBd(BigDecimal top, BigDecimal bottom) {
        return top.divide(bottom, 4, RoundingMode.HALF_UP);
    }

    static BigDecimal calculatePossessions(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                           Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts) {
        BigDecimal bdTeam1, bdTeam2, bdTeam3;
        bdTeam1 = new BigDecimal(teamFieldGoalAttempts);
        bdTeam2 = calculatePercentShort(teamReboundsOffense, (short)(teamReboundsOffense + opptReboundsDefense))
            .multiply(new BigDecimal(teamFieldGoalAttempts - teamFieldGoalMade))
            .multiply(new BigDecimal(1.07));
        bdTeam3 = bdTeam1.subtract(bdTeam2)
            .add(new BigDecimal(teamTurnovers))
            .add(new BigDecimal(.4 * teamFreeThrowAttempts));

        BigDecimal bdOppt1, bdOppt2, bdOppt3;
        bdOppt1 = new BigDecimal(opptFieldGoalAttempts);
        bdOppt2 = calculatePercentShort(opptReboundsOffense, (short)(opptReboundsOffense + teamReboundsDefense))
            .multiply(new BigDecimal(opptFieldGoalAttempts - opptFieldGoalMade))
            .multiply(new BigDecimal(1.07));
        bdOppt3 = bdOppt1.subtract(bdOppt2)
            .add(new BigDecimal(opptTurnovers))
            .add(new BigDecimal(.4 * opptFreeThrowAttempts));

        return calculatePercentBd(bdTeam3.add(bdOppt3), new BigDecimal(2));
    }

    static BigDecimal calculatePace(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                    Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts,
                                    Short teamMinutes) {
        BigDecimal top = calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                                              opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts)
            .multiply(new BigDecimal(48 * 5));
        return calculatePercentBd(top, new BigDecimal(teamMinutes));
    }

    static BigDecimal calculateTrueShootingPct(Short points, Short fieldGoalAttempts, Short freeThrowAttempts) {
        BigDecimal bottom = new BigDecimal(fieldGoalAttempts)
            .add((new BigDecimal(freeThrowAttempts))
            .multiply(new BigDecimal(0.44)))
            .multiply(new BigDecimal(2));
        return calculatePercentBd(new BigDecimal(points), bottom);
    }

    static BigDecimal calculateEffectiveFieldGoalPct(Short fieldGoalMade, Short threePointMade, Short fieldGoalAttempts) {
        BigDecimal top = new BigDecimal(fieldGoalMade)
            .add((new BigDecimal(threePointMade))
            .multiply(new BigDecimal(0.5)));
        return calculatePercentBd(top, new BigDecimal(fieldGoalAttempts));
    }

    static BigDecimal calculateOffensiveReboundPct(Short teamOffensiveRebound, Short opptDefensiveRebound) {
        BigDecimal top = new BigDecimal(teamOffensiveRebound)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = new BigDecimal(teamOffensiveRebound)
            .add(new BigDecimal(opptDefensiveRebound));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateDefensiveReboundPct(Short teamDefensiveRebound, Short opptOffensiveRebound) {
        BigDecimal top = new BigDecimal(teamDefensiveRebound)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = new BigDecimal(teamDefensiveRebound)
            .add(new BigDecimal(opptOffensiveRebound));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateTotalReboundPct(Short teamOffensiveRebound, Short teamDefensiveRebound, Short opptOffensiveRebound, Short opptDefensiveRebound) {
        BigDecimal teamTotalRebound = new BigDecimal(teamOffensiveRebound)
            .add(new BigDecimal(teamDefensiveRebound));
        BigDecimal opptTotalRebound = new BigDecimal(opptOffensiveRebound)
            .add(new BigDecimal(opptDefensiveRebound));
        BigDecimal top = teamTotalRebound
            .multiply(new BigDecimal(100));
        BigDecimal bottom = teamTotalRebound
            .add(opptTotalRebound);
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateAssistedFieldGoalPct(Short teamAssist, Short fieldGoalMade) {
        return calculatePercentShort(teamAssist, fieldGoalMade);
    }

    static BigDecimal calculateTurnoverPct(Short teamTurnover, Short fieldGoalAttempt, Short freeThrowAttempt) {
        BigDecimal top = new BigDecimal(teamTurnover)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = new BigDecimal(fieldGoalAttempt)
            .add((new BigDecimal(freeThrowAttempt))
            .multiply(new BigDecimal(0.44)))
            .add(new BigDecimal(teamTurnover));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateStealPct(Short teamSteal, Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                        Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts) {
        BigDecimal top = new BigDecimal(teamSteal)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
            opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts);
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateBlockPct(Short teamBlock, Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                        Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts) {
        BigDecimal top = new BigDecimal(teamBlock)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
            opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts);
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateBlockRate(Short teamBlock, Short teamFieldGoalAttempt, Short teamThreePointAttempt) {
        BigDecimal top = new BigDecimal(teamBlock)
            .multiply(new BigDecimal(100));
        BigDecimal bottom = new BigDecimal(teamFieldGoalAttempt)
            .subtract(new BigDecimal(teamThreePointAttempt));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculatePointsPerShot(Short teamPoint, Short teamFieldGoalAttempt) {
        return calculatePercentShort(teamPoint, teamFieldGoalAttempt);
    }

    static BigDecimal calculateFloorImpactCounter(Short teamPoint, Short teamOffensiveRebound, Short teamDefensiveRebound, Short teamAssist, Short teamSteal, Short teamBlock, Short teamFieldGoalAttempt,
                                                  Short teamFreeThrowAttempt, Short teamTurnover, Short teamPersonalFoul) {
        return new BigDecimal(teamPoint)
            .add(new BigDecimal(teamOffensiveRebound))
            .add((new BigDecimal(teamDefensiveRebound)).multiply(new BigDecimal(0.75)))
            .add(new BigDecimal(teamAssist))
            .add(new BigDecimal(teamSteal))
            .add(new BigDecimal(teamBlock))
            .subtract((new BigDecimal(teamFieldGoalAttempt)).multiply(new BigDecimal(0.75)))
            .subtract((new BigDecimal(teamFreeThrowAttempt)).multiply(new BigDecimal(0.375)))
            .subtract(new BigDecimal(teamTurnover))
            .subtract((new BigDecimal(teamPersonalFoul)).multiply(new BigDecimal(0.5)));
    }

    static BigDecimal calculateFloorImpactCounterPer40(Short teamPoint, Short teamOffensiveRebound, Short teamDefensiveRebound, Short teamAssist, Short teamSteal, Short teamBlock, Short teamFieldGoalAttempt,
                                                       Short teamFreeThrowAttempt, Short teamTurnover, Short teamPersonalFoul, Short teamMinutePlayed) {
        BigDecimal top = calculateFloorImpactCounter(teamPoint, teamOffensiveRebound, teamDefensiveRebound, teamAssist, teamSteal, teamBlock, teamFieldGoalAttempt, teamFreeThrowAttempt, teamTurnover, teamPersonalFoul)
            .multiply(new BigDecimal(40)
            .multiply(new BigDecimal(5)));
        return calculatePercentBd(top, new BigDecimal(teamMinutePlayed));
    }

    static BigDecimal calculateOffensiveRating(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                               Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts,
                                               Short teamPoints) {
        BigDecimal bottom =  calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                                                  opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts);
        BigDecimal top = new BigDecimal(teamPoints)
            .multiply(new BigDecimal(100));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateDefensiveRating(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                               Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts,
                                               Short opptPoints) {
        BigDecimal bottom =  calculatePossessions(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                                                  opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts);
        BigDecimal top = new BigDecimal(opptPoints)
            .multiply(new BigDecimal(100));
        return calculatePercentBd(top, bottom);
    }

    static BigDecimal calculateEfficiencyDifferential(Short teamFieldGoalAttempts, Short teamReboundsOffense, Short opptReboundsDefense, Short teamFieldGoalMade, Short teamTurnovers, Short teamFreeThrowAttempts,
                                                      Short opptFieldGoalAttempts, Short opptReboundsOffense, Short teamReboundsDefense, Short opptFieldGoalMade, Short opptTurnovers, Short opptFreeThrowAttempts,
                                                      Short teamPoints, Short opptPoints) {
        BigDecimal offensiveRating =  calculateOffensiveRating(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                                                               opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts,
                                                               teamPoints);
        BigDecimal defensiveRating =  calculateDefensiveRating(teamFieldGoalAttempts, teamReboundsOffense, opptReboundsDefense, teamFieldGoalMade, teamTurnovers, teamFreeThrowAttempts,
                                                               opptFieldGoalAttempts, opptReboundsOffense, teamReboundsDefense, opptFieldGoalMade, opptTurnovers, opptFreeThrowAttempts,
                                                               opptPoints);
        return offensiveRating
            .subtract(defensiveRating);
    }
}