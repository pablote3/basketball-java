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
}