package com.rossotti.basketball.client.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rossotti.basketball.util.DateTimeConverter;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.jupiter.api.Assertions.*;

public class JacksonMapperTest {

	private final ObjectMapper objectMapper = Jackson2ObjectMapperBuilder.json().build();

	@Test
	public void deserializeRoster() throws IOException {
		InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json");
		RosterDTO roster = objectMapper.readValue(baseJson, RosterDTO.class);
		assertEquals("detroit-pistons", roster.team.getTeam_id());
		assertEquals("Eskişehir, Turkey", roster.players[8].getBirthplace());
		assertEquals("Ersan Ilyasova", roster.players[8].getDisplay_name());
		assertEquals(LocalDate.of(1987, 5, 15), roster.players[8].getBirthdate());
		baseJson.close();
	}

	@Test
	public void deserializeGame() throws IOException {
		InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/gameClient.json");
		GameDTO game = objectMapper.readValue(baseJson, GameDTO.class);
		assertEquals("detroit-pistons", game.away_team.getTeam_id());
		assertEquals(17, game.home_period_scores[1]);
		assertEquals("Bojan Bogdanović", game.home_stats[0].getDisplay_name());
		assertEquals(0f, game.home_stats[0].getFree_throw_percentage(), 0.0f);
		assertEquals("Zarba", game.officials[0].getLast_name());
		assertEquals("completed", game.event_information.getStatus());
		assertEquals(LocalDateTime.of(2015, 11, 29, 18, 0), DateTimeConverter.getLocalDateTime(game.event_information.getStart_date_time()));
		assertEquals((short)24, (short)game.away_totals.getThree_point_field_goals_attempted());
		baseJson.close();
	}

	@Test
	public void deserializeStandings() throws IOException {
		InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/standingsClient.json");
		StandingsDTO standings = objectMapper.readValue(baseJson, StandingsDTO.class);
		assertEquals(LocalDateTime.of(2016, 2, 11, 22, 19), LocalDateTime.ofInstant(standings.standings_date.toInstant(), ZoneId.of("US/Eastern")));
		assertEquals(30, standings.standing.length);
		assertEquals("W3", standings.standing[0].getStreak());
		assertEquals("toronto-raptors", standings.standing[1].getTeam_id());
		baseJson.close();
	}
}