package com.crawler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan(basePackages = {"com.common", "com.crawler"})
@EntityScan(basePackages = "com.common.entity")
@EnableRedisRepositories(basePackages = "com.common.redis")
@EnableJpaRepositories(basePackages = "com.common.repository")
@EnableScheduling
@SpringBootApplication
public class CrawlerApplication {
    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(CrawlerApplication.class);
        application.run(args);
    }
}
