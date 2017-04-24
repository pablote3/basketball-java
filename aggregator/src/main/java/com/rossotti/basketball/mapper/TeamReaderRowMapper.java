package com.rossotti.basketball.mapper;

import com.rossotti.basketball.model.Team;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamReaderRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Team team = new Team();
        team.setGameDateTime(resultSet.getString("gameDateTime"));
        team.setTeamKey(resultSet.getString("teamKey"));
        team.setStatus(resultSet.getString("status"));
        return team;
    }
}