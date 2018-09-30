package com.rossotti.basketball.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan(basePackages = "com.rossotti.basketball")
public class ServiceConfig {

	private final Environment env;

	@Autowired
	public ServiceConfig(Environment env) {
		this.env = env;
	}
}