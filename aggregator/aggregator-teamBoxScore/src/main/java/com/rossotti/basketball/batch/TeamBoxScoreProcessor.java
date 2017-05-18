package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;
import java.math.BigDecimal;

public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private TeamBoxScore teamBoxScore;

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScoreIn) {
        teamBoxScore = teamBoxScoreIn;
        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace().floatValue());
        teamBoxScore.setTeamTrueShootingPct(calculateTeamTrueShootingPct().floatValue());
        teamBoxScore.setTeamEffectiveFieldGoalPct(calculateTeamEffectiveFieldGoalPct().floatValue());
        teamBoxScore.setTeamOffensiveReboundPct(calculateTeamOffensiveReboundPercentage().floatValue());
        teamBoxScore.setTeamDefensiveReboundPct(calculateTeamDefensiveReboundPercentage().floatValue());
        teamBoxScore.setTeamTotalReboundPct(calculateTeamTotalReboundPercentage().floatValue());
        teamBoxScore.setTeamAssistedFieldGoalPct(calculateTeamAssistedFieldGoalPercentage().floatValue());
        teamBoxScore.setTeamTurnoverPct(calculateTeamTurnoverPercentage().floatValue());
        teamBoxScore.setTeamStealPct(calculateTeamStealPercentage().floatValue());
        teamBoxScore.setTeamBlockPct(calculateTeamBlockPercentage().floatValue());
        teamBoxScore.setTeamBlockRate(calculateTeamBlockRate().floatValue());
        teamBoxScore.setTeamPointsPerShot(calculateTeamPointsPerShot().floatValue());
        teamBoxScore.setTeamFloorImpactCounter(calculateTeamFloorImpactCounter().floatValue());
        teamBoxScore.setTeamFloorImpactCounterPer40(calculateTeamFloorImpactCounterPer40().floatValue());
        teamBoxScore.setTeamOffensiveRating(calculateTeamOffensiveRating().floatValue());
        teamBoxScore.setTeamDefensiveRating(calculateTeamDefensiveRating().floatValue());
        teamBoxScore.setTeamEfficiencyDifferential(calculateTeamEfficiencyDifferential().floatValue());
        return teamBoxScore;
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

    private BigDecimal calculateTeamOffensiveReboundPercentage() {
        return TeamBoxScoreCalculations.calculateOffensiveReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamDefensiveReboundPercentage() {
        return TeamBoxScoreCalculations.calculateDefensiveReboundPct(
            teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateTeamTotalReboundPercentage() {
        return TeamBoxScoreCalculations.calculateTotalReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamAssistedFieldGoalPercentage() {
        return TeamBoxScoreCalculations.calculateAssistedFieldGoalPct(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamFieldGoalMade()
        );
    }

    private BigDecimal calculateTeamTurnoverPercentage() {
        return TeamBoxScoreCalculations.calculateTurnoverPct(
            teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamStealPercentage() {
        return TeamBoxScoreCalculations.calculateStealPct(
            teamBoxScore.getTeamSteals(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockPercentage() {
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

    private Float calculateTeamFieldGoalPercentage() {
        //bsTeam.fieldGoalMade / bsTeam.fieldGoalAttempts
        return 0F;
    }

    private Float calculateTeamThreePointPercentage() {
        //bsTeam.threePointMade / bsTeam.threePointAttempts
        return 0F;
    }

    private Float calculateTeamFreeThrowPercentage() {
        //bsTeam.freeThrowMade / bsTeam.freeThrowAttempts
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

    private Float calculateTeamBlockPct() {
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
