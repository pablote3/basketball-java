package com.rossotti.basketball.batch;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamBoxScoreMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TeamBoxScore teamBoxScore = new TeamBoxScore();
        teamBoxScore.setGameDateTime(resultSet.getString("gameDateTime"));
        teamBoxScore.setSeasonType(resultSet.getString("seasonType"));
        teamBoxScore.setOfficialLastName1(resultSet.getString("officialLastName1"));
        teamBoxScore.setOfficialFirstName1(resultSet.getString("officialFirstName1"));
        teamBoxScore.setOfficialLastName2(resultSet.getString("officialLastName2"));
        teamBoxScore.setOfficialFirstName2(resultSet.getString("officialFirstName2"));
        teamBoxScore.setOfficialLastName3(resultSet.getString("officialLastName3"));
        teamBoxScore.setOfficialFirstName3(resultSet.getString("officialFirstName3"));
        teamBoxScore.setTeamAbbr(resultSet.getString("teamAbbr"));
        teamBoxScore.setTeamConference(resultSet.getString("teamConf"));
        teamBoxScore.setTeamDivision(resultSet.getString("teamDiv"));
        teamBoxScore.setTeamLocation(resultSet.getString("teamLocation"));
        teamBoxScore.setTeamResult(resultSet.getString("teamResult"));
        teamBoxScore.setTeamMinutes(resultSet.getShort("teamMinutes"));
        teamBoxScore.setTeamDaysOff(resultSet.getShort("teamDaysOff"));
        teamBoxScore.setTeamPoints(resultSet.getShort("teamPTS"));
        teamBoxScore.setTeamAssists(resultSet.getShort("teamAST"));
        teamBoxScore.setTeamTurnovers(resultSet.getShort("teamTOV"));
        teamBoxScore.setTeamSteals(resultSet.getShort("teamSTL"));
        teamBoxScore.setTeamBlocks(resultSet.getShort("teamBLK"));
        teamBoxScore.setTeamPersonalFouls(resultSet.getShort("teamPF"));
        teamBoxScore.setTeamFieldGoalAttempts(resultSet.getShort("teamFGA"));
        teamBoxScore.setTeamFieldGoalMade(resultSet.getShort("teamFGM"));
        teamBoxScore.setTeamThreePointAttempts(resultSet.getShort("team3PA"));
        teamBoxScore.setTeamThreePointMade(resultSet.getShort("team3PM"));
        teamBoxScore.setTeamFreeThrowAttempts(resultSet.getShort("teamFTA"));
        teamBoxScore.setTeamFreeThrowMade(resultSet.getShort("teamFTM"));
        teamBoxScore.setTeamReboundsOffense(resultSet.getShort("teamOREB"));
        teamBoxScore.setTeamReboundsDefense(resultSet.getShort("teamDREB"));
        teamBoxScore.setTeamPointsQ1(resultSet.getShort("teamPTQ1"));
        teamBoxScore.setTeamPointsQ2(resultSet.getShort("teamPTQ2"));
        teamBoxScore.setTeamPointsQ3(resultSet.getShort("teamPTQ3"));
        teamBoxScore.setTeamPointsQ4(resultSet.getShort("teamPTQ4"));
        teamBoxScore.setTeamPointsQ5(resultSet.getShort("teamPTQ5"));
        teamBoxScore.setTeamPointsQ6(resultSet.getShort("teamPTQ6"));
        teamBoxScore.setTeamPointsQ7(resultSet.getShort("teamPTQ7"));
        teamBoxScore.setTeamPointsQ8(resultSet.getShort("teamPTQ8"));
        teamBoxScore.setOpptAbbr(resultSet.getString("opptAbbr"));
        teamBoxScore.setOpptConference(resultSet.getString("opptConf"));
        teamBoxScore.setOpptDivision(resultSet.getString("opptDiv"));
        teamBoxScore.setOpptLocation(resultSet.getString("opptLocation"));
        teamBoxScore.setOpptResult(resultSet.getString("opptResult"));
        teamBoxScore.setOpptMinutes(resultSet.getShort("opptMinutes"));
        teamBoxScore.setOpptDaysOff(resultSet.getShort("opptDaysOff"));
        teamBoxScore.setOpptPoints(resultSet.getShort("opptPTS"));
        teamBoxScore.setOpptAssists(resultSet.getShort("opptAST"));
        teamBoxScore.setOpptTurnovers(resultSet.getShort("opptTOV"));
        teamBoxScore.setOpptSteals(resultSet.getShort("opptSTL"));
        teamBoxScore.setOpptBlocks(resultSet.getShort("opptBLK"));
        teamBoxScore.setOpptPersonalFouls(resultSet.getShort("opptPF"));
        teamBoxScore.setOpptFieldGoalAttempts(resultSet.getShort("opptFGA"));
        teamBoxScore.setOpptFieldGoalMade(resultSet.getShort("opptFGM"));
        teamBoxScore.setOpptThreePointAttempts(resultSet.getShort("oppt3PA"));
        teamBoxScore.setOpptThreePointMade(resultSet.getShort("oppt3PM"));
        teamBoxScore.setOpptFreeThrowAttempts(resultSet.getShort("opptFTA"));
        teamBoxScore.setOpptFreeThrowMade(resultSet.getShort("opptFTM"));
        teamBoxScore.setOpptReboundsOffense(resultSet.getShort("opptOREB"));
        teamBoxScore.setOpptReboundsDefense(resultSet.getShort("opptDREB"));
        teamBoxScore.setOpptPointsQ1(resultSet.getShort("opptPTQ1"));
        teamBoxScore.setOpptPointsQ2(resultSet.getShort("opptPTQ2"));
        teamBoxScore.setOpptPointsQ3(resultSet.getShort("opptPTQ3"));
        teamBoxScore.setOpptPointsQ4(resultSet.getShort("opptPTQ4"));
        teamBoxScore.setOpptPointsQ5(resultSet.getShort("opptPTQ5"));
        teamBoxScore.setOpptPointsQ6(resultSet.getShort("opptPTQ6"));
        teamBoxScore.setOpptPointsQ7(resultSet.getShort("opptPTQ7"));
        teamBoxScore.setOpptPointsQ8(resultSet.getShort("opptPTQ8"));
        return teamBoxScore;
    }
}