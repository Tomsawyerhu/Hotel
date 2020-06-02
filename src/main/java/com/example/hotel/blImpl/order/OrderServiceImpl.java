package com.example.hotel.blImpl.order;

import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.bl.hotel.RoomService;
import com.example.hotel.bl.order.OrderService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.blImpl.hotel.HotelServiceImpl;
import com.example.hotel.data.order.OrderMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Annotation;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class OrderServiceImpl implements OrderService {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "删除失败";
    private final static String ORDER_DONT_EXIST = "订单不存在";
    @Autowired
    OrderMapper orderMapper;
    @Autowired
    HotelService hotelService;
    @Autowired
    AccountService accountService;
    @Autowired
    OrderService orderService;
    @Autowired
    RoomService roomService;

    @Override
    public ResponseVO addOrder(OrderVO orderVO) {
        int reserveRoomNum = orderVO.getRoomNum();
        int curNum = hotelService.getRoomCurNum(orderVO.getHotelId(), orderVO.getRoomType());
        System.out.println(orderVO.getHotelId());
        System.out.println(orderVO.getRoomType());
        if (reserveRoomNum > curNum) {
            return ResponseVO.buildFailure(ROOMNUM_LACK);
        }
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            Date date = new Date(System.currentTimeMillis());
            String curdate = sf.format(date);
            orderVO.setCreateDate(curdate);
            orderVO.setOrderState("已预订");
            UserVO user = accountService.getUserInfo(orderVO.getUserId());
            orderVO.setClientName(user.getUserName());
            orderVO.setPhoneNumber(user.getPhoneNumber());
            Order order = new Order();
            BeanUtils.copyProperties(orderVO, order);
            orderMapper.addOrder(order);
            hotelService.subRoomNum(orderVO.getHotelId(), orderVO.getRoomType(), orderVO.getRoomNum()); //这里的update是原有房间数减去这次订单房间数
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(RESERVE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public List<OrderVO> getAllOrders() {
        List<Order> orders = orderMapper.getAllOrders();
        List<OrderVO> orderVOS = new ArrayList<>();
        for(Order order: orders){
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Override
    public List<OrderVO> getAbnormalOrders() {
        List<Order> orders = orderMapper.getAbnormalOrders();
        List<OrderVO> orderVOS = new ArrayList<>();
        for(Order order: orders){
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Override
    public List<OrderVO> getUserOrders(int userid) {
        List<Order> orders = orderMapper.getUserOrders(userid);
        List<OrderVO> orderVOS = new ArrayList<>();
        for(Order order: orders){
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Override
    public List<OrderVO> getUserOrdersInCertainHotel(Integer userId, Integer hotelId) {
        List<Order> orders = orderMapper.getUserOrdersInCertainHotel(userId, hotelId);
        List<OrderVO> orderVOS = new ArrayList<>();
        for(Order order: orders){
            OrderVO orderVO = new OrderVO();
            BeanUtils.copyProperties(order,orderVO);
            orderVOS.add(orderVO);
        }
        return orderVOS;
    }

    @Override
    public int changeOrderStatus(int orderId, int orderStatus) {
        System.out.println(orderStatus);
        Order order = orderMapper.getOrderById(orderId);
        if(order==null){
            return -1;
        }
        if(orderStatus==1){
            orderMapper.updateOrderState("已预订",orderId);
            return order.getUserId();
        }else if(orderStatus==2){
            orderMapper.updateOrderState("已执行",orderId);
            return order.getUserId();
        }else if(orderStatus==3){
            orderMapper.updateOrderState("已撤销",orderId);
            return order.getUserId();
        }else if(orderStatus==4){
            orderMapper.updateOrderState("异常",orderId);
            return order.getUserId();
        }else if(orderStatus==5){
            orderMapper.updateOrderState("已退房",orderId);
            return order.getUserId();
        } else{
            return -2;
        }
    }

    @Override
    public void restoreRoom(int orderId) {
        Order order = orderMapper.getOrderById(orderId);
        int hotelId=order.getHotelId();
        String roomType=order.getRoomType();
        int roomNum=order.getRoomNum();
        roomService.addRoomNum(hotelId,roomType,roomNum);
    }

    @Override
    public OrderVO getOrderByOrderId(int orderId) {
        Order order = new Order();
        try {
            order = orderMapper.getOrderById(orderId);
        }catch (Exception e){
            System.out.println(ORDER_DONT_EXIST);
            return null;
        }
        OrderVO orderVO = new OrderVO();
        BeanUtils.copyProperties(order, orderVO);
        return orderVO;
    }


    @Override
    public ResponseVO annulOrder(int orderid) {
        //取消订单逻辑的具体实现（注意可能有和别的业务类之间的交互）
        Order order = orderMapper.getOrderById(orderid);
        //房间数
        int roomNum = order.getRoomNum();
        //酒店编号
        int hotelId = order.getHotelId();
        //房间类型
        String roomType = order.getRoomType();

        try {
            //删除订单,但是还没有考虑当前时间已经超过订单checkInDate,照理说超过订单checkInDate应该变为异常订单，这时撤销会报错，我还没有实现
            orderMapper.annulOrder(orderid);
            accountService.subCreditByAnnulOrder(order.getUserId(), order);
            //更新相应酒店客房信息,增加剩余房间数
            roomService.addRoomNum(hotelId, roomType, roomNum);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO annulAbnormalOrder(int orderid, int userid) {
        Order order = orderMapper.getOrderById(orderid);
        try {
            orderMapper.annulOrder(orderid);
            //恢复客户因该笔订单被扣除的全部信用值，与房间数量的交互没有放在这里，酒店管理人员把订单设置为异常是就返还了房间数量
            double amount = order.getPrice();
            accountService.addCreditByAnnulAbnormalOrder(userid, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ANNUL_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }


    /**
     * @param hotelId
     * @return
     */
    @Override
    public List<OrderVO> getHotelOrders(Integer hotelId) {
        List<OrderVO> orders = orderService.getAllOrders();
        return orders.stream().filter(order -> order.getHotelId().equals(hotelId)).collect(Collectors.toList());
    }

}
