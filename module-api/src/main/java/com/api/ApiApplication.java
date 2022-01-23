package com.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@EnableRedisRepositories(basePackages = "com.common.redis")
@ComponentScan(basePackages = {"com.common", "com.api"})
@SpringBootApplication
public class ApiApplication {
    public static void main(String[] args) {
        var application = new SpringApplication(ApiApplication.class);
        application.run(args);
    }
}
