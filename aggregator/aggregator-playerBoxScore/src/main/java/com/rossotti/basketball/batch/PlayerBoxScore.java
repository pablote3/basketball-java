package com.rossotti.basketball.batch;

import com.rossotti.basketball.calc.BoxScoreCalculations;

import java.math.BigDecimal;

public class PlayerBoxScore {
    private String gameDateTime;
    private String playerLastName;
    private String playerFirstName;
    private String teamAbbr;

    private String seasonType;
    private String officialLastName1;
    private String officialFirstName1;
    private String officialLastName2;
    private String officialFirstName2;
    private String officialLastName3;
    private String officialFirstName3;
    private String teamConference;
    private String teamDivision;
    private String teamLocation;
    private String teamResult;
    private Short teamDaysOff;

    private String displayName;
    private String status;
    private Short minutes;
    private String position;
    private Short height;
    private Short weight;
    private String birthdate;
    private String birthplace;

    private Short points;
    private Short assists;
    private Short turnovers;
    private Short steals;
    private Short blocks;
    private Short personalFouls;
    private Short fieldGoalAttempts;
    private Short fieldGoalMade;
    private Float fieldGoalPct;
    private Short twoPointAttempts;
    private Short twoPointMade;
    private Float twoPointPct;
    private Short threePointAttempts;
    private Short threePointMade;
    private Float threePointPct;
    private Short freeThrowAttempts;
    private Short freeThrowMade;
    private Float freeThrowPct;
    private Short reboundsOffense;
    private Short reboundsDefense;
    private Short reboundsTotal;

    private String opptAbbr;
    private String opptConference;
    private String opptDivision;
    private String opptLocation;
    private String opptResult;
    private Short opptDaysOff;

    public String getGameDateTime() {
        return gameDateTime;
    }
    public void setGameDateTime(String gameDateTime) {
        this.gameDateTime = gameDateTime;
    }

    public String getPlayerLastName() {
        return playerLastName;
    }
    public void setPlayerLastName(String playerLastName) {
        this.playerLastName = playerLastName;
    }

    public String getPlayerFirstName() {
        return playerFirstName;
    }
    public void setPlayerFirstName(String playerFirstName) {
        this.playerFirstName = playerFirstName;
    }

    public String getTeamAbbr() {
        return teamAbbr;
    }
    public void setTeamAbbr(String teamAbbr) {
        this.teamAbbr = teamAbbr;
    }

    public String getSeasonType() {
        return seasonType;
    }
    public void setSeasonType(String seasonType) {
        this.seasonType = seasonType;
    }

    public String getOfficialLastName1() {
        return officialLastName1;
    }
    public void setOfficialLastName1(String officialLastName1) {
        this.officialLastName1 = officialLastName1;
    }

    public String getOfficialFirstName1() {
        return officialFirstName1;
    }
    public void setOfficialFirstName1(String officialFirstName1) {
        this.officialFirstName1 = officialFirstName1;
    }

    public String getOfficialLastName2() {
        return officialLastName2;
    }
    public void setOfficialLastName2(String officialLastName2) {
        this.officialLastName2 = officialLastName2;
    }

    public String getOfficialFirstName2() {
        return officialFirstName2;
    }
    public void setOfficialFirstName2(String officialFirstName2) {
        this.officialFirstName2 = officialFirstName2;
    }

    public String getOfficialLastName3() {
        return officialLastName3;
    }
    public void setOfficialLastName3(String officialLastName3) {
        this.officialLastName3 = officialLastName3;
    }

    public String getOfficialFirstName3() {
        return officialFirstName3;
    }
    public void setOfficialFirstName3(String officialFirstName3) {
        this.officialFirstName3 = officialFirstName3;
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

    public Short getTeamDaysOff() {
        return teamDaysOff;
    }
    public void setTeamDaysOff(Short teamDaysOff) {
        this.teamDaysOff = teamDaysOff;
    }

    public String getDisplayName() {
        return displayName;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }

    public Short getMinutes() {
        return minutes;
    }
    public void setMinutes(Short minutes) {
        this.minutes = minutes;
    }

    public String getPosition() {
        return position;
    }
    public void setPosition(String position) {
        this.position = position;
    }

    public Short getHeight() {
        return height;
    }
    public void setHeight(Short height) {
        this.height = height;
    }

    public Short getWeight() {
        return weight;
    }
    public void setWeight(Short weight) {
        this.weight = weight;
    }

    public String getBirthdate() {
        return birthdate;
    }
    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getBirthplace() {
        return birthplace;
    }
    public void setBirthplace(String birthplace) {
        this.birthplace = birthplace;
    }

    public Short getPoints() {
        return points;
    }
    public void setPoints(Short points) {
        this.points = points;
    }

    public Short getAssists() {
        return assists;
    }
    public void setAssists(Short assists) {
        this.assists = assists;
    }

    public Short getTurnovers() {
        return turnovers;
    }
    public void setTurnovers(Short turnovers) {
        this.turnovers = turnovers;
    }

    public Short getSteals() {
        return steals;
    }
    public void setSteals(Short steals) {
        this.steals = steals;
    }

    public Short getBlocks() {
        return blocks;
    }
    public void setBlocks(Short blocks) {
        this.blocks = blocks;
    }

    public Short getPersonalFouls() {
        return personalFouls;
    }
    public void setPersonalFouls(Short personalFouls) {
        this.personalFouls = personalFouls;
    }

    public Short getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }
    public void setFieldGoalAttempts(Short fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public Short getFieldGoalMade() {
        return fieldGoalMade;
    }
    public void setFieldGoalMade(Short fieldGoalMade) {
        this.fieldGoalMade = fieldGoalMade;
    }

    public Float getFieldGoalPct() {
        return fieldGoalPct;
    }
    public void setFieldGoalPct(Float fieldGoalPct) {
        this.fieldGoalPct = fieldGoalPct;
    }

    public Short getTwoPointAttempts() {
        return twoPointAttempts;
    }
    public void setTwoPointAttempts(Short twoPointAttempts) {
        this.twoPointAttempts = twoPointAttempts;
    }

    public Short getTwoPointMade() {
        return twoPointMade;
    }
    public void setTwoPointMade(Short twoPointMade) {
        this.twoPointMade = twoPointMade;
    }

    public Float getTwoPointPct() {
        return twoPointPct;
    }
    public void setTwoPointPct(Float twoPointPct) {
        this.twoPointPct = twoPointPct;
    }

    public Short getThreePointAttempts() {
        return threePointAttempts;
    }
    public void setThreePointAttempts(Short threePointAttempts) {
        this.threePointAttempts = threePointAttempts;
    }

    public Short getThreePointMade() {
        return threePointMade;
    }
    public void setThreePointMade(Short teamThreePointMade) {
        this.threePointMade = teamThreePointMade;
    }

    public Float getThreePointPct() {
        return threePointPct;
    }
    public void setThreePointPct(Float threePointPct) {
        this.threePointPct = threePointPct;
    }

    public Short getFreeThrowAttempts() {
        return freeThrowAttempts;
    }
    public void setFreeThrowAttempts(Short freeThrowAttempts) {
        this.freeThrowAttempts = freeThrowAttempts;
    }

    public Short getFreeThrowMade() {
        return freeThrowMade;
    }
    public void setFreeThrowMade(Short freeThrowMade) {
        this.freeThrowMade = freeThrowMade;
    }

    public Float getFreeThrowPct() {
        return freeThrowPct;
    }
    public void setFreeThrowPct(Float freeThrowPct) {
        this.freeThrowPct = freeThrowPct;
    }

    public Short getReboundsOffense() {
        return reboundsOffense;
    }
    public void setReboundsOffense(Short reboundsOffense) {
        this.reboundsOffense = reboundsOffense;
    }

    public Short getReboundsDefense() {
        return reboundsDefense;
    }
    public void setReboundsDefense(Short reboundsDefense) {
        this.reboundsDefense = reboundsDefense;
    }

    public Short getReboundsTotal() {
        return reboundsTotal;
    }
    public void setReboundsTotal(Short reboundsTotal) {
        this.reboundsTotal = reboundsTotal;
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

    public Short getOpptDaysOff() {
        return opptDaysOff;
    }
    public void setOpptDaysOff(Short opptDaysOff) {
        this.opptDaysOff = opptDaysOff;
    }

    public Short calculateTwoPointMade() {
        return BoxScoreCalculations.calculateTwoPointMade(
            this.getFieldGoalMade(), this.getThreePointMade()
        );
    }

    public Short calculateTwoPointAttempt() {
        return BoxScoreCalculations.calculateTwoPointAttempt(
            this.getFieldGoalAttempts(), this.getThreePointAttempts()
        );
    }

    public BigDecimal calculateTwoPointPct() {
        Short attempt = BoxScoreCalculations.calculateTwoPointAttempt(
            this.getFieldGoalAttempts(), this.getThreePointAttempts()
        );
        Short made = BoxScoreCalculations.calculateTwoPointMade(
            this.getFieldGoalMade(), this.getThreePointMade()
        );
        return BoxScoreCalculations.calculatePercent(made, attempt);
    }

    public BigDecimal calculateThreePointPct() {
        return BoxScoreCalculations.calculatePercent(
            this.getThreePointMade(), this.getThreePointAttempts()
        );
    }

    public BigDecimal calculateFieldGoalPct() {
        return BoxScoreCalculations.calculatePercent(
            this.getFieldGoalMade(), this.getFieldGoalAttempts()
        );
    }

    public BigDecimal calculateFreeThrowPct() {
        return BoxScoreCalculations.calculatePercent(
            this.getFreeThrowMade(), this.getFreeThrowAttempts()
        );
    }

    public Short calculateReboundTotal() {
        return BoxScoreCalculations.calculateTotalRebound(
            this.getReboundsOffense(), this.getReboundsDefense()
        );
    }
}
