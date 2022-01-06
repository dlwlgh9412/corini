package com.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EntityScan(basePackages = "com.common.entity")
@EnableJpaRepositories(basePackages = "com.common.repository")
@EnableScheduling
@SpringBootApplication
public class CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CrawlerApplication.class);
        application.run(args);
    }
}
