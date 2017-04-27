package com.rossotti.basketball.processor;

import com.rossotti.basketball.model.TeamBoxScore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class TeamProcessor implements ItemProcessor<TeamBoxScore, TeamBoxScore> {

    private final Logger logger = LoggerFactory.getLogger(TeamProcessor.class);

    @Override
    public TeamBoxScore process(TeamBoxScore teamBoxScore) throws Exception {
        logger.info("TeamBoxScore: " + teamBoxScore.getTeamAbbr());

        teamBoxScore.setPossessions(calculateTeamPossessions());
        teamBoxScore.setPace(calculatePace());

        return teamBoxScore;
    }

    private Float calculateTeamPossessions() {
//      round(((bsTeam.fieldGoalAttempts - (bsTeam.reboundsOffense / (bsTeam.reboundsOffense + bsOppt.reboundsDefense)) * (bsTeam.fieldGoalAttempts - bsTeam.fieldGoalMade) * 1.07 + bsTeam.turnovers + (.4 * bsTeam.freeThrowAttempts)) +
//             (bsOppt.fieldGoalAttempts - (bsOppt.reboundsOffense / (bsOppt.reboundsOffense + bsTeam.reboundsDefense)) * (bsOppt.fieldGoalAttempts - bsOppt.fieldGoalMade) * 1.07 + bsOppt.turnovers + (.4 * bsOppt.freeThrowAttempts))) /2, 4)
        return 0F;
    }

    private Float calculatePace() {
        return 0F;
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
