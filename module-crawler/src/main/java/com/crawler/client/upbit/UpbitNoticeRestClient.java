package com.crawler.client.upbit;

import com.crawler.client.upbit.dto.UpbitNoticeResponse;
import com.crawler.config.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Slf4j
@Component
public class UpbitNoticeRestClient {
    private final WebClient webClient;

    public UpbitNoticeRestClient(UrlProperties urlProperties) {
        this.webClient = WebClient.builder()
                .baseUrl(urlProperties.getUpbitNoticesUrl())
                .build();
    }

    public UpbitNoticeResponse getNotices(int page) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .path("/notices")
                        .queryParam("page", page)
                        .queryParam("per_page", 20)
                        .queryParam("thread_name", "general")
                        .build())
                .retrieve()
                .bodyToMono(UpbitNoticeResponse.class).block();
    }
}
