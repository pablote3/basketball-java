package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;

public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScore) {
        teamBoxScore.setTeamTwoPointAttempts(teamBoxScore.calculateTeamTwoPointAttempt());
        teamBoxScore.setTeamTwoPointMade(teamBoxScore.calculateTeamTwoPointMade());
        teamBoxScore.setTeamTwoPointPct(teamBoxScore.calculateTeamTwoPointPct().floatValue());
        teamBoxScore.setTeamTwoPointRate(teamBoxScore.calculateTeamTwoPointRate().floatValue());
        teamBoxScore.setTeamThreePointPct(teamBoxScore.calculateTeamThreePointPct().floatValue());
        teamBoxScore.setTeamThreePointRate(teamBoxScore.calculateTeamThreePointRate().floatValue());
        teamBoxScore.setTeamFieldGoalPct(teamBoxScore.calculateTeamFieldGoalPct().floatValue());
        teamBoxScore.setTeamFreeThrowPct(teamBoxScore.calculateTeamFreeThrowPct().floatValue());
        teamBoxScore.setTeamFreeThrowRate(teamBoxScore.calculateTeamFreeThrowRate().floatValue());
        teamBoxScore.setTeamReboundsTotal(teamBoxScore.calculateTeamReboundTotal());

        teamBoxScore.setTeamTrueShootingPct(teamBoxScore.calculateTeamTrueShootingPct().floatValue());
        teamBoxScore.setTeamEffectiveFieldGoalPct(teamBoxScore.calculateTeamEffectiveFieldGoalPct().floatValue());
        teamBoxScore.setTeamOffensiveReboundPct(teamBoxScore.calculateTeamOffensiveReboundPct().floatValue());
        teamBoxScore.setTeamDefensiveReboundPct(teamBoxScore.calculateTeamDefensiveReboundPct().floatValue());
        teamBoxScore.setTeamTotalReboundPct(teamBoxScore.calculateTeamTotalReboundPct().floatValue());
        teamBoxScore.setTeamAssistedFieldGoalPct(teamBoxScore.calculateTeamAssistedFieldGoalPct().floatValue());
        teamBoxScore.setTeamTurnoverPct(teamBoxScore.calculateTeamTurnoverPct().floatValue());
        teamBoxScore.setTeamStealPct(teamBoxScore.calculateTeamStealPct().floatValue());
        teamBoxScore.setTeamBlockPct(teamBoxScore.calculateTeamBlockPct().floatValue());
        teamBoxScore.setTeamBlockRate(teamBoxScore.calculateTeamBlockRate().floatValue());
        teamBoxScore.setTeamPointsPerShot(teamBoxScore.calculateTeamPointsPerShot().floatValue());
        teamBoxScore.setTeamFloorImpactCounter(teamBoxScore.calculateTeamFloorImpactCounter().floatValue());
        teamBoxScore.setTeamFloorImpactCounterPer40(teamBoxScore.calculateTeamFloorImpactCounterPer40().floatValue());
        teamBoxScore.setTeamOffensiveRating(teamBoxScore.calculateTeamOffensiveRating().floatValue());
        teamBoxScore.setTeamDefensiveRating(teamBoxScore.calculateTeamDefensiveRating().floatValue());
        teamBoxScore.setTeamEfficiencyDifferential(teamBoxScore.calculateTeamEfficiencyDifferential().floatValue());
        teamBoxScore.setTeamPlayPct(teamBoxScore.calculateTeamPlayPct().floatValue());
        teamBoxScore.setTeamAssistRate(teamBoxScore.calculateTeamAssistRate().floatValue());
        teamBoxScore.setTeamAssistToTurnoverRatio(teamBoxScore.calculateTeamAssistToTurnoverRatio().floatValue());
        teamBoxScore.setTeamStealToTurnoverRatio(teamBoxScore.calculateTeamStealToTurnoverRatio().floatValue());
        
        teamBoxScore.setOpptTwoPointAttempts(teamBoxScore.calculateOpptTwoPointAttempt());
        teamBoxScore.setOpptTwoPointMade(teamBoxScore.calculateOpptTwoPointMade());
        teamBoxScore.setOpptTwoPointPct(teamBoxScore.calculateOpptTwoPointPct().floatValue());
        teamBoxScore.setOpptTwoPointRate(teamBoxScore.calculateOpptTwoPointRate().floatValue());
        teamBoxScore.setOpptThreePointPct(teamBoxScore.calculateOpptThreePointPct().floatValue());
        teamBoxScore.setOpptThreePointRate(teamBoxScore.calculateOpptThreePointRate().floatValue());
        teamBoxScore.setOpptFieldGoalPct(teamBoxScore.calculateOpptFieldGoalPct().floatValue());
        teamBoxScore.setOpptFreeThrowPct(teamBoxScore.calculateOpptFreeThrowPct().floatValue());
        teamBoxScore.setOpptFreeThrowRate(teamBoxScore.calculateOpptFreeThrowRate().floatValue());
        teamBoxScore.setOpptReboundsTotal(teamBoxScore.calculateOpptReboundTotal());

        teamBoxScore.setOpptTrueShootingPct(teamBoxScore.calculateOpptTrueShootingPct().floatValue());
        teamBoxScore.setOpptEffectiveFieldGoalPct(teamBoxScore.calculateOpptEffectiveFieldGoalPct().floatValue());
        teamBoxScore.setOpptOffensiveReboundPct(teamBoxScore.calculateOpptOffensiveReboundPct().floatValue());
        teamBoxScore.setOpptDefensiveReboundPct(teamBoxScore.calculateOpptDefensiveReboundPct().floatValue());
        teamBoxScore.setOpptTotalReboundPct(teamBoxScore.calculateOpptTotalReboundPct().floatValue());
        teamBoxScore.setOpptAssistedFieldGoalPct(teamBoxScore.calculateOpptAssistedFieldGoalPct().floatValue());
        teamBoxScore.setOpptTurnoverPct(teamBoxScore.calculateOpptTurnoverPct().floatValue());
        teamBoxScore.setOpptStealPct(teamBoxScore.calculateOpptStealPct().floatValue());
        teamBoxScore.setOpptBlockPct(teamBoxScore.calculateOpptBlockPct().floatValue());
        teamBoxScore.setOpptBlockRate(teamBoxScore.calculateOpptBlockRate().floatValue());
        teamBoxScore.setOpptPointsPerShot(teamBoxScore.calculateOpptPointsPerShot().floatValue());
        teamBoxScore.setOpptFloorImpactCounter(teamBoxScore.calculateOpptFloorImpactCounter().floatValue());
        teamBoxScore.setOpptFloorImpactCounterPer40(teamBoxScore.calculateOpptFloorImpactCounterPer40().floatValue());
        teamBoxScore.setOpptOffensiveRating(teamBoxScore.calculateOpptOffensiveRating().floatValue());
        teamBoxScore.setOpptDefensiveRating(teamBoxScore.calculateOpptDefensiveRating().floatValue());
        teamBoxScore.setOpptEfficiencyDifferential(teamBoxScore.calculateOpptEfficiencyDifferential().floatValue());
        teamBoxScore.setOpptPlayPct(teamBoxScore.calculateOpptPlayPct().floatValue());
        teamBoxScore.setOpptAssistRate(teamBoxScore.calculateOpptAssistRate().floatValue());
        teamBoxScore.setOpptAssistToTurnoverRatio(teamBoxScore.calculateOpptAssistToTurnoverRatio().floatValue());
        teamBoxScore.setOpptStealToTurnoverRatio(teamBoxScore.calculateOpptStealToTurnoverRatio().floatValue());

        teamBoxScore.setPossessions(teamBoxScore.calculatePossessions().floatValue());
        teamBoxScore.setPace(teamBoxScore.calculatePace().floatValue());
        teamBoxScore.setPythagoreanWinningPct_13_91(teamBoxScore.calculatePythagoreanWinningPct_13_91().floatValue());
        teamBoxScore.setPythagoreanWins_13_91(teamBoxScore.calculatePythagoreanWins_13_91().floatValue());
        teamBoxScore.setPythagoreanLosses_13_91(teamBoxScore.calculatePythagoreanLosses_13_91().floatValue());
        teamBoxScore.setPythagoreanWinningPct_16_5(teamBoxScore.calculatePythagoreanWinningPct_16_5().floatValue());
        teamBoxScore.setPythagoreanWins_16_5(teamBoxScore.calculatePythagoreanWins_16_5().floatValue());
        teamBoxScore.setPythagoreanLosses_16_5(teamBoxScore.calculatePythagoreanLosses_16_5().floatValue());
        return teamBoxScore;
    }
}
