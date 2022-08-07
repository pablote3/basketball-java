package com.rossotti.basketball.app;

import com.rossotti.basketball.app.service.OfficialAppService;
import com.rossotti.basketball.client.dto.OfficialDTO;
import com.rossotti.basketball.jpa.exception.NoSuchEntityException;
import com.rossotti.basketball.jpa.model.AbstractDomainClass.StatusCodeDAO;
import com.rossotti.basketball.jpa.model.Game;
import com.rossotti.basketball.jpa.model.GameOfficial;
import com.rossotti.basketball.jpa.model.Official;
import com.rossotti.basketball.jpa.service.OfficialJpaService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class OfficialAppServiceTest {
	@Mock
	private OfficialJpaService officialJpaService;

	@InjectMocks
	private OfficialAppService officialAppService;

	@Test
	public void getGameOfficials_notFound() {
		assertThrows(NoSuchEntityException.class,
			()->{
				when(officialJpaService.findByLastNameAndFirstNameAndAsOfDate(anyString(), anyString(), any()))
					.thenReturn(createMockOfficial("", "", StatusCodeDAO.NotFound));
				List<GameOfficial> officials = officialAppService.getGameOfficials(createMockOfficialDTOs(), createMockGame(), LocalDate.of(1995, 11, 26));
				assertEquals(0, officials.size());
			});
	}

	@Test
	public void getGameOfficials_found() {
		when(officialJpaService.findByLastNameAndFirstNameAndAsOfDate(anyString(), anyString(), any()))
			.thenReturn(createMockOfficial("Adams", "Samuel", StatusCodeDAO.Found))
			.thenReturn(createMockOfficial("Coors", "Adolph", StatusCodeDAO.Found));
		List<GameOfficial> officials = officialAppService.getGameOfficials(createMockOfficialDTOs(), createMockGame(), LocalDate.of(1995, 11, 26));
		assertEquals(2, officials.size());
		assertEquals("Coors", officials.get(1).getOfficial().getLastName());
		assertEquals("Adolph", officials.get(1).getOfficial().getFirstName());
	}

	private OfficialDTO[] createMockOfficialDTOs() {
		OfficialDTO[] officials = new OfficialDTO[2];
		officials[0] = createMockOfficialDTO("Adams", "Samuel");
		officials[1] = createMockOfficialDTO("Coors", "Adolph");
		return officials;
	}

	private OfficialDTO createMockOfficialDTO(String lastName, String firstName) {
		OfficialDTO official = new OfficialDTO();
		official.setLast_name(lastName);
		official.setFirst_name(firstName);
		return official;
	}

	private Official createMockOfficial(String lastName, String firstName, StatusCodeDAO statusCode) {
		Official official = new Official();
		official.setLastName(lastName);
		official.setFirstName(firstName);
		official.setStatusCode(statusCode);
		return official;
	}

	private Game createMockGame() {
		return new Game();
	}
}