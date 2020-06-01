package com.example.hotel.blImpl.order;

import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class OrderServiceImplTest {
    private final static String RESERVE_ERROR = "预订失败";
    private final static String ROOMNUM_LACK = "预订房间数量剩余不足";
    private final static String ANNUL_ERROR = "删除失败";
    @Autowired
    private OrderServiceImpl orderService;

    @Test
    @Transactional
    public void getUserOrdersTest() {
        List<OrderVO> orderVOList = orderService.getUserOrders(); //随便选一个只有一个订单的用户
        assertEquals(, orderVOList.get(0).getId()); //预期填该用户那次订单的订单id
    }

    @Test
    @Transactional
    public void getOrderByIdTest() {
        OrderVO orderVO1 = orderService.getOrderByOrderId(1);
        assertEquals(1, (int) orderVO1.getId());
        OrderVO orderVO2 = orderService.getOrderByOrderId(10000);
        assertNull(orderVO2);
    }

    @Test
    @Transactional
    public void getHotelOrdersTest(){
        List<OrderVO> orderVOList = orderService.getHotelOrders(); //选一个只被下过一次订单的酒店
        assertEquals(, orderVOList.get(0).getId()); //预期填那次订单的订单id
    }
}
