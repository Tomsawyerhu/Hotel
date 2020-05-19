package com.example.hotel.controller.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-02-29
 */


@RestController()
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO){
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders(){
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userid}/getUserOrders")
    public  ResponseVO retrieveUserOrders(@PathVariable int userid){
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid){

        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{orderid}/getOrder")
    public ResponseVO retrieveOrder(@PathVariable int orderid){
        System.out.println(orderService.getOrderByOrderId(orderid));
        return ResponseVO.buildSuccess(orderService.getOrderByOrderId(orderid));
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    @GetMapping("/{userId}/{hotelId}/getUserOrdersInCertainHotel")
    public ResponseVO retrieveUserOrdersInCertainHotel(@PathVariable Integer userId,@PathVariable Integer hotelId){
        return ResponseVO.buildSuccess(orderService.getUserOrdersInCertainHotel(userId,hotelId));
    }


}
