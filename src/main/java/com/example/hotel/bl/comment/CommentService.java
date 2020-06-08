package com.example.hotel.bl.comment;


import com.example.hotel.vo.CommentVO;

import java.util.List;


public interface CommentService {
    /**
     * 获取酒店的住客评论列表
     * @param hotelId
     * @return
     */
    List<CommentVO> getHotelCommentList(int hotelId);

    /**
     * 添加住客评论
     * @param commentVO
     * @return
     */
    CommentVO addComment(CommentVO commentVO);
}
