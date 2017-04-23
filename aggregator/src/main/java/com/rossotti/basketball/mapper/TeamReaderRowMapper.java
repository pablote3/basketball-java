package com.rossotti.basketball.mapper;

import com.rossotti.basketball.model.TeamReaderInput;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TeamReaderRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        TeamReaderInput teamReaderInput = new TeamReaderInput();
        teamReaderInput.setGameDateTime(resultSet.getString("gameDateTime"));
        return teamReaderInput;
    }
}