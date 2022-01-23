package com.api.service;

import com.api.dto.NoticesResponseDto;
import com.common.entity.Exchange;
import com.common.redis.entity.RedisNotice;
import com.common.redis.repository.RedisNoticeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class NoticeService {
    private final RedisNoticeRepository redisNoticeRepository;

    public List<NoticesResponseDto> getNotices() {
        return bindingNotices(redisNoticeRepository.findAll());
    }

    public List<NoticesResponseDto> getNotices(Exchange.ExchangeType type) {
        return bindingNotices(redisNoticeRepository.findAllByExchangeType(type));
    }

    private List<NoticesResponseDto> bindingNotices(List<RedisNotice> notices) {
        return notices.stream().map(notice -> NoticesResponseDto.builder()
                        .title(notice.getTitle())
                        .url(notice.getUrl())
                        .createdDate(notice.getCreatedDate())
                        .exchangeType(notice.getExchangeType())
                        .build())
                .collect(Collectors.toList());
    }

}
