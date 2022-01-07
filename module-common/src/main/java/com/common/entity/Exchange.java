package com.common.entity;

import com.common.enums.EntityEnumModel;
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
    @Enumerated(EnumType.STRING)
    private ExchangeEntity name;

    private Boolean overSea;

    private String url;

    @OneToMany(mappedBy = "exchange", cascade = CascadeType.ALL)
    private List<Notice> noticeEntities = new ArrayList<>();

    @Builder
    public Exchange(ExchangeEntity name, Boolean overSea, String url) {
        this.name = name;
        this.overSea = overSea;
        this.url = url;
    }

    public enum ExchangeEntity implements EntityEnumModel {
        UPBIT("업비트"),
        COINONE("코인원");

        private String type;

        ExchangeEntity(String type) {
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
