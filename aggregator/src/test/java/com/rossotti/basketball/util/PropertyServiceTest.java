package com.rossotti.basketball.util;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PropertyServiceTest {
	@Autowired
	private PropertyService propertyService;

	@Test
	public void getProperty_String_Valid() {
		String prop = propertyService.getProperty_String("aggregator.string.valid");
		Assert.assertEquals("validString", prop);
	}
	@Test()
	public void getProperty_String_Empty() {
		Assert.assertNull(propertyService.getProperty_String("aggregator.string.empty"));
	}

	@Test
	public void getProperty_LocalDate_Valid() {
		LocalDate localDate = propertyService.getProperty_LocalDate("aggregator.localDate.valid");
		Assert.assertEquals(LocalDate.of(2017, 5, 29), localDate);
	}
	@Test()
	public void getProperty_LocalDate_Invalid() {
		Assert.assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.invalid"));
	}
	@Test()
	public void getProperty_LocalDate_Empty() {
		Assert.assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.empty"));
	}
	@Test()
	public void getProperty_LocalDate_Null() {
		Assert.assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.null"));
	}
}