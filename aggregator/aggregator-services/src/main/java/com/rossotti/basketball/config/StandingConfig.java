package com.rossotti.basketball.config;

import com.rossotti.basketball.mapper.StandingMapper;
import com.rossotti.basketball.model.Standing;
import com.rossotti.basketball.model.TeamBoxScore;
import com.rossotti.basketball.processor.StandingProcessor;
import com.rossotti.basketball.util.DateTimeConverter;
import com.rossotti.basketball.util.PropertyService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.time.LocalDate;

@Configuration
public class StandingConfig {

    private final PropertyService propertyService;

    private final DatabaseConfig databaseConfig;

    @Autowired
    public StandingConfig(PropertyService propertyService, DatabaseConfig databaseConfig) {
        this.propertyService = propertyService;
        this.databaseConfig = databaseConfig;
    }

    @SuppressWarnings("unchecked")
    @Bean
    public JdbcCursorItemReader<Standing> reader() {
        LocalDate fromDate = propertyService.getProperty_LocalDate("aggregator.fromDate");
        LocalDate toDate = propertyService.getProperty_LocalDate("aggregator.toDate");

        JdbcCursorItemReader<Standing> reader = new JdbcCursorItemReader<>();
        String minDate = DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTimeMin(fromDate));
        String maxDate = DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTimeMax(toDate));
        String sql =
            "SELECT " +
                "standing.standingDate, team.abbr, " +
                "standing.rank, standing.ordinalRank, standing.gamesWon, standing.gamesLost, standing.streak, standing.streakType, standing.streakTotal, standing.gamesBack, standing.pointsFor, " +
                "standing.pointsAgainst, standing.homeWins, standing.homeLosses, standing.awayWins, standing.awayLosses, standing.conferenceWins, standing.conferenceLosses, standing.lastFive,  " +
                "standing.lastTen, standing.gamesPlayed,standing.pointsScoredPerGame, standing.pointsAllowedPerGame, standing.pointDifferentialPerGame, standing.opptGamesPlayed, standing.opptGamesWon, " +
                "standing.opptOpptGamesPlayed, standing.opptOpptGamesWon " +
            "FROM standing AS standing " +
            "INNER JOIN team AS team ON team.id = standing.teamId " +
            "WHERE standing.standingDate BETWEEN '" + minDate + "' AND '" + maxDate + "' " +
            "ORDER BY standing.standingDate, team.abbr asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSourceAccumulate());
        reader.setRowMapper(new StandingMapper());
        return reader;
    }

    @Bean
    public ItemWriter<Standing> fileWriter() {
        FlatFileItemWriter<Standing> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource(new File("/home/pablote/pdrive/pwork/basketball/aggregator/extracts/standing_Extract.txt")));
        flatFileItemWriter.setShouldDeleteIfExists(true);
        BeanWrapperFieldExtractor<Standing> fieldExtractor = new BeanWrapperFieldExtractor<>();
        String[] fields = new String[]{
            "standingDate", "teamAbbr", "rank", "ordinalRank", "gamesWon", "gamesLost", "streak", "streakType", "streakTotal", "gamesBack", "pointsFor",
            "pointsAgainst", "homeWins", "homeLosses", "awayWins", "awayLosses", "conferenceWins", "conferenceLosses", "lastFive", "lastTen", "gamesPlayed",
            "pointsScoredPerGame", "pointsAllowedPerGame", "pointDifferentialPerGame", "opptGamesPlayed", "opptGamesWon", "opptOpptGamesPlayed",
            "opptOpptGamesWon", "strengthOfSchedule"
        };
        fieldExtractor.setNames(fields);
        DelimitedLineAggregator<Standing> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        flatFileItemWriter.setLineAggregator(lineAggregator);
        return flatFileItemWriter;
    }

    @Bean
    public ItemWriter<Standing> jdbcWriter() {
        JdbcBatchItemWriter<Standing> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(databaseConfig.dataSourceAggregate());
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        String sql =
            "INSERT INTO Standing " +
            "(" +
                "standingDate, teamAbbr, rank, ordinalRank, gamesWon, gamesLost, streak, streakType, streakTotal, gamesBack, pointsFor, " +
                "pointsAgainst, homeWins, homeLosses, awayWins, awayLosses, conferenceWins, conferenceLosses, lastFive, lastTen, gamesPlayed, " +
                "pointsScoredPerGame, pointsAllowedPerGame, pointDifferentialPerGame, opptGamesPlayed, opptGamesWon, opptOpptGamesPlayed, " +
                "opptOpptGamesWon, strengthOfSchedule" +
            ") " +
            "VALUES " +
            "(" +
                ":standingDate, :teamAbbr, :rank, :ordinalRank, :gamesWon, :gamesLost, :streak, :streakType, :streakTotal, :gamesBack, :pointsFor, " +
                ":pointsAgainst, :homeWins, :homeLosses, :awayWins, :awayLosses, :conferenceWins, :conferenceLosses, :lastFive, :lastTen, :gamesPlayed, " +
                ":pointsScoredPerGame, :pointsAllowedPerGame, :pointDifferentialPerGame, :opptGamesPlayed, :opptGamesWon, :opptOpptGamesPlayed, " +
                ":opptOpptGamesWon, :strengthOfSchedule" +
            ")";
        jdbcBatchItemWriter.setSql(sql);
        return jdbcBatchItemWriter;
    }
}
