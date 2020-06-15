package com.example.hotel.data.comment;

import com.example.hotel.po.Comment;
import com.example.hotel.vo.ResponseVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    List<Comment> selectHotelCommentList(int hotelId);

    int insertComment(Comment comment);

    void updateCommentLikeNum(int commentId,int amount);

    void updateCommentDislikeNum(int commentId,int amount);

    Comment selectComment(int commentId);
}
