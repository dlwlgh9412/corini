package com.crawler.scheduler;

import com.crawler.service.UpbitNoticeService;
import com.crawler.service.enums.PerPage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Slf4j
@Component
public class NoticeSearchScheduler {
    private final UpbitNoticeService upbitNoticeService;

    public NoticeSearchScheduler(UpbitNoticeService upbitNoticeService) {
        this.upbitNoticeService = upbitNoticeService;
    }

    @Scheduled(cron = "0 0/1 * * * ?", zone = "Asia/Seoul")
    public void searchNotice() {
        log.info("{} 업비트 공지사항 Searching", ZonedDateTime.now(ZoneId.of("Asia/Seoul")));
        upbitNoticeService.searchNotice(PerPage.UPBIT_PER_PAGE_DEFAULT.getValue());
    }
}
