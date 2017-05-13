package com.rossotti.basketball.config;

import com.rossotti.basketball.model.Standing;
import com.rossotti.basketball.model.TeamBoxScore;
import com.rossotti.basketball.processor.StandingProcessor;
import com.rossotti.basketball.processor.TeamBoxScoreProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final StandingConfig standingConfig;

    private final TeamBoxScoreConfig teamBoxScoreConfig;

    private final JobBuilderFactory jobBuilderFactory;

    private final StepBuilderFactory stepBuilderFactory;

    @Autowired
    public BatchConfig(StandingConfig standingConfig, TeamBoxScoreConfig teamBoxScoreConfig, JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory) {
        this.standingConfig = standingConfig;
        this.teamBoxScoreConfig = teamBoxScoreConfig;
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
            .reader(teamBoxScoreConfig.reader())
            .processor(teamBoxScoreProcessor())
            .writer(teamBoxScoreConfig.jdbcWriter())
            .build();
    }

    @Bean
    public Step stepStanding() {
        return stepBuilderFactory.get("stepStanding")
            .<Standing, Standing>chunk(20)
            .reader(standingConfig.reader())
            .processor(standingProcessor())
            .writer(standingConfig.jdbcWriter())
            .build();
    }

    @Bean
    public TeamBoxScoreProcessor teamBoxScoreProcessor() {
        return new TeamBoxScoreProcessor();
    }

    @Bean
    public StandingProcessor standingProcessor() {
        return new StandingProcessor();
    }
}