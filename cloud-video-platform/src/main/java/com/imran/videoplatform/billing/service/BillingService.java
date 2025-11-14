package com.imran.videoplatform.billing.service;

import com.imran.videoplatform.billing.model.BillingRecord;
import com.imran.videoplatform.billing.repository.BillingRecordRepository;
import com.imran.videoplatform.video.model.Video;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BillingService {

    private final BillingRecordRepository billingRecordRepository;

    // simple pricing model
    private static final BigDecimal PRICE_PER_MINUTE = new BigDecimal("0.05");
    private static final BigDecimal PRICE_PER_MB = new BigDecimal("0.001");

    public BillingService(BillingRecordRepository billingRecordRepository) {
        this.billingRecordRepository = billingRecordRepository;
    }

    public BillingRecord createBillingForVideo(Video video) {
        // for now we simulate duration & size
        double durationMinutes = 10 + Math.random() * 20; // 10-30 mins
        double sizeMb = 100 + Math.random() * 900;        // 100-1000 MB

        BigDecimal amount = PRICE_PER_MINUTE.multiply(BigDecimal.valueOf(durationMinutes))
                .add(PRICE_PER_MB.multiply(BigDecimal.valueOf(sizeMb)));

        BillingRecord record = new BillingRecord();
        record.setVideoId(video.getId());
        record.setDurationMinutes(durationMinutes);
        record.setSizeMb(sizeMb);
        record.setAmount(amount);
        record.setCurrency("USD");

        return billingRecordRepository.save(record);
    }

    public List<BillingRecord> getAll() {
        return billingRecordRepository.findAll();
    }

    public List<BillingRecord> getByVideoId(Long videoId) {
        return billingRecordRepository.findByVideoId(videoId);
    }
}
