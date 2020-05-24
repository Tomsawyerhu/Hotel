package com.example.hotel.vo;

import java.time.LocalDateTime;

public class HotelTimeCouponVO extends CouponVO {
    private String[] discountDate;

    //自行决定是打折还是直接减金额 如果选择打折就把targetMoney变为负数 否则就把discount变为负数

    private double discount=-1;
    private Integer discountMoney=-1;
    private Integer targetMoney=-1;
    private Integer hotelId;

    public String[] getDiscountDate() {
        return discountDate;
    }

    public void setDiscountDate(String[] discountDate) {
        this.discountDate = discountDate;
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

    public Integer getTargetMoney() {
        return targetMoney;
    }

    public void setTargetMoney(Integer targetMoney) {
        this.targetMoney = targetMoney;
    }

    @Override
    public String toString() {
        return super.toString()+"&&"+"HotelTimeCouponVO{" +
                "startTime=" + discountDate[0] +
                ", endTime=" + discountDate[1] +
                ", discount=" + discount +
                ", targetMoney=" + targetMoney +
                ", discountMoney=" + discountMoney +
                ", hotelId=" + hotelId +
                '}';
    }
}
