package com.rossotti.basketball.processor;

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
        logger.info("TeamBoxScore: " + teamBoxScore.getTeamAbbr());
        teamBoxScore.setPossessions(calculatePossessions().floatValue());
        teamBoxScore.setPace(calculatePace());

        return this.teamBoxScore;
    }

    private Float calculatePossessions() {
        BigDecimal bd = new BigDecimal(
        (teamBoxScore.getTeamFieldGoalAttempts() - (teamBoxScore.getTeamReboundsOffense() / (teamBoxScore.getTeamReboundsOffense() + teamBoxScore.getOpptReboundsDefense())) * (teamBoxScore.getTeamFieldGoalAttempts() - teamBoxScore.getTeamFieldGoalMade()) * 1.07 + teamBoxScore.getTeamTurnovers() + (.4 * teamBoxScore.getTeamFreeThrowAttempts()))
        +
           (teamBoxScore.getOpptFieldGoalAttempts() - (teamBoxScore.getOpptReboundsOffense() / (teamBoxScore.getOpptReboundsOffense() + teamBoxScore.getTeamReboundsDefense())) * (teamBoxScore.getOpptFieldGoalAttempts() - teamBoxScore.getOpptFieldGoalMade()) * 1.07 + teamBoxScore.getOpptTurnovers() + (.4 * teamBoxScore.getOpptFreeThrowAttempts()))
        /2
        );

        return bd.floatValue();
    }

    private Float calculatePossessions2() {
        BigDecimal bd = new BigDecimal(teamBoxScore.getTeamFieldGoalAttempts())
                .subtract((new BigDecimal(teamBoxScore.getTeamReboundsOffense())
                        .divide(new BigDecimal(teamBoxScore.getTeamReboundsOffense() + teamBoxScore.getOpptReboundsDefense())))
                        .multiply(new BigDecimal(teamBoxScore.getOpptFieldGoalAttempts() - teamBoxScore.getOpptFieldGoalMade())
                                .multiply(new BigDecimal(1.07)))
                        .add(new BigDecimal(teamBoxScore.getTeamTurnovers()))
                        .add(new BigDecimal(.4 * teamBoxScore.getTeamFreeThrowAttempts())));
        return bd.floatValue();
    }

    private Float calculatePace() {
        return calculatePossessions() / (teamBoxScore.getTeamMinutes() * 48 * 5);
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
