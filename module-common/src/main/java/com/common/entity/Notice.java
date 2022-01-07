package com.common.entity;

import com.common.enums.EntityEnumModel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "TBL_NOTICE")
@DynamicUpdate
public class Notice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal noticeId;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private NoticeKind noticeKind;

    private String title;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "EXCHANGE")
    private Exchange exchange;

    @Column(name = "URL")
    private String url;

    @Column(name = "REG_DATE")
    private ZonedDateTime createdDate;

    @Column(name = "UPDATED_DATE")
    private ZonedDateTime updatedDate;

    @Builder
    public Notice(BigDecimal noticeId, NoticeKind noticeKind, String title, Exchange exchange, String url, ZonedDateTime createdDate, ZonedDateTime updatedDate) {
        this.noticeId = noticeId;
        this.noticeKind = noticeKind;
        this.title = title;
        this.exchange = exchange;
        this.url = url;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public enum NoticeKind implements EntityEnumModel {
        ALL("전체"),
        NOTICE("공지사항"),
        EVENT("이벤트");

        String kind;

        NoticeKind(String kind) {
            this.kind = kind;
        }

        public String getKey() {
            return name();
        }

        public String getValue() {
            return kind;
        }
    }
}
