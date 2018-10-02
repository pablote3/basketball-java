package com.rossotti.basketball.util;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
@TestPropertySource("classpath:service.properties")
public class PropertyTest {
	@Autowired
	Environment env;

	@Test
	public void getProperty_String_Valid() {
		Assert.assertEquals("validString", env.getProperty("accumulator.string.valid"));
	}
	@Test
	public void getProperty_String_Empty() {
		Assert.assertEquals("", env.getProperty("accumulator.string.empty", String.class));
	}
	@Test
	public void getProperty_String_Null() {
		Assert.assertNull(env.getProperty("accumulator.string.null", String.class));
	}
	@Test
	public void getRequiredProperty_String_Valid() {
		Assert.assertEquals("validString", env.getRequiredProperty("accumulator.string.valid", String.class));
	}
	@Test
	public void getRequiredProperty_String_Empty() {
		Assert.assertEquals("", env.getRequiredProperty("accumulator.string.empty"));
	}
	@Test(expected=IllegalStateException.class)
	public void getRequiredProperty_String_Null() {
		Assert.assertNull(env.getRequiredProperty("accumulator.string.null"));
	}
}