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
        teamBoxScore.setTeamPlayPct(calculateTeamPlayPct().floatValue());
        teamBoxScore.setTeamAssistRate(calculateTeamAssisstRate().floatValue());
        teamBoxScore.setTeamAssistToTurnoverRatio(calculateTeamAssistToTurnoverRatio().floatValue());
        teamBoxScore.setTeamStealToTurnoverRatio(calculateTeamStealToTurnoverRatio().floatValue());
        
        teamBoxScore.setOpptTwoPointAttempts(calculateOpptTwoPointAttempt());
        teamBoxScore.setOpptTwoPointMade(calculateOpptTwoPointMade());
        teamBoxScore.setOpptTwoPointPct(calculateOpptTwoPointPct().floatValue());
        teamBoxScore.setOpptTwoPointRate(calculateOpptTwoPointRate().floatValue());
        teamBoxScore.setOpptThreePointPct(calculateOpptThreePointPct().floatValue());
        teamBoxScore.setOpptThreePointRate(calculateOpptThreePointRate().floatValue());
        teamBoxScore.setOpptFieldGoalPct(calculateOpptFieldGoalPct().floatValue());
        teamBoxScore.setOpptFreeThrowPct(calculateOpptFreeThrowPct().floatValue());
        teamBoxScore.setOpptFreeThrowRate(calculateOpptFreeThrowRate().floatValue());

        teamBoxScore.setOpptTrueShootingPct(calculateOpptTrueShootingPct().floatValue());
        teamBoxScore.setOpptEffectiveFieldGoalPct(calculateOpptEffectiveFieldGoalPct().floatValue());
        teamBoxScore.setOpptOffensiveReboundPct(calculateOpptOffensiveReboundPct().floatValue());
        teamBoxScore.setOpptDefensiveReboundPct(calculateOpptDefensiveReboundPct().floatValue());
        teamBoxScore.setOpptTotalReboundPct(calculateOpptTotalReboundPct().floatValue());
        teamBoxScore.setOpptAssistedFieldGoalPct(calculateOpptAssistedFieldGoalPct().floatValue());
        teamBoxScore.setOpptTurnoverPct(calculateOpptTurnoverPct().floatValue());
        teamBoxScore.setOpptStealPct(calculateOpptStealPct().floatValue());
        teamBoxScore.setOpptBlockPct(calculateOpptBlockPct().floatValue());
        teamBoxScore.setOpptBlockRate(calculateOpptBlockRate().floatValue());
        teamBoxScore.setOpptPointsPerShot(calculateOpptPointsPerShot().floatValue());
        teamBoxScore.setOpptFloorImpactCounter(calculateOpptFloorImpactCounter().floatValue());
        teamBoxScore.setOpptFloorImpactCounterPer40(calculateOpptFloorImpactCounterPer40().floatValue());
        teamBoxScore.setOpptOffensiveRating(calculateOpptOffensiveRating().floatValue());
        teamBoxScore.setOpptDefensiveRating(calculateOpptDefensiveRating().floatValue());
        teamBoxScore.setOpptEfficiencyDifferential(calculateOpptEfficiencyDifferential().floatValue());
        teamBoxScore.setOpptPlayPct(calculateOpptPlayPct().floatValue());
        teamBoxScore.setOpptAssistRate(calculateOpptAssisstRate().floatValue());
        teamBoxScore.setOpptAssistToTurnoverRatio(calculateOpptAssistToTurnoverRatio().floatValue());
        teamBoxScore.setOpptStealToTurnoverRatio(calculateOpptStealToTurnoverRatio().floatValue());

        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace().floatValue());
        teamBoxScore.setPythagoreanWinningPct_13_91(calculatePythagoreanWinningPct_13_91().floatValue());
        teamBoxScore.setPythagoreanWins_13_91(calculatePythagoreanWins_13_91().floatValue());
        teamBoxScore.setPythagoreanLosses_13_91(calculatePythagoreanLosses_13_91().floatValue());
        teamBoxScore.setPythagoreanWinningPct_16_5(calculatePythagoreanWinningPct_16_5().floatValue());
        teamBoxScore.setPythagoreanWins_16_5(calculatePythagoreanWins_16_5().floatValue());
        teamBoxScore.setPythagoreanLosses_16_5(calculatePythagoreanLosses_16_5().floatValue());
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

    private BigDecimal calculateTeamPlayPct() {
        return TeamBoxScoreCalculations.calculatePlayPct(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssisstRate() {
        return TeamBoxScoreCalculations.calculateAssistRate(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssistToTurnoverRatio() {
        return TeamBoxScoreCalculations.calculateAssistToTurnoverRatio(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamStealToTurnoverRatio() {
        return TeamBoxScoreCalculations.calculateStealToTurnoverRatio(
            teamBoxScore.getTeamSteals(), teamBoxScore.getTeamTurnovers()
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

    private BigDecimal calculateOpptTrueShootingPct() {
        return TeamBoxScoreCalculations.calculateTrueShootingPct(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptEffectiveFieldGoalPct() {
        return TeamBoxScoreCalculations.calculateEffectiveFieldGoalPct(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptOffensiveReboundPct() {
        return TeamBoxScoreCalculations.calculateOffensiveReboundPct(
            teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateOpptDefensiveReboundPct() {
        return TeamBoxScoreCalculations.calculateDefensiveReboundPct(
            teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateOpptTotalReboundPct() {
        return TeamBoxScoreCalculations.calculateTotalReboundPct(
            teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense()
        );
    }

    private BigDecimal calculateOpptAssistedFieldGoalPct() {
        return TeamBoxScoreCalculations.calculateAssistedFieldGoalPct(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptFieldGoalMade()
        );
    }

    private BigDecimal calculateOpptTurnoverPct() {
        return TeamBoxScoreCalculations.calculateTurnoverPct(
            teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptStealPct() {
        return TeamBoxScoreCalculations.calculateStealPct(
            teamBoxScore.getOpptSteals(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockPct() {
        return TeamBoxScoreCalculations.calculateBlockPct(
            teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockRate() {
        return TeamBoxScoreCalculations.calculateBlockRate(
            teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
    }

    private BigDecimal calculateOpptPointsPerShot() {
        return TeamBoxScoreCalculations.calculatePointsPerShot(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounter() {
        return TeamBoxScoreCalculations.calculateFloorImpactCounter(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptAssists(), teamBoxScore.getOpptSteals(), teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(),
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptPersonalFouls()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounterPer40() {
        return TeamBoxScoreCalculations.calculateFloorImpactCounterPer40(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptAssists(), teamBoxScore.getOpptSteals(), teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(),
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptPersonalFouls(), teamBoxScore.getOpptMinutes()
        );
    }

    private BigDecimal calculateOpptOffensiveRating() {
        return TeamBoxScoreCalculations.calculateOffensiveRating(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculateOpptDefensiveRating() {
        return TeamBoxScoreCalculations.calculateDefensiveRating(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptEfficiencyDifferential() {
        return TeamBoxScoreCalculations.calculateEfficiencyDifferential(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptPoints(), teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptPlayPct() {
        return TeamBoxScoreCalculations.calculatePlayPct(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssisstRate() {
        return TeamBoxScoreCalculations.calculateAssistRate(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssistToTurnoverRatio() {
        return TeamBoxScoreCalculations.calculateAssistToTurnoverRatio(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptStealToTurnoverRatio() {
        return TeamBoxScoreCalculations.calculateStealToTurnoverRatio(
            teamBoxScore.getOpptSteals(), teamBoxScore.getOpptTurnovers()
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

    private BigDecimal calculatePythagoreanWinningPct_13_91() {
        return TeamBoxScoreCalculations.calculatePythagoreanWinningPct_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWins_13_91() {
        return TeamBoxScoreCalculations.calculatePythagoreanWins_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanLosses_13_91() {
        return TeamBoxScoreCalculations.calculatePythagoreanLosses_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWinningPct_16_5() {
        return TeamBoxScoreCalculations.calculatePythagoreanWinningPct_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWins_16_5() {
        return TeamBoxScoreCalculations.calculatePythagoreanWins_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanLosses_16_5() {
        return TeamBoxScoreCalculations.calculatePythagoreanLosses_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }
}
