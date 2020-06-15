package com.example.hotel.vo;

import java.time.LocalDateTime;

public class CommentVO {
    /**
     * 评论id
     */
    private int id;
    /**
     * 评论所属酒店Id
     */
    private int hotelId;
    /**
     * 评论对应的订单Id
     */
    private int orderId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 评论时间
     */
    private LocalDateTime commentDate;
    /**
     * 用户对该笔订单的评分，只有0-5分的6个离散int值
     */
    private int rate;
    /**
     * 顾客在该订单中预订的房型
     */
    private String roomType;
    /**
     * 顾客预订的房间数
     */
    private String userName;
    private int roomNum;
    /**
     * 评论赞数
     */
    private int likeNum;
    /**
     * 评论踩数
     */
    private int dislikeNum;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public LocalDateTime getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(LocalDateTime commentDate) {
        this.commentDate = commentDate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(int roomNum) {
        this.roomNum = roomNum;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getLikeNum() {
        return likeNum;
    }

    public void setLikeNum(int likeNum) {
        this.likeNum = likeNum;
    }

    public int getDislikeNum() {
        return dislikeNum;
    }

    public void setDislikeNum(int dislikeNum) {
        this.dislikeNum = dislikeNum;
    }
}
