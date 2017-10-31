package com.rossotti.basketball.batch;

import com.rossotti.basketball.calc.BoxScoreCalculations;
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
        teamBoxScore.setTeamReboundsTotal(calculateTeamReboundTotal());

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
        teamBoxScore.setOpptReboundsTotal(calculateOpptReboundTotal());

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
        teamBoxScore.setOpptAssistRate(calculateOpptAssistRate().floatValue());
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
        return BoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade()
        );
    }
    private Short calculateTeamTwoPointAttempt() {
        return BoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
    }
    private BigDecimal calculateTeamTwoPointPct() {
        Short attempt = BoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
        Short made = BoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade()
        );
        return BoxScoreCalculations.calculatePercent(made, attempt);
    }
    private BigDecimal calculateTeamTwoPointRate() {
        return BoxScoreCalculations.calculatePercent (
            teamBoxScore.getTeamTwoPointAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamThreePointPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamThreePointMade(), teamBoxScore.getTeamThreePointAttempts()
        );
    }
    private BigDecimal calculateTeamThreePointRate() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamThreePointAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFieldGoalPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFreeThrowPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFreeThrowMade(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }
    private BigDecimal calculateTeamFreeThrowRate() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private Short calculateTeamReboundTotal() {
        return BoxScoreCalculations.calculateTotalRebound(
                teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense()
        );
    }

    private BigDecimal calculateTeamTrueShootingPct() {
        return BoxScoreCalculations.calculateTrueShootingPct(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamEffectiveFieldGoalPct() {
        return BoxScoreCalculations.calculateEffectiveFieldGoalPct(
            teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamThreePointMade(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamOffensiveReboundPct() {
        return BoxScoreCalculations.calculateOffensiveReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamDefensiveReboundPct() {
        return BoxScoreCalculations.calculateDefensiveReboundPct(
            teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateTeamTotalReboundPct() {
        return BoxScoreCalculations.calculateTotalReboundPct(
            teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamAssistedFieldGoalPct() {
        return BoxScoreCalculations.calculateAssistedFieldGoalPct(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamFieldGoalMade()
        );
    }

    private BigDecimal calculateTeamTurnoverPct() {
        return BoxScoreCalculations.calculateTurnoverPct(
            teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamStealPct() {
        return BoxScoreCalculations.calculateStealPct(
            teamBoxScore.getTeamSteals(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockPct() {
        return BoxScoreCalculations.calculateBlockPct(
            teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockRate() {
        return BoxScoreCalculations.calculateBlockRate(
            teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamThreePointAttempts()
        );
    }

    private BigDecimal calculateTeamPointsPerShot() {
        return BoxScoreCalculations.calculatePointsPerShot(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounter() {
        return BoxScoreCalculations.calculateFloorImpactCounter(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getTeamAssists(), teamBoxScore.getTeamSteals(), teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(),
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamPersonalFouls()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounterPer40() {
        return BoxScoreCalculations.calculateFloorImpactCounterPer40(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getTeamAssists(), teamBoxScore.getTeamSteals(), teamBoxScore.getTeamBlocks(), teamBoxScore.getTeamFieldGoalAttempts(),
            teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamPersonalFouls(), teamBoxScore.getTeamMinutes()
        );
    }

    private BigDecimal calculateTeamOffensiveRating() {
        return BoxScoreCalculations.calculateOffensiveRating(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateTeamDefensiveRating() {
        return BoxScoreCalculations.calculateDefensiveRating(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculateTeamEfficiencyDifferential() {
        return BoxScoreCalculations.calculateEfficiencyDifferential(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculateTeamPlayPct() {
        return BoxScoreCalculations.calculatePlayPct(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssisstRate() {
        return BoxScoreCalculations.calculateAssistRate(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssistToTurnoverRatio() {
        return BoxScoreCalculations.calculateAssistToTurnoverRatio(
            teamBoxScore.getTeamAssists(), teamBoxScore.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamStealToTurnoverRatio() {
        return BoxScoreCalculations.calculateStealToTurnoverRatio(
            teamBoxScore.getTeamSteals(), teamBoxScore.getTeamTurnovers()
        );
    }



    private Short calculateOpptTwoPointMade() {
        return BoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade()
        );
    }
    private Short calculateOpptTwoPointAttempt() {
        return BoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
    }
    private BigDecimal calculateOpptTwoPointPct() {
        Short attempt = BoxScoreCalculations.calculateTwoPointAttempt(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
        Short made = BoxScoreCalculations.calculateTwoPointMade(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade()
        );
        return BoxScoreCalculations.calculatePercent(
            made, attempt
        );
    }
    private BigDecimal calculateOpptTwoPointRate() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptTwoPointAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptThreePointPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptThreePointMade(), teamBoxScore.getOpptThreePointAttempts()
        );
    }
    private BigDecimal calculateOpptThreePointRate() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptThreePointAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFieldGoalPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFreeThrowPct() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptFreeThrowMade(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }
    private BigDecimal calculateOpptFreeThrowRate() {
        return BoxScoreCalculations.calculatePercent(
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private Short calculateOpptReboundTotal() {
        return BoxScoreCalculations.calculateTotalRebound(
            teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateOpptTrueShootingPct() {
        return BoxScoreCalculations.calculateTrueShootingPct(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptEffectiveFieldGoalPct() {
        return BoxScoreCalculations.calculateEffectiveFieldGoalPct(
            teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptThreePointMade(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptOffensiveReboundPct() {
        return BoxScoreCalculations.calculateOffensiveReboundPct(
            teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateOpptDefensiveReboundPct() {
        return BoxScoreCalculations.calculateDefensiveReboundPct(
            teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateOpptTotalReboundPct() {
        return BoxScoreCalculations.calculateTotalReboundPct(
            teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getTeamReboundsDefense()
        );
    }

    private BigDecimal calculateOpptAssistedFieldGoalPct() {
        return BoxScoreCalculations.calculateAssistedFieldGoalPct(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptFieldGoalMade()
        );
    }

    private BigDecimal calculateOpptTurnoverPct() {
        return BoxScoreCalculations.calculateTurnoverPct(
            teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptStealPct() {
        return BoxScoreCalculations.calculateStealPct(
            teamBoxScore.getOpptSteals(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockPct() {
        return BoxScoreCalculations.calculateBlockPct(
            teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockRate() {
        return BoxScoreCalculations.calculateBlockRate(
            teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptThreePointAttempts()
        );
    }

    private BigDecimal calculateOpptPointsPerShot() {
        return BoxScoreCalculations.calculatePointsPerShot(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounter() {
        return BoxScoreCalculations.calculateFloorImpactCounter(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptAssists(), teamBoxScore.getOpptSteals(), teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(),
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptPersonalFouls()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounterPer40() {
        return BoxScoreCalculations.calculateFloorImpactCounterPer40(
            teamBoxScore.getOpptPoints(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getOpptAssists(), teamBoxScore.getOpptSteals(), teamBoxScore.getOpptBlocks(), teamBoxScore.getOpptFieldGoalAttempts(),
            teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptPersonalFouls(), teamBoxScore.getOpptMinutes()
        );
    }

    private BigDecimal calculateOpptOffensiveRating() {
        return BoxScoreCalculations.calculateOffensiveRating(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculateOpptDefensiveRating() {
        return BoxScoreCalculations.calculateDefensiveRating(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptEfficiencyDifferential() {
        return BoxScoreCalculations.calculateEfficiencyDifferential(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptPoints(), teamBoxScore.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptPlayPct() {
        return BoxScoreCalculations.calculatePlayPct(
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssistRate() {
        return BoxScoreCalculations.calculateAssistRate(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptFreeThrowAttempts(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssistToTurnoverRatio() {
        return BoxScoreCalculations.calculateAssistToTurnoverRatio(
            teamBoxScore.getOpptAssists(), teamBoxScore.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptStealToTurnoverRatio() {
        return BoxScoreCalculations.calculateStealToTurnoverRatio(
            teamBoxScore.getOpptSteals(), teamBoxScore.getOpptTurnovers()
        );
    }


    private BigDecimal calculatePossessions() {
        return BoxScoreCalculations.calculatePossessions(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculatePace() {
        return BoxScoreCalculations.calculatePace(
            teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamReboundsOffense(), teamBoxScore.getOpptReboundsDefense(), teamBoxScore.getTeamFieldGoalMade(), teamBoxScore.getTeamTurnovers(), teamBoxScore.getTeamFreeThrowAttempts(),
            teamBoxScore.getOpptFieldGoalAttempts(), teamBoxScore.getOpptReboundsOffense(), teamBoxScore.getTeamReboundsDefense(), teamBoxScore.getOpptFieldGoalMade(), teamBoxScore.getOpptTurnovers(), teamBoxScore.getOpptFreeThrowAttempts(),
            teamBoxScore.getTeamMinutes()
        );
    }

    private BigDecimal calculatePythagoreanWinningPct_13_91() {
        return BoxScoreCalculations.calculatePythagoreanWinningPct_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWins_13_91() {
        return BoxScoreCalculations.calculatePythagoreanWins_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanLosses_13_91() {
        return BoxScoreCalculations.calculatePythagoreanLosses_13_91(
            teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWinningPct_16_5() {
        return BoxScoreCalculations.calculatePythagoreanWinningPct_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanWins_16_5() {
        return BoxScoreCalculations.calculatePythagoreanWins_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }

    private BigDecimal calculatePythagoreanLosses_16_5() {
        return BoxScoreCalculations.calculatePythagoreanLosses_16_5(
                teamBoxScore.getTeamPoints(), teamBoxScore.getOpptPoints()
        );
    }
}
