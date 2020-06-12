package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.blImpl.user.AccountServiceImpl;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.OrderVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BirthdayCouponStrategyImpl implements CouponMatchStrategy {

    @Autowired
    AccountService accountService;

    @Override
    public boolean isMatch(OrderVO orderVO, Coupon coupon) {
        if (coupon.getCouponType() == 1 && orderVO.getPrice() >= coupon.getTargetMoney()) {
            if (orderVO.getHotelId().equals(coupon.getHotelId()) || coupon.getHotelId() == -1) {  //判断该优惠券是否是对应酒店发放或者是网站发放的
                int userId=orderVO.getUserId();
                UserVO userVO=accountService.getUserInfo(userId);
                System.out.println(userVO);
                if(userVO.getMemberType().equals("VIP")&&userVO.getBirthday().getTime()<new Date().getTime()&&userVO.getBirthday().getTime()>new Date().getTime()-1000*24*60*60){
                    return true;
                }
            }
        }
        return false;
    }
}
