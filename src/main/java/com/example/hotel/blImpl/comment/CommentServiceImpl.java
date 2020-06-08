package com.example.hotel.blImpl.comment;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.data.comment.CommentMapper;
import com.example.hotel.po.Comment;
import com.example.hotel.vo.CommentVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private OrderService orderService;

    @Override
    public List<CommentVO> getHotelCommentList(int hotelId) {
        List<CommentVO> res = new ArrayList<>();
        List<Comment> commentList = commentMapper.selectHotelCommentList(hotelId);
        for (Comment comment : commentList) {
            CommentVO commentVO = new CommentVO();
            BeanUtils.copyProperties(comment, commentVO);
            res.add(commentVO);
        }
        if (res.isEmpty()) {
            return null;
        } else {
            return res;
        }
    }

    @Override
    public CommentVO addComment(CommentVO commentVO) {
        Comment comment = new Comment();
        BeanUtils.copyProperties(commentVO, comment);
        //设置评论时间为当前时间
        LocalDateTime now = LocalDateTime.now();
        comment.setCommentDate(now);
        int res = commentMapper.insertComment(comment);
        //设置订单为已评价
        orderService.setOrderHasCommented(comment.getOrderId(),true);
        commentVO.setId(res);
        return commentVO;
    }
}
