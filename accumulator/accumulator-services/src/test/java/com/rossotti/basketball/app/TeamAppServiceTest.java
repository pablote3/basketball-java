package com.rossotti.basketball.app;

import com.rossotti.basketball.app.service.TeamAppService;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.Team;
import com.rossotti.basketball.jpa.service.TeamJpaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class TeamAppServiceTest {
	@Mock
	private TeamJpaService teamJpaService;

	@InjectMocks
	private TeamAppService teamAppService;

	@Test
	public void findTeamByTeamKey_notFound() {
		assertThrows(NoSuchEntityException.class,
			()->{
				when(teamJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
					.thenReturn(createMockTeam("new-orleans-hornets", "Hornets", StatusCodeDAO.NotFound));
				Team team = teamAppService.findTeamByTeamKey("new-orleans-hornets", LocalDate.of(2015, 11, 26));
				assertTrue(team.isNotFound());
			});
	}

	@Test
	public void findTeamByTeamKey_found() {
		when(teamJpaService.findByTeamKeyAndAsOfDate(anyString(), any()))
			.thenReturn(createMockTeam("denver-nuggets", "Nuggets", StatusCodeDAO.Found));
		Team team = teamAppService.findTeamByTeamKey("denver-nuggets", LocalDate.of(2015, 11, 26));
		assertEquals("denver-nuggets", team.getTeamKey());
		assertTrue(team.isFound());
	}

	@Test
	public void findTeamByLastName_notFound() {
		assertThrows(NoSuchEntityException.class,
			()->{
				when(teamJpaService.findByLastNameAndAsOfDate(anyString(), any()))
					.thenReturn(createMockTeam("new-orleans-hornets", "Hornets", StatusCodeDAO.NotFound));
				Team team = teamAppService.findTeamByLastName("Hornets", LocalDate.of(2015, 11, 26));
				assertTrue(team.isNotFound());
			});
	}

	@Test
	public void findTeamByLastName_found() {
		when(teamJpaService.findByLastNameAndAsOfDate(anyString(), any()))
			.thenReturn(createMockTeam("denver-nuggets", "Nuggets", StatusCodeDAO.Found));
		Team team = teamAppService.findTeamByLastName("Hornets", LocalDate.of(2015, 11, 26));
		assertEquals("Nuggets", team.getLastName());
		assertTrue(team.isFound());
	}

	private Team createMockTeam(String teamKey, String lastName, StatusCodeDAO statusCode) {
		Team team = new Team();
		team.setTeamKey(teamKey);
		team.setLastName(lastName);
		team.setFromDate(LocalDate.of(2015, 11, 26));
		team.setToDate(LocalDate.of(2016, 11, 26));
		team.setStatusCode(statusCode);
		return team;
	}
}