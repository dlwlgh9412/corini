package com.crawler.client.upbit.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class UpbitNoticeData {
    @JsonProperty("total_count")
    private Long totalCount;

    @JsonProperty("total_pages")
    private Long totalPages;

    @JsonProperty("list")
    private List<UpbitNoticeInfo> list;

    @JsonProperty("fixed_notices")
    private List<UpbitNoticeInfo> fixedNotices;
}
