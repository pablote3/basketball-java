package com.rossotti.basketball.batch;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class PlayerBoxScoreMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        PlayerBoxScore playerBoxScore = new PlayerBoxScore();
        playerBoxScore.setGameDateTime(resultSet.getString("gameDateTime"));
        playerBoxScore.setPlayerLastName(resultSet.getString("playerLastName"));
        playerBoxScore.setPlayerFirstName(resultSet.getString("playerFirstName"));
        playerBoxScore.setTeamAbbr(resultSet.getString("teamAbbr"));
        playerBoxScore.setSeasonType(resultSet.getString("seasonType"));
        playerBoxScore.setOfficialLastName1(resultSet.getString("officialLastName1"));
        playerBoxScore.setOfficialFirstName1(resultSet.getString("officialFirstName1"));
        playerBoxScore.setOfficialLastName2(resultSet.getString("officialLastName2"));
        playerBoxScore.setOfficialFirstName2(resultSet.getString("officialFirstName2"));
        playerBoxScore.setOfficialLastName3(resultSet.getString("officialLastName3"));
        playerBoxScore.setOfficialFirstName3(resultSet.getString("officialFirstName3"));
        playerBoxScore.setTeamConference(resultSet.getString("teamConf"));
        playerBoxScore.setTeamDivision(resultSet.getString("teamDiv"));
        playerBoxScore.setTeamLocation(resultSet.getString("teamLocation"));
        playerBoxScore.setTeamResult(resultSet.getString("teamResult"));
        playerBoxScore.setTeamDaysOff(resultSet.getShort("teamDaysOff"));
        playerBoxScore.setDisplayName(resultSet.getString("displayName"));
        playerBoxScore.setStatus(resultSet.getString("status"));
        playerBoxScore.setMinutes(resultSet.getShort("minutes"));

        playerBoxScore.setPosition(resultSet.getString("position"));
        playerBoxScore.setHeight(resultSet.getShort("height"));
        playerBoxScore.setWeight(resultSet.getShort("weight"));
        playerBoxScore.setBirthdate(resultSet.getString("birthdate"));
        playerBoxScore.setBirthplace(resultSet.getString("birthplace"));

        playerBoxScore.setPoints(resultSet.getShort("points"));
        playerBoxScore.setAssists(resultSet.getShort("assists"));
        playerBoxScore.setTurnovers(resultSet.getShort("turnovers"));
        playerBoxScore.setSteals(resultSet.getShort("steals"));
        playerBoxScore.setBlocks(resultSet.getShort("blocks"));
        playerBoxScore.setPersonalFouls(resultSet.getShort("personalFouls"));
        playerBoxScore.setFieldGoalAttempts(resultSet.getShort("fieldGoalAttempts"));
        playerBoxScore.setFieldGoalMade(resultSet.getShort("fieldGoalMade"));
        playerBoxScore.setThreePointAttempts(resultSet.getShort("threePointAttempts"));
        playerBoxScore.setThreePointMade(resultSet.getShort("threePointMade"));
        playerBoxScore.setFreeThrowAttempts(resultSet.getShort("freeThrowAttempts"));
        playerBoxScore.setFreeThrowMade(resultSet.getShort("freeThrowMade"));
        playerBoxScore.setReboundsOffense(resultSet.getShort("reboundsOffense"));
        playerBoxScore.setReboundsDefense(resultSet.getShort("reboundsDefense"));
        playerBoxScore.setOpptAbbr(resultSet.getString("opptAbbr"));
        playerBoxScore.setOpptConference(resultSet.getString("opptConf"));
        playerBoxScore.setOpptDivision(resultSet.getString("opptDiv"));
        playerBoxScore.setOpptLocation(resultSet.getString("opptLocation"));
        playerBoxScore.setOpptResult(resultSet.getString("opptResult"));
        playerBoxScore.setOpptDaysOff(resultSet.getShort("opptDaysOff"));
        return playerBoxScore;
    }
}