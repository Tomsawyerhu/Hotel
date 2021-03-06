package com.example.hotel.po;

import com.example.hotel.enums.BizRegion;
import com.example.hotel.enums.HotelStar;
import com.example.hotel.vo.RoomVO;

import java.util.List;

public class Hotel {
    private Integer id;
    private String name;
    private String address;
    /**
     商圈
     */
    private BizRegion bizRegion;
    private HotelStar hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    private Integer managerId;
    private String pictureUrl;

    private List<RoomVO> rooms;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public BizRegion getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(BizRegion bizRegion) {
        this.bizRegion = bizRegion;
    }

    public HotelStar getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(HotelStar hotelStar) {
        this.hotelStar = hotelStar;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public List<RoomVO> getRooms() {
        return rooms;
    }

    public void setRooms(List<RoomVO> rooms) {
        this.rooms = rooms;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String PictureUrl) {
        this.pictureUrl = PictureUrl;
    }

    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", bizRegion=" + bizRegion +
                ", hotelStar=" + hotelStar +
                ", rate=" + rate +
                ", description='" + description + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", managerId=" + managerId +
                ", rooms=" + rooms +
                '}';
    }
    /*private Integer id;
    private String hotelName;
    private String address;
    *//**
     * 商圈
     *//*
    private BizRegion bizRegion;
    private HotelStar hotelStar;
    private Double rate;
    private String description;
    private String phoneNum;
    *//**
     * 酒店管理员id
     *//*
    private Integer managerId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHotelName() { return hotelName; }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BizRegion getBizRegion() {
        return bizRegion;
    }

    public void setBizRegion(BizRegion bizRegion) {
        this.bizRegion = bizRegion;
    }

    public HotelStar getHotelStar() {
        return hotelStar;
    }

    public void setHotelStar(HotelStar hotelStar) {
        this.hotelStar = hotelStar;
    }

    public Double getRate() {
        return rate;
    }

    public void setRate(Double rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getManagerId() {
        return managerId;
    }

    public void setManagerId(Integer managerId) {
        this.managerId = managerId;
    }*/
}

