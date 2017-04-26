package com.rossotti.basketball.mapper;

import com.rossotti.basketball.model.TeamBoxScore;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamBoxScoreMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TeamBoxScore team = new TeamBoxScore();
        team.setGameDateTime(resultSet.getString("gameDateTime"));
        team.setTeamKey(resultSet.getString("teamKey"));
        team.setStatus(resultSet.getString("status"));
        return team;
    }
}