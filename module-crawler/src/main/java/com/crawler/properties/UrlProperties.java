package com.crawler.properties;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@Getter
@EnableConfigurationProperties
@PropertySource("classpath:properties/url.properties")
public class UrlProperties {
    @Value("${upbit}")
    private String upbitUrl;

    @Value("${upbit.noticeUrl}")
    private String upbitNoticeUrl;

    @Value("${upbit.noticesUrl}")
    private String upbitNoticesUrl;

    @Value("${coinone}")
    private String coinoneUrl;

    @Value("${coinone.noticesUrl}")
    private String coinoneNoticesUrl;
}
