package com.rossotti.basketball.mapper;

import com.rossotti.basketball.model.Standing;
import org.springframework.jdbc.core.RowMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StandingMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Standing standing = new Standing();
        standing.setStandingDate(resultSet.getString("standingDate"));
        standing.setTeamAbbr(resultSet.getString("abbr"));
        standing.setRank(resultSet.getString("rank"));
        standing.setOrdinalRank(resultSet.getString("ordinalRank"));
        standing.setGamesWon(resultSet.getShort("gamesWon"));
        standing.setGamesLost(resultSet.getShort("gamesLost"));
        standing.setStreak(resultSet.getString("streak"));
        standing.setStreakType(resultSet.getString("streakType"));
        standing.setStreakTotal(resultSet.getShort("streakTotal"));
        standing.setGamesBack(resultSet.getFloat("gamesBack"));
        standing.setPointsFor(resultSet.getShort("pointsFor"));
        standing.setPointsAgainst(resultSet.getShort("pointsAgainst"));
        standing.setHomeWins(resultSet.getShort("homeWins"));
        standing.setHomeLosses(resultSet.getShort("homeLosses"));
        standing.setAwayWins(resultSet.getShort("awayWins"));
        standing.setAwayLosses(resultSet.getShort("awayLosses"));
        standing.setConferenceWins(resultSet.getShort("conferenceWins"));
        standing.setConferenceLosses(resultSet.getShort("conferenceLosses"));
        standing.setLastFive(resultSet.getShort("lastFive"));
        standing.setLastTen(resultSet.getShort("lastTen"));
        standing.setGamesPlayed(resultSet.getShort("gamesPlayed"));
        standing.setPointsScoredPerGame(resultSet.getFloat("pointsScoredPerGame"));
        standing.setPointsAllowedPerGame(resultSet.getFloat("pointsAllowedPerGame"));
        standing.setPointDifferentialPerGame(resultSet.getFloat("pointDifferentialPerGame"));
        standing.setOpptGamesPlayed(resultSet.getInt("opptGamesPlayed"));
        standing.setOpptGamesWon(resultSet.getInt("opptGamesWon"));
        standing.setOpptOpptGamesPlayed(resultSet.getInt("opptOpptGamesPlayed"));
        standing.setOpptOpptGamesWon(resultSet.getInt("opptOpptGamesWon"));
        return standing;
    }
}