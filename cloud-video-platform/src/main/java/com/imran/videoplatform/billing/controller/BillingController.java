package com.imran.videoplatform.billing.controller;

import com.imran.videoplatform.billing.dto.BillingRecordDto;
import com.imran.videoplatform.billing.mapper.BillingMapper;
import com.imran.videoplatform.billing.service.BillingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/billing")
public class BillingController {

    private final BillingService billingService;

    public BillingController(BillingService billingService) {
        this.billingService = billingService;
    }

    @GetMapping
    public ResponseEntity<List<BillingRecordDto>> getAll() {
        List<BillingRecordDto> dtos = billingService.getAll()
                .stream()
                .map(BillingMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/video/{videoId}")
    public ResponseEntity<List<BillingRecordDto>> getByVideo(@PathVariable Long videoId) {
        List<BillingRecordDto> dtos = billingService.getByVideoId(videoId)
                .stream()
                .map(BillingMapper::toDto)
                .toList();
        return ResponseEntity.ok(dtos);
    }
}
