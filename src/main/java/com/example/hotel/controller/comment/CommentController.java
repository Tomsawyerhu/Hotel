package com.example.hotel.controller.comment;

import com.example.hotel.bl.comment.CommentService;
import com.example.hotel.vo.CommentVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping("/{hotelId}/getHotelCommentList")
    public ResponseVO getHotelCommentList(@PathVariable int hotelId){
        return ResponseVO.buildSuccess(commentService.getHotelCommentList(hotelId));
    }

    @PostMapping("/addComment")
    public ResponseVO addComment(@RequestBody CommentVO commentVO){
        return ResponseVO.buildSuccess(commentService.addComment(commentVO));
    }

    @PostMapping("/{commentId}/addLikeNum")
    public ResponseVO addCommentLikeNum(@PathVariable int commentId){
        return ResponseVO.buildSuccess(commentService.addCommentLikeNum(commentId));
    }

    @PostMapping("/{commentId}/addDislikeNum")
    public ResponseVO addCommentDislikeNum(@PathVariable int commentId){
        return ResponseVO.buildSuccess(commentService.addCommentDislikeNum(commentId));
    }
    @PostMapping("/{commentId}/subLikeNum")
    public ResponseVO subCommentLikeNum(@PathVariable int commentId){
        return ResponseVO.buildSuccess(commentService.subCommentLikeNum(commentId));
    }
    @PostMapping("/{commentId}/subDislikeNum")
    public ResponseVO subCommentDislikeNum(@PathVariable int commentId){
        return ResponseVO.buildSuccess(commentService.subCommentDislikeNum(commentId));
    }
    @GetMapping("/{commentId}/getComment")
    public ResponseVO getCommentById(@PathVariable int commentId){
        return ResponseVO.buildSuccess(commentService.getCommentById(commentId));
    }
}
