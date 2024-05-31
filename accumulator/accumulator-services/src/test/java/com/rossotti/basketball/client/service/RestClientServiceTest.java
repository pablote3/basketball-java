package com.rossotti.basketball.client.service;

import com.rossotti.basketball.client.dto.GameDTO;
import com.rossotti.basketball.client.dto.RosterDTO;
import com.rossotti.basketball.client.dto.StandingsDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO.StatusCode;
import com.rossotti.basketball.util.ThreadSleep;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class RestClientServiceTest {

	@Autowired
	private RestStatsService restStatsService;

	@Autowired
	private RestClientService restClientService;

	@Disabled
	@Test
	public void retrieveRoster_200() {
		String event = "toronto-raptors";
		RosterDTO roster = restStatsService.retrieveRoster(event, false, LocalDate.of(2016, 12, 15));
		assertEquals(StatusCode.Found, roster.getStatusCode());
		assertEquals(15, roster.players.length);
	}

	@Disabled
	@Test
	public void retrieveStandings_200() {
		String event = "20141028";
		StandingsDTO standings = restStatsService.retrieveStandings(event, false);
		assertEquals(StatusCode.Found, standings.getStatusCode());
		assertEquals(30, standings.standing.length);
	}

	@Disabled
	@Test
	public void retrieveBoxScore_200() {
		String event = "20150415-utah-jazz-at-houston-rockets";
		GameDTO game = restStatsService.retrieveBoxScore(event, false);
		assertEquals(StatusCode.Found, game.getStatusCode());
		assertEquals(3, game.officials.length);
	}

	@Disabled
	@Test
	public void retrieveRoster_401() {
		String accessToken = "badToken";
		String userAgent = "validUserAgent";
		String rosterUrl = "https://erikberg.com/nba/roster/toronto-raptors.json";
		int status = restClientService.getJson(rosterUrl).getStatusCode().value();
		assertEquals(401, status);
	}

	@Disabled
	@Test
	public void retrieveRoster_404() {
		String accessToken = "validAccessToken";
		String userAgent = "validUserAgent";
		String badUrl = "https://erikberg.com/nba/roster/toronto-raps.json";
		int status = restClientService.getJson(badUrl).getStatusCode().value();
		assertEquals(404, status);
	}

	@Disabled
	@Test
	public void retrieveRoster_403() {
		//could cause ban of IP
		String accessToken = "validAccessToken";
		String userAgent = "badUserAgent";
		String rosterUrl = "https://erikberg.com/nba/roster/toronto-raptors.json";
		int status = restClientService.getJson(rosterUrl).getStatusCode().value();
		assertEquals(403, status);
	}

	@Disabled
	@Test
	public void retrieveRoster_429() {
		//sending more than 6 requests in a minute is counted against account
		String accessToken = "validAccessToken";
		String userAgent = "validUserAgent";
		String rosterUrl = "https://erikberg.com/nba/roster/toronto-raptors.json";
		int status200 = restClientService.getJson(rosterUrl).getStatusCode().value();
		assertEquals(200, status200);
		ThreadSleep.sleep(1);
		int status429 = restClientService.getJson(rosterUrl).getStatusCode().value();
		assertEquals(429, status429);
	}
}