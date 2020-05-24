package com.example.hotel.enums;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */
public enum UserType {
    Client("1"),
    HotelManager("2"),
    Admin("3"),   //原本3对应的是manager，改成Admin试一试
    MarketingStaff("4");
    private String value;

    UserType(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return value;
    }

}
