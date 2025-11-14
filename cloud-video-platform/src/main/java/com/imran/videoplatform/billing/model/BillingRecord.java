package com.imran.videoplatform.billing.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "billing_records")
public class BillingRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long videoId;

    private BigDecimal amount;       // total charge
    private String currency;         // e.g. "USD"
    private Double durationMinutes;  // simulated
    private Double sizeMb;           // simulated

    private LocalDateTime createdAt;

    @PrePersist
    public void onCreate() {
        this.createdAt = LocalDateTime.now();
    }

    // getters & setters...

    public Long getId() { return id; }

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
}
