package com.graalvm;

import java.util.Objects;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ApplicationBeans {

	private ConfigService configService;

	@Bean(name = "userDetails")
	public Config getConfig() {
		Config config = configService.getConfig();
		if (Objects.isNull(config.getEmail()) || Objects.isNull(config.getUsername())) {
			log.error("Config are not set correctly");
		}
		return config;
	}

}
