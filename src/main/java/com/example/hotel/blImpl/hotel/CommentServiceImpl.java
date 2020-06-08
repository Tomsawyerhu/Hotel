package com.example.hotel.blImpl.hotel;

import com.example.hotel.bl.hotel.CommentService;
import com.example.hotel.data.hotel.CommentMapper;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    private CommentMapper commentMapper;

    @Override
    public ResponseVO getHotelCommentList(int hotelId) {
        return null;
    }
}
