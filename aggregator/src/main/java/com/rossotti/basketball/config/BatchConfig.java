package com.rossotti.basketball.config;

import com.rossotti.basketball.mapper.TeamBoxScoreMapper;
import com.rossotti.basketball.model.Standing;
import com.rossotti.basketball.model.TeamBoxScore;
import com.rossotti.basketball.processor.StandingProcessor;
import com.rossotti.basketball.processor.TeamBoxScoreProcessor;
import com.rossotti.basketball.util.DateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
@EnableBatchProcessing
public class BatchConfig {

    private final DatabaseConfig databaseConfig;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    private final Logger logger = LoggerFactory.getLogger(BatchConfig.class);

    //LocalDate gameDate = LocalDate.now().minusDays(1);
    LocalDate fromDate = LocalDate.of(2016, 10, 25);
    LocalDate toDate = LocalDate.of(2017, 4, 12);

    @Autowired
    public BatchConfig(DatabaseConfig databaseConfig, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.databaseConfig = databaseConfig;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job aggregateGameJob() {
        return jobBuilderFactory.get("aggregateGameJob")
                .incrementer(new RunIdIncrementer())
                .flow(stepTeamBoxScore())
                .next(stepStanding())
                .end()
                .build();
    }

    @Bean
    public Step stepTeamBoxScore() {
        return stepBuilderFactory.get("stepTeamBoxScore")
                .<TeamBoxScore, TeamBoxScore>chunk(20)
                .reader(teamBoxScoreReader())
                .processor(teamBoxScoreProcessor())
                .writer(teamBoxScoreJdbcWriter())
                .build();
    }

    @Bean
    public Step stepStanding() {
        return stepBuilderFactory.get("stepStanding")
                .<Standing, Standing>chunk(20)
                .reader(standingReader())
                .processor(standingProcessor())
                .writer(standingJdbcWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Standing> standingReader() {
        JdbcCursorItemReader<Standing> reader = new JdbcCursorItemReader<>();
        String minDate = DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTimeMin(fromDate));
        String maxDate = DateTimeConverter.getStringDate(DateTimeConverter.getLocalDateTimeMax(toDate));
        String sql =
            "SELECT " +
                "standingDate, teamAbbr, " +
                "rank, ordinalRank, gamesWon, gamesLost, streak, streakType, streakTotal, gamesBack, pointsFor, pointsAgainst,  " +
                "homeWins, homeLosses, awayWins, awayLosses, conferenceWins, conferenceLosses, lastFive, lastTen, gamesPlayed, " +
                "pointsScoredPerGame, pointsAllowedPerGame, pointDifferentialPerGame, opptGamesPlayed, opptGamesWon, opptOpptGamesPlayed, " +
                "opptOpptGamesWon, strengthOfSchedule " +
            "FROM standing " +
            "WHERE standing.gameDate BETWEEN '" + minDate + "' AND '" + maxDate + "' " +
            "ORDER BY standingDate, teamAbbr asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSourceAccumulate());
        reader.setRowMapper(new TeamBoxScoreMapper());
        return reader;
    }

    @Bean
    public StandingProcessor standingProcessor() {
        return new StandingProcessor();
    }

    @Bean
    public ItemWriter<Standing> standingFileWriter() {
        FlatFileItemWriter<Standing> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource(new File("/home/pablote/pdrive/pwork/basketball/aggregator/extracts/standing_Extract.txt")));
        flatFileItemWriter.setShouldDeleteIfExists(true);
        BeanWrapperFieldExtractor<Standing> fieldExtractor = new BeanWrapperFieldExtractor<>();
        String[] fields = new String[]{
            "standingDate", "seasonType", "teamAbbr", "rank", "ordinalRank", "gamesWon", "gamesLost", "streak", "streakType", "streakTotal", "gamesBack", "pointsFor",
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
    public ItemWriter<Standing> standingJdbcWriter() {
        JdbcBatchItemWriter<Standing> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(databaseConfig.dataSourceAggregate());
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        BeanWrapperFieldExtractor<Standing> fieldExtractor = new BeanWrapperFieldExtractor<>();

        String sql =
            "INSERT INTO TeamBoxScore (" +
                "standingDate, seasonType, teamAbbr, rank, ordinalRank, gamesWon, gamesLost, streak, streakType, streakTotal, gamesBack, pointsFor, " +
                "pointsAgainst, homeWins, homeLosses, awayWins, awayLosses, conferenceWins, conferenceLosses, lastFive, lastTen, gamesPlayed, " +
                "pointsScoredPerGame, pointsAllowedPerGame, pointDifferentialPerGame, opptGamesPlayed, opptGamesWon, opptOpptGamesPlayed, " +
                "opptOpptGamesWon, strengthOfSchedule" +
            ") " +
            "VALUES (" +
                ":gameDateTime, :seasonType, :possessions, :pace, " +
                ":teamAbbr, :teamConference, :teamDivision, :teamLocation, :teamResult, :teamMinutes, :teamDaysOff, " +
                ":teamPoints, :teamAssists, :teamTurnovers, :teamSteals, :teamBlocks, :teamPersonalFouls, :teamFieldGoalAttempts, :teamFieldGoalMade, " +
                ":teamThreePointAttempts, :teamThreePointMade, :teamFreeThrowAttempts, :teamFreeThrowMade, :teamReboundsOffense, :teamReboundsDefense, " +
                ":teamPointsQ1, :teamPointsQ2, :teamPointsQ3, :teamPointsQ4, :teamPointsQ5, :teamPointsQ6, :teamPointsQ7, :teamPointsQ8, " +
                ":opptAbbr, :opptConference, :opptDivision, :opptLocation, :opptResult, :opptMinutes, :opptDaysOff, " +
                ":opptPoints, :opptAssists, :opptTurnovers, :opptSteals, :opptBlocks, :opptPersonalFouls, :opptFieldGoalAttempts, :opptFieldGoalMade, " +
                ":opptThreePointAttempts, :opptThreePointMade, :opptFreeThrowAttempts, :opptFreeThrowMade, :opptReboundsOffense, :opptReboundsDefense, " +
                ":opptPointsQ1, :opptPointsQ2, :opptPointsQ3, :opptPointsQ4, :opptPointsQ5, :opptPointsQ6, :opptPointsQ7, :opptPointsQ8" +
            ")";
        jdbcBatchItemWriter.setSql(sql);
        return jdbcBatchItemWriter;
    }

    @Bean
    public JdbcCursorItemReader<TeamBoxScore> teamBoxScoreReader() {
        JdbcCursorItemReader<TeamBoxScore> reader = new JdbcCursorItemReader<>();
        String minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(fromDate));
        String maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(toDate));
        String sql =
            "SELECT game.gameDateTime, game.seasonType, " +
                "team.abbr as teamAbbr, team.conference as teamConf, team.division as teamDiv, " +
                "bsTeam.location as teamLocation, bsTeam.result as teamResult, bsTeam.minutes as teamMinutes, bsTeam.daysOff as teamDaysOff, " +
                "bsTeam.points as teamPTS, bsTeam.assists as teamAST, bsTeam.turnovers as teamTOV, bsTeam.steals as teamSTL, bsTeam.blocks as teamBLK, bsTeam.personalFouls as teamPF, " +
                "bsTeam.fieldGoalAttempts as teamFGA, bsTeam.fieldGoalMade as teamFGM, bsTeam.threePointAttempts as team3PA, bsTeam.threePointMade as team3PM, " +
                "bsTeam.freeThrowAttempts as teamFTA, bsTeam.freeThrowMade as teamFTM, bsTeam.reboundsOffense as teamOREB, bsTeam.reboundsDefense as teamDREB, " +
                "bsTeam.pointsPeriod1 as teamPTQ1, bsTeam.pointsPeriod2 as teamPTQ2, bsTeam.pointsPeriod3 as teamPTQ3, bsTeam.pointsPeriod4 as teamPTQ4, " +
                "bsTeam.pointsPeriod5 as teamPTQ5, bsTeam.pointsPeriod6 as teamPTQ6, bsTeam.pointsPeriod7 as teamPTQ7, bsTeam.pointsPeriod8 as teamPTQ8, " +
                "oppt.abbr as opptAbbr, oppt.conference as opptConf, oppt.division as opptDiv, " +
                "bsOppt.location as opptLocation, bsOppt.result as opptResult, bsOppt.minutes as opptMinutes, bsOppt.daysOff as opptDaysOff, " +
                "bsOppt.points as opptPTS, bsOppt.assists as opptAST, bsOppt.turnovers as opptTOV, bsOppt.steals as opptSTL, bsOppt.blocks as opptBLK, bsOppt.personalFouls as opptPF, " +
                "bsOppt.fieldGoalAttempts as opptFGA, bsOppt.fieldGoalMade as opptFGM, bsOppt.threePointAttempts as oppt3PA, bsOppt.threePointMade as oppt3PM, " +
                "bsOppt.freeThrowAttempts as opptFTA, bsOppt.freeThrowMade as opptFTM, bsOppt.reboundsOffense as opptOREB, bsOppt.reboundsDefense as opptDREB, " +
                "bsOppt.pointsPeriod1 as opptPTQ1, bsOppt.pointsPeriod2 as opptPTQ2, bsOppt.pointsPeriod3 as opptPTQ3, bsOppt.pointsPeriod4 as opptPTQ4, " +
                "bsOppt.pointsPeriod5 as opptPTQ5, bsOppt.pointsPeriod6 as opptPTQ6, bsOppt.pointsPeriod7 as opptPTQ7, bsOppt.pointsPeriod8 as opptPTQ8 " +
            "FROM game AS game " +
            "INNER JOIN boxScore AS bsTeam ON game.id = bsTeam.gameId " +
            "INNER JOIN team AS team ON team.id = bsTeam.teamId " +
            "INNER JOIN boxScore AS bsOppt ON game.id = bsOppt.gameId " +
            "INNER JOIN team AS oppt ON oppt.id = bsOppt.teamId " +
            "WHERE game.gameDateTime BETWEEN '" + minDateTime + "' AND '" + maxDateTime + "' " +
            "AND game.status = 'Completed' " +
            "AND team.abbr <> oppt.abbr " +
            "ORDER BY game.gameDateTime asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSourceAccumulate());
        reader.setRowMapper(new TeamBoxScoreMapper());
        return reader;
    }

    @Bean
    public TeamBoxScoreProcessor teamBoxScoreProcessor() {
        return new TeamBoxScoreProcessor();
    }

    @Bean
    public ItemWriter<TeamBoxScore> teamBoxScoreFileWriter() {
        FlatFileItemWriter<TeamBoxScore> flatFileItemWriter = new FlatFileItemWriter<>();
        flatFileItemWriter.setResource(new FileSystemResource(new File("/home/pablote/pdrive/pwork/basketball/aggregator/extracts/teamBoxScore_Extract.txt")));
        flatFileItemWriter.setShouldDeleteIfExists(true);
        BeanWrapperFieldExtractor<TeamBoxScore> fieldExtractor = new BeanWrapperFieldExtractor<>();
        String[] fields = new String[]{
            "gameDateTime", "seasonType", "possessions", "pace",
            "teamAbbr", "teamConference", "teamDivision", "teamLocation", "teamResult", "teamMinutes", "teamDaysOff",
            "teamPoints", "teamAssists", "teamTurnovers", "teamSteals", "teamBlocks", "teamPersonalFouls", "teamFieldGoalAttempts", "teamFieldGoalMade", 
            "teamThreePointAttempts", "teamThreePointMade", "teamFreeThrowAttempts", "teamFreeThrowMade", "teamReboundsOffense", "teamReboundsDefense",
            "teamPointsQ1", "teamPointsQ2", "teamPointsQ3", "teamPointsQ4", "teamPointsQ5", "teamPointsQ6", "teamPointsQ7", "teamPointsQ8",
            "opptAbbr", "opptConference", "opptDivision", "opptLocation", "opptResult", "opptMinutes", "opptDaysOff",
            "opptPoints", "opptAssists", "opptTurnovers", "opptSteals", "opptBlocks", "opptPersonalFouls", "opptFieldGoalAttempts", "opptFieldGoalMade",
            "opptThreePointAttempts", "opptThreePointMade", "opptFreeThrowAttempts", "opptFreeThrowMade", "opptReboundsOffense", "opptReboundsDefense",
            "opptPointsQ1", "opptPointsQ2", "opptPointsQ3", "opptPointsQ4", "opptPointsQ5", "opptPointsQ6", "opptPointsQ7", "opptPointsQ8"
        };
        fieldExtractor.setNames(fields);
        DelimitedLineAggregator<TeamBoxScore> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        flatFileItemWriter.setLineAggregator(lineAggregator);
        return flatFileItemWriter;
    }

    @Bean
    public ItemWriter<TeamBoxScore> teamBoxScoreJdbcWriter() {
        JdbcBatchItemWriter<TeamBoxScore> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(databaseConfig.dataSourceAggregate());
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql =
            "INSERT INTO TeamBoxScore (" +
                "gameDateTime, seasonType, possessions, pace, " +
                "teamAbbr, teamConference, teamDivision, teamLocation, teamResult, teamMinutes, teamDaysOff, " +
                "teamPoints, teamAssists, teamTurnovers, teamSteals, teamBlocks, teamPersonalFouls, teamFieldGoalAttempts, teamFieldGoalMade, " +
                "teamThreePointAttempts, teamThreePointMade, teamFreeThrowAttempts, teamFreeThrowMade, teamReboundsOffense, teamReboundsDefense, " +
                "teamPointsQ1, teamPointsQ2, teamPointsQ3, teamPointsQ4, teamPointsQ5, teamPointsQ6, teamPointsQ7, teamPointsQ8, " +
                "opptAbbr, opptConference, opptDivision, opptLocation, opptResult, opptMinutes, opptDaysOff, " +
                "opptPoints, opptAssists, opptTurnovers, opptSteals, opptBlocks, opptPersonalFouls, opptFieldGoalAttempts, opptFieldGoalMade, " +
                "opptThreePointAttempts, opptThreePointMade, opptFreeThrowAttempts, opptFreeThrowMade, opptReboundsOffense, opptReboundsDefense, " +
                "opptPointsQ1, opptPointsQ2, opptPointsQ3, opptPointsQ4, opptPointsQ5, opptPointsQ6, opptPointsQ7, opptPointsQ8" +
            ") " +
            "VALUES (" +
                ":gameDateTime, :seasonType, :possessions, :pace, " +
                ":teamAbbr, :teamConference, :teamDivision, :teamLocation, :teamResult, :teamMinutes, :teamDaysOff, " +
                ":teamPoints, :teamAssists, :teamTurnovers, :teamSteals, :teamBlocks, :teamPersonalFouls, :teamFieldGoalAttempts, :teamFieldGoalMade, " +
                ":teamThreePointAttempts, :teamThreePointMade, :teamFreeThrowAttempts, :teamFreeThrowMade, :teamReboundsOffense, :teamReboundsDefense, " +
                ":teamPointsQ1, :teamPointsQ2, :teamPointsQ3, :teamPointsQ4, :teamPointsQ5, :teamPointsQ6, :teamPointsQ7, :teamPointsQ8, " +
                ":opptAbbr, :opptConference, :opptDivision, :opptLocation, :opptResult, :opptMinutes, :opptDaysOff, " +
                ":opptPoints, :opptAssists, :opptTurnovers, :opptSteals, :opptBlocks, :opptPersonalFouls, :opptFieldGoalAttempts, :opptFieldGoalMade, " +
                ":opptThreePointAttempts, :opptThreePointMade, :opptFreeThrowAttempts, :opptFreeThrowMade, :opptReboundsOffense, :opptReboundsDefense, " +
                ":opptPointsQ1, :opptPointsQ2, :opptPointsQ3, :opptPointsQ4, :opptPointsQ5, :opptPointsQ6, :opptPointsQ7, :opptPointsQ8" +
            ")";
        jdbcBatchItemWriter.setSql(sql);
        return jdbcBatchItemWriter;
    }
}