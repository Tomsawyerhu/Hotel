package com.example.hotel.controller.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
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
    @Autowired
    private AccountService accountService;

    @PostMapping("/addOrder")
    public ResponseVO reserveHotel(@RequestBody OrderVO orderVO) {
        return orderService.addOrder(orderVO);
    }

    @GetMapping("/getAllOrders")
    public ResponseVO retrieveAllOrders() {
        return ResponseVO.buildSuccess(orderService.getAllOrders());
    }

    @GetMapping("/{userid}/getUserOrders")
    public ResponseVO retrieveUserOrders(@PathVariable int userid) {
        return ResponseVO.buildSuccess(orderService.getUserOrders(userid));
    }

    @GetMapping("/{orderid}/annulOrder")
    public ResponseVO annulOrder(@PathVariable int orderid) {

        return orderService.annulOrder(orderid);
    }

    @GetMapping("/{orderid}/{userid}/annulAbnormalOrder")
    public ResponseVO annulAbnormalOrder(@PathVariable int orderid, @PathVariable int userid) {
        return orderService.annulAbnormalOrder(orderid, userid);
    }

    @GetMapping("/{orderid}/getOrder")
    public ResponseVO retrieveOrder(@PathVariable int orderid) {
        //System.out.println(orderService.getOrderByOrderId(orderid));
        return ResponseVO.buildSuccess(orderService.getOrderByOrderId(orderid));
    }

    @GetMapping("/{hotelId}/allOrders")
    public ResponseVO retrieveHotelOrders(@PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getHotelOrders(hotelId));
    }

    @GetMapping("/{userId}/{hotelId}/getUserOrdersInCertainHotel")
    public ResponseVO retrieveUserOrdersInCertainHotel(@PathVariable Integer userId, @PathVariable Integer hotelId) {
        return ResponseVO.buildSuccess(orderService.getUserOrdersInCertainHotel(userId, hotelId));
    }

    @GetMapping("/getAbnormalOrders")
    public ResponseVO retrieveAbnormalOrders() {
        return ResponseVO.buildSuccess(orderService.getAbnormalOrders());
    }

    @GetMapping("/checkIn/{orderId}/{amount}")
    public ResponseVO checkIn(@PathVariable Integer orderId,@PathVariable String amount) {
        System.out.println(orderId);
        int userId= orderService.changeOrderStatus(orderId,2);
        if(userId<0) return ResponseVO.buildFailure("更改状态失败");
        else {
            return accountService.addCreditAsWorker(userId,Double.parseDouble(amount));
        }
    }

    @GetMapping("/badOrder/{orderId}")
    public ResponseVO badOrder(@PathVariable Integer orderId){
        int userId= orderService.changeOrderStatus(orderId,4);
        if(userId<0) return ResponseVO.buildFailure("更改状态失败");
        else {
            return ResponseVO.buildSuccess("更改状态成功");
        }
    }

    @GetMapping("/checkOut/{orderId}")
    public ResponseVO checkOut(@PathVariable Integer orderId){
        int userId= orderService.changeOrderStatus(orderId,5);
        if(userId<0) return ResponseVO.buildFailure("更改状态失败");
        else {
            orderService.restoreRoom(orderId);
            return ResponseVO.buildSuccess("更改状态成功");
        }
    }




}
