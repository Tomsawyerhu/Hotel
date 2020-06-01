package com.example.hotel.vo;

public class RoomVO {
    private Integer id;
    private String roomType;
    private Double price;
    private Integer curNum;
    private Integer total;
    private String breakfast ;
    private Integer peopleNum;

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

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getCurNum() {
        return curNum;
    }

    public void setCurNum(Integer curNum) {
        this.curNum = curNum;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
