package com.api.dto;

import com.common.entity.Exchange;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoticesResponseDto {
    private Exchange.ExchangeType exchangeType;
    private String title;
    private String url;
    private ZonedDateTime createdDate;
}
