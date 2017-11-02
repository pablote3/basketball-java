package com.rossotti.basketball.batch;

import com.rossotti.basketball.config.DatabaseConfig;
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
@EnableBatchProcessing
public class PlayerBoxScoreConfig {

    private final PropertyService propertyService;

    private final DatabaseConfig databaseConfig;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public PlayerBoxScoreConfig(PropertyService propertyService, DatabaseConfig databaseConfig, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.propertyService = propertyService;
        this.databaseConfig = databaseConfig;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job aggregatePlayerBoxScoreJob() {
        return jobBuilderFactory.get("aggregatePlayerBoxScoreJob")
            .incrementer(new RunIdIncrementer())
            .flow(stepPlayerBoxScore())
            .end()
            .build();
    }

    @Bean
    public Step stepPlayerBoxScore() {
        return stepBuilderFactory.get("stepPlayerBoxScore")
            .<PlayerBoxScore, PlayerBoxScore>chunk(20)
            .reader(reader())
            .processor(playerBoxScoreProcessor())
            .writer(writer())
            .build();
    }

    @Bean
    public PlayerBoxScoreProcessor playerBoxScoreProcessor() {
        return new PlayerBoxScoreProcessor();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public JdbcCursorItemReader<PlayerBoxScore> reader() {
        String minDateTime = null;
        String maxDateTime = null;

        if (System.getProperty("fromDate") != null) {
            String fromDate = System.getProperty("fromDate");
            if (fromDate.isEmpty()) {
                minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(LocalDate.now().minusDays(1)));
            }
            else {
                if (DateTimeConverter.isDate(fromDate)) {
                    minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(DateTimeConverter.getLocalDate(fromDate)));
                }
                else {
                    System.out.println("Invalid fromDate argument");
                    System.exit(1);
                }
            }
        }
        else {
            System.out.println("Argument fromDate not supplied - assumed to be unit test execution");
            minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(DateTimeConverter.getLocalDate("2016-10-26")));
        }

        if (System.getProperty("toDate") != null) {
            String toDate = System.getProperty("toDate");
            if (toDate.isEmpty()) {
                maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(LocalDate.now().minusDays(1)));
            }
            else {
                if (DateTimeConverter.isDate(toDate)) {
                    maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(DateTimeConverter.getLocalDate(toDate)));
                }
                else {
                    System.out.println("Invalid toDate argument");
                    System.exit(1);
                }
            }
        }
        else {
            System.out.println("Argument toDate not supplied - assumed to be unit test execution");
            maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(DateTimeConverter.getLocalDate("2016-10-26")));
        }

        JdbcCursorItemReader<PlayerBoxScore> reader = new JdbcCursorItemReader<>();
        String sql =
            "SELECT game.gameDateTime, p.lastName as playerLastName, p.firstName as playerFirstName, team.abbr as teamAbbr, game.seasonType, " +
                "(select o1.lastName from gameOfficial as go1 inner join official as o1 on go1.officialId = o1.id where gameId = game.id limit 1 offset 0) as officialLastName1, " +
                "(select o2.firstName from gameOfficial as go2 inner join official as o2 on go2.officialId = o2.id where gameId = game.id limit 1 offset 0) as officialFirstName1, " +
                "(select o3.lastName from gameOfficial as go3 inner join official as o3 on go3.officialId = o3.id where gameId = game.id limit 1 offset 1) as officialLastName2, " +
                "(select o4.firstName from gameOfficial as go4 inner join official as o4 on go4.officialId = o4.id where gameId = game.id limit 1 offset 1) as officialFirstName2, " +
                "(select o5.lastName from gameOfficial as go5 inner join official as o5 on go5.officialId = o5.id where gameId = game.id limit 1 offset 2) as officialLastName3, " +
                "(select o6.firstName from gameOfficial as go6 inner join official as o6 on go6.officialId = o6.id where gameId = game.id limit 1 offset 2) as officialFirstName3, " +
                "team.conference as teamConf, team.division as teamDiv, bsTeam.location as teamLocation, bsTeam.result as teamResult, bsTeam.daysOff as teamDaysOff, " +
                "p.displayName, bsp.starter as status, bsp.minutes, bsp.position, p.height, p.weight, p.birthplace, p.birthdate, " +
                "bsp.points, bsp.assists, bsp.turnovers, bsp.steals, bsp.blocks, bsp.personalFouls, bsp.fieldGoalAttempts, bsp.fieldGoalMade, " +
                "bsp.threePointAttempts, bsp.threePointMade, bsp.freeThrowAttempts, bsp.freeThrowMade, bsp.reboundsOffense, bsp.reboundsDefense, " +
                "oppt.abbr as opptAbbr, oppt.conference as opptConf, oppt.division as opptDiv, bsOppt.location as opptLocation, bsOppt.result as opptResult, bsOppt.daysOff as opptDaysOff " +
            "FROM game " +
            "INNER JOIN boxScore AS bsTeam ON game.id = bsTeam.gameId " +
            "INNER JOIN team AS team ON team.id = bsTeam.teamId " +
            "INNER JOIN boxScore AS bsOppt ON game.id = bsOppt.gameId " +
            "INNER JOIN team AS oppt ON oppt.id = bsOppt.teamId " +
            "INNER JOIN boxScorePlayer AS bsp on bsTeam.id = bsp.boxScoreId " +
            "INNER JOIN rosterPlayer as rp on bsp.rosterPlayerId = rp.id " +
            "INNER JOIN player as p on p.id = rp.playerId " +
            "WHERE game.gameDateTime between '" + minDateTime + "' AND '" + maxDateTime + "' " +
            "AND game.status = 'Completed' " +
            "AND team.abbr <> oppt.abbr " +
            "ORDER BY game.gameDateTime asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSourceAccumulate());
        reader.setRowMapper(new PlayerBoxScoreMapper());
        return reader;
    }

    @Bean
    public ItemWriter<PlayerBoxScore> writer() {
        String destination = propertyService.getProperty_String("writer.destination");
        System.out.println("made it to the writer for " + destination);
        switch (destination) {
            case "database":
                return jdbcWriter();
            case "file":
                return fileWriter();
            default:
                System.out.println("invalid property value for writer.destination");
                return null;
        }
    }

    private ItemWriter<PlayerBoxScore> fileWriter() {
        FlatFileItemWriter<PlayerBoxScore> flatFileItemWriter = new FlatFileItemWriter<>();
        String path = propertyService.getProperty_Path("writer.extract");
        if (path != null) {
            flatFileItemWriter.setResource(new FileSystemResource(new File(path + "/playerBoxScore.csv")));
            flatFileItemWriter.setShouldDeleteIfExists(true);
            BeanWrapperFieldExtractor<PlayerBoxScore> fieldExtractor = new BeanWrapperFieldExtractor<>();
            String[] fields = new String[]{
                "gameDateTime", "playerLastName", "playerFirstName", "seasonType", "teamAbbr",
                "officialLastName1", "officialFirstName1", "officialLastName2", "officialFirstName2", "officialLastName3", "officialFirstName3",
                "teamConference", "teamDivision", "teamLocation", "teamResult", "teamDaysOff", "displayName", "status", "minutes", "position", "height", "birthplace", "birthdate",
                "points", "assists", "turnovers", "steals", "blocks", "personalFouls", "fieldGoalAttempts", "fieldGoalMade", "fieldGoalPct",
                "twoPointAttempts", "twoPointMade", "twoPointPct", "threePointAttempts", "threePointMade", "threePointPct", "freeThrowAttempts", "freeThrowMade", "freeThrowPct",
                "reboundsOffense", "reboundsDefense", "reboundsTotal", "opptAbbr", "opptConference", "opptDivision", "opptLocation", "opptResult", "opptDaysOff"
            };
            fieldExtractor.setNames(fields);
            DelimitedLineAggregator<PlayerBoxScore> lineAggregator = new DelimitedLineAggregator<>();
            lineAggregator.setFieldExtractor(fieldExtractor);
            flatFileItemWriter.setLineAggregator(lineAggregator);
            return flatFileItemWriter;
        }
        else {
            return null;
        }
    }

    private ItemWriter<PlayerBoxScore> jdbcWriter() {
        JdbcBatchItemWriter<PlayerBoxScore> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(databaseConfig.dataSourceAggregate());
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        String sql =
            "INSERT INTO playerBoxScore " +
            "(" +
                "gameDateTime, playerLastName, playerFirstName, seasonType, teamAbbr, " +
                "officialLastName1, officialFirstName1, officialLastName2, officialFirstName2, officialLastName3, officialFirstName3, " +
                "teamConference, teamDivision, teamLocation, teamResult, teamDaysOff, displayName, status, minutes, position, height, weight, birthplace, birthdate, " +
                "points, assists, turnovers, steals, blocks, personalFouls, fieldGoalAttempts, fieldGoalMade, fieldGoalPct, " +
                "twoPointAttempts, twoPointMade, twoPointPct, threePointAttempts, threePointMade, threePointPct, freeThrowAttempts, freeThrowMade, freeThrowPct, " +
                "reboundsOffense, reboundsDefense, reboundsTotal, opptAbbr, opptConference, opptDivision, opptLocation, opptResult, opptDaysOff " +
            ") " +
            "VALUES " +
            "(" +
                ":gameDateTime, :playerLastName, :playerFirstName, :seasonType, :teamAbbr, " +
                ":officialLastName1, :officialFirstName1, :officialLastName2, :officialFirstName2, :officialLastName3, :officialFirstName3, " +
                ":teamConference, :teamDivision, :teamLocation, :teamResult, :teamDaysOff, :displayName, :status, :minutes, :position, :height, :weight, :birthplace, :birthdate, " +
                ":points, :assists, :turnovers, :steals, :blocks, :personalFouls, :fieldGoalAttempts, :fieldGoalMade, :fieldGoalPct, " +
                ":twoPointAttempts, :twoPointMade, :twoPointPct, :threePointAttempts, :threePointMade, :threePointPct, :freeThrowAttempts, :freeThrowMade, :freeThrowPct, " +
                ":reboundsOffense, :reboundsDefense, :reboundsTotal, :opptAbbr, :opptConference, :opptDivision, :opptLocation, :opptResult, :opptDaysOff " +
            ")";
        jdbcBatchItemWriter.setSql(sql);
        return jdbcBatchItemWriter;
    }
}