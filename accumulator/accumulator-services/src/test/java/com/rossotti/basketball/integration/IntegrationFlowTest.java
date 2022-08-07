package com.rossotti.basketball.integration;

import com.rossotti.basketball.config.IntegrationConfig;
import com.rossotti.basketball.jpa.model.Game;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ContextConfiguration(classes={IntegrationConfig.class})
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class IntegrationFlowTest {

    private GatewayService gatewayService;

    @Autowired
    public void setGatewayService(GatewayService gatewayService) {
        this.gatewayService = gatewayService;
    }

    @Test
    public void testFlow_GameNotFound() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2016-10-27");
        serviceProperties.setGameTeam("chicago-zephyr's");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(0, games.size());
    }

    @Test
    public void testFlow_AsOfDateTeam_Completed() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2015-10-27");
        serviceProperties.setGameTeam("chicago-zephyr's");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(1, games.size());
    }

    @Test
    public void testFlow_AsOfDateTeam_Scheduled() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2016-10-27");
        serviceProperties.setGameTeam("st-louis-bomber's");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(1, games.size());
    }

    @Test
    public void testFlow_AsOfDate_Mixed_Multiple() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2016-10-28");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(2, games.size());
    }

    @Test
    public void testFlow_AsOfDateTeam_Roster_OneTeam() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2016-10-29");
        serviceProperties.setGameTeam("st-louis-bomber's");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(1, games.size());
    }

    @Test
    public void testFlow_AsOfDateTeam_Roster_TwoTeam() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setGameDate("2016-10-30");
        serviceProperties.setGameTeam("detroit-pistons");
        List<Game> games = gatewayService.processGames(serviceProperties);
        assertEquals(1, games.size());
    }
}