package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Player;
import com.rossotti.basketball.jpa.model.RosterPlayer;
import com.rossotti.basketball.jpa.model.Team;
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
public class RosterPlayerRepositoryTest {

	private RosterPlayerRepository rosterPlayerRepository;

	@Autowired
	public void setRosterPlayerRepository(RosterPlayerRepository rosterPlayerRepository) {
		this.rosterPlayerRepository = rosterPlayerRepository;
	}

	@Test
	public void getById() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findById(1L);
		assertEquals("21", rosterPlayer.getNumber());
		assertEquals("chicago-zephyr's", rosterPlayer.getTeam().getTeamKey());
		assertEquals("Luke", rosterPlayer.getPlayer().getFirstName());
	}

	@Test
	public void findAll() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findAll();
		assertTrue(rosterPlayers.size() >= 19);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_Found() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 20), LocalDate.of(2009, 10, 30));
		assertEquals("31", rosterPlayer.getNumber());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_Found_UTF_8() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Valančiūnas", "Jonas", LocalDate.of(1992, 5, 6), LocalDate.of(2015, 10, 30));
		assertEquals("9", rosterPlayer.getNumber());
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_LastName() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiew'iczy", "Luke", LocalDate.of(2002, 2, 20), LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_FirstName() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiew'icz", "Lukey", LocalDate.of(2002, 2, 20), LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_Birthdate() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 21), LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameBirthdate_NotFound_AsOfDate() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 20), LocalDate.of(2009, 10, 29));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameTeamKey_Found() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Puzdrakiew'icz", "Luke", "salinas-cowboys", LocalDate.of(2009, 10, 30));
		assertEquals("31", rosterPlayer.getNumber());
	}

	@Test
	public void findByLastNameFirstNameTeamKey_Found_UTF_8() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Valančiūnas", "Jonas", "detroit-pistons", LocalDate.of(2015, 10, 30));
		assertEquals("9", rosterPlayer.getNumber());
	}

	@Test
	public void findByLastNameFirstNameTeamKey_NotFound_LastName() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Puzdrakiew'iczy", "Luke", "salinas-cowboys", LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameTeamKey_NotFound_FirstName() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Puzdrakiew'icz", "Lukey", "salinas-cowboys", LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameTeamKey_NotFound_TeamKey() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Puzdrakiew'icz", "Luke", "salinas-cowboy", LocalDate.of(2009, 10, 30));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameFirstNameTeamKey_NotFound_AsOfDate() {
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndTeamKeyAndAsOfDate("Puzdrakiew'icz", "Luke", "salinas-cowboys", LocalDate.of(2009, 10, 29));
		assertNull(rosterPlayer);
	}

	@Test
	public void findByLastNameAndFirstNameAndBirthdate_Found() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 20));
		assertEquals(3, rosterPlayers.size());
	}

	@Test
	public void findByLastNameAndFirstNameAndBirthdate_Found_UTF_8() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate("Valančiūnas", "Jonas", LocalDate.of(1992, 5, 6));
		assertEquals(1, rosterPlayers.size());
	}

	@Test
	public void findByLastNameAndFirstNameAndBirthdate_NotFound_LastName() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'iczy", "Luke", LocalDate.of(2002, 2, 20));
		assertEquals(0, rosterPlayers.size());
	}

	@Test
	public void findByLastNameAndFirstNameAndBirthdate_NotFound_FirstName() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Lukey", LocalDate.of(2002, 2, 20));
		assertEquals(0, rosterPlayers.size());
	}

	@Test
	public void findByLastNameAndFirstNameAndBirthdate_NotFound_Birthdate() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdate("Puzdrakiew'icz", "Luke", LocalDate.of(2002, 2, 21));
		assertEquals(0, rosterPlayers.size());
	}

	@Test
	public void findByTeamKeyAndAsOfDate_Found() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByTeamKeyAndAsOfDate("detroit-pistons", LocalDate.of(2015, 10, 30));
		assertEquals(4, rosterPlayers.size());
	}

	@Test
	public void findByTeamKeyAndAsOfDate_NotFound_TeamKey() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByTeamKeyAndAsOfDate("detroit-pistols", LocalDate.of(2015, 10, 30));
		assertEquals(0, rosterPlayers.size());
	}

	@Test
	public void findByTeamKeyAndAsOfDate_NotFound_AsOfDate() {
		List<RosterPlayer> rosterPlayers = rosterPlayerRepository.findByTeamKeyAndAsOfDate("detroit-pistons", LocalDate.of(2014, 10, 30));
		assertEquals(0, rosterPlayers.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		rosterPlayerRepository.save(createMockRosterPlayer(2L, 2L, LocalDate.of(2010, 1, 11), LocalDate.of(2010, 1, 21), "22"));
		RosterPlayer findRosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Puzdrakiewicz", "Thad", LocalDate.of(1966, 6, 2), LocalDate.of(2010, 1, 21));
		assertEquals("22", findRosterPlayer.getNumber());
	}

	@Test
	public void create_Existing() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				rosterPlayerRepository.save(createMockRosterPlayer(2L, 2L, LocalDate.of(2010, 1, 1), LocalDate.of(2010, 1, 10), "44"));
			});
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				rosterPlayerRepository.save(createMockRosterPlayer(2L, 2L, LocalDate.of(2010, 1, 1), LocalDate.of(2010, 1, 10), null));
			});
	}

	@Test
	public void update_Updated() {
		rosterPlayerRepository.save(updateMockRosterPlayer(LocalDate.of(2015, 11, 15), LocalDate.of(9999, 12, 31),"51"));
		RosterPlayer rosterPlayer = rosterPlayerRepository.findByLastNameAndFirstNameAndBirthdateAndAsOfDate("Drummond", "Andre", LocalDate.of(1990, 3, 4), LocalDate.of(2015, 11, 15));
		assertEquals("51", rosterPlayer.getNumber());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				rosterPlayerRepository.save(updateMockRosterPlayer(LocalDate.of(2015, 11, 15), LocalDate.of(9999, 12, 31),null));
			});
	}

	@Test
	public void delete_Deleted() {
		rosterPlayerRepository.deleteById(22L);
		RosterPlayer findRosterPlayer = rosterPlayerRepository.findById(22L);
		assertNull(findRosterPlayer);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()->{
				rosterPlayerRepository.deleteById(101L);
			});
	}

	private RosterPlayer createMockRosterPlayer(Long playerId, Long teamId, LocalDate fromDate, LocalDate toDate, String number) {
		RosterPlayer rosterPlayer = new RosterPlayer();
		rosterPlayer.setPlayer(getMockPlayer(playerId));
		rosterPlayer.setTeam(getMockTeam(teamId));
		rosterPlayer.setFromDate(fromDate);
		rosterPlayer.setToDate(toDate);
		rosterPlayer.setNumber(number);
		rosterPlayer.setPosition(RosterPlayer.Position.G);
		return rosterPlayer;
	}

	private RosterPlayer updateMockRosterPlayer(LocalDate fromDate, LocalDate toDate, String number) {
		RosterPlayer rosterPlayer = createMockRosterPlayer(10L, 9L, fromDate, toDate, number);
		rosterPlayer.setId(10L);
		return rosterPlayer;
	}

	private Player getMockPlayer(Long playerId) {
		Player player = new Player();
		player.setId(playerId);
		return player;
	}

	private Team getMockTeam(Long teamId) {
		Team team = new Team();
		team.setId(teamId);
		return team;
	}
}