package com.imran.videoplatform.billing.mapper;

import com.imran.videoplatform.billing.dto.BillingRecordDto;
import com.imran.videoplatform.billing.model.BillingRecord;

public class BillingMapper {

    public static BillingRecordDto toDto(BillingRecord br) {
        BillingRecordDto dto = new BillingRecordDto();
        dto.setId(br.getId());
        dto.setVideoId(br.getVideoId());
        dto.setAmount(br.getAmount());
        dto.setCurrency(br.getCurrency());
        dto.setDurationMinutes(br.getDurationMinutes());
        dto.setSizeMb(br.getSizeMb());
        dto.setCreatedAt(br.getCreatedAt());
        return dto;
    }
}
