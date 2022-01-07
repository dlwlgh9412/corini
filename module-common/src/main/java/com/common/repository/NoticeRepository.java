package com.common.repository;

import com.common.entity.Notice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

@Repository
public interface NoticeRepository extends JpaRepository<Notice, Long> {
    @Query(value = "select MAX(NOTICE_ID) from TBL_NOTICE inner join TBL_EXCHANGE TE on TBL_NOTICE.EXCHANGE = TE.NAME where TE.NAME = ?1", nativeQuery = true)
    Optional<BigDecimal> getLastNoticeIdByExchange(String exchange);
}
