package com.rossotti.basketball.jpa.repository;

import com.rossotti.basketball.jpa.model.Official;
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
public class OfficialRepositoryTest {

	private OfficialRepository officialRepository;

	@Autowired
	public void setOfficialRepository(OfficialRepository officialRepository) {
		this.officialRepository = officialRepository;
	}

	@Test
	public void getById() {
		Official official = officialRepository.findById(1L);
		assertEquals("LateCa'll", official.getLastName());
	}

	@Test
	public void findAll() {
		List<Official> officials = officialRepository.findAll();
		assertTrue(officials.size() >= 11);
	}

	@Test
	public void findByLastNameFirstName_Found_FromDate() {
		Official official = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCa'll", "Joe", LocalDate.of(2009, 7, 1), LocalDate.of(2009, 7, 1));
		assertEquals("96", official.getNumber());
	}

	@Test
	public void findByLastNameFirstName_Found_ToDate() {
		Official official = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCa'll", "Joe", LocalDate.of(2010, 6, 30), LocalDate.of(2010, 6, 30));
		assertEquals("96", official.getNumber());
	}

	@Test
	public void findByLastNameFirstName_NotFound_LastName() {
		Official official = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCall", "Joe", LocalDate.of(2010, 6, 30), LocalDate.of(2010, 6, 30));
		assertNull(official);
	}

 	@Test
	public void findByLastNameFirstName_NotFound_FirstName() {
	    Official official = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCa'll", "Joey", LocalDate.of(2010, 6, 30), LocalDate.of(2010, 6, 30));
		assertNull(official);
	}

	@Test
	public void findByLastNameFirstName_NotFound_BeforeAsOfDate() {
		Official team = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCa'll", "Joe", LocalDate.of(2009, 6, 30), LocalDate.of(2009, 7, 1));
		assertNull(team);
	}

	@Test
	public void findByLastNameFirstName_NotFound_AfterAsOfDate() {
		Official team = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("LateCa'll", "Joe", LocalDate.of(2010, 6, 30), LocalDate.of(2010, 7, 1));
		assertNull(team);
	}

	@Test
	public void findByAsOfDate_Found() {
		List<Official> officials = officialRepository.findByFromDateAndToDate(LocalDate.of(2009, 7, 1), LocalDate.of(2009, 7, 1));
		assertTrue(officials.size() >= 3);
	}

	@Test
	public void findByAsOfDate_NotFound() {
		List<Official> officials = officialRepository.findByFromDateAndToDate(LocalDate.of(1989, 7, 1), LocalDate.of(1989, 7, 1));
		assertEquals(0, officials.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created() {
		officialRepository.save(createMockOfficial("BadCall", "Melvin", LocalDate.of(2006, 7, 1), LocalDate.of(2006, 7, 5), "999"));
		Official findOfficial = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("BadCall", "Melvin", LocalDate.of(2006, 7, 1), LocalDate.of(2006, 7, 5));
		assertEquals("999", findOfficial.getNumber());
	}

	@Test
	public void create_Existing() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				officialRepository.save(createMockOfficial("TerribleCall", "Limo", LocalDate.of(2005, 7, 1), LocalDate.of(2006, 6, 30), "100"));
			});
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				officialRepository.save(createMockOfficial("BadCall", "Melvin", LocalDate.of(2006, 7, 1), LocalDate.of(9999, 12, 31), null));
			});
}

	@Test
	public void update_Updated() {
		officialRepository.save(updateMockOfficial(LocalDate.of(2010, 10, 30), LocalDate.of(9999, 12, 31), "58"));
		Official team = officialRepository.findByLastNameAndFirstNameAndFromDateAndToDate("Zarba", "Zach", LocalDate.of(2010, 10, 30), LocalDate.of(9999, 12, 31));
		assertEquals("58", team.getNumber());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				officialRepository.save(updateMockOfficial(LocalDate.of(2010, 10, 30), LocalDate.of(9999, 12, 31), null));
			});
	}

	@Test
	public void delete_Deleted() {
		officialRepository.deleteById(20L);
		Official findOfficial = officialRepository.findById(20L);
		assertNull(findOfficial);
	}

	@Test
	public void delete_NotFound() {
		assertThrows(EmptyResultDataAccessException.class,
			()->{
				officialRepository.deleteById(101L);
			});
	}

	public static Official createMockOfficial(String lastName, String firstName, LocalDate fromDate, LocalDate toDate, String number) {
		Official official = new Official();
		official.setLastName(lastName);
		official.setFirstName(firstName);
		official.setFromDate(fromDate);
		official.setToDate(toDate);
		official.setNumber(number);
		return official;
	}

	private Official updateMockOfficial(LocalDate fromDate, LocalDate toDate, String number) {
		Official official = createMockOfficial("Zarba", "Zach", fromDate, toDate, number);
		official.setId(10L);
		return official;
	}
}