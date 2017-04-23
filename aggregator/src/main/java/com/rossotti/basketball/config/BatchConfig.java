package com.rossotti.basketball.config;

import com.rossotti.basketball.mapper.TeamReaderRowMapper;
import com.rossotti.basketball.model.TeamReaderInput;
import com.rossotti.basketball.util.DateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import javax.sql.DataSource;
import java.time.LocalDate;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    @Bean
    public JdbcCursorItemReader<TeamReaderInput> teamReader(DataSource dataSource) {
        JdbcCursorItemReader<TeamReaderInput> reader = new JdbcCursorItemReader<>();
        LocalDate gameDate = LocalDate.now().minusDays(1);
        String minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(gameDate));
        String maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(gameDate));
        String sql = "select g.gameDateTime, t.teamKey, g.status " +
                "from game g " +
                "inner join boxScore as bs on g.id = bs.gameId " +
                "inner join team as t on t.id = bs.teamId " +
                "where g.gameDateTime between '" + minDateTime + "' and '" + maxDateTime + "' " +
                "and bs.location = 'Home' " +
                "order by g.gameDateTime asc";
        reader.setSql(sql);
        reader.setDataSource(dataSource);
        reader.setRowMapper(new TeamReaderRowMapper());
        return reader;
    }
}
