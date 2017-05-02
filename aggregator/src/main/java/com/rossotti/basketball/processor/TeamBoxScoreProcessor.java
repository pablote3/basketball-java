package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.TeamBoxScore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;
import java.math.RoundingMode;


public class TeamBoxScoreProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private final Logger logger = LoggerFactory.getLogger(TeamBoxScoreProcessor.class);
    private TeamBoxScore teamBoxScore;

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScoreIn) {
        teamBoxScore = teamBoxScoreIn;
        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace().floatValue());
        return teamBoxScore;
    }

    private BigDecimal calculatePossessions() {
        BigDecimal bdTeam1, bdTeam2, bdTeam3;
        bdTeam1 = new BigDecimal(teamBoxScore.getTeamFieldGoalAttempts());
        bdTeam2 = new BigDecimal(teamBoxScore.getTeamReboundsOffense())
            .divide(new BigDecimal(teamBoxScore.getTeamReboundsOffense() + teamBoxScore.getOpptReboundsDefense()), 4, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(teamBoxScore.getTeamFieldGoalAttempts() - teamBoxScore.getTeamFieldGoalMade()))
            .multiply(new BigDecimal(1.07));
        bdTeam3 = bdTeam1.subtract(bdTeam2)
            .add(new BigDecimal(teamBoxScore.getTeamTurnovers()))
            .add(new BigDecimal(.4 * teamBoxScore.getTeamFreeThrowAttempts()));

        BigDecimal bdOppt1, bdOppt2, bdOppt3;
        bdOppt1 = new BigDecimal(teamBoxScore.getOpptFieldGoalAttempts());
        bdOppt2 = new BigDecimal(teamBoxScore.getOpptReboundsOffense())
            .divide(new BigDecimal(teamBoxScore.getOpptReboundsOffense() + teamBoxScore.getTeamReboundsDefense()), 4, RoundingMode.HALF_UP)
            .multiply(new BigDecimal(teamBoxScore.getOpptFieldGoalAttempts() - teamBoxScore.getOpptFieldGoalMade()))
            .multiply(new BigDecimal(1.07));
        bdOppt3 = bdOppt1.subtract(bdOppt2)
            .add(new BigDecimal(teamBoxScore.getOpptTurnovers()))
            .add(new BigDecimal(.4 * teamBoxScore.getOpptFreeThrowAttempts()));

        return (bdTeam3.add(bdOppt3)).divide(new BigDecimal(2), 4, RoundingMode.HALF_UP);
    }

    private BigDecimal calculatePace() {
        return calculatePossessions()
            .multiply(new BigDecimal(48 * 5))
            .divide(new BigDecimal(teamBoxScore.getTeamMinutes()), 4, RoundingMode.HALF_UP);
    }

    private Float calculateTeamOffEff() {
//        bsTeam.points * 100 / (bsTeam.fieldGoalAttempts - bsTeam.reboundsOffense + bsTeam.turnovers + (.44 * bsTeam.freeThrowAttempts))
        return 0F;
    }

    private Float calculateTeamDEFR() {
//      (bsTeam.blocks + bsTeam.steals) * 100 / (bsOppt.fieldGoalAttempts - bsOppt.reboundsOffense + bsOppt.turnovers + (.44 * bsOppt.freeThrowAttempts))
        return 0F;
    }

    private Float calculateTeamTrueShootingAPercentage() {
        //bsTeam.points / (2 * (bsTeam.fieldGoalAttempts + (0.44 * bsTeam.freeThrowAttempts)))
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
