package com.rossotti.basketball.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import javax.sql.DataSource;

@Configuration
public class DatabaseConfig {

    private final Environment env;

    @Autowired
    public DatabaseConfig(Environment env) {
        this.env = env;
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
}