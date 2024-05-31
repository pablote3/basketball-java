package com.rossotti.basketball.batch;

import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

class OfficialBoxScoreMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        OfficialBoxScore officialBoxScore = new OfficialBoxScore();
        officialBoxScore.setGameDateTime(resultSet.getString("gameDateTime"));
        officialBoxScore.setSeasonType(resultSet.getString("seasonType"));
        officialBoxScore.setOfficialLastName(resultSet.getString("officialLastName"));
        officialBoxScore.setOfficialFirstName(resultSet.getString("officialFirstName"));
        officialBoxScore.setTeamAbbr(resultSet.getString("teamAbbr"));
        officialBoxScore.setTeamConference(resultSet.getString("teamConf"));
        officialBoxScore.setTeamDivision(resultSet.getString("teamDiv"));
        officialBoxScore.setTeamLocation(resultSet.getString("teamLocation"));
        officialBoxScore.setTeamResult(resultSet.getString("teamResult"));
        officialBoxScore.setTeamMinutes(resultSet.getShort("teamMinutes"));
        officialBoxScore.setTeamDaysOff(resultSet.getShort("teamDaysOff"));
        officialBoxScore.setTeamPoints(resultSet.getShort("teamPTS"));
        officialBoxScore.setTeamAssists(resultSet.getShort("teamAST"));
        officialBoxScore.setTeamTurnovers(resultSet.getShort("teamTOV"));
        officialBoxScore.setTeamSteals(resultSet.getShort("teamSTL"));
        officialBoxScore.setTeamBlocks(resultSet.getShort("teamBLK"));
        officialBoxScore.setTeamPersonalFouls(resultSet.getShort("teamPF"));
        officialBoxScore.setTeamFieldGoalAttempts(resultSet.getShort("teamFGA"));
        officialBoxScore.setTeamFieldGoalMade(resultSet.getShort("teamFGM"));
        officialBoxScore.setTeamThreePointAttempts(resultSet.getShort("team3PA"));
        officialBoxScore.setTeamThreePointMade(resultSet.getShort("team3PM"));
        officialBoxScore.setTeamFreeThrowAttempts(resultSet.getShort("teamFTA"));
        officialBoxScore.setTeamFreeThrowMade(resultSet.getShort("teamFTM"));
        officialBoxScore.setTeamReboundsOffense(resultSet.getShort("teamOREB"));
        officialBoxScore.setTeamReboundsDefense(resultSet.getShort("teamDREB"));
        officialBoxScore.setTeamPointsQ1(resultSet.getShort("teamPTQ1"));
        officialBoxScore.setTeamPointsQ2(resultSet.getShort("teamPTQ2"));
        officialBoxScore.setTeamPointsQ3(resultSet.getShort("teamPTQ3"));
        officialBoxScore.setTeamPointsQ4(resultSet.getShort("teamPTQ4"));
        officialBoxScore.setTeamPointsQ5(resultSet.getShort("teamPTQ5"));
        officialBoxScore.setTeamPointsQ6(resultSet.getShort("teamPTQ6"));
        officialBoxScore.setTeamPointsQ7(resultSet.getShort("teamPTQ7"));
        officialBoxScore.setTeamPointsQ8(resultSet.getShort("teamPTQ8"));
        officialBoxScore.setOpptAbbr(resultSet.getString("opptAbbr"));
        officialBoxScore.setOpptConference(resultSet.getString("opptConf"));
        officialBoxScore.setOpptDivision(resultSet.getString("opptDiv"));
        officialBoxScore.setOpptLocation(resultSet.getString("opptLocation"));
        officialBoxScore.setOpptResult(resultSet.getString("opptResult"));
        officialBoxScore.setOpptMinutes(resultSet.getShort("opptMinutes"));
        officialBoxScore.setOpptDaysOff(resultSet.getShort("opptDaysOff"));
        officialBoxScore.setOpptPoints(resultSet.getShort("opptPTS"));
        officialBoxScore.setOpptAssists(resultSet.getShort("opptAST"));
        officialBoxScore.setOpptTurnovers(resultSet.getShort("opptTOV"));
        officialBoxScore.setOpptSteals(resultSet.getShort("opptSTL"));
        officialBoxScore.setOpptBlocks(resultSet.getShort("opptBLK"));
        officialBoxScore.setOpptPersonalFouls(resultSet.getShort("opptPF"));
        officialBoxScore.setOpptFieldGoalAttempts(resultSet.getShort("opptFGA"));
        officialBoxScore.setOpptFieldGoalMade(resultSet.getShort("opptFGM"));
        officialBoxScore.setOpptThreePointAttempts(resultSet.getShort("oppt3PA"));
        officialBoxScore.setOpptThreePointMade(resultSet.getShort("oppt3PM"));
        officialBoxScore.setOpptFreeThrowAttempts(resultSet.getShort("opptFTA"));
        officialBoxScore.setOpptFreeThrowMade(resultSet.getShort("opptFTM"));
        officialBoxScore.setOpptReboundsOffense(resultSet.getShort("opptOREB"));
        officialBoxScore.setOpptReboundsDefense(resultSet.getShort("opptDREB"));
        officialBoxScore.setOpptPointsQ1(resultSet.getShort("opptPTQ1"));
        officialBoxScore.setOpptPointsQ2(resultSet.getShort("opptPTQ2"));
        officialBoxScore.setOpptPointsQ3(resultSet.getShort("opptPTQ3"));
        officialBoxScore.setOpptPointsQ4(resultSet.getShort("opptPTQ4"));
        officialBoxScore.setOpptPointsQ5(resultSet.getShort("opptPTQ5"));
        officialBoxScore.setOpptPointsQ6(resultSet.getShort("opptPTQ6"));
        officialBoxScore.setOpptPointsQ7(resultSet.getShort("opptPTQ7"));
        officialBoxScore.setOpptPointsQ8(resultSet.getShort("opptPTQ8"));
        return officialBoxScore;
    }
}