package com.example.hotel.blImpl.coupon;

import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.ResponseVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;


import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TimeCouponStrategyTest {
    @Autowired
    private TimeCouponStrategyImpl timeCouponStrategy;

    @Test
    @Transactional
    public void isMatchTest(){
        OrderVO orderVO = new OrderVO();
        Coupon coupon1 = new Coupon();
        Coupon coupon2 = new Coupon();
        orderVO.setHotelId(1);
        coupon1.setHotelId(1);
        coupon2.setHotelId(1);
        coupon1.setCouponType(4);
        coupon2.setCouponType(4);
        assertEquals(false,timeCouponStrategy.isMatch(orderVO,coupon1));
        assertEquals(false,timeCouponStrategy.isMatch(orderVO,coupon2));
    }
}
