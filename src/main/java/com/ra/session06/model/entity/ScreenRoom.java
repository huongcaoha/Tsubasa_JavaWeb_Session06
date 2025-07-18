package com.ra.session06.model.entity;

public class ScreenRoom {
    private Long id;
    private String screenRoomName;
    private Integer totalSeat;

    public ScreenRoom() {
    }

    public ScreenRoom(Long id, String screenRoomName, Integer totalSeat) {
        this.id = id;
        this.screenRoomName = screenRoomName;
        this.totalSeat = totalSeat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScreenRoomName() {
        return screenRoomName;
    }

    public void setScreenRoomName(String screenRoomName) {
        this.screenRoomName = screenRoomName;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }
}
