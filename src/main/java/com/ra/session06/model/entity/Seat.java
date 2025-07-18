package com.ra.session06.model.entity;

public class Seat {
    private String seatName ;
    private boolean status;

    public Seat() {
    }

    public Seat(String seatName, boolean status) {
        this.seatName = seatName;
        this.status = status;
    }

    public String getSeatName() {
        return seatName;
    }

    public void setSeatName(String seatName) {
        this.seatName = seatName;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
