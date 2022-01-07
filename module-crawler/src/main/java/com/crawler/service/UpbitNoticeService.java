package com.crawler.service;

import com.common.entity.Exchange;
import com.common.entity.Notice;
import com.common.repository.ExchangeRepository;
import com.common.repository.NoticeRepository;
import com.crawler.client.upbit.UpbitNoticeRestClient;
import com.crawler.client.upbit.dto.UpbitNoticeInfo;
import com.crawler.client.upbit.dto.UpbitNoticeResponse;
import com.crawler.config.UrlProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UpbitNoticeService {
    private final NoticeRepository noticeRepository;
    private final UpbitNoticeRestClient upbitNoticeRestClient;
    private final ExchangeRepository exchangeRepository;
    private final UrlProperties urlProperties;

    public UpbitNoticeService(NoticeRepository noticeRepository, ExchangeRepository exchangeRepository, UpbitNoticeRestClient upbitNoticeRestClient, UrlProperties urlProperties) {
        this.noticeRepository = noticeRepository;
        this.exchangeRepository = exchangeRepository;
        this.upbitNoticeRestClient = upbitNoticeRestClient;
        this.urlProperties = urlProperties;

    }

    @Transactional
    public void searchNotice(int per_page) {
        UpbitNoticeResponse response = upbitNoticeRestClient.getNotices(per_page);
        BigDecimal lastNoticeId = noticeRepository.getLastNoticeIdByExchange(Exchange.ExchangeEntity.UPBIT.getKey()).orElse(BigDecimal.ZERO);

        if (response.getSuccess()) {
            for (int page = 1; page <= response.getData().getTotalPages(); page++) {
                response = upbitNoticeRestClient.getNotices(page, per_page);
                if (response.getSuccess() && insertNotices(response.getData().getList(), lastNoticeId) < per_page)
                    break;
            }
        } else {
            // 사용자정의 예외로 수정
            throw new RuntimeException();
        }
    }

    private Integer insertNotices(List<UpbitNoticeInfo> noticeInfoList, BigDecimal lastNoticeId) {
        noticeInfoList = noticeInfoList.stream().filter(noticeInfo -> BigDecimal.valueOf(noticeInfo.getId()).compareTo(lastNoticeId) > 0).collect(Collectors.toList());
        Exchange exchange = selectExchange();
        List<Notice> noticeList = noticeInfoList.stream().map(noticeInfo -> Notice.builder()
                .noticeId(BigDecimal.valueOf(noticeInfo.getId()))
                .exchange(exchange)
                .title(noticeInfo.getTitle())
                .noticeKind(discriminateNoticeKind(noticeInfo.getTitle()))
                .url(buildNoticeUrl(noticeInfo.getId()))
                .createdDate(ZonedDateTime.parse(noticeInfo.getCreatedAt()))
                .updatedDate(ZonedDateTime.parse(noticeInfo.getUpdatedAt()))
                .build()).collect(Collectors.toList());

        return noticeRepository.saveAll(noticeList).size();
    }

    private Exchange selectExchange() {
        Exchange exchange = exchangeRepository.findById(Exchange.ExchangeEntity.UPBIT).orElse(Exchange.builder()
                .name(Exchange.ExchangeEntity.UPBIT)
                .overSea(false)
                .url(urlProperties.getUpbitUrl())
                .build());
        return exchange;
    }

    private Notice.NoticeKind discriminateNoticeKind(String title) {
        if (title.contains(Notice.NoticeKind.EVENT.getValue()))
            return Notice.NoticeKind.EVENT;
        else
            return Notice.NoticeKind.NOTICE;
    }

    private String buildNoticeUrl(Integer noticeId) {
        return urlProperties.getUpbitNoticeUrl() + noticeId;
    }
}
