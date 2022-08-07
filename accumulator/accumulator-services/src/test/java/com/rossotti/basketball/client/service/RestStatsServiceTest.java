package com.rossotti.basketball.client.service;

import com.rossotti.basketball.client.dto.GameDTO;
import com.rossotti.basketball.client.dto.RosterDTO;
import com.rossotti.basketball.client.dto.StandingsDTO;
import com.rossotti.basketball.util.FileService;
import com.rossotti.basketball.util.FileServiceException;
import com.rossotti.basketball.util.StreamConverter;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class RestStatsServiceTest {
	@Mock
	private Environment env;

	@Mock
	private RestClientService restClientService;

	@Mock
	private FileService fileService;

	@InjectMocks
	private RestStatsService restStatsService;

	@Test
	public void retrieveBoxScore_PropertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_PropertyException_BoxScore() {
		when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_NotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isNotFound());
	}

	@Test
	public void retrieveBoxScore_Unauthorized() {
		when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isNotFound());
	}

	@Test
	public void retrieveBoxScore_IOException() {
		when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>("test".getBytes(), HttpStatus.OK));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_Found_NoPersist() {
		when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json")), HttpStatus.OK));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", false);
		assertTrue(game.isFound());
	}

	@Test
	public void retrieveBoxScore_PropertyException_Persist() {
		when(env.getProperty("xmlstats.urlBoxScore"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json")), HttpStatus.OK));
		when(env.getProperty("xmlstats.fileBoxScore"))
			.thenThrow(new IllegalStateException("property exception"));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", true);
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_FileException_Persist() {
		when(env.getProperty("xmlstats.urlBoxScore"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json")), HttpStatus.OK));
		when(env.getProperty("xmlstats.fileBoxScore"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenThrow(new FileServiceException("IO Exception"));
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", true);
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_Found_Persist() {
		when(env.getProperty("xmlstats.urlBoxScore"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json")), HttpStatus.OK));
		when(env.getProperty("xmlstats.fileBoxScore"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenReturn(true);
		GameDTO game = restStatsService.retrieveBoxScore("20160311-houston-rockets-at-boston-celtics", true);
		assertTrue(game.isFound());
	}

	@Test
	public void retrieveRoster_PropertyException_Roster() {
        when(env.getProperty(anyString()))
            .thenThrow(new IllegalStateException("property exception"));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_PropertyException_ClientService() {
        when(env.getProperty(anyString()))
            .thenReturn("https://");
		when(restClientService.getJson(anyString()))
            .thenThrow(new IllegalStateException("property exception"));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_NotFound() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isNotFound());
	}

	@Test
	public void retrieveRoster_Unauthorized() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isNotFound());
	}

	@Test
	public void retrieveRoster_IOException() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>("test".getBytes(), HttpStatus.OK));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_Found_NoPersist() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json")), HttpStatus.OK));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", false, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isFound());
	}

	@Test
	public void retrieveRoster_PropertyException_Persist() {
        when(env.getProperty("xmlstats.urlRoster"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileRoster"))
            .thenThrow(new IllegalStateException("property exception"));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", true, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_FileException_Persist() {
        when(env.getProperty("xmlstats.urlRoster"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileRoster"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenThrow(new FileServiceException("IO Exception"));
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", true, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_Found_Persist() {
        when(env.getProperty("xmlstats.urlRoster"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileRoster"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenReturn(true);
		RosterDTO roster = restStatsService.retrieveRoster("houston-rockets", true, LocalDate.of(2016, 3, 11));
		assertTrue(roster.isFound());
	}

	@Test
	public void retrieveStandings_PropertyException_Standings() {
        when(env.getProperty(anyString()))
            .thenThrow(new IllegalStateException("property exception"));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_PropertyException_ClientService() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
            .thenThrow(new IllegalStateException("property exception"));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_NotFound() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.NOT_FOUND));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isNotFound());
	}

	@Test
	public void retrieveStandings_Unauthorized() {
        when(env.getProperty(anyString()))
            .thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(HttpStatus.UNAUTHORIZED));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isNotFound());
	}

	@Test
	public void retrieveStandings_IOException() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>("test".getBytes(), HttpStatus.OK));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_Found_NoPersist() {
        when(env.getProperty(anyString()))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json")), HttpStatus.OK));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", false);
		assertTrue(standings.isFound());
	}

	@Test
	public void retrieveStandings_PropertyException_Persist() {
        when(env.getProperty("xmlstats.urlStandings"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileStandings"))
            .thenThrow(new IllegalStateException("property exception"));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", true);
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_FileException_Persist() {
        when(env.getProperty("xmlstats.urlStandings"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileStandings"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenThrow(new FileServiceException("IO Exception"));
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", true);
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_Found_Persist() {
        when(env.getProperty("xmlstats.urlStandings"))
			.thenReturn("https://");
		when(restClientService.getJson(anyString()))
			.thenReturn(new ResponseEntity<>(StreamConverter.getBytes(getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json")), HttpStatus.OK));
        when(env.getProperty("xmlstats.fileStandings"))
			.thenReturn("//");
		when(fileService.fileStreamWriter(anyString(), any(byte[].class)))
			.thenReturn(true);
		StandingsDTO standings = restStatsService.retrieveStandings("20160311", true);
		assertTrue(standings.isFound());
	}
}