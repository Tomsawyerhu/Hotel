package com.example.hotel.vo;


public class HotelMultiRoomCouponVO extends CouponVO {
    private Integer hotelId;
    private double discount;
    private Integer targetRoomNum;

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Integer getTargetRoomNum() {
        return targetRoomNum;
    }

    public void setTargetRoomNum(Integer targetRoomNum) {
        this.targetRoomNum = targetRoomNum;
    }

    @Override
    public String toString() {
        return super.toString()+"&&"+"HotelMultiRoomCouponVO{" +
                "hotelId=" + hotelId +
                ", discount=" + discount +
                ", targetRoomNum=" + targetRoomNum +
                '}';
    }
}
