package com.rossotti.basketball.util;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import org.slf4j.Logger;

@Service
@Configuration
@PropertySource("classpath:/service.properties")
public class PropertyService {
	private final Environment env;

	private final Logger logger = LoggerFactory.getLogger(PropertyService.class);

	@Autowired
	public PropertyService(Environment env) {
		this.env = env;
	}

	public String getProperty_String(String propertyName) {
		String property = env.getProperty(propertyName);
		if (StringUtils.isEmpty(property)) {
			return null;
		}
		return property;
	}

	public LocalDate getProperty_LocalDate(String propertyName) {
		String stringDate = getProperty_String(propertyName);
		LocalDate localDate = null;
		if (stringDate != null) {
			try {
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				localDate = LocalDate.parse(stringDate, formatter);
			}
			catch (DateTimeParseException pe) {
				logger.info("property exception = " + pe);
			}
		}
		return localDate;
	}

	public String getProperty_Path(String propertyName) {
		String path = getProperty_String(propertyName);
		try {
            if (!new File(path).exists()) {
                logger.info("property exception = path " + propertyName + " does not exist");
                return null;
            }
        }
        catch (NullPointerException ne) {
		    return null;
        }
		return path;
	}

	public static String getMinDateTimeProperty() {
		String minDateTime = null;
		if (System.getProperty("fromDate") != null) {
			String fromDate = System.getProperty("fromDate");
			if (fromDate.isEmpty()) {
				minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(LocalDate.now().minusDays(1)));
			}
			else {
				if (DateTimeConverter.isDate(fromDate)) {
					minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(DateTimeConverter.getLocalDate(fromDate)));
				}
				else {
					System.out.println("Invalid fromDate argument");
					System.exit(1);
				}
			}
		}
		else {
			System.out.println("Argument fromDate not supplied - assumed to be unit test execution");
			minDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMin(DateTimeConverter.getLocalDate("2016-10-26")));
		}
		return minDateTime;
	}

	public static String getMaxDateTimeProperty() {
		String maxDateTime = null;
		if (System.getProperty("toDate") != null) {
			String toDate = System.getProperty("toDate");
			if (toDate.isEmpty()) {
				maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(LocalDate.now().minusDays(1)));
			}
			else {
				if (DateTimeConverter.isDate(toDate)) {
					maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(DateTimeConverter.getLocalDate(toDate)));
				}
				else {
					System.out.println("Invalid toDate argument");
					System.exit(1);
				}
			}
		}
		else {
			System.out.println("Argument toDate not supplied - assumed to be unit test execution");
			maxDateTime = DateTimeConverter.getStringDateTime(DateTimeConverter.getLocalDateTimeMax(DateTimeConverter.getLocalDate("2016-10-26")));
		}
		return maxDateTime;
	}
}