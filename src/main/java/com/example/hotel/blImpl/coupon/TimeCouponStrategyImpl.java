package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import com.fasterxml.jackson.databind.JsonSerializer;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.SimpleTimeZone;


@Service
public class TimeCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种限时优惠策略
     *
     * @param orderVO
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if(coupon.getStartTime()== null &&coupon.getEndTime()== null){
            return false;
        }
        if (coupon.getCouponType() == 4 && (orderVO.getHotelId().equals(coupon.getHotelId()) || orderVO.getHotelId().equals(coupon.getHotelId()))) {
            DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH-mm-ss");
            LocalDateTime orderCheckInDate=LocalDateTime.parse(orderVO.getCheckInDate()+" 00-00-00",df);
            return (orderCheckInDate.isAfter(coupon.getStartTime()) && orderCheckInDate.isBefore(coupon.getEndTime()))||orderCheckInDate.isEqual(coupon.getStartTime())||orderCheckInDate.isEqual(coupon.getEndTime());
        }
        return false;
    }
}
