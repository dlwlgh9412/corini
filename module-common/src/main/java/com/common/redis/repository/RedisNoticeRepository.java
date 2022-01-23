package com.common.redis.repository;

import com.common.entity.Exchange;
import com.common.redis.entity.RedisNotice;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RedisNoticeRepository extends CrudRepository<RedisNotice, Long> {
    List<RedisNotice> findAllByExchangeType(Exchange.ExchangeType exchangeType);
    List<RedisNotice> findAll();
}
