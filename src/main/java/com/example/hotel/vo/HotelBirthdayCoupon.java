package com.example.hotel.vo;

public class HotelBirthdayCoupon extends CouponVO{
    private Integer hotelId;

    private Integer targetMoney;

    private Integer discountMoney;

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

    public Integer getDiscountMoney() {
        return discountMoney;
    }

    public void setDiscountMoney(Integer discountMoney) {
        this.discountMoney = discountMoney;
    }

    @Override
    public String toString() {
        return super.toString()+"&&"+"HotelBirthdayCouponVO{" +
                "hotelId=" + hotelId +
                ", targetMoney=" + targetMoney +
                ", discountMoney=" + discountMoney +
                '}';
    }
}
