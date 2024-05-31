package com.rossotti.basketball.model;

import com.rossotti.basketball.calc.BoxScoreCalculations;
import com.rossotti.basketball.calc.CommonCalculations;
import com.rossotti.basketball.util.DateTimeConverter;

import java.math.BigDecimal;

public class BoxScore {
    private String gameDateTime;
    private String gameDate;
    private String gameTime;
    private String seasonType;

    private String teamAbbr;
    private String teamConference;
    private String teamDivision;
    private String teamLocation;
    private String teamResult;
    private Short teamMinutes;
    private Short teamDaysOff;
    private Short teamPoints;
    private Short teamAssists;
    private Short teamTurnovers;
    private Short teamSteals;
    private Short teamBlocks;
    private Short teamPersonalFouls;
    private Short teamFieldGoalAttempts;
    private Short teamFieldGoalMade;
    private Float teamFieldGoalPct;
    private Short teamTwoPointAttempts;
    private Short teamTwoPointMade;
    private Float teamTwoPointPct;
    private Float teamTwoPointRate;
    private Short teamThreePointAttempts;
    private Short teamThreePointMade;
    private Float teamThreePointPct;
    private Float teamThreePointRate;
    private Short teamFreeThrowAttempts;
    private Short teamFreeThrowMade;
    private Float teamFreeThrowPct;
    private Float teamFreeThrowRate;
    private Short teamReboundsOffense;
    private Short teamReboundsDefense;
    private Short teamReboundsTotal;
    private Short teamPointsQ1;
    private Short teamPointsQ2;
    private Short teamPointsQ3;
    private Short teamPointsQ4;
    private Short teamPointsQ5;
    private Short teamPointsQ6;
    private Short teamPointsQ7;
    private Short teamPointsQ8;

    private Float teamTrueShootingPct;
    private Float teamEffectiveFieldGoalPct;
    private Float teamOffensiveReboundPct;
    private Float teamDefensiveReboundPct;
    private Float teamTotalReboundPct;
    private Float teamAssistedFieldGoalPct;
    private Float teamTurnoverPct;
    private Float teamStealPct;
    private Float teamBlockPct;
    private Float teamBlockRate;
    private Float teamPointsPerShot;
    private Float teamFloorImpactCounter;
    private Float teamFloorImpactCounterPer40;
    private Float teamOffensiveRating;
    private Float teamDefensiveRating;
    private Float teamEfficiencyDifferential;
    private Float teamPlayPct;
    private Float teamAssistRate;
    private Float teamAssistToTurnoverRatio;
    private Float teamStealToTurnoverRatio;

    private String opptAbbr;
    private String opptConference;
    private String opptDivision;
    private String opptLocation;
    private String opptResult;
    private Short opptMinutes;
    private Short opptDaysOff;
    private Short opptPoints;
    private Short opptAssists;
    private Short opptTurnovers;
    private Short opptSteals;
    private Short opptBlocks;
    private Short opptPersonalFouls;
    private Short opptFieldGoalAttempts;
    private Short opptFieldGoalMade;
    private Float opptFieldGoalPct;
    private Short opptTwoPointAttempts;
    private Short opptTwoPointMade;
    private Float opptTwoPointPct;
    private Float opptTwoPointRate;
    private Short opptThreePointAttempts;
    private Short opptThreePointMade;
    private Float opptThreePointPct;
    private Float opptThreePointRate;
    private Short opptFreeThrowAttempts;
    private Short opptFreeThrowMade;
    private Float opptFreeThrowPct;
    private Float opptFreeThrowRate;
    private Short opptReboundsOffense;
    private Short opptReboundsDefense;
    private Short opptReboundsTotal;
    private Short opptPointsQ1;
    private Short opptPointsQ2;
    private Short opptPointsQ3;
    private Short opptPointsQ4;
    private Short opptPointsQ5;
    private Short opptPointsQ6;
    private Short opptPointsQ7;
    private Short opptPointsQ8;

    private Float opptTrueShootingPct;
    private Float opptEffectiveFieldGoalPct;
    private Float opptOffensiveReboundPct;
    private Float opptDefensiveReboundPct;
    private Float opptTotalReboundPct;
    private Float opptAssistedFieldGoalPct;
    private Float opptTurnoverPct;
    private Float opptStealPct;
    private Float opptBlockPct;
    private Float opptBlockRate;
    private Float opptPointsPerShot;
    private Float opptFloorImpactCounter;
    private Float opptFloorImpactCounterPer40;
    private Float opptOffensiveRating;
    private Float opptDefensiveRating;
    private Float opptEfficiencyDifferential;
    private Float opptPlayPct;
    private Float opptAssistRate;
    private Float opptAssistToTurnoverRatio;
    private Float opptStealToTurnoverRatio;

    private Float possessions;
    private Float pace;

    public String getGameDateTime() {
        return gameDateTime;
    }
    public void setGameDateTime(String gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public String getGameDate() {
        return gameDate;
    }
    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }
    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getSeasonType() {
        return seasonType;
    }
    public void setSeasonType(String seasonType) {
        this.seasonType = seasonType;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }
    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getTeamConference() {
        return teamConference;
    }
    public void setTeamConference(String teamConference) {
        this.teamConference = teamConference;
    }

    public String getTeamDivision() {
        return teamDivision;
    }
    public void setTeamDivision(String teamDivision) {
        this.teamDivision = teamDivision;
    }

    public String getTeamLocation() {
        return teamLocation;
    }
    public void setTeamLocation(String teamLocation) {
        this.teamLocation = teamLocation;
    }

    public String getTeamResult() {
        return teamResult;
    }
    public void setTeamResult(String teamResult) {
        this.teamResult = teamResult;
    }

    public Short getTeamMinutes() {
        return teamMinutes;
    }
    public void setTeamMinutes(Short teamMinutes) {
        this.teamMinutes = teamMinutes;
    }

    public Short getTeamDaysOff() {
        return teamDaysOff;
    }
    public void setTeamDaysOff(Short teamDaysOff) {
        this.teamDaysOff = teamDaysOff;
    }

    public Short getTeamPoints() {
        return teamPoints;
    }
    public void setTeamPoints(Short teamPoints) {
        this.teamPoints = teamPoints;
    }

    public Short getTeamAssists() {
        return teamAssists;
    }
    public void setTeamAssists(Short teamAssists) {
        this.teamAssists = teamAssists;
    }

    public Short getTeamTurnovers() {
        return teamTurnovers;
    }
    public void setTeamTurnovers(Short teamTurnovers) {
        this.teamTurnovers = teamTurnovers;
    }

    public Short getTeamSteals() {
        return teamSteals;
    }
    public void setTeamSteals(Short teamSteals) {
        this.teamSteals = teamSteals;
    }

    public Short getTeamBlocks() {
        return teamBlocks;
    }
    public void setTeamBlocks(Short teamBlocks) {
        this.teamBlocks = teamBlocks;
    }

    public Short getTeamPersonalFouls() {
        return teamPersonalFouls;
    }
    public void setTeamPersonalFouls(Short teamPersonalFouls) {
        this.teamPersonalFouls = teamPersonalFouls;
    }

    public Short getTeamFieldGoalAttempts() {
        return teamFieldGoalAttempts;
    }
    public void setTeamFieldGoalAttempts(Short teamFieldGoalAttempts) {
        this.teamFieldGoalAttempts = teamFieldGoalAttempts;
    }

    public Short getTeamFieldGoalMade() {
        return teamFieldGoalMade;
    }
    public void setTeamFieldGoalMade(Short teamFieldGoalMade) {
        this.teamFieldGoalMade = teamFieldGoalMade;
    }

    public Float getTeamFieldGoalPct() {
        return teamFieldGoalPct;
    }
    public void setTeamFieldGoalPct(Float teamFieldGoalPct) {
        this.teamFieldGoalPct = teamFieldGoalPct;
    }

    public Short getTeamTwoPointAttempts() {
        return teamTwoPointAttempts;
    }
    public void setTeamTwoPointAttempts(Short teamTwoPointAttempts) {
        this.teamTwoPointAttempts = teamTwoPointAttempts;
    }

    public Short getTeamTwoPointMade() {
        return teamTwoPointMade;
    }
    public void setTeamTwoPointMade(Short teamTwoPointMade) {
        this.teamTwoPointMade = teamTwoPointMade;
    }

    public Float getTeamTwoPointPct() {
        return teamTwoPointPct;
    }
    public void setTeamTwoPointPct(Float teamTwoPointPct) {
        this.teamTwoPointPct = teamTwoPointPct;
    }

    public Float getTeamTwoPointRate() {
        return teamTwoPointRate;
    }
    public void setTeamTwoPointRate(Float teamTwoPointRate) {
        this.teamTwoPointRate = teamTwoPointRate;
    }

    public Short getTeamThreePointAttempts() {
        return teamThreePointAttempts;
    }
    public void setTeamThreePointAttempts(Short teamThreePointAttempts) {
        this.teamThreePointAttempts = teamThreePointAttempts;
    }

    public Short getTeamThreePointMade() {
        return teamThreePointMade;
    }
    public void setTeamThreePointMade(Short teamThreePointMade) {
        this.teamThreePointMade = teamThreePointMade;
    }

    public Float getTeamThreePointPct() {
        return teamThreePointPct;
    }
    public void setTeamThreePointPct(Float teamThreePointPct) {
        this.teamThreePointPct = teamThreePointPct;
    }

    public Float getTeamThreePointRate() {
        return teamThreePointRate;
    }
    public void setTeamThreePointRate(Float teamThreePointRate) {
        this.teamThreePointRate = teamThreePointRate;
    }

    public Short getTeamFreeThrowAttempts() {
        return teamFreeThrowAttempts;
    }
    public void setTeamFreeThrowAttempts(Short teamFreeThrowAttempts) {
        this.teamFreeThrowAttempts = teamFreeThrowAttempts;
    }

    public Short getTeamFreeThrowMade() {
        return teamFreeThrowMade;
    }
    public void setTeamFreeThrowMade(Short teamFreeThrowMade) {
        this.teamFreeThrowMade = teamFreeThrowMade;
    }

    public Float getTeamFreeThrowPct() {
        return teamFreeThrowPct;
    }
    public void setTeamFreeThrowPct(Float teamFreeThrowPct) {
        this.teamFreeThrowPct = teamFreeThrowPct;
    }

    public Float getTeamFreeThrowRate() {
        return teamFreeThrowRate;
    }
    public void setTeamFreeThrowRate(Float teamFreeThrowRate) {
        this.teamFreeThrowRate = teamFreeThrowRate;
    }

    public Short getTeamReboundsOffense() {
        return teamReboundsOffense;
    }
    public void setTeamReboundsOffense(Short teamReboundsOffense) {
        this.teamReboundsOffense = teamReboundsOffense;
    }

    public Short getTeamReboundsDefense() {
        return teamReboundsDefense;
    }
    public void setTeamReboundsDefense(Short teamReboundsDefense) {
        this.teamReboundsDefense = teamReboundsDefense;
    }

    public Short getTeamReboundsTotal() {
        return teamReboundsTotal;
    }
    public void setTeamReboundsTotal(Short teamReboundsTotal) {
        this.teamReboundsTotal = teamReboundsTotal;
    }

    public Short getTeamPointsQ1() {
        return teamPointsQ1;
    }
    public void setTeamPointsQ1(Short teamPointsQ1) {
        this.teamPointsQ1 = teamPointsQ1;
    }

    public Short getTeamPointsQ2() {
        return teamPointsQ2;
    }
    public void setTeamPointsQ2(Short teamPointsQ2) {
        this.teamPointsQ2 = teamPointsQ2;
    }

    public Short getTeamPointsQ3() {
        return teamPointsQ3;
    }
    public void setTeamPointsQ3(Short teamPointsQ3) {
        this.teamPointsQ3 = teamPointsQ3;
    }

    public Short getTeamPointsQ4() {
        return teamPointsQ4;
    }
    public void setTeamPointsQ4(Short teamPointsQ4) {
        this.teamPointsQ4 = teamPointsQ4;
    }

    public Short getTeamPointsQ5() {
        return teamPointsQ5;
    }
    public void setTeamPointsQ5(Short teamPointsQ5) {
        this.teamPointsQ5 = teamPointsQ5;
    }

    public Short getTeamPointsQ6() {
        return teamPointsQ6;
    }
    public void setTeamPointsQ6(Short teamPointsQ6) {
        this.teamPointsQ6 = teamPointsQ6;
    }

    public Short getTeamPointsQ7() {
        return teamPointsQ7;
    }
    public void setTeamPointsQ7(Short teamPointsQ7) {
        this.teamPointsQ7 = teamPointsQ7;
    }

    public Short getTeamPointsQ8() {
        return teamPointsQ8;
    }
    public void setTeamPointsQ8(Short teamPointsQ8) {
        this.teamPointsQ8 = teamPointsQ8;
    }

    public Float getTeamTrueShootingPct() {
        return teamTrueShootingPct;
    }
    public void setTeamTrueShootingPct(Float teamTrueShootingPct) {
        this.teamTrueShootingPct = teamTrueShootingPct;
    }

    public Float getTeamEffectiveFieldGoalPct() {
        return teamEffectiveFieldGoalPct;
    }
    public void setTeamEffectiveFieldGoalPct(Float teamEffectiveFieldGoalPct) {
        this.teamEffectiveFieldGoalPct = teamEffectiveFieldGoalPct;
    }

    public Float getTeamOffensiveReboundPct() {
        return teamOffensiveReboundPct;
    }
    public void setTeamOffensiveReboundPct(Float teamOffensiveReboundPct) {
        this.teamOffensiveReboundPct = teamOffensiveReboundPct;
    }

    public Float getTeamDefensiveReboundPct() {
        return teamDefensiveReboundPct;
    }
    public void setTeamDefensiveReboundPct(Float teamDefensiveReboundPct) {
        this.teamDefensiveReboundPct = teamDefensiveReboundPct;
    }

    public Float getTeamTotalReboundPct() {
        return teamTotalReboundPct;
    }
    public void setTeamTotalReboundPct(Float teamTotalReboundPct) {
        this.teamTotalReboundPct = teamTotalReboundPct;
    }

    public Float getTeamAssistedFieldGoalPct() {
        return teamAssistedFieldGoalPct;
    }
    public void setTeamAssistedFieldGoalPct(Float teamAssistedFieldGoalPct) {
        this.teamAssistedFieldGoalPct = teamAssistedFieldGoalPct;
    }

    public Float getTeamTurnoverPct() {
        return teamTurnoverPct;
    }
    public void setTeamTurnoverPct(Float teamTurnoverPct) {
        this.teamTurnoverPct = teamTurnoverPct;
    }

    public Float getTeamStealPct() {
        return teamStealPct;
    }
    public void setTeamStealPct(Float teamStealPct) {
        this.teamStealPct = teamStealPct;
    }

    public Float getTeamBlockPct() {
        return teamBlockPct;
    }
    public void setTeamBlockPct(Float teamBlockPct) {
        this.teamBlockPct = teamBlockPct;
    }

    public Float getTeamBlockRate() {
        return teamBlockRate;
    }
    public void setTeamBlockRate(Float teamBlockRate) {
        this.teamBlockRate = teamBlockRate;
    }

    public Float getTeamPointsPerShot() {
        return teamPointsPerShot;
    }
    public void setTeamPointsPerShot(Float teamPointsPerShot) {
        this.teamPointsPerShot = teamPointsPerShot;
    }

    public Float getTeamFloorImpactCounter() {
        return teamFloorImpactCounter;
    }
    public void setTeamFloorImpactCounter(Float teamFloorImpactCounter) {
        this.teamFloorImpactCounter = teamFloorImpactCounter;
    }

    public Float getTeamFloorImpactCounterPer40() {
        return teamFloorImpactCounterPer40;
    }
    public void setTeamFloorImpactCounterPer40(Float teamFloorImpactCounterPer40) {
        this.teamFloorImpactCounterPer40 = teamFloorImpactCounterPer40;
    }

    public Float getTeamOffensiveRating() {
        return teamOffensiveRating;
    }
    public void setTeamOffensiveRating(Float teamOffensiveRating) {
        this.teamOffensiveRating = teamOffensiveRating;
    }

    public Float getTeamDefensiveRating() {
        return teamDefensiveRating;
    }
    public void setTeamDefensiveRating(Float teamDefensiveRating) {
        this.teamDefensiveRating = teamDefensiveRating;
    }

    public Float getTeamEfficiencyDifferential() {
        return teamEfficiencyDifferential;
    }
    public void setTeamEfficiencyDifferential(Float teamEfficiencyDifferential) {
        this.teamEfficiencyDifferential = teamEfficiencyDifferential;
    }

    public Float getTeamPlayPct() {
        return teamPlayPct;
    }
    public void setTeamPlayPct(Float teamPlayPct) {
        this.teamPlayPct = teamPlayPct;
    }

    public Float getTeamAssistRate() {
        return teamAssistRate;
    }
    public void setTeamAssistRate(Float teamAssistRate) {
        this.teamAssistRate = teamAssistRate;
    }

    public Float getTeamAssistToTurnoverRatio() {
        return teamAssistToTurnoverRatio;
    }
    public void setTeamAssistToTurnoverRatio(Float teamAssistToTurnoverRatio) {
        this.teamAssistToTurnoverRatio = teamAssistToTurnoverRatio;
    }

    public Float getTeamStealToTurnoverRatio() {
        return teamStealToTurnoverRatio;
    }
    public void setTeamStealToTurnoverRatio(Float teamStealToTurnoverRatio) {
        this.teamStealToTurnoverRatio = teamStealToTurnoverRatio;
    }


    public String getOpptAbbr() {
        return opptAbbr;
    }
    public void setOpptAbbr(String opptAbbr) {
        this.opptAbbr = opptAbbr;
    }

    public String getOpptConference() {
        return opptConference;
    }
    public void setOpptConference(String opptConference) {
        this.opptConference = opptConference;
    }

    public String getOpptDivision() {
        return opptDivision;
    }
    public void setOpptDivision(String opptDivision) {
        this.opptDivision = opptDivision;
    }

    public String getOpptLocation() {
        return opptLocation;
    }
    public void setOpptLocation(String opptLocation) {
        this.opptLocation = opptLocation;
    }

    public String getOpptResult() {
        return opptResult;
    }
    public void setOpptResult(String opptResult) {
        this.opptResult = opptResult;
    }

    public Short getOpptMinutes() {
        return opptMinutes;
    }
    public void setOpptMinutes(Short opptMinutes) {
        this.opptMinutes = opptMinutes;
    }

    public Short getOpptDaysOff() {
        return opptDaysOff;
    }
    public void setOpptDaysOff(Short opptDaysOff) {
        this.opptDaysOff = opptDaysOff;
    }

    public Short getOpptPoints() {
        return opptPoints;
    }
    public void setOpptPoints(Short opptPoints) {
        this.opptPoints = opptPoints;
    }

    public Short getOpptAssists() {
        return opptAssists;
    }
    public void setOpptAssists(Short opptAssists) {
        this.opptAssists = opptAssists;
    }

    public Short getOpptTurnovers() {
        return opptTurnovers;
    }
    public void setOpptTurnovers(Short opptTurnovers) {
        this.opptTurnovers = opptTurnovers;
    }

    public Short getOpptSteals() {
        return opptSteals;
    }
    public void setOpptSteals(Short opptSteals) {
        this.opptSteals = opptSteals;
    }

    public Short getOpptBlocks() {
        return opptBlocks;
    }
    public void setOpptBlocks(Short opptBlocks) {
        this.opptBlocks = opptBlocks;
    }

    public Short getOpptPersonalFouls() {
        return opptPersonalFouls;
    }
    public void setOpptPersonalFouls(Short opptPersonalFouls) {
        this.opptPersonalFouls = opptPersonalFouls;
    }

    public Short getOpptFieldGoalAttempts() {
        return opptFieldGoalAttempts;
    }
    public void setOpptFieldGoalAttempts(Short opptFieldGoalAttempts) {
        this.opptFieldGoalAttempts = opptFieldGoalAttempts;
    }

    public Short getOpptFieldGoalMade() {
        return opptFieldGoalMade;
    }
    public void setOpptFieldGoalMade(Short opptFieldGoalMade) {
        this.opptFieldGoalMade = opptFieldGoalMade;
    }

    public Float getOpptFieldGoalPct() {
        return opptFieldGoalPct;
    }
    public void setOpptFieldGoalPct(Float opptFieldGoalPct) {
        this.opptFieldGoalPct = opptFieldGoalPct;
    }

    public Short getOpptTwoPointAttempts() {
        return opptTwoPointAttempts;
    }
    public void setOpptTwoPointAttempts(Short opptTwoPointAttempts) {
        this.opptTwoPointAttempts = opptTwoPointAttempts;
    }

    public Short getOpptTwoPointMade() {
        return opptTwoPointMade;
    }
    public void setOpptTwoPointMade(Short opptTwoPointMade) {
        this.opptTwoPointMade = opptTwoPointMade;
    }

    public Float getOpptTwoPointPct() {
        return opptTwoPointPct;
    }
    public void setOpptTwoPointPct(Float opptTwoPointPct) {
        this.opptTwoPointPct = opptTwoPointPct;
    }

    public Float getOpptTwoPointRate() {
        return opptTwoPointRate;
    }
    public void setOpptTwoPointRate(Float opptTwoPointRate) {
        this.opptTwoPointRate = opptTwoPointRate;
    }

    public Short getOpptThreePointAttempts() {
        return opptThreePointAttempts;
    }
    public void setOpptThreePointAttempts(Short opptThreePointAttempts) {
        this.opptThreePointAttempts = opptThreePointAttempts;
    }

    public Short getOpptThreePointMade() {
        return opptThreePointMade;
    }
    public void setOpptThreePointMade(Short opptThreePointMade) {
        this.opptThreePointMade = opptThreePointMade;
    }

    public Float getOpptThreePointPct() {
        return opptThreePointPct;
    }
    public void setOpptThreePointPct(Float opptThreePointPct) {
        this.opptThreePointPct = opptThreePointPct;
    }

    public Float getOpptThreePointRate() {
        return opptThreePointRate;
    }
    public void setOpptThreePointRate(Float opptThreePointRate) {
        this.opptThreePointRate = opptThreePointRate;
    }

    public Short getOpptFreeThrowAttempts() {
        return opptFreeThrowAttempts;
    }
    public void setOpptFreeThrowAttempts(Short opptFreeThrowAttempts) {
        this.opptFreeThrowAttempts = opptFreeThrowAttempts;
    }

    public Short getOpptFreeThrowMade() {
        return opptFreeThrowMade;
    }
    public void setOpptFreeThrowMade(Short opptFreeThrowMade) {
        this.opptFreeThrowMade = opptFreeThrowMade;
    }

    public Float getOpptFreeThrowPct() {
        return opptFreeThrowPct;
    }
    public void setOpptFreeThrowPct(Float opptFreeThrowPct) {
        this.opptFreeThrowPct = opptFreeThrowPct;
    }

    public Float getOpptFreeThrowRate() {
        return opptFreeThrowRate;
    }
    public void setOpptFreeThrowRate(Float opptFreeThrowRate) {
        this.opptFreeThrowRate = opptFreeThrowRate;
    }

    public Short getOpptReboundsOffense() {
        return opptReboundsOffense;
    }
    public void setOpptReboundsOffense(Short opptReboundsOffense) {
        this.opptReboundsOffense = opptReboundsOffense;
    }

    public Short getOpptReboundsDefense() {
        return opptReboundsDefense;
    }
    public void setOpptReboundsDefense(Short opptReboundsDefense) {
        this.opptReboundsDefense = opptReboundsDefense;
    }

    public Short getOpptReboundsTotal() {
        return opptReboundsTotal;
    }
    public void setOpptReboundsTotal(Short opptReboundsTotal) {
        this.opptReboundsTotal = opptReboundsTotal;
    }

    public Short getOpptPointsQ1() {
        return opptPointsQ1;
    }
    public void setOpptPointsQ1(Short opptPointsQ1) {
        this.opptPointsQ1 = opptPointsQ1;
    }

    public Short getOpptPointsQ2() {
        return opptPointsQ2;
    }
    public void setOpptPointsQ2(Short opptPointsQ2) {
        this.opptPointsQ2 = opptPointsQ2;
    }

    public Short getOpptPointsQ3() {
        return opptPointsQ3;
    }
    public void setOpptPointsQ3(Short opptPointsQ3) {
        this.opptPointsQ3 = opptPointsQ3;
    }

    public Short getOpptPointsQ4() {
        return opptPointsQ4;
    }
    public void setOpptPointsQ4(Short opptPointsQ4) {
        this.opptPointsQ4 = opptPointsQ4;
    }

    public Short getOpptPointsQ5() {
        return opptPointsQ5;
    }
    public void setOpptPointsQ5(Short opptPointsQ5) {
        this.opptPointsQ5 = opptPointsQ5;
    }

    public Short getOpptPointsQ6() {
        return opptPointsQ6;
    }
    public void setOpptPointsQ6(Short opptPointsQ6) {
        this.opptPointsQ6 = opptPointsQ6;
    }

    public Short getOpptPointsQ7() {
        return opptPointsQ7;
    }
    public void setOpptPointsQ7(Short opptPointsQ7) {
        this.opptPointsQ7 = opptPointsQ7;
    }

    public Short getOpptPointsQ8() {
        return opptPointsQ8;
    }
    public void setOpptPointsQ8(Short opptPointsQ8) {
        this.opptPointsQ8 = opptPointsQ8;
    }



    public Float getOpptTrueShootingPct() {
        return opptTrueShootingPct;
    }
    public void setOpptTrueShootingPct(Float opptTrueShootingPct) {
        this.opptTrueShootingPct = opptTrueShootingPct;
    }

    public Float getOpptEffectiveFieldGoalPct() {
        return opptEffectiveFieldGoalPct;
    }
    public void setOpptEffectiveFieldGoalPct(Float opptEffectiveFieldGoalPct) {
        this.opptEffectiveFieldGoalPct = opptEffectiveFieldGoalPct;
    }

    public Float getOpptOffensiveReboundPct() {
        return opptOffensiveReboundPct;
    }
    public void setOpptOffensiveReboundPct(Float opptOffensiveReboundPct) {
        this.opptOffensiveReboundPct = opptOffensiveReboundPct;
    }

    public Float getOpptDefensiveReboundPct() {
        return opptDefensiveReboundPct;
    }
    public void setOpptDefensiveReboundPct(Float opptDefensiveReboundPct) {
        this.opptDefensiveReboundPct = opptDefensiveReboundPct;
    }

    public Float getOpptTotalReboundPct() {
        return opptTotalReboundPct;
    }
    public void setOpptTotalReboundPct(Float opptTotalReboundPct) {
        this.opptTotalReboundPct = opptTotalReboundPct;
    }

    public Float getOpptAssistedFieldGoalPct() {
        return opptAssistedFieldGoalPct;
    }
    public void setOpptAssistedFieldGoalPct(Float opptAssistedFieldGoalPct) {
        this.opptAssistedFieldGoalPct = opptAssistedFieldGoalPct;
    }

    public Float getOpptTurnoverPct() {
        return opptTurnoverPct;
    }
    public void setOpptTurnoverPct(Float opptTurnoverPct) {
        this.opptTurnoverPct = opptTurnoverPct;
    }

    public Float getOpptStealPct() {
        return opptStealPct;
    }
    public void setOpptStealPct(Float opptStealPct) {
        this.opptStealPct = opptStealPct;
    }

    public Float getOpptBlockPct() {
        return opptBlockPct;
    }
    public void setOpptBlockPct(Float opptBlockPct) {
        this.opptBlockPct = opptBlockPct;
    }

    public Float getOpptBlockRate() {
        return opptBlockRate;
    }
    public void setOpptBlockRate(Float opptBlockRate) {
        this.opptBlockRate = opptBlockRate;
    }

    public Float getOpptPointsPerShot() {
        return opptPointsPerShot;
    }
    public void setOpptPointsPerShot(Float opptPointsPerShot) {
        this.opptPointsPerShot = opptPointsPerShot;
    }

    public Float getOpptFloorImpactCounter() {
        return opptFloorImpactCounter;
    }
    public void setOpptFloorImpactCounter(Float opptFloorImpactCounter) {
        this.opptFloorImpactCounter = opptFloorImpactCounter;
    }

    public Float getOpptFloorImpactCounterPer40() {
        return opptFloorImpactCounterPer40;
    }
    public void setOpptFloorImpactCounterPer40(Float opptFloorImpactCounterPer40) {
        this.opptFloorImpactCounterPer40 = opptFloorImpactCounterPer40;
    }

    public Float getOpptOffensiveRating() {
        return opptOffensiveRating;
    }
    public void setOpptOffensiveRating(Float opptOffensiveRating) {
        this.opptOffensiveRating = opptOffensiveRating;
    }

    public Float getOpptDefensiveRating() {
        return opptDefensiveRating;
    }
    public void setOpptDefensiveRating(Float opptDefensiveRating) {
        this.opptDefensiveRating = opptDefensiveRating;
    }

    public Float getOpptEfficiencyDifferential() {
        return opptEfficiencyDifferential;
    }
    public void setOpptEfficiencyDifferential(Float opptEfficiencyDifferential) {
        this.opptEfficiencyDifferential = opptEfficiencyDifferential;
    }

    public Float getOpptPlayPct() {
        return opptPlayPct;
    }
    public void setOpptPlayPct(Float opptPlayPct) {
        this.opptPlayPct = opptPlayPct;
    }

    public Float getOpptAssistRate() {
        return opptAssistRate;
    }
    public void setOpptAssistRate(Float opptAssistRate) {
        this.opptAssistRate = opptAssistRate;
    }

    public Float getOpptAssistToTurnoverRatio() {
        return opptAssistToTurnoverRatio;
    }
    public void setOpptAssistToTurnoverRatio(Float opptAssistToTurnoverRatio) {
        this.opptAssistToTurnoverRatio = opptAssistToTurnoverRatio;
    }

    public Float getOpptStealToTurnoverRatio() {
        return opptStealToTurnoverRatio;
    }
    public void setOpptStealToTurnoverRatio(Float opptStealToTurnoverRatio) {
        this.opptStealToTurnoverRatio = opptStealToTurnoverRatio;
    }


    public Float getPossessions() {
        return possessions;
    }
    public void setPossessions(Float possessions) {
        this.possessions = possessions;
    }

    public Float getPace() {
        return pace;
    }
    public void setPace(Float pace) {
        this.pace = pace;
    }

    private Short calculateTeamTwoPointMade() {
        return BoxScoreCalculations.calculateTwoPointMade(
            this.getTeamFieldGoalMade(), this.getTeamThreePointMade()
        );
    }

    private Short calculateTeamTwoPointAttempt() {
        return BoxScoreCalculations.calculateTwoPointAttempt(
            this.getTeamFieldGoalAttempts(), this.getTeamThreePointAttempts()
        );
    }

    private BigDecimal calculateTeamTwoPointPct() {
        Short attempt = BoxScoreCalculations.calculateTwoPointAttempt(
            this.getTeamFieldGoalAttempts(), this.getTeamThreePointAttempts()
        );
        Short made = BoxScoreCalculations.calculateTwoPointMade(
            this.getTeamFieldGoalMade(), this.getTeamThreePointMade()
        );
        return CommonCalculations.calculatePercent(made, attempt);
    }

    private BigDecimal calculateTeamTwoPointRate() {
        return CommonCalculations.calculatePercent (
            this.getTeamTwoPointAttempts(), this.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamThreePointPct() {
        return CommonCalculations.calculatePercent(
            this.getTeamThreePointMade(), this.getTeamThreePointAttempts()
        );
    }

    private BigDecimal calculateTeamThreePointRate() {
        return CommonCalculations.calculatePercent(
            this.getTeamThreePointAttempts(), this.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFieldGoalPct() {
        return CommonCalculations.calculatePercent(
            this.getTeamFieldGoalMade(), this.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFreeThrowPct() {
        return CommonCalculations.calculatePercent(
            this.getTeamFreeThrowMade(), this.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamFreeThrowRate() {
        return CommonCalculations.calculatePercent(
            this.getTeamFreeThrowAttempts(), this.getTeamFieldGoalAttempts()
        );
    }

    private Short calculateTeamReboundTotal() {
        return BoxScoreCalculations.calculateTotalRebound(
            this.getTeamReboundsOffense(), this.getTeamReboundsDefense()
        );
    }

    private BigDecimal calculateTeamTrueShootingPct() {
        return BoxScoreCalculations.calculateTrueShootingPct(
            this.getTeamPoints(), this.getTeamFieldGoalAttempts(), this.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamEffectiveFieldGoalPct() {
        return BoxScoreCalculations.calculateEffectiveFieldGoalPct(
            this.getTeamFieldGoalMade(), this.getTeamThreePointMade(), this.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamOffensiveReboundPct() {
        return BoxScoreCalculations.calculateOffensiveReboundPct(
            this.getTeamReboundsOffense(), this.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamDefensiveReboundPct() {
        return BoxScoreCalculations.calculateDefensiveReboundPct(
            this.getTeamReboundsDefense(), this.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateTeamTotalReboundPct() {
        return BoxScoreCalculations.calculateTotalReboundPct(
            this.getTeamReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptReboundsOffense(), this.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateTeamAssistedFieldGoalPct() {
        return BoxScoreCalculations.calculateAssistedFieldGoalPct(
            this.getTeamAssists(), this.getTeamFieldGoalMade()
        );
    }

    private BigDecimal calculateTeamTurnoverPct() {
        return BoxScoreCalculations.calculateTurnoverPct(
            this.getTeamTurnovers(), this.getTeamFieldGoalAttempts(), this.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamStealPct() {
        return BoxScoreCalculations.calculateStealPct(
            this.getTeamSteals(), this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockPct() {
        return BoxScoreCalculations.calculateBlockPct(
            this.getTeamBlocks(), this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateTeamBlockRate() {
        return BoxScoreCalculations.calculateBlockRate(
            this.getTeamBlocks(), this.getTeamFieldGoalAttempts(), this.getTeamThreePointAttempts()
        );
    }

    private BigDecimal calculateTeamPointsPerShot() {
        return BoxScoreCalculations.calculatePointsPerShot(
            this.getTeamPoints(), this.getTeamFieldGoalAttempts()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounter() {
        return BoxScoreCalculations.calculateFloorImpactCounter(
            this.getTeamPoints(), this.getTeamReboundsOffense(), this.getTeamReboundsDefense(), this.getTeamAssists(), this.getTeamSteals(), this.getTeamBlocks(), this.getTeamFieldGoalAttempts(),
            this.getTeamFreeThrowAttempts(), this.getTeamTurnovers(), this.getTeamPersonalFouls()
        );
    }

    private BigDecimal calculateTeamFloorImpactCounterPer40() {
        return BoxScoreCalculations.calculateFloorImpactCounterPer40(
            this.getTeamPoints(), this.getTeamReboundsOffense(), this.getTeamReboundsDefense(), this.getTeamAssists(), this.getTeamSteals(), this.getTeamBlocks(), this.getTeamFieldGoalAttempts(),
            this.getTeamFreeThrowAttempts(), this.getTeamTurnovers(), this.getTeamPersonalFouls(), this.getTeamMinutes()
        );
    }

    private BigDecimal calculateTeamOffensiveRating() {
        return BoxScoreCalculations.calculateOffensiveRating(
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamPoints()
        );
    }

    private BigDecimal calculateTeamDefensiveRating() {
        return BoxScoreCalculations.calculateDefensiveRating(
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getOpptPoints()
        );
    }

    private BigDecimal calculateTeamEfficiencyDifferential() {
        return BoxScoreCalculations.calculateEfficiencyDifferential(
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamPoints(), this.getOpptPoints()
        );
    }

    private BigDecimal calculateTeamPlayPct() {
        return BoxScoreCalculations.calculatePlayPct(
            this.getTeamFieldGoalAttempts(), this.getTeamFieldGoalMade(), this.getTeamReboundsOffense(), this.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssistRate() {
        return BoxScoreCalculations.calculateAssistRate(
            this.getTeamAssists(), this.getTeamFieldGoalAttempts(), this.getTeamFreeThrowAttempts(), this.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamAssistToTurnoverRatio() {
        return BoxScoreCalculations.calculateAssistToTurnoverRatio(
            this.getTeamAssists(), this.getTeamTurnovers()
        );
    }

    private BigDecimal calculateTeamStealToTurnoverRatio() {
        return BoxScoreCalculations.calculateStealToTurnoverRatio(
            this.getTeamSteals(), this.getTeamTurnovers()
        );
    }

    private Short calculateOpptTwoPointMade() {
        return BoxScoreCalculations.calculateTwoPointMade(
            this.getOpptFieldGoalMade(), this.getOpptThreePointMade()
        );
    }

    private Short calculateOpptTwoPointAttempt() {
        return BoxScoreCalculations.calculateTwoPointAttempt(
            this.getOpptFieldGoalAttempts(), this.getOpptThreePointAttempts()
        );
    }

    private BigDecimal calculateOpptTwoPointPct() {
        Short attempt = BoxScoreCalculations.calculateTwoPointAttempt(
            this.getOpptFieldGoalAttempts(), this.getOpptThreePointAttempts()
        );
        Short made = BoxScoreCalculations.calculateTwoPointMade(
            this.getOpptFieldGoalMade(), this.getOpptThreePointMade()
        );
        return CommonCalculations.calculatePercent(
             made, attempt
        );
    }

    private BigDecimal calculateOpptTwoPointRate() {
        return CommonCalculations.calculatePercent(
            this.getOpptTwoPointAttempts(), this.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptThreePointPct() {
        return CommonCalculations.calculatePercent(
            this.getOpptThreePointMade(), this.getOpptThreePointAttempts()
        );
    }
    private BigDecimal calculateOpptThreePointRate() {
        return CommonCalculations.calculatePercent(
            this.getOpptThreePointAttempts(), this.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFieldGoalPct() {
        return CommonCalculations.calculatePercent(
            this.getOpptFieldGoalMade(), this.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFreeThrowPct() {
        return CommonCalculations.calculatePercent(
            this.getOpptFreeThrowMade(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptFreeThrowRate() {
        return CommonCalculations.calculatePercent(
            this.getOpptFreeThrowAttempts(), this.getOpptFieldGoalAttempts()
        );
    }

    private Short calculateOpptReboundTotal() {
        return BoxScoreCalculations.calculateTotalRebound(
            this.getOpptReboundsOffense(), this.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateOpptTrueShootingPct() {
        return BoxScoreCalculations.calculateTrueShootingPct(
            this.getOpptPoints(), this.getOpptFieldGoalAttempts(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptEffectiveFieldGoalPct() {
        return BoxScoreCalculations.calculateEffectiveFieldGoalPct(
            this.getOpptFieldGoalMade(), this.getOpptThreePointMade(), this.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptOffensiveReboundPct() {
        return BoxScoreCalculations.calculateOffensiveReboundPct(
            this.getOpptReboundsOffense(), this.getOpptReboundsDefense()
        );
    }

    private BigDecimal calculateOpptDefensiveReboundPct() {
        return BoxScoreCalculations.calculateDefensiveReboundPct(
            this.getOpptReboundsDefense(), this.getOpptReboundsOffense()
        );
    }

    private BigDecimal calculateOpptTotalReboundPct() {
        return BoxScoreCalculations.calculateTotalReboundPct(
            this.getOpptReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamReboundsOffense(), this.getTeamReboundsDefense()
        );
    }

    private BigDecimal calculateOpptAssistedFieldGoalPct() {
        return BoxScoreCalculations.calculateAssistedFieldGoalPct(
            this.getOpptAssists(), this.getOpptFieldGoalMade()
        );
    }

    private BigDecimal calculateOpptTurnoverPct() {
        return BoxScoreCalculations.calculateTurnoverPct(
            this.getOpptTurnovers(), this.getOpptFieldGoalAttempts(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptStealPct() {
        return BoxScoreCalculations.calculateStealPct(
            this.getOpptSteals(), this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockPct() {
        return BoxScoreCalculations.calculateBlockPct(
            this.getOpptBlocks(), this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts()
        );
    }

    private BigDecimal calculateOpptBlockRate() {
        return BoxScoreCalculations.calculateBlockRate(
            this.getOpptBlocks(), this.getOpptFieldGoalAttempts(), this.getOpptThreePointAttempts()
        );
    }

    private BigDecimal calculateOpptPointsPerShot() {
        return BoxScoreCalculations.calculatePointsPerShot(
            this.getOpptPoints(), this.getOpptFieldGoalAttempts()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounter() {
        return BoxScoreCalculations.calculateFloorImpactCounter(
            this.getOpptPoints(), this.getOpptReboundsOffense(), this.getOpptReboundsDefense(), this.getOpptAssists(), this.getOpptSteals(), this.getOpptBlocks(), this.getOpptFieldGoalAttempts(),
            this.getOpptFreeThrowAttempts(), this.getOpptTurnovers(), this.getOpptPersonalFouls()
        );
    }

    private BigDecimal calculateOpptFloorImpactCounterPer40() {
        return BoxScoreCalculations.calculateFloorImpactCounterPer40(
            this.getOpptPoints(), this.getOpptReboundsOffense(), this.getOpptReboundsDefense(), this.getOpptAssists(), this.getOpptSteals(), this.getOpptBlocks(), this.getOpptFieldGoalAttempts(),
            this.getOpptFreeThrowAttempts(), this.getOpptTurnovers(), this.getOpptPersonalFouls(), this.getOpptMinutes()
        );
    }

    private BigDecimal calculateOpptOffensiveRating() {
        return BoxScoreCalculations.calculateOffensiveRating(
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptPoints()
        );
    }

    private BigDecimal calculateOpptDefensiveRating() {
        return BoxScoreCalculations.calculateDefensiveRating(
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptEfficiencyDifferential() {
        return BoxScoreCalculations.calculateEfficiencyDifferential(
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptPoints(), this.getTeamPoints()
        );
    }

    private BigDecimal calculateOpptPlayPct() {
        return BoxScoreCalculations.calculatePlayPct(
            this.getOpptFieldGoalAttempts(), this.getOpptFieldGoalMade(), this.getOpptReboundsOffense(), this.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssistRate() {
        return BoxScoreCalculations.calculateAssistRate(
            this.getOpptAssists(), this.getOpptFieldGoalAttempts(), this.getOpptFreeThrowAttempts(), this.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptAssistToTurnoverRatio() {
        return BoxScoreCalculations.calculateAssistToTurnoverRatio(
            this.getOpptAssists(), this.getOpptTurnovers()
        );
    }

    private BigDecimal calculateOpptStealToTurnoverRatio() {
        return BoxScoreCalculations.calculateStealToTurnoverRatio(
            this.getOpptSteals(), this.getOpptTurnovers()
        );
    }

    private BigDecimal calculatePossessions() {
        return BoxScoreCalculations.calculatePossessions(
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts()
        );
    }

    private BigDecimal calculatePace() {
        return BoxScoreCalculations.calculatePace(
            this.getTeamFieldGoalAttempts(), this.getTeamReboundsOffense(), this.getOpptReboundsDefense(), this.getTeamFieldGoalMade(), this.getTeamTurnovers(), this.getTeamFreeThrowAttempts(),
            this.getOpptFieldGoalAttempts(), this.getOpptReboundsOffense(), this.getTeamReboundsDefense(), this.getOpptFieldGoalMade(), this.getOpptTurnovers(), this.getOpptFreeThrowAttempts(),
            this.getTeamMinutes()
        );
    }

    public static void process(BoxScore boxScore) {
        boxScore.setGameDate(DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTime(boxScore.getGameDateTime())));
        boxScore.setGameTime(DateTimeConverter.getStringTime(DateTimeConverter.getLocalDateTime(boxScore.getGameDateTime())));

        boxScore.setTeamTwoPointAttempts(boxScore.calculateTeamTwoPointAttempt());
        boxScore.setTeamTwoPointMade(boxScore.calculateTeamTwoPointMade());
        boxScore.setTeamTwoPointPct(boxScore.calculateTeamTwoPointPct().floatValue());
        boxScore.setTeamTwoPointRate(boxScore.calculateTeamTwoPointRate().floatValue());
        boxScore.setTeamThreePointPct(boxScore.calculateTeamThreePointPct().floatValue());
        boxScore.setTeamThreePointRate(boxScore.calculateTeamThreePointRate().floatValue());
        boxScore.setTeamFieldGoalPct(boxScore.calculateTeamFieldGoalPct().floatValue());
        boxScore.setTeamFreeThrowPct(boxScore.calculateTeamFreeThrowPct().floatValue());
        boxScore.setTeamFreeThrowRate(boxScore.calculateTeamFreeThrowRate().floatValue());
        boxScore.setTeamReboundsTotal(boxScore.calculateTeamReboundTotal());

        boxScore.setTeamTrueShootingPct(boxScore.calculateTeamTrueShootingPct().floatValue());
        boxScore.setTeamEffectiveFieldGoalPct(boxScore.calculateTeamEffectiveFieldGoalPct().floatValue());
        boxScore.setTeamOffensiveReboundPct(boxScore.calculateTeamOffensiveReboundPct().floatValue());
        boxScore.setTeamDefensiveReboundPct(boxScore.calculateTeamDefensiveReboundPct().floatValue());
        boxScore.setTeamTotalReboundPct(boxScore.calculateTeamTotalReboundPct().floatValue());
        boxScore.setTeamAssistedFieldGoalPct(boxScore.calculateTeamAssistedFieldGoalPct().floatValue());
        boxScore.setTeamTurnoverPct(boxScore.calculateTeamTurnoverPct().floatValue());
        boxScore.setTeamStealPct(boxScore.calculateTeamStealPct().floatValue());
        boxScore.setTeamBlockPct(boxScore.calculateTeamBlockPct().floatValue());
        boxScore.setTeamBlockRate(boxScore.calculateTeamBlockRate().floatValue());
        boxScore.setTeamPointsPerShot(boxScore.calculateTeamPointsPerShot().floatValue());
        boxScore.setTeamFloorImpactCounter(boxScore.calculateTeamFloorImpactCounter().floatValue());
        boxScore.setTeamFloorImpactCounterPer40(boxScore.calculateTeamFloorImpactCounterPer40().floatValue());
        boxScore.setTeamOffensiveRating(boxScore.calculateTeamOffensiveRating().floatValue());
        boxScore.setTeamDefensiveRating(boxScore.calculateTeamDefensiveRating().floatValue());
        boxScore.setTeamEfficiencyDifferential(boxScore.calculateTeamEfficiencyDifferential().floatValue());
        boxScore.setTeamPlayPct(boxScore.calculateTeamPlayPct().floatValue());
        boxScore.setTeamAssistRate(boxScore.calculateTeamAssistRate().floatValue());
        boxScore.setTeamAssistToTurnoverRatio(boxScore.calculateTeamAssistToTurnoverRatio().floatValue());
        boxScore.setTeamStealToTurnoverRatio(boxScore.calculateTeamStealToTurnoverRatio().floatValue());

        boxScore.setOpptTwoPointAttempts(boxScore.calculateOpptTwoPointAttempt());
        boxScore.setOpptTwoPointMade(boxScore.calculateOpptTwoPointMade());
        boxScore.setOpptTwoPointPct(boxScore.calculateOpptTwoPointPct().floatValue());
        boxScore.setOpptTwoPointRate(boxScore.calculateOpptTwoPointRate().floatValue());
        boxScore.setOpptThreePointPct(boxScore.calculateOpptThreePointPct().floatValue());
        boxScore.setOpptThreePointRate(boxScore.calculateOpptThreePointRate().floatValue());
        boxScore.setOpptFieldGoalPct(boxScore.calculateOpptFieldGoalPct().floatValue());
        boxScore.setOpptFreeThrowPct(boxScore.calculateOpptFreeThrowPct().floatValue());
        boxScore.setOpptFreeThrowRate(boxScore.calculateOpptFreeThrowRate().floatValue());
        boxScore.setOpptReboundsTotal(boxScore.calculateOpptReboundTotal());

        boxScore.setOpptTrueShootingPct(boxScore.calculateOpptTrueShootingPct().floatValue());
        boxScore.setOpptEffectiveFieldGoalPct(boxScore.calculateOpptEffectiveFieldGoalPct().floatValue());
        boxScore.setOpptOffensiveReboundPct(boxScore.calculateOpptOffensiveReboundPct().floatValue());
        boxScore.setOpptDefensiveReboundPct(boxScore.calculateOpptDefensiveReboundPct().floatValue());
        boxScore.setOpptTotalReboundPct(boxScore.calculateOpptTotalReboundPct().floatValue());
        boxScore.setOpptAssistedFieldGoalPct(boxScore.calculateOpptAssistedFieldGoalPct().floatValue());
        boxScore.setOpptTurnoverPct(boxScore.calculateOpptTurnoverPct().floatValue());
        boxScore.setOpptStealPct(boxScore.calculateOpptStealPct().floatValue());
        boxScore.setOpptBlockPct(boxScore.calculateOpptBlockPct().floatValue());
        boxScore.setOpptBlockRate(boxScore.calculateOpptBlockRate().floatValue());
        boxScore.setOpptPointsPerShot(boxScore.calculateOpptPointsPerShot().floatValue());
        boxScore.setOpptFloorImpactCounter(boxScore.calculateOpptFloorImpactCounter().floatValue());
        boxScore.setOpptFloorImpactCounterPer40(boxScore.calculateOpptFloorImpactCounterPer40().floatValue());
        boxScore.setOpptOffensiveRating(boxScore.calculateOpptOffensiveRating().floatValue());
        boxScore.setOpptDefensiveRating(boxScore.calculateOpptDefensiveRating().floatValue());
        boxScore.setOpptEfficiencyDifferential(boxScore.calculateOpptEfficiencyDifferential().floatValue());
        boxScore.setOpptPlayPct(boxScore.calculateOpptPlayPct().floatValue());
        boxScore.setOpptAssistRate(boxScore.calculateOpptAssistRate().floatValue());
        boxScore.setOpptAssistToTurnoverRatio(boxScore.calculateOpptAssistToTurnoverRatio().floatValue());
        boxScore.setOpptStealToTurnoverRatio(boxScore.calculateOpptStealToTurnoverRatio().floatValue());

        boxScore.setPossessions(boxScore.calculatePossessions().floatValue());
        boxScore.setPace(boxScore.calculatePace().floatValue());
    }
}
