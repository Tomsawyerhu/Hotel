package com.example.hotel.vo;

import java.time.LocalDateTime;

public class TimeCouponVO extends CouponVO {
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    //自行决定是打折还是直接减金额 如果选择打折就把targetMoney变为负数 否则就把discount变为负数

    private double discount;
    private Integer discountMoney;
    private Integer hotelId;

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }
}
