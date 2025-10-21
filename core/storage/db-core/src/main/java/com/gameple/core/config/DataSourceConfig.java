package com.gameple.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@Configuration
@EnableJpaRepositories(basePackages = "com.gameple.core.repository")
@EntityScan(basePackages = "com.gameple.core.entity")
public class DataSourceConfig {

}
