package com.api.web;

import com.api.service.NoticeService;
import com.common.entity.Exchange;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/notices")
public class NoticeRestController {
    private final NoticeService noticeService;

    @GetMapping
    public ResponseEntity getNotices() {
        return new ResponseEntity(noticeService.getNotices(), HttpStatus.OK);
    }

    @GetMapping("/{exchange}")
    public ResponseEntity getNotices(@PathVariable(name = "exchange") Exchange.ExchangeType type) {
        return new ResponseEntity(noticeService.getNotices(type), HttpStatus.OK);
    }
}