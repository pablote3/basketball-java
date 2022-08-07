package com.rossotti.basketball.util;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;

import static org.junit.jupiter.api.Assertions.*;

//@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class PropertyTest {
	@Autowired
	Environment env;

	@Test
	public void getProperty_String_Valid() {
		assertEquals("validString", env.getProperty("accumulator.string.valid"));
	}
	@Test
	public void getProperty_String_Empty() {
		assertEquals("", env.getProperty("accumulator.string.empty", String.class));
	}
	@Test
	public void getProperty_String_Null() {
		assertNull(env.getProperty("accumulator.string.null", String.class));
	}

	@Test
	public void getRequiredProperty_String_Valid() {
		assertEquals("validString", env.getRequiredProperty("accumulator.string.valid", String.class));
	}
	@Test
	public void getRequiredProperty_String_Empty() {
		assertEquals("", env.getRequiredProperty("accumulator.string.empty"));
	}
	@Test
	public void getRequiredProperty_String_Null() {
		assertThrows(IllegalStateException.class,
			()->{
				assertNull(env.getRequiredProperty("accumulator.string.null"));
			});
	}
}