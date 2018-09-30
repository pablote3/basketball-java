package com.rossotti.basketball.app;

import com.rossotti.basketball.app.service.PlayerAppService;
import com.rossotti.basketball.jpa.exception.DuplicateEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.Player;
import com.rossotti.basketball.jpa.service.PlayerJpaService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PlayerAppServiceTest {
	@Mock
	private PlayerJpaService playerJpaService;

	@InjectMocks
	private PlayerAppService playerAppService;

	@Test
	public void findByPlayerNameBirthdate_notFound() {
		when(playerJpaService.findByLastNameAndFirstNameAndBirthdate(anyString(), anyString(), any()))
			.thenReturn(createMockPlayer("Simmons", "Richard", StatusCodeDAO.NotFound));
		Player player = playerAppService.findByPlayerNameBirthdate("Simmons", "Richard", LocalDate.of(1995, 11, 26));
		Assert.assertTrue(player.isNotFound());
	}

	@Test
	public void findByPlayerNameBirthdate_found() {
		when(playerJpaService.findByLastNameAndFirstNameAndBirthdate(anyString(), anyString(), any()))
			.thenReturn(createMockPlayer("Adams", "Samuel", StatusCodeDAO.Found));
		Player player = playerAppService.findByPlayerNameBirthdate("Adams", "Samuel", LocalDate.of(1995, 11, 26));
		Assert.assertEquals("Samuel", player.getFirstName());
		Assert.assertTrue(player.isFound());
	}

	@Test(expected=DuplicateEntityException.class)
	public void createPlayer_alreadyExists() {
		when(playerJpaService.create(any()))
			.thenThrow(new DuplicateEntityException(Player.class));
		Player player = playerAppService.createPlayer(createMockPlayer("Smith", "Emmitt", StatusCodeDAO.Found));
		Assert.assertTrue(player.isNotFound());
	}

	@Test
	public void createPlayer_created() {
		when(playerJpaService.create(any()))
			.thenReturn(createMockPlayer("Payton", "Walter", StatusCodeDAO.Created));
		Player player = playerAppService.createPlayer(createMockPlayer("Payton", "Walter", StatusCodeDAO.Created));
		Assert.assertEquals("Walter", player.getFirstName());
		Assert.assertTrue(player.isCreated());
	}

	private Player createMockPlayer(String lastName, String firstName, StatusCodeDAO statusCode) {
		Player player = new Player();
		player.setLastName(lastName);
		player.setFirstName(firstName);
		player.setBirthdate(LocalDate.of(1995, 11, 26));
		player.setStatusCode(statusCode);
		return player;
	}
}