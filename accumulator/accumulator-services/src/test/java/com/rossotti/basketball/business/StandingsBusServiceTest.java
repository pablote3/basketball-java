package com.rossotti.basketball.business;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossotti.basketball.app.model.StandingRecord;
import com.rossotti.basketball.app.service.StandingAppService;
import com.rossotti.basketball.business.model.StandingsBusiness;
import com.rossotti.basketball.business.service.StandingBusService;
import com.rossotti.basketball.client.dto.StandingDTO;
import com.rossotti.basketball.client.dto.StandingsDTO;
import com.rossotti.basketball.client.dto.StatusCodeDTO.StatusCode;
import com.rossotti.basketball.client.service.FileStatsService;
import com.rossotti.basketball.client.service.RestStatsService;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.Standing;
import com.rossotti.basketball.jpa.model.Team;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.env.Environment;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class StandingsBusServiceTest {
	@Mock
	private Environment env;

	@Mock
	private FileStatsService fileStatsService;

	@Mock
	private RestStatsService restStatsService;

	@Mock
	private StandingAppService standingAppService;

	@InjectMocks
	private StandingBusService standingsBusinessService;

	private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

	@Test
	public void propertyException() {
		when(env.getProperty(anyString()))
			.thenThrow(new IllegalStateException("property exception"));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isServerError());
	}

	@Test
	public void fileClientService_standingsNotFound() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveStandings(anyString()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.NotFound));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void fileClientService_clientException() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveStandings(anyString()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.ClientException));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void fileClientService_emptyList() {
		when(env.getProperty(anyString()))
			.thenReturn("File");
		when(fileStatsService.retrieveStandings(anyString()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.Found));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void restClientService_standingsNotFound() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.NotFound));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void restClientService_clientException() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.ClientException));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void restClientService_emptyList() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createMockStandingsDTO_StatusCode(StatusCode.Found));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void standingsService_noSuchEntity_team() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createStandingsDTO_Found());
		when(standingAppService.getStandings(any()))
			.thenThrow(new NoSuchEntityException(Team.class));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isClientError());
	}

	@Test
	public void standingsService_createStanding_exists() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createStandingsDTO_Found());
		when(standingAppService.getStandings(any()))
			.thenReturn(createMockStandings());
		when(standingAppService.buildStandingsMap(any(), any()))
			.thenReturn(createMockStandingsMap());
		when(standingAppService.buildHeadToHeadMap(anyString(), any(), any()))
			.thenReturn(createMockHeadToHeadMap());
		when(standingAppService.calculateStrengthOfSchedule(anyString(), any(), any(), any()))
			.thenReturn(new StandingRecord(5, 10, 20, 40));
		when(standingAppService.createStanding(any()))
			.thenReturn(createMockStanding_StatusCode(StatusCodeDAO.Found));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isServerError());
	}

	@Test
	public void standingsService_createStanding_created() {
		when(env.getProperty("accumulator.source.standings"))
			.thenReturn("Api");
		when(env.getProperty("sleep.duration"))
			.thenReturn("0");
		when(restStatsService.retrieveStandings(anyString(), anyBoolean()))
			.thenReturn(createStandingsDTO_Found());
		when(standingAppService.getStandings(any()))
			.thenReturn(createMockStandings());
		when(standingAppService.buildStandingsMap(any(), any()))
			.thenReturn(createMockStandingsMap());
		when(standingAppService.buildHeadToHeadMap(anyString(), any(), any()))
			.thenReturn(createMockHeadToHeadMap());
		when(standingAppService.calculateStrengthOfSchedule(anyString(), any(), any(), any()))
			.thenReturn(new StandingRecord(5, 10, 20, 40));
		when(standingAppService.createStanding(any()))
			.thenReturn(createMockStanding_StatusCode(StatusCodeDAO.Created));
		StandingsBusiness standings = standingsBusinessService.rankStandings("2014-10-28");
		assertTrue(standings.isCompleted());
	}

	private StandingsDTO createStandingsDTO_Found() {
		StandingsDTO standings;
		try {
			InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json");
			standings = objectMapper.readValue(baseJson, StandingsDTO.class);
			standings.setStatusCode(StatusCode.Found);
		}
		catch (IOException e) {
			standings = new StandingsDTO();
			standings.setStatusCode(StatusCode.ClientException);
		}
		return standings;
	}

	private StandingsDTO createMockStandingsDTO_StatusCode(StatusCode statusCode) {
		StandingsDTO standings = new StandingsDTO();
		standings.setStatusCode(statusCode);
		standings.standing = new StandingDTO[0];
		return standings;
	}

	private List<Standing> createMockStandings() {
		List<Standing> standings = new ArrayList<>();
		standings.add(createMockStanding());
		return standings;
	}

	private Standing createMockStanding() {
		Standing standing = new Standing();
		standing.setTeam(createMockTeam());
		standing.setStandingDate(LocalDate.of(2015, 12, 27));
		return standing;
	}

	private Team createMockTeam() {
		Team team = new Team();
		team.setTeamKey("cleveland-cavaliers");
		return team;
	}

	private Map<String, StandingRecord> createMockStandingsMap() {
		Map<String, StandingRecord> standingsMap = new HashMap<>();
		standingsMap.put("cleveland-cavaliers", new StandingRecord(5, 10, 0, 0));
		return standingsMap;
	}

	private Map<String, StandingRecord> createMockHeadToHeadMap() {
		Map<String, StandingRecord> standingsMap = new HashMap<>();
		standingsMap.put("cleveland-cavaliers", new StandingRecord(5, 10, 0, 0));
		return standingsMap;
	}

	private Standing createMockStanding_StatusCode(StatusCodeDAO status) {
		Standing standing = new Standing();
		standing.setStatusCode(status);
		return standing;
	}
}