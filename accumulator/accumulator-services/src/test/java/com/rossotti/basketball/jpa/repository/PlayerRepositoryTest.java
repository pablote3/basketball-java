package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Player;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class PlayerRepositoryTest {

	private PlayerRepository playerRepository;

	@Autowired
	public void setPlayerRepository(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	@Test
	public void getById() {
		Player player = playerRepository.findById(1L);
		assertEquals("Puzdrakiew'icz", player.getLastName());
		assertEquals(3, player.getRosterPlayers().size());
	}

	@Test
	public void findAll() {
		List<Player> players = playerRepository.findAll();
		assertTrue(players.size() >= 17);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_Found() {
		Player player = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 20));
		assertEquals("Sacramento, CA, USA", player.getBirthplace());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_LastName() {
		Player player = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew''icz", "Luke", LocalDate.of(2002, 2, 20));
		assertNull(player);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_FirstName() {
		Player player = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Like", LocalDate.of(2002, 2, 20));
		assertNull(player);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_Birthdate() {
		Player player = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 21));
		assertNull(player);
	}

	@Test
	public void findByLastNameFirstName_Found() {
		List<Player> players = playerRepository.findByLastNameAndFirstName("Puzdrakiewicz", "Thad");
		assertEquals(2, players.size());
	}

	@Test
	public void findByLastNameFirstName_NotFound_LastName() {
		List<Player> players = playerRepository.findByLastNameAndFirstName("Puzdrakiewiczy", "Thad");
		assertEquals(0, players.size());
	}

	@Test
	public void findByLastNameFirstName_NotFound_FirstName() {
		List<Player> players = playerRepository.findByLastNameAndFirstName("Puzdrakiewicz", "Thady");
		assertEquals(0, players.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		playerRepository.save(createMockPlayer("Puzdrakiewicz", "Fred", LocalDate.of(1968, 11, 8), "Fred Puzdrakiewicz"));
		Player findPlayer = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiewicz", "Fred", LocalDate.of(1968, 11, 8));
		assertEquals("Fred Puzdrakiewicz", findPlayer.getDisplayName());
	}

	@Test
	public void create_Existing() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				playerRepository.save(createMockPlayer("Puzdrakiewicz", "Michelle", LocalDate.of(1969, 9, 8), "Michelle Puzdrakiewicz"));
			});
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				playerRepository.save(createMockPlayer("Puzdrakiewicz", "Fred", LocalDate.of(1968, 11, 8), null));
			});
	}

	@Test
	public void update_Updated() {
		playerRepository.save(updateMockPlayer("Thady Puzdrakiewicz"));
		Player player = playerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiewicz", "Thad", LocalDate.of(1966, 6, 2));
		assertEquals("Thady Puzdrakiewicz", player.getDisplayName());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				playerRepository.save(updateMockPlayer(null));
			});
	}

	@Test
	public void delete_Deleted() {
		playerRepository.deleteById(7L);
		Player findPlayer = playerRepository.findById(7L);
		assertNull(findPlayer);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()->{
				playerRepository.deleteById(101L);
			});
	}

	public static Player createMockPlayer(String lastName, String firstName, LocalDate birthdate, String displayName) {
		Player player = new Player();
		player.setLastName(lastName);
		player.setFirstName(firstName);
		player.setBirthdate(birthdate);
		player.setDisplayName(displayName);
		player.setHeight((short)79);
		player.setWeight((short)195);
		player.setBirthplace("Monroe, Louisiana, USA");
		return player;
	}

	private Player updateMockPlayer(String displayName) {
		Player player = createMockPlayer("Puzdrakiewicz", "Thad", LocalDate.of(1966, 6, 2), displayName);
		player.setId(2L);
		return player;
	}
}