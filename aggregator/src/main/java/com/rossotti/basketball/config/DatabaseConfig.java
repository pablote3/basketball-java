package com.rossotti.basketball.config;

import org.springframework.batch.core.configuration.annotation.BatchConfigurer;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import javax.sql.DataSource;

@Configuration
@EnableTransactionManagement
public class DatabaseConfig {

    private final Environment env;

    @Autowired
    public DatabaseConfig(Environment env) {
        this.env = env;
    }

    @Primary
    @Bean
    DataSource dataSourceAccumulate() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getRequiredProperty("db.accumulate.driver"));
        dataSourceBuilder.url(env.getRequiredProperty("db.accumulate.url"));
        dataSourceBuilder.username(env.getRequiredProperty("db.accumulate.username"));
        dataSourceBuilder.password(env.getRequiredProperty("db.accumulate.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    DataSource dataSourceAggregate() {
        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName(env.getRequiredProperty("db.aggregate.driver"));
        dataSourceBuilder.url(env.getRequiredProperty("db.aggregate.url"));
        dataSourceBuilder.username(env.getRequiredProperty("db.aggregate.username"));
        dataSourceBuilder.password(env.getRequiredProperty("db.aggregate.password"));
        return dataSourceBuilder.build();
    }

    @Bean
    BatchConfigurer configurer (DataSource dataSource) {
        return new DefaultBatchConfigurer(dataSource);
    }
}