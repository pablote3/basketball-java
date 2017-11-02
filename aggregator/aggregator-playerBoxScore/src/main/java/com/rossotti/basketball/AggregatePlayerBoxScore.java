package com.rossotti.basketball;

import com.rossotti.basketball.batch.PlayerBoxScore;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AggregatePlayerBoxScore {
    public static void main(String[] args) {
        SpringApplication.run(PlayerBoxScore.class, args);
    }
}