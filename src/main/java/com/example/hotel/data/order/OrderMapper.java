package com.example.hotel.data.order;

import com.example.hotel.po.Order;
import com.example.hotel.vo.OrderVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Mapper
@Repository
public interface OrderMapper {

    int addOrder(Order order);

    List<Order> getAllOrders();

    List<Order> getAbnormalOrders();

    List<Order> getUserOrders(@Param("userid") int userid);

    List<Order> getUserOrdersInCertainHotel(@Param("userid") int userid,@Param("hotelid") int hotelid);

    int annulOrder(@Param("orderid") int orderid);

    Order getOrderById(@Param("orderid") int orderid);

    int updateOrderState(@Param("orderstate")String orderstate,@Param("orderid") int orderid);


}
