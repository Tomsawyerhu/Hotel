package com.example.hotel.po;

import com.example.hotel.enums.RoomType;

public class HotelRoom {
    private Integer id;
    private String roomType;
    private Integer hotelId;
    private double price;
    private String breakfast ;
    private Integer peopleNum;
    /**
     * 当前剩余可预定房间数
     */
    private int curNum;
    /**
     * 某类型房间总数
     */
    private int total;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCurNum() {
        return curNum;
    }

    public void setCurNum(int curNum) {
        this.curNum = curNum;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getbreakfast() {
        return breakfast;
    }

    public void setbreakfast(String breakfast) {
        this.breakfast = breakfast;
    }

    public int getpeopleNum() {
        return peopleNum;
    }

    public void setpeopleNum(int peopleNum) {
        this.peopleNum = peopleNum;
    }
}
