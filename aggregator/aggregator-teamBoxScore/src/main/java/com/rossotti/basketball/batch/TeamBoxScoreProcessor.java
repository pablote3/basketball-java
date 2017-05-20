package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;
import java.math.BigDecimal;

public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private TeamBoxScore teamBoxScore;

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScoreIn) {
        teamBoxScore = teamBoxScoreIn;
        teamBoxScore.setTeamTwoPointAttempts(calculateTeamTwoPointAttempt());
        teamBoxScore.setTeamTwoPointMade(calculateTeamTwoPointMade());
        teamBoxScore.setTeamTwoPointPct(calculateTeamTwoPointPct().floatValue());
        teamBoxScore.setTeamTwoPointRate(calculateTeamTwoPointRate().floatValue());
        teamBoxScore.setTeamThreePointPct(calculateTeamThreePointPct().floatValue());
        teamBoxScore.setTeamThreePointRate(calculateTeamThreePointRate().floatValue());
        teamBoxScore.setTeamFieldGoalPct(calculateTeamFieldGoalPct().floatValue());
        teamBoxScore.setTeamFreeThrowPct(calculateTeamFreeThrowPct().floatValue());
        teamBoxScore.setTeamFreeThrowRate(calculateTeamFreeThrowRate().floatValue());
        teamBoxScore.setOpptTwoPointAttempts(calculateOpptTwoPointAttempt());
        teamBoxScore.setOpptTwoPointMade(calculateOpptTwoPointMade());
        teamBoxScore.setOpptTwoPointPct(calculateOpptTwoPointPct().floatValue());
        teamBoxScore.setOpptTwoPointRate(calculateOpptTwoPointRate().floatValue());
        teamBoxScore.setOpptThreePointPct(calculateOpptThreePointPct().floatValue());
        teamBoxScore.setOpptThreePointRate(calculateOpptThreePointRate().floatValue());
        teamBoxScore.setOpptFieldGoalPct(calculateOpptFieldGoalPct().floatValue());
        teamBoxScore.setOpptFreeThrowPct(calculateOpptFreeThrowPct().floatValue());
        teamBoxScore.setOpptFreeThrowRate(calculateOpptFreeThrowRate().floatValue());
        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace().floatValue());
        teamBoxScore.setTeamTrueShootingPct(calculateTeamTrueShootingPct().floatValue());
        teamBoxScore.setTeamEffectiveFieldGoalPct(calculateTeamEffectiveFieldGoalPct().floatValue());
        teamBoxScore.setTeamOffensiveReboundPct(calculateTeamOffensiveReboundPct().floatValue());
        teamBoxScore.setTeamDefensiveReboundPct(calculateTeamDefensiveReboundPct().floatValue());
        teamBoxScore.setTeamTotalReboundPct(calculateTeamTotalReboundPct().floatValue());
        teamBoxScore.setTeamAssistedFieldGoalPct(calculateTeamAssistedFieldGoalPct().floatValue());
        teamBoxScore.setTeamTurnoverPct(calculateTeamTurnoverPct().floatValue());
        teamBoxScore.setTeamStealPct(calculateTeamStealPct().floatValue());
        teamBoxScore.setTeamBlockPct(calculateTeamBlockPct().floatValue());
        teamBoxScore.setTeamBlockRate(calculateTeamBlockRate().floatValue());
        teamBoxScore.setTeamPointsPerShot(calculateTeamPointsPerShot().floatValue());
        teamBoxScore.setTeamFloorImpactCounter(calculateTeamFloorImpactCounter().floatValue());
        teamBoxScore.setTeamFloorImpactCounterPer40(calculateTeamFloorImpactCounterPer40().floatValue());
        teamBoxScore.setTeamOffensiveRating(calculateTeamOffensiveRating().floatValue());
        teamBoxScore.setTeamDefensiveRating(calculateTeamDefensiveRating().floatValue());
        teamBoxScore.setTeamEfficiencyDifferential(calculateTeamEfficiencyDifferential().floatValue());
        return teamBoxScore;
    }

    private Short calculateTeamTwoPointMade() {
        return TeamBoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade()
        );
    }
    private Short calculateTeamTwoPointAttempt() {
        return TeamBoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
    }
    private BigDecimal calculateTeamTwoPointPct() {
        Short attempt = TeamBoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
        Short made = TeamBoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade()
        );
        return TeamBoxScoreCalculations.calculatePercent(made, attempt);
    }
    private BigDecimal calculateTeamTwoPointRate() {
        return TeamBoxScoreCalculations.calculatePercent (
            teamBoxScore.getTeamTwoPointAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamThreePointPct() {
        return TeamBoxScoreCalculations.calculatePercent(
                teamBoxScore.getTeamThreePointMade(), teamBoxScore.getTeamThreePointAttempts()
        );
    }
    private BigDecimal calculateTeamThreePointRate() {
        return TeamBoxScoreCalculations.calculatePercent(
                teamBoxScore.getTeamThreePointAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFieldGoalPct() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFreeThrowPct() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFreeThrowMade(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }
    private BigDecimal calculateTeamFreeThrowRate() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }


    private Short calculateOpptTwoPointMade() {
        return TeamBoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade()
        );
    }
    private Short calculateOpptTwoPointAttempt() {
        return TeamBoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
    }
    private BigDecimal calculateOpptTwoPointPct() {
        Short attempt = TeamBoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
        Short made = TeamBoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade()
        );
        return TeamBoxScoreCalculations.calculatePercent(
            made, attempt
        );
    }
    private BigDecimal calculateOpptTwoPointRate() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptTwoPointAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptThreePointPct() {
        return TeamBoxScoreCalculations.calculatePercent(
                teamBoxScore.getOpptThreePointMade(), teamBoxScore.getOpptThreePointAttempts()
        );
    }
    private BigDecimal calculateOpptThreePointRate() {
        return TeamBoxScoreCalculations.calculatePercent(
                teamBoxScore.getOpptThreePointAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFieldGoalPct() {
        return TeamBoxScoreCalculations.calculatePercent(
                teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFreeThrowPct() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptFreeThrowMade(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }
    private BigDecimal calculateOpptFreeThrowRate() {
        return TeamBoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculatePossessions() {
        return TeamBoxScoreCalculations.calculatePossessions(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculatePace() {
        return TeamBoxScoreCalculations.calculatePace(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamMinutes()
        );
    }

    private BigDecimal calculateTeamTrueShootingPct() {
        return TeamBoxScoreCalculations.calculateTrueShootingPct(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamEffectiveFieldGoalPct() {
        return TeamBoxScoreCalculations.calculateEffectiveFieldGoalPct(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamOffensiveReboundPct() {
        return TeamBoxScoreCalculations.calculateOffensiveReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamDefensiveReboundPct() {
        return TeamBoxScoreCalculations.calculateDefensiveReboundPct(
            teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateTeamTotalReboundPct() {
        return TeamBoxScoreCalculations.calculateTotalReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamAssistedFieldGoalPct() {
        return TeamBoxScoreCalculations.calculateAssistedFieldGoalPct(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamFieldGoalMade()
        );
    }

    private BigDecimal calculateTeamTurnoverPct() {
        return TeamBoxScoreCalculations.calculateTurnoverPct(
            teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamStealPct() {
        return TeamBoxScoreCalculations.calculateStealPct(
            teamBoxScore.getTeamSteals(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockPct() {
        return TeamBoxScoreCalculations.calculateBlockPct(
            teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockRate() {
        return TeamBoxScoreCalculations.calculateBlockRate(
            teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
    }

    private BigDecimal calculateTeamPointsPerShot() {
        return TeamBoxScoreCalculations.calculatePointsPerShot(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounter() {
        return TeamBoxScoreCalculations.calculateFloorImpactCounter(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getTeamAssists(), teamBoxScore.getTeamSteals(), teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(),
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamPersonalFouls()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounterPer40() {
        return TeamBoxScoreCalculations.calculateFloorImpactCounterPer40(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getTeamAssists(), teamBoxScore.getTeamSteals(), teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(),
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamPersonalFouls(), teamBoxScore.getTeamMinutes()
        );
    }

    private BigDecimal calculateTeamOffensiveRating() {
        return TeamBoxScoreCalculations.calculateOffensiveRating(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateTeamDefensiveRating() {
        return TeamBoxScoreCalculations.calculateDefensiveRating(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculateTeamEfficiencyDifferential() {
        return TeamBoxScoreCalculations.calculateEfficiencyDifferential(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private Float calculateTeamOffEff() {
//        bsTeam.points * 100 / (bsTeam.fieldGoalAttempts - bsTeam.reboundsOffense + bsTeam.turnovers + (.44 * bsTeam.freeThrowAttempts))
        return 0F;
    }

    private Float calculateTeamDEFR() {
//      (bsTeam.blocks + bsTeam.steals) * 100 / (bsOppt.fieldGoalAttempts - bsOppt.reboundsOffense + bsOppt.turnovers + (.44 * bsOppt.freeThrowAttempts))
        return 0F;
    }

    private Float calculateTeamAR() {
        //bsTeam.assists * 100 / (bsTeam.fieldGoalAttempts + (.44 * bsTeam.freeThrowAttempts) + bsTeam.turnovers)
        return 0F;
    }

    private Float calculateTeamTOR() {
        //bsTeam.turnovers * 100 / (bsTeam.fieldGoalAttempts + (.44 * bsTeam.freeThrowAttempts) + bsTeam.turnovers)
        return 0F;
    }

    private Float calculateTeamBlockPct2() {
        //bsTeam.blocks / bsTeam.fieldGoalAttempts
        return 0F;
    }

    private Float calculateTeamBLK() {
        //bsTeam.blocks * 100 / (bsOppt.fieldGoalAttempts - bsOppt.reboundsOffense + bsOppt.turnovers + (.44 * bsOppt.freeThrowAttempts))
        return 0F;
    }

    private Float calculateTeam3PR() {
        //bsTeam.freeThrowAttempts / bsTeam.fieldGoalAttempts as homeFTR, bsTeam.threePointAttempts / bsTeam.fieldGoalAttempts
        return 0F;
    }
}
