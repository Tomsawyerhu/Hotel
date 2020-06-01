package com.example.hotel.blImpl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TargetMoneyCouponStrategyImplTest {
    @Autowired
    private TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;

    @Test
    @Transactional
    public void isMatchTest() {
        OrderVO orderVO = new OrderVO();
        Coupon coupon1 = new Coupon(); //匹配
        Coupon coupon2 = new Coupon(); //不匹配
        orderVO.setHotelId(1);
        orderVO.setPrice(500.0);
        coupon1.setHotelId(1);
        coupon2.setHotelId(1);
        coupon1.setCouponType(3);
        coupon2.setCouponType(3);
        coupon1.setTargetMoney(400.0);
        coupon1.setTargetMoney(600.0);
        assertEquals(true, targetMoneyCouponStrategy.isMatch(orderVO, coupon1));
        assertEquals(false, targetMoneyCouponStrategy.isMatch(orderVO, coupon2));
    }
}
