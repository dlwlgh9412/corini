package com.crawler.client.upbit;

import com.crawler.client.upbit.dto.UpbitNoticeResponse;
import com.crawler.properties.UrlProperties;
import com.crawler.enums.PerPage;
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

    public UpbitNoticeResponse getNotices(PerPage perPage) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .queryParam("per_page", perPage.getValue())
                        .queryParam("thread_name", "general")
                        .build())
                .retrieve()
                .bodyToMono(UpbitNoticeResponse.class).block();
    }

    public UpbitNoticeResponse getNotices(int page, PerPage perPage) {
        return webClient.get().uri(uriBuilder -> uriBuilder
                        .queryParam("page", page)
                        .queryParam("per_page", perPage.getValue())
                        .queryParam("thread_name", "general")
                        .build())
                .retrieve()
                .bodyToMono(UpbitNoticeResponse.class).block();
    }
}
