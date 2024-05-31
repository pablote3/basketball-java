package com.rossotti.basketball.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(classes = com.rossotti.basketball.config.ServiceConfig.class)
public class FileServiceTest {
	@Autowired
	private FileService fileService;

	@Test
	public void writeFile_UTF8_Valid() {
		String input = "Test Valančiūnas";
		InputStream stream = new ByteArrayInputStream(input.getBytes());
		String fileName = "/home/pablote/testFile.txt";
		fileService.fileStreamWriter(fileName, StreamConverter.getBytes(stream));
		assertEquals("Test Valančiūnas", fileService.fileLineReader(fileName));
		fileService.fileDelete(fileName);
	}

	@Test
	public void writeFile_RosterJson_Valid() {
		InputStream baseJson = this.getClass().getClassLoader().getResourceAsStream("mockClient/rosterClient.json");
		String fileName = "/home/pablote/testFile.txt";
		fileService.fileStreamWriter(fileName, StreamConverter.getBytes(baseJson));
		assertTrue(fileService.fileExists(fileName));
		fileService.fileDelete(fileName);
	}
}