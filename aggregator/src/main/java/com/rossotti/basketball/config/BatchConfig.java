package com.rossotti.basketball.config;

import com.rossotti.basketball.mapper.TeamBoxScoreMapper;
import com.rossotti.basketball.model.TeamBoxScore;
import com.rossotti.basketball.processor.TeamProcessor;
import com.rossotti.basketball.util.DateTimeConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
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
                .flow(stepTeam())
//            .next(stepPlayer())
                .end()
                .build();
    }

    @Bean
    public Step stepTeam() {
        return stepBuilderFactory.get("stepTeam")
                .<TeamBoxScore, TeamBoxScore>chunk(1)
                .reader(teamBoxScoreReader())
                .processor(teamProcessor())
                .writer(teamFileWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<TeamBoxScore> teamBoxScoreReader() {
        JdbcCursorItemReader<TeamBoxScore> reader = new JdbcCursorItemReader<>();
        //LocalDate gameDate = LocalDate.now().minusDays(1);
        LocalDate gameDate = LocalDate.of(2017, 3, 30);
        String minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(gameDate));
        String maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(gameDate));
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
            "AND bsTeam.location = 'Home' " +
            "AND game.status = 'Completed' " +
            "AND team.abbr <> oppt.abbr " +
            "ORDER BY game.gameDateTime asc";

        System.out.println("sql = " +sql);

        reader.setSql(sql);
        reader.setDataSource(databaseConfig.dataSource());
        reader.setRowMapper(new TeamBoxScoreMapper());
        return reader;
    }

    @Bean
    public TeamProcessor teamProcessor() {
        return new TeamProcessor();
    }

    @Bean
    public FlatFileItemWriter<TeamBoxScore> teamFileWriter() {
        FlatFileItemWriter<TeamBoxScore> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(new File("/home/pablote/pdrive/pwork/basketball/aggregator/extracts/paulOut.txt")));
        writer.setShouldDeleteIfExists(true);
        BeanWrapperFieldExtractor<TeamBoxScore> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"gameDateTime", "seasonType", "teamAbbr", "teamConference", "teamDivision"});
        DelimitedLineAggregator<TeamBoxScore> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);
        return writer;
    }
}