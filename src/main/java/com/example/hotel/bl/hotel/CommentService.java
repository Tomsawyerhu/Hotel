package com.example.hotel.bl.hotel;

import com.example.hotel.vo.ResponseVO;



public interface CommentService {
    /**
     * 获取酒店的住客评论列表
     * @param hotelId
     * @return
     */
    ResponseVO getHotelCommentList(int hotelId);
}
