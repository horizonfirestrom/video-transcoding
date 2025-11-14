package com.imran.videoplatform.billing.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imran.videoplatform.billing.model.BillingRecord;

import java.util.List;

public interface BillingRecordRepository extends JpaRepository<BillingRecord, Long> {

    List<BillingRecord> findByVideoId(Long videoId);
}
