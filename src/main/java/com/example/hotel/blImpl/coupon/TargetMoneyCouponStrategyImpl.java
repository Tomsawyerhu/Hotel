package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.CouponVO;
import com.example.hotel.vo.HotelTargetMoneyCouponVO;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class TargetMoneyCouponStrategyImpl implements CouponMatchStrategy {


    /**
     * 判断某个订单是否满足某种满减金额优惠策略
     * @param orderVO
     * @param coupon
     * @return
     */
    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {

        if (coupon.getCouponType() == 3 && orderVO.getPrice() >= coupon.getTargetMoney()) {
            /*if (orderVO.getHotelId().equals(coupon.getHotelId()) || coupon.getHotelId() == -1) {  //判断该优惠券是否是对应酒店发放或者是网站发放的
                return true;
            }*/
            return true;
        }
        return false;
    }
}
