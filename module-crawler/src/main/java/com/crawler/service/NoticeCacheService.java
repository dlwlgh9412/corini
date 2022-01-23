package com.crawler.service;

import com.common.entity.Notice;
import com.crawler.properties.UrlProperties;
import com.common.redis.entity.RedisNotice;
import com.common.redis.repository.RedisNoticeRepository;
import com.common.repository.NoticeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NoticeCacheService {
    private final RedisNoticeRepository redisNoticeRepository;
    private final NoticeRepository noticeRepository;

    public NoticeCacheService(RedisNoticeRepository redisNoticeRepository, NoticeRepository noticeRepository, UrlProperties urlProperties) {
        this.redisNoticeRepository = redisNoticeRepository;
        this.noticeRepository = noticeRepository;
    }

    public void cachingNotices() {
        List<Notice> noticeList = noticeRepository.findAll();
        List<RedisNotice> redisNotices = noticeList.stream().map(notice -> RedisNotice.builder()
                        .id(notice.getId())
                        .title(notice.getTitle())
                        .url(notice.getUrl())
                        .createdDate(notice.getCreatedDate())
                        .exchangeType(notice.getExchange().getType())
                        .expiration(1L)
                        .build())
                .collect(Collectors.toList());

        redisNoticeRepository.saveAll(redisNotices);
    }
}
