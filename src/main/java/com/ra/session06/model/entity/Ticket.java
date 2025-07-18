package com.ra.session06.model.entity;

import java.util.Date;

public class Ticket {
    private Long id;
    private Long customerId;
    private Long scheduleId;
    private String seatName;
    private Double totalMoney;
    private Date createdAt;

    public Ticket() {
    }

    public Ticket(Long id, Long customerId, Long scheduleId, String seatName, Double totalMoney, Date createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.scheduleId = scheduleId;
        this.seatName = seatName;
        this.totalMoney = totalMoney;
        this.createdAt = createdAt;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public Double getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
