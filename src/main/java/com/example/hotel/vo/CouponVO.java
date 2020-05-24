package com.example.hotel.vo;

public class CouponVO {
    private Integer id;
    private String description;
    private Integer status;
    private String couponName;
    private Integer couponType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getCouponName() {
        return couponName;
    }

    public void setCouponName(String couponName) {
        this.couponName = couponName;
    }

    public Integer getCouponType() {
        return couponType;
    }

    public void setCouponType(Integer type) {
        this.couponType = type;
    }

    @Override
    public String toString() {
        return "CouponVO{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", name='" + couponName + '\'' +
                ", type=" + couponType+
                '}';
    }
}
