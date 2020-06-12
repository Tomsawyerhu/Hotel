package com.example.hotel.vo;

import java.util.Date;

public class CreditHistoryVO {
    private int userId;
    private double value;
    private int  type;
    private String message;

    public CreditHistoryVO() {
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }


    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
