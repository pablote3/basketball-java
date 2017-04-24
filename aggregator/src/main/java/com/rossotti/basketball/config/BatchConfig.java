package com.rossotti.basketball.config;

import com.rossotti.basketball.mapper.TeamReaderRowMapper;
import com.rossotti.basketball.model.Team;
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
                .<Team, Team>chunk(1)
                .reader(teamReader())
                .processor(teamProcessor())
                .writer(teamWriter())
                .build();
    }

    @Bean
    public JdbcCursorItemReader<Team> teamReader() {
        JdbcCursorItemReader<Team> reader = new JdbcCursorItemReader<>();
        //LocalDate gameDate = LocalDate.now().minusDays(1);
        LocalDate gameDate = LocalDate.of(2017, 3, 30);
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
        reader.setDataSource(databaseConfig.dataSource());
        reader.setRowMapper(new TeamReaderRowMapper());
        return reader;
    }

    @Bean
    public TeamProcessor teamProcessor() {


        return new TeamProcessor();
    }

    @Bean
    public FlatFileItemWriter<Team> teamWriter() {
        FlatFileItemWriter<Team> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource(new File("target/paulOut.txt")));
        writer.setShouldDeleteIfExists(true);
        BeanWrapperFieldExtractor<Team> fieldExtractor = new BeanWrapperFieldExtractor<>();
        fieldExtractor.setNames(new String[]{"gameDateTime", "teamKey", "status"});
        DelimitedLineAggregator<Team> lineAggregator = new DelimitedLineAggregator<>();
        lineAggregator.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(lineAggregator);
        return writer;
    }
}