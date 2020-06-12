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
    List<OrderVO> getAllOrders();

    /**
     * 获得所有异常订单
     *
     * @return
     */
    List<OrderVO> getAbnormalOrders();

    /**
     * 获得指定用户的所有订单信息
     *
     * @param userid
     * @return
     */
    List<OrderVO> getUserOrders(int userid);

    /**
     * 撤销订单
     *
     * @param orderid
     * @return
     */
    ResponseVO annulOrder(int orderid);

    /**
     * 撤销异常订单，恢复客户信用值
     *
     * @param orderid
     * @return
     */
    ResponseVO annulAbnormalOrder(int orderid, int userid);

    /**
     * 查看酒店的所有订单
     *
     * @param hotelId
     * @return
     */
    List<OrderVO> getHotelOrders(Integer hotelId);

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
    List<OrderVO> getUserOrdersInCertainHotel(Integer userId, Integer hotelId);


    /**
     * 修改订单状态
     *
     * @param orderId
     * @param orderStatus
     * @return
     */
    int changeOrderStatus(int orderId, int orderStatus);

    /**
     * 恢复房间数
     *
     * @param orderId
     * @return
     */
    void restoreRoom(int orderId);

    /**
     * 设置订单是否被评价过，true为已评价
     *
     * @param data
     */
    void setOrderHasCommented(int orderId, boolean data);


}
