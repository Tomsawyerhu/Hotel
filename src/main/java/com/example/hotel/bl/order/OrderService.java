package com.example.hotel.bl.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface OrderService {

    /**
     * 预订酒店
     *
     * @param orderVO
     * @return
     */
    ResponseVO addOrder(OrderVO orderVO);

    /**
     * 获得所有订单信息
     *
     * @return
     */
    List<Order> getAllOrders();

    /**
     * 获得所有异常订单
     * @return
     */
    List<Order> getAbnormalOrders();

    /**
     * 获得指定用户的所有订单信息
     *
     * @param userid
     * @return
     */
    List<Order> getUserOrders(int userid);

    /**
     * 撤销订单
     *
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 撤销异常订单，恢复客户信用值
     * @param orderid
     * @return
     */
    ResponseVO annulAbnormalOrder(int orderid,int userid);

    /**
     * 查看酒店的所有订单
     *
     * @param hotelId
     * @return
     */
    List<Order> getHotelOrders(Integer hotelId);

    /**
     * 根据orderId获取相应order
     *
     * @param orderId
     * @return
     */
    OrderVO getOrderByOrderId(int orderId);

    /**
     * 根据userId，hotelId获取客户在某家酒店的订单列表
     *
     * @param userId
     * @param hotelId
     * @return
     */
    List<Order> getUserOrdersInCertainHotel(Integer userId, Integer hotelId);


}
