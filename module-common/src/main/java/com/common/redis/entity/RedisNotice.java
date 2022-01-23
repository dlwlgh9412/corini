package com.common.redis.entity;

import com.common.entity.Exchange;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.index.Indexed;

import javax.persistence.Id;
import java.time.ZonedDateTime;
import java.util.concurrent.TimeUnit;

@NoArgsConstructor
@Getter
@RedisHash("notice")
public class RedisNotice {
    @Id
    private Long id;

    private String title;

    private String url;

    private ZonedDateTime createdDate;

    @Indexed
    private Exchange.ExchangeType exchangeType;

    @TimeToLive(unit = TimeUnit.DAYS)
    private Long expiration;

    @Builder
    public RedisNotice(Long id, String title, String url, ZonedDateTime createdDate, Exchange.ExchangeType exchangeType, Long expiration) {
        this.id = id;
        this.title = title;
        this.url = url;
        this.createdDate = createdDate;
        this.exchangeType = exchangeType;
        this.expiration = expiration;
    }
}
