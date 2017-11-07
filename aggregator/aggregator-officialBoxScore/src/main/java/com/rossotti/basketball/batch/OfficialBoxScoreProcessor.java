package com.rossotti.basketball.batch;

import org.springframework.batch.item.ItemProcessor;

public class OfficialBoxScoreProcessor implements ItemProcessor<OfficialBoxScore, OfficialBoxScore> {

    @Override
    public OfficialBoxScore process(OfficialBoxScore officialBoxScore) {
        officialBoxScore.setTeamTwoPointAttempts(officialBoxScore.calculateTeamTwoPointAttempt());
        officialBoxScore.setTeamTwoPointMade(officialBoxScore.calculateTeamTwoPointMade());
        officialBoxScore.setTeamTwoPointPct(officialBoxScore.calculateTeamTwoPointPct().floatValue());
        officialBoxScore.setTeamTwoPointRate(officialBoxScore.calculateTeamTwoPointRate().floatValue());
        officialBoxScore.setTeamThreePointPct(officialBoxScore.calculateTeamThreePointPct().floatValue());
        officialBoxScore.setTeamThreePointRate(officialBoxScore.calculateTeamThreePointRate().floatValue());
        officialBoxScore.setTeamFieldGoalPct(officialBoxScore.calculateTeamFieldGoalPct().floatValue());
        officialBoxScore.setTeamFreeThrowPct(officialBoxScore.calculateTeamFreeThrowPct().floatValue());
        officialBoxScore.setTeamFreeThrowRate(officialBoxScore.calculateTeamFreeThrowRate().floatValue());
        officialBoxScore.setTeamReboundsTotal(officialBoxScore.calculateTeamReboundTotal());

        officialBoxScore.setTeamTrueShootingPct(officialBoxScore.calculateTeamTrueShootingPct().floatValue());
        officialBoxScore.setTeamEffectiveFieldGoalPct(officialBoxScore.calculateTeamEffectiveFieldGoalPct().floatValue());
        officialBoxScore.setTeamOffensiveReboundPct(officialBoxScore.calculateTeamOffensiveReboundPct().floatValue());
        officialBoxScore.setTeamDefensiveReboundPct(officialBoxScore.calculateTeamDefensiveReboundPct().floatValue());
        officialBoxScore.setTeamTotalReboundPct(officialBoxScore.calculateTeamTotalReboundPct().floatValue());
        officialBoxScore.setTeamAssistedFieldGoalPct(officialBoxScore.calculateTeamAssistedFieldGoalPct().floatValue());
        officialBoxScore.setTeamTurnoverPct(officialBoxScore.calculateTeamTurnoverPct().floatValue());
        officialBoxScore.setTeamStealPct(officialBoxScore.calculateTeamStealPct().floatValue());
        officialBoxScore.setTeamBlockPct(officialBoxScore.calculateTeamBlockPct().floatValue());
        officialBoxScore.setTeamBlockRate(officialBoxScore.calculateTeamBlockRate().floatValue());
        officialBoxScore.setTeamPointsPerShot(officialBoxScore.calculateTeamPointsPerShot().floatValue());
        officialBoxScore.setTeamFloorImpactCounter(officialBoxScore.calculateTeamFloorImpactCounter().floatValue());
        officialBoxScore.setTeamFloorImpactCounterPer40(officialBoxScore.calculateTeamFloorImpactCounterPer40().floatValue());
        officialBoxScore.setTeamOffensiveRating(officialBoxScore.calculateTeamOffensiveRating().floatValue());
        officialBoxScore.setTeamDefensiveRating(officialBoxScore.calculateTeamDefensiveRating().floatValue());
        officialBoxScore.setTeamEfficiencyDifferential(officialBoxScore.calculateTeamEfficiencyDifferential().floatValue());
        officialBoxScore.setTeamPlayPct(officialBoxScore.calculateTeamPlayPct().floatValue());
        officialBoxScore.setTeamAssistRate(officialBoxScore.calculateTeamAssistRate().floatValue());
        officialBoxScore.setTeamAssistToTurnoverRatio(officialBoxScore.calculateTeamAssistToTurnoverRatio().floatValue());
        officialBoxScore.setTeamStealToTurnoverRatio(officialBoxScore.calculateTeamStealToTurnoverRatio().floatValue());
        
        officialBoxScore.setOpptTwoPointAttempts(officialBoxScore.calculateOpptTwoPointAttempt());
        officialBoxScore.setOpptTwoPointMade(officialBoxScore.calculateOpptTwoPointMade());
        officialBoxScore.setOpptTwoPointPct(officialBoxScore.calculateOpptTwoPointPct().floatValue());
        officialBoxScore.setOpptTwoPointRate(officialBoxScore.calculateOpptTwoPointRate().floatValue());
        officialBoxScore.setOpptThreePointPct(officialBoxScore.calculateOpptThreePointPct().floatValue());
        officialBoxScore.setOpptThreePointRate(officialBoxScore.calculateOpptThreePointRate().floatValue());
        officialBoxScore.setOpptFieldGoalPct(officialBoxScore.calculateOpptFieldGoalPct().floatValue());
        officialBoxScore.setOpptFreeThrowPct(officialBoxScore.calculateOpptFreeThrowPct().floatValue());
        officialBoxScore.setOpptFreeThrowRate(officialBoxScore.calculateOpptFreeThrowRate().floatValue());
        officialBoxScore.setOpptReboundsTotal(officialBoxScore.calculateOpptReboundTotal());

        officialBoxScore.setOpptTrueShootingPct(officialBoxScore.calculateOpptTrueShootingPct().floatValue());
        officialBoxScore.setOpptEffectiveFieldGoalPct(officialBoxScore.calculateOpptEffectiveFieldGoalPct().floatValue());
        officialBoxScore.setOpptOffensiveReboundPct(officialBoxScore.calculateOpptOffensiveReboundPct().floatValue());
        officialBoxScore.setOpptDefensiveReboundPct(officialBoxScore.calculateOpptDefensiveReboundPct().floatValue());
        officialBoxScore.setOpptTotalReboundPct(officialBoxScore.calculateOpptTotalReboundPct().floatValue());
        officialBoxScore.setOpptAssistedFieldGoalPct(officialBoxScore.calculateOpptAssistedFieldGoalPct().floatValue());
        officialBoxScore.setOpptTurnoverPct(officialBoxScore.calculateOpptTurnoverPct().floatValue());
        officialBoxScore.setOpptStealPct(officialBoxScore.calculateOpptStealPct().floatValue());
        officialBoxScore.setOpptBlockPct(officialBoxScore.calculateOpptBlockPct().floatValue());
        officialBoxScore.setOpptBlockRate(officialBoxScore.calculateOpptBlockRate().floatValue());
        officialBoxScore.setOpptPointsPerShot(officialBoxScore.calculateOpptPointsPerShot().floatValue());
        officialBoxScore.setOpptFloorImpactCounter(officialBoxScore.calculateOpptFloorImpactCounter().floatValue());
        officialBoxScore.setOpptFloorImpactCounterPer40(officialBoxScore.calculateOpptFloorImpactCounterPer40().floatValue());
        officialBoxScore.setOpptOffensiveRating(officialBoxScore.calculateOpptOffensiveRating().floatValue());
        officialBoxScore.setOpptDefensiveRating(officialBoxScore.calculateOpptDefensiveRating().floatValue());
        officialBoxScore.setOpptEfficiencyDifferential(officialBoxScore.calculateOpptEfficiencyDifferential().floatValue());
        officialBoxScore.setOpptPlayPct(officialBoxScore.calculateOpptPlayPct().floatValue());
        officialBoxScore.setOpptAssistRate(officialBoxScore.calculateOpptAssistRate().floatValue());
        officialBoxScore.setOpptAssistToTurnoverRatio(officialBoxScore.calculateOpptAssistToTurnoverRatio().floatValue());
        officialBoxScore.setOpptStealToTurnoverRatio(officialBoxScore.calculateOpptStealToTurnoverRatio().floatValue());

        officialBoxScore.setPossessions(officialBoxScore.calculatePossessions().floatValue());
        officialBoxScore.setPace(officialBoxScore.calculatePace().floatValue());
        officialBoxScore.setPythagoreanWinningPct_13_91(officialBoxScore.calculatePythagoreanWinningPct_13_91().floatValue());
        officialBoxScore.setPythagoreanWins_13_91(officialBoxScore.calculatePythagoreanWins_13_91().floatValue());
        officialBoxScore.setPythagoreanLosses_13_91(officialBoxScore.calculatePythagoreanLosses_13_91().floatValue());
        officialBoxScore.setPythagoreanWinningPct_16_5(officialBoxScore.calculatePythagoreanWinningPct_16_5().floatValue());
        officialBoxScore.setPythagoreanWins_16_5(officialBoxScore.calculatePythagoreanWins_16_5().floatValue());
        officialBoxScore.setPythagoreanLosses_16_5(officialBoxScore.calculatePythagoreanLosses_16_5().floatValue());
        return officialBoxScore;
    }
}
