package com.crawler.service.enums;

public enum PerPage {
    UPBIT_PER_PAGE_DEFAULT(20),
    UPBIT_PER_PAGE_MAX(30);

    private Integer perPage;

    PerPage(Integer perPage) {
        this.perPage = perPage;
    }

    public Integer getValue() {
        return perPage;
    }
}
