package com.rossotti.basketball.jpa.service;

import com.rossotti.basketball.jpa.model.Official;
import com.rossotti.basketball.jpa.repository.OfficialRepositoryTest;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class OfficialJpaServiceTest {

	private OfficialJpaService officialJpaService;

	@Autowired
	public void setOfficialJpaService(OfficialJpaService officialJpaService) {
		this.officialJpaService = officialJpaService;
	}

	@Test
	public void getById() {
		Official official = officialJpaService.getById(1L);
		assertEquals("LateCa'll", official.getLastName());
	}

	@Test
	public void listAll() {
		List<Official> officials = (List<Official>) officialJpaService.listAll();
		assertTrue(officials.size() >= 10);
	}

	@Test
	public void findByLastNameFirstName_Found_FromDate() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCa'll", "Joe", LocalDate.of(2009, 7, 1));
		assertEquals("96", official.getNumber());
		assertTrue(official.isFound());
	}

	@Test
	public void findByLastNameFirstName_Found_ToDate() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCa'll", "Joe", LocalDate.of(2010, 6, 30));
		assertEquals("96", official.getNumber());
		assertTrue(official.isFound());
	}

	@Test
	public void findByLastNameFirstName_NotFound_LastName() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCall", "Joe", LocalDate.of(2010, 6, 30));
		assertTrue(official.isNotFound());
	}

	@Test
	public void findByLastNameFirstName_NotFound_FirstName() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCa'll", "Joey", LocalDate.of(2010, 6, 30));
		assertTrue(official.isNotFound());
	}

	@Test
	public void findByLastNameFirstName_NotFound_BeforeAsOfDate() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCa'll", "Joe", LocalDate.of(2009, 6, 30));
		assertTrue(official.isNotFound());
	}

	@Test
	public void findByLastNameFirstName_NotFound_AfterAsOfDate() {
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("LateCa'll", "Joe", LocalDate.of(2010, 7, 1));
		assertTrue(official.isNotFound());
	}

	@Test
	public void findByAsOfDate_Found() {
		List<Official> officials = officialJpaService.findByAsOfDate(LocalDate.of(2009, 10, 30));
		assertTrue(officials.size() >= 3);
	}

	@Test
	public void findByDateRange_NotFound() {
		List<Official> officials = officialJpaService.findByAsOfDate(LocalDate.of(1989, 10, 30));
		assertEquals(0, officials.size());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created_AsOfDate() {
		Official createOfficial = officialJpaService.create(OfficialRepositoryTest.createMockOfficial("BadCall", "Melvin", LocalDate.of(2006, 7, 6), LocalDate.of(9999, 12, 31), "996"));
		Official findOfficial = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("BadCall", "Melvin", LocalDate.of(2006, 7, 6));
		assertTrue(createOfficial.isCreated());
		assertEquals("996", findOfficial.getNumber());
	}

	@Disabled("Disabled until new work on persistence")
	@Test
	public void create_Created_DateRange() {
		Official createOfficial = officialJpaService.create(OfficialRepositoryTest.createMockOfficial("BadCall", "Melvon", LocalDate.of(2006, 7, 6), LocalDate.of(2006, 7, 10), "995"));
		Official findOfficial = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("BadCall", "Melvon", LocalDate.of(2006, 7, 7));
		assertTrue(createOfficial.isCreated());
		assertEquals("995", findOfficial.getNumber());
	}

	@Test
	public void create_OverlappingDates() {
		Official createOfficial = officialJpaService.create(OfficialRepositoryTest.createMockOfficial("QuestionableCall", "Hefe", LocalDate.of(2005, 7, 1), LocalDate.of(2006, 6, 20), "18"));
		assertTrue(createOfficial.isFound());
	}

	@Test
	public void create_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				officialJpaService.create(OfficialRepositoryTest.createMockOfficial("BadCaller", "Melvyn", LocalDate.of(2006, 7, 6), LocalDate.of(2006, 7, 10), null));
			});
	}

	@Test
	public void update_Updated() {
		Official updateOfficial = officialJpaService.update(OfficialRepositoryTest.createMockOfficial("Forte", "Brian", LocalDate.of(2010, 4, 25), LocalDate.of(2012, 12, 31), "19"));
		Official official = officialJpaService.findByLastNameAndFirstNameAndAsOfDate("Forte", "Brian", LocalDate.of(2010, 4, 25));
		assertEquals("19", official.getNumber());
		assertEquals(LocalDate.of(2012, 12, 31), official.getToDate());
		assertTrue(updateOfficial.isUpdated());
	}

	@Test
	public void update_NotFound() {
		Official updateOfficial = officialJpaService.update(OfficialRepositoryTest.createMockOfficial("Forte", "Brian", LocalDate.of(2009, 4, 25), LocalDate.of(2009, 12, 31), "19"));
		assertTrue(updateOfficial.isNotFound());
	}

	@Test
	public void update_MissingRequiredData() {
		assertThrows(DataIntegrityViolationException.class,
			()->{
				officialJpaService.update(OfficialRepositoryTest.createMockOfficial("Forte", "Brian", LocalDate.of(2010, 4, 25), LocalDate.of(2012, 12, 31), null));
			});
	}

	@Test
	public void delete_Deleted() {
		Official deleteOfficial = officialJpaService.delete(21L);
		Official findOfficial = officialJpaService.getById(21L);
		assertNull(findOfficial);
		assertTrue(deleteOfficial.isDeleted());
	}

	@Test
	public void delete_NotFound() {
		Official deleteOfficial = officialJpaService.delete(101L);
		assertTrue(deleteOfficial.isNotFound());
	}
}
