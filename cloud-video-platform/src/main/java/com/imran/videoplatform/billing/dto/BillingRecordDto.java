package com.imran.videoplatform.billing.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class  BillingRecordDto {

    private Long id;
    private Long videoId;
    private BigDecimal amount;
    private String currency;
    private Double durationMinutes;
    private Double sizeMb;
    private LocalDateTime createdAt;

    // getters & setters...
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getVideoId() { return videoId; }
    public void setVideoId(Long videoId) { this.videoId = videoId; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public Double getDurationMinutes() { return durationMinutes; }
    public void setDurationMinutes(Double durationMinutes) { this.durationMinutes = durationMinutes; }

    public Double getSizeMb() { return sizeMb; }
    public void setSizeMb(Double sizeMb) { this.sizeMb = sizeMb; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
