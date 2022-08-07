package com.rossotti.basketball.jpa.service;

import com.rossotti.basketball.jpa.model.Player;
import com.rossotti.basketball.jpa.repository.PlayerRepositoryTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class PlayerJpaServiceTest {

	private PlayerJpaService playerJpaService;

	@Autowired
	public void setPlayerJpaService(PlayerJpaService playerJpaService) {
		this.playerJpaService = playerJpaService;
	}

	@Test
	public void getById() {
		Player player = playerJpaService.getById(1L);
		assertEquals("Puzdrakiew'icz", player.getLastName());
		assertEquals(3, player.getRosterPlayers().size());
	}

	@Test
	public void listAll() {
		List<Player> players = (List<Player>) playerJpaService.listAll();
		assertTrue(players.size() >= 14);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_Found() {
		Player player = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 20));
		assertEquals("Sacramento, CA, USA", player.getBirthplace());
		assertTrue(player.isFound());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_Found_UTF_8() {
		Player findPlayer = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Valančiūnas", "Jonas", LocalDate.of(1992, 5, 6));
		assertEquals("Jonas Valančiūnas", findPlayer.getDisplayName());
		assertEquals("Utėnai, Lithuania", findPlayer.getBirthplace());
		assertTrue(findPlayer.isFound());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_LastName() {
		Player player = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew''icz", "Luke", LocalDate.of(2002, 2, 20));
		assertTrue(player.isNotFound());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_FirstName() {
		Player player = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Like", LocalDate.of(2002, 2, 20));
		assertTrue(player.isNotFound());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_Birthdate() {
		Player player = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 21));
		assertTrue(player.isNotFound());
	}

	@Test
	public void findByAsLastNameFirstName_Found() {
		List<Player> players = playerJpaService.findByLastNameAndFirstName("Puzdrakiewicz", "Thad");
		assertEquals(2, players.size());
	}

	@Test
	public void findByLastNameFirstName_NotFound_LastName() {
		List<Player> players = playerJpaService.findByLastNameAndFirstName("Puzdrakiewiczy", "Thad");
		assertEquals(0, players.size());
	}

	@Test
	public void findByLastNameFirstName_NotFound_FirstName() {
		List<Player> players = playerJpaService.findByLastNameAndFirstName("Puzdrakiewicz", "Thady");
		assertEquals(0, players.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		Player createPlayer = playerJpaService.create(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Fred", LocalDate.of(1968, 11, 9), "Fred Puzdrakiewicz"));
		Player findPlayer = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiewicz", "Fred", LocalDate.of(1968, 11, 9));
		assertTrue(createPlayer.isCreated());
		assertEquals("Fred Puzdrakiewicz", findPlayer.getDisplayName());
	}

	@Test
	public void create_Existing() {
		Player createPlayer = playerJpaService.create(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Michelle", LocalDate.of(1969, 9, 8), "Michelle Puzdrakiewicz"));
		assertTrue(createPlayer.isFound());
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				playerJpaService.create(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Fred", LocalDate.of(1969, 11, 9), null));
			});
	}

	@Test
	public void update_Updated() {
		Player updatePlayer = playerJpaService.update(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Thad", LocalDate.of(2000, 3, 13), "Thad Puzdrakiewicz2"));
		Player player = playerJpaService.findByLastNameAndFirstNameAndBirthdate("Puzdrakiewicz", "Thad", LocalDate.of(2000, 3, 13));
		assertEquals("Thad Puzdrakiewicz2", player.getDisplayName());
		assertTrue(updatePlayer.isUpdated());
	}

	@Test
	public void update_NotFound() {
		Player player = playerJpaService.update(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Thad", LocalDate.of(2000, 3, 14), "Thad Puzdrakiewicz"));
		assertTrue(player.isNotFound());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				playerJpaService.update(PlayerRepositoryTest.createMockPlayer("Puzdrakiewicz", "Thad", LocalDate.of(2000, 3, 13), null));
			});
	}

	@Test
	public void delete_Deleted() {
		Player deletePlayer = playerJpaService.delete(6L);
		Player findPlayer = playerJpaService.getById(6L);
		assertNull(findPlayer);
		assertTrue(deletePlayer.isDeleted());
	}

	@Test
	public void delete_NotFound() {
		Player deletePlayer = playerJpaService.delete(101L);
		assertTrue(deletePlayer.isNotFound());
	}
}
