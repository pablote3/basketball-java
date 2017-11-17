package com.rossotti.basketball.batch;

import com.rossotti.basketball.config.DatabaseConfig;
import com.rossotti.basketball.util.DateTimeConverter;
import com.rossotti.basketball.util.PropertyService;
import com.rossotti.basketball.util.StringHeaderWriter;
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
public class StandingConfig {

    private final PropertyService propertyService;

    private final DatabaseConfig databaseConfig;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public StandingConfig(PropertyService propertyService, DatabaseConfig databaseConfig, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.propertyService = propertyService;
        this.databaseConfig = databaseConfig;
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job aggregateStandingJob() {
        return jobBuilderFactory.get("aggregateStandingJob")
            .incrementer(new RunIdIncrementer())
            .flow(stepStanding())
            .end()
            .build();
    }

    @Bean
    public Step stepStanding() {
        return stepBuilderFactory.get("stepStanding")
            .<Standing, Standing>chunk(20)
            .reader(reader())
            .processor(standingProcessor())
            .writer(writer())
            .build();
    }

    @Bean
    public StandingProcessor standingProcessor() {
        return new StandingProcessor();
    }

    @SuppressWarnings("unchecked")
    @Bean
    public JdbcCursorItemReader<Standing> reader() {
        String fromDate = null;
        String toDate = null;

        if (System.getProperty("fromDate") != null) {
            String fromDateProp = System.getProperty("fromDate");
            if (fromDateProp.isEmpty()) {
                fromDate = DateTimeConverter.getStringDate(LocalDate.now().minusDays(1));
            }
            else {
                if (DateTimeConverter.isDate(fromDateProp)) {
                    fromDate = fromDateProp;
                }
                else {
                    System.out.println("Invalid fromDate argument");
                    System.exit(1);
                }
            }
        }
        else {
            System.out.println("Argument fromDate not supplied - assumed to be unit test execution");
            fromDate = "2016-10-26";
        }

        if (System.getProperty("toDate") != null) {
            String toDateProp = System.getProperty("toDate");
            if (toDateProp.isEmpty()) {
                toDate = DateTimeConverter.getStringDate(LocalDate.now().minusDays(1));
            }
            else {
                if (DateTimeConverter.isDate(toDateProp)) {
                    toDate = toDateProp;
                }
                else {
                    System.out.println("Invalid toDate argument");
                    System.exit(1);
                }
            }
        }
        else {
            System.out.println("Argument toDate not supplied - assumed to be unit test execution");
            toDate = "2016-10-26";
        }

        JdbcCursorItemReader<Standing> reader = new JdbcCursorItemReader<>();
        String sql =
            "SELECT " +
                "standing.standingDate, team.abbr, " +
                "standing.rank, standing.ordinalRank, standing.gamesWon, standing.gamesLost, standing.streak, standing.streakType, standing.streakTotal, standing.gamesBack, " +
                "standing.pointsFor, standing.pointsAgainst, standing.homeWins, standing.homeLosses, standing.awayWins, standing.awayLosses, standing.conferenceWins, standing.conferenceLosses, " +
                "standing.lastFive, standing.lastTen, standing.gamesPlayed,standing.pointsScoredPerGame, standing.pointsAllowedPerGame, standing.pointDifferentialPerGame, " +
                "standing.opptGamesPlayed, standing.opptGamesWon, standing.opptOpptGamesPlayed, standing.opptOpptGamesWon " +
            "FROM standing AS standing " +
            "INNER JOIN team AS team ON team.id = standing.teamId " +
            "WHERE standing.standingDate BETWEEN '" + fromDate + "' AND '" + toDate + "' " +
            "ORDER BY standing.standingDate, team.abbr asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSourceAccumulate());
        reader.setRowMapper(new StandingMapper());
        return reader;
    }

    @Bean
    public ItemWriter<Standing> writer() {
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

    private ItemWriter<Standing> fileWriter() {
        FlatFileItemWriter<Standing> flatFileItemWriter = new FlatFileItemWriter<>();
        String path = propertyService.getProperty_Path("writer.extract");
        if (path != null) {
            String exportHeaderWriter = "stDate,teamAbbr,rank,rankOrd,gameWon,gameLost,stk,stkType,stkTot,gameBack,ptsFor,ptsAgnst,homeWin,homeLoss,awayWin,awayLoss,confWin,confLoss," +
                                        "lastFive,lastTen,gamePlay,ptsScore,ptsAllow,ptsDiff,opptGmPlay,opptGmWon,opptOpptGmPlay,opptOpptGmWon,sos,rel%Indx,mov,srs,pw%," +
                                        "pyth%13.91,wpyth13.91,lpyth13.91,pyth%16.5,wpyth16.5,lpyth16.5";
            StringHeaderWriter headerWriter = new StringHeaderWriter(exportHeaderWriter);
            flatFileItemWriter.setHeaderCallback(headerWriter);

            flatFileItemWriter.setResource(new FileSystemResource(new File(path + "/standings.csv")));
            flatFileItemWriter.setShouldDeleteIfExists(true);

            BeanWrapperFieldExtractor<Standing> fieldExtractor = new BeanWrapperFieldExtractor<>();
            String[] fields = new String[]{
                "standingDate", "teamAbbr", "rank", "ordinalRank", "gamesWon", "gamesLost", "streak", "streakType", "streakTotal", "gamesBack", "pointsFor",
                "pointsAgainst", "homeWins", "homeLosses", "awayWins", "awayLosses", "conferenceWins", "conferenceLosses", "lastFive", "lastTen", "gamesPlayed",
                "pointsScoredPerGame", "pointsAllowedPerGame", "pointDifferentialPerGame", "opptGamesPlayed", "opptGamesWon", "opptOpptGamesPlayed", "opptOpptGamesWon",
                "strengthOfSchedule", "relativePercentageIndex", "marginOfVictory", "simpleRatingSystem", "projectedWinningPct", "pythagoreanWinningPct_13_91",
                "pythagoreanWins_13_91", "pythagoreanLosses_13_91", "pythagoreanWinningPct_16_5", "pythagoreanWins_16_5", "pythagoreanLosses_16_5"

            };
            fieldExtractor.setNames(fields);
            DelimitedLineAggregator<Standing> lineAggregator = new DelimitedLineAggregator<>();
            lineAggregator.setFieldExtractor(fieldExtractor);
            flatFileItemWriter.setLineAggregator(lineAggregator);
            return flatFileItemWriter;
        }
        else {
            return null;
        }
    }

    private ItemWriter<Standing> jdbcWriter() {
        JdbcBatchItemWriter<Standing> jdbcBatchItemWriter = new JdbcBatchItemWriter<>();
        jdbcBatchItemWriter.setDataSource(databaseConfig.dataSourceAggregate());
        jdbcBatchItemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());

        String sql =
            "INSERT INTO standing " +
            "(" +
                "standingDate, teamAbbr, rank, ordinalRank, gamesWon, gamesLost, streak, streakType, streakTotal, gamesBack, pointsFor, " +
                "pointsAgainst, homeWins, homeLosses, awayWins, awayLosses, conferenceWins, conferenceLosses, lastFive, lastTen, gamesPlayed, " +
                "pointsScoredPerGame, pointsAllowedPerGame, pointDifferentialPerGame, opptGamesPlayed, opptGamesWon, opptOpptGamesPlayed, " +
                "opptOpptGamesWon, strengthOfSchedule, relativePercentageIndex, marginOfVictory, simpleRatingSystem, projectedWinningPct" +
            ") " +
            "VALUES " +
            "(" +
                ":standingDate, :teamAbbr, :rank, :ordinalRank, :gamesWon, :gamesLost, :streak, :streakType, :streakTotal, :gamesBack, :pointsFor, " +
                ":pointsAgainst, :homeWins, :homeLosses, :awayWins, :awayLosses, :conferenceWins, :conferenceLosses, :lastFive, :lastTen, :gamesPlayed, " +
                ":pointsScoredPerGame, :pointsAllowedPerGame, :pointDifferentialPerGame, :opptGamesPlayed, :opptGamesWon, :opptOpptGamesPlayed, " +
                ":opptOpptGamesWon, :strengthOfSchedule, :relativePercentageIndex, :marginOfVictory, :simpleRatingSystem, :projectedWinningPct" +
            ")";
        jdbcBatchItemWriter.setSql(sql);
        return jdbcBatchItemWriter;
    }
}