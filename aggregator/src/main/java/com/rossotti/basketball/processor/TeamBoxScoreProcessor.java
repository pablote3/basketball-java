package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.Standing;
import com.rossotti.basketball.model.TeamBoxScore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import java.math.BigDecimal;

public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private final Logger logger = LoggerFactory.getLogger(TeamBoxScoreProcessor.class);
    private TeamBoxScore teamBoxScore;

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScoreIn) {
        teamBoxScore = teamBoxScoreIn;
        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace().floatValue());
        teamBoxScore.setTeamTrueShootingPct(calculateTeamTrueShootingPct().floatValue());
        return teamBoxScore;
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

    private BigDecimal calculateTeamTrueShootingPct() {
        return BoxScoreCalculations.calculateTrueShootingPct(
            teamBoxScore.getTeamPoints(), teamBoxScore.getTeamFieldGoalAttempts(), teamBoxScore.getTeamFreeThrowAttempts()
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

    private Float calculateTeamOffensiveReboundPercentage() {
        //bsTeam.reboundsOffense * 100 / (bsTeam.reboundsOffense + bsOppt.reboundsDefense)
        return 0F;
    }

    private Float calculateTeamDefensiveReboundPercentage() {
        //bsTeam.reboundsDefense * 100 / (bsTeam.reboundsOffense + bsOppt.reboundsDefense)
        return 0F;
    }

    private Float calculateTeamBlockPercentage() {
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
