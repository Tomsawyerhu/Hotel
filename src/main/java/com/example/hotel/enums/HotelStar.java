package com.example.hotel.enums;

public enum HotelStar {
    Five("五星级"),
    Four("四星级"),
    Three("三星级");

    private String value;

    HotelStar(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }

    public int getStarAsNumber(){
        if(this.value.equals("三星级")) return 3;
        if(this.value.equals("四星级")) return 4;
        if(this.value.equals("五星级")) return 5;
        return -1;
    }
}
