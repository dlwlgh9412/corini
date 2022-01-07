package com.common.entity;

import com.common.enums.EnumModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TBL_EXCHANGE")
public class Exchange {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Enumerated(EnumType.STRING)
    private ExchangeType exchangeType;

    private Boolean overSea;

    private String url;

    @OneToMany(mappedBy = "exchange")
    private List<Notice> noticeEntities = new ArrayList<>();

    @Builder
    public Exchange(ExchangeType exchangeType, Boolean overSea, String url) {
        this.exchangeType = exchangeType;
        this.overSea = overSea;
        this.url = url;
    }

    public enum ExchangeType implements EnumModel {
        UPBIT("업비트"),
        COINONE("코인원");

        private String type;

        ExchangeType(String type) {
            this.type = type;
        }

        public String getKey() {
            return name();
        }

        public String getValue() {
            return type;
        }
    }
}
