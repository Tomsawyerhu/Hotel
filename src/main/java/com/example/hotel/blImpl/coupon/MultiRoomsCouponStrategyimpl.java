package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import org.springframework.stereotype.Service;

@Service
public class MultiRoomsCouponStrategyimpl implements CouponMatchStrategy {

    /**
     * 判断某个订单是否满足多间优惠策略
     * @param orderVO
     * @return
     */

    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if(coupon.getCouponType() == 2 &&(orderVO.getHotelId().equals(coupon.getHotelId()) || coupon.getHotelId() == -1)){
            if(orderVO.getRoomNum()>=coupon.getTargetRoomNum()){
                return true;
            }
        }
        return false;
    }
}
