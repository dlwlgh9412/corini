package com.crawler.service.enums;

public enum PerPage implements ParamEnumModel {
    UPBIT_DEFAULT(20),
    UPBIT_MAX(30);

    private Integer perPage;

    PerPage(Integer perPage) {
        this.perPage = perPage;
    }

    @Override
    public Integer getValue() {
        return perPage;
    }
}
