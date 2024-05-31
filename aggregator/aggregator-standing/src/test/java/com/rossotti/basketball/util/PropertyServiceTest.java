package com.rossotti.basketball.util;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class PropertyServiceTest {
    @Autowired
    private PropertyService propertyService;

    @Test
    public void getProperty_String_Valid() {
        String prop = propertyService.getProperty_String("aggregator.string.valid");
        assertEquals("validString", prop);
    }
    @Test()
    public void getProperty_String_Empty() {
        assertNull(propertyService.getProperty_String("aggregator.string.empty"));
    }

    @Test
    public void getProperty_LocalDate_Valid() {
        LocalDate localDate = propertyService.getProperty_LocalDate("aggregator.localDate.valid");
        assertEquals(LocalDate.of(2017, 5, 29), localDate);
    }
    @Test()
    public void getProperty_LocalDate_Invalid() {
        assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.invalid"));
    }
    @Test()
    public void getProperty_LocalDate_Empty() {
        assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.empty"));
    }
    @Test()
    public void getProperty_LocalDate_Null() {
        assertNull(propertyService.getProperty_LocalDate("aggregator.localDate.null"));
    }

    @Test
    public void getProperty_Path_Valid() {
        String prop = propertyService.getProperty_Path("aggregator.path.valid");
        assertEquals("/usr/bin", prop);
    }
    @Test()
    public void getProperty_Path_Invalid() {
        String prop = propertyService.getProperty_Path("aggregator.path.invalid");
        assertNull(prop);
    }
    @Test()
    public void getProperty_Path_Empty() {
        String prop = propertyService.getProperty_Path("aggregator.path.empty");
        assertNull(prop);
    }
    @Test()
    public void getProperty_Path_Null() {
        String prop = propertyService.getProperty_Path("aggregator.path.null");
        assertNull(prop);
    }
}
