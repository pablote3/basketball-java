package com.rossotti.basketball.client.service;

import com.rossotti.basketball.client.dto.GameDTO;
import com.rossotti.basketball.client.dto.RosterDTO;
import com.rossotti.basketball.client.dto.StandingsDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO.StatusCode;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.core.env.Environment;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class FileStatsServiceTest {
	@Mock
	private Environment env;

	@Mock
	private FileClientService fileClientService;

	@InjectMocks
	private FileStatsService fileStatsService;

	@Test
	public void retrieveBoxScore_PropertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		GameDTO game = fileStatsService.retrieveBoxScore("20150415-utah-jazz-at-houston-rockets");
		assertTrue(game.isServerException());
	}

	@Test
	public void retrieveBoxScore_NotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new GameDTO(), StatusCode.NotFound));
		GameDTO game = fileStatsService.retrieveBoxScore("20150415-utah-jazz-at-houston-rockets");
		assertTrue(game.isNotFound());
	}

	@Test
	public void retrieveBoxScore_ClientException() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new GameDTO(), StatusCode.ClientException));
		GameDTO game = fileStatsService.retrieveBoxScore("20150415-utah-jazz-at-houston-rockets");
		assertTrue(game.isClientException());
	}

	@Test
	public void retrieveBoxScore_Found() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new GameDTO(), StatusCode.Found));
		GameDTO game = fileStatsService.retrieveBoxScore("20150415-utah-jazz-at-houston-rockets");
		assertTrue(game.isFound());
	}

	@Test
	public void retrieveRoster_PropertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		RosterDTO roster = fileStatsService.retrieveRoster("toronto-raptors", LocalDate.of(2015, 4, 15));
		assertTrue(roster.isServerException());
	}

	@Test
	public void retrieveRoster_NotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new RosterDTO(), StatusCode.NotFound));
		RosterDTO roster = fileStatsService.retrieveRoster("toronto-raptors-20150415", LocalDate.of(2015, 4, 15));
		assertTrue(roster.isNotFound());
	}

	@Test
	public void retrieveRoster_ClientException() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new RosterDTO(), StatusCode.ClientException));
		RosterDTO roster = fileStatsService.retrieveRoster("toronto-raptors", LocalDate.of(2015, 4, 15));
		assertTrue(roster.isClientException());
	}

	@Test
	public void retrieveRoster_Found() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new RosterDTO(), StatusCode.Found));
		RosterDTO roster = fileStatsService.retrieveRoster("toronto-raptors", LocalDate.of(2015, 4, 15));
		assertTrue(roster.isFound());
	}

	@Test
	public void retrieveStandings_PropertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		StandingsDTO standings = fileStatsService.retrieveStandings("20141028");
		assertTrue(standings.isServerException());
	}

	@Test
	public void retrieveStandings_NotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new StandingsDTO(), StatusCode.NotFound));
		StandingsDTO standings = fileStatsService.retrieveStandings("20141028");
		assertTrue(standings.isNotFound());
	}

	@Test
	public void retrieveStandings_ClientException() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new StandingsDTO(), StatusCode.ClientException));
		StandingsDTO standings = fileStatsService.retrieveStandings("20141028");
		assertTrue(standings.isClientException());
	}

	@Test
	public void retrieveStandings_Found() {
		when(env.getProperty(anyString()))
			.thenReturn("/home/pablote/");
		when(fileClientService.retrieveStats(anyString(), anyString(), any()))
			.thenReturn(createMockStatsDTO(new StandingsDTO(), StatusCode.Found));
		StandingsDTO standings = fileStatsService.retrieveStandings("20141028");
		assertTrue(standings.isFound());
	}

	private StatusCodeDTO createMockStatsDTO(StatusCodeDTO statusCodeDTO, StatusCode statusCode) {
		statusCodeDTO.setStatusCode(statusCode);
		return statusCodeDTO;
	}
}