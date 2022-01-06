package com.crawler.service;

import com.common.entity.Exchange;
import com.common.entity.Notice;
import com.common.repository.NoticeRepository;
import com.crawler.client.upbit.UpbitNoticeRestClient;
import com.crawler.client.upbit.dto.UpbitNoticeResponse;
import com.crawler.config.UrlProperties;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UpbitNoticeService {
    private final NoticeRepository noticeRepository;
    private final UpbitNoticeRestClient upbitNoticeRestClient;
    private final UrlProperties urlProperties;
    private final Exchange exchange;

    public UpbitNoticeService(NoticeRepository noticeRepository, UpbitNoticeRestClient upbitNoticeRestClient, UrlProperties urlProperties) {
        this.noticeRepository = noticeRepository;
        this.upbitNoticeRestClient = upbitNoticeRestClient;
        this.urlProperties = urlProperties;
        this.exchange = Exchange.builder()
                .exchangeType(Exchange.ExchangeType.UPBIT)
                .overSea(false)
                .url(urlProperties.getUpbitUrl())
                .build();
    }

    public void checkLastNoticeId() {
        BigDecimal lastNoticeId = noticeRepository.getLastNoticeIdByExchange("UPBIT").orElse(BigDecimal.ZERO);
    }

    public List<Notice> searchNotice(BigDecimal lastNoticeId, int page) {
        UpbitNoticeResponse response = upbitNoticeRestClient.getNotices(page);


        if (response.getSuccess()) {
            return response.getData().getList().stream().filter(noticeInfo -> BigDecimal.valueOf(noticeInfo.getId()).compareTo(lastNoticeId) > 0)
                    .map(noticeInfo -> Notice.builder()
                            .noticeId(BigDecimal.valueOf(noticeInfo.getId()))
                            .exchange(exchange)
                            .noticeKind(discriminateNoticeKind(noticeInfo.getTitle()))
                            .url(buildNoticeUrl(noticeInfo.getId()))
                            .createdDate(ZonedDateTime.parse(noticeInfo.getCreatedAt()))
                            .updatedDate(ZonedDateTime.parse(noticeInfo.getUpdatedAt()))
                            .build()).collect(Collectors.toList());
        } else {
            // 커스텀 예외로 수정
            throw new RuntimeException();
        }
    }

    public List<Integer> findOutPageList() {

    }

    public Notice.NoticeKind discriminateNoticeKind(String title) {
        if(title.contains(Notice.NoticeKind.EVENT.getValue()))
            return Notice.NoticeKind.EVENT;
        else
            return Notice.NoticeKind.NOTICE;
    }

    public String buildNoticeUrl(Integer noticeId) {
        return urlProperties.getUpbitNoticeUrl() + noticeId;
    }
}