package com.example.hotel.blImpl.coupon;

import com.example.hotel.bl.coupon.CouponService;
import com.example.hotel.bl.coupon.CouponMatchStrategy;
import com.example.hotel.data.coupon.CouponMapper;
import com.example.hotel.po.Coupon;
import com.example.hotel.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;


@Service
public class CouponServiceImpl implements CouponService {


    private final  TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy;

    private final  TimeCouponStrategyImpl timeCouponStrategy;

    private final MultiRoomsCouponStrategyimpl multiRoomsCouponStrategyimpl;

    private final CouponMapper couponMapper;

    private static List<CouponMatchStrategy> strategyList = new ArrayList<>();

    private static String COUPON_DONT_EXIST="该优惠不存在";

    @Autowired
    public CouponServiceImpl(TargetMoneyCouponStrategyImpl targetMoneyCouponStrategy,
                             TimeCouponStrategyImpl timeCouponStrategy, MultiRoomsCouponStrategyimpl multiRoomsCouponStrategyimpl,
                             CouponMapper couponMapper) {
        this.couponMapper = couponMapper;
        this.targetMoneyCouponStrategy = targetMoneyCouponStrategy;
        this.timeCouponStrategy = timeCouponStrategy;
        this.multiRoomsCouponStrategyimpl = multiRoomsCouponStrategyimpl;
        strategyList.add(targetMoneyCouponStrategy);
        strategyList.add(timeCouponStrategy);
    }



    @Override
    public List<Coupon> getMatchOrderCoupon(OrderVO orderVO) {

        List<Coupon> hotelCoupons = getHotelAllCoupon(orderVO.getHotelId());

        List<Coupon> availAbleCoupons = new ArrayList<>();

        for (int i = 0; i < hotelCoupons.size(); i++) {
            for (CouponMatchStrategy strategy : strategyList) {
                if (strategy.isMatch(orderVO, hotelCoupons.get(i))) {
                    availAbleCoupons.add(hotelCoupons.get(i));
                }
            }
        }

        return availAbleCoupons;
    }

    @Override
    public List<Coupon> getHotelAllCoupon(Integer hotelId) {
        List<Coupon> hotelCoupons = couponMapper.selectByHotelId(hotelId);
        return hotelCoupons;
    }

    @Override
    public List<Coupon> getWebsiteAllCoupons() {
        List<Coupon> websiteCoupons = couponMapper.selectAllWebsiteCoupons();
        return websiteCoupons;
    }

    @Override
    public CouponVO addHotelTargetMoneyCoupon(HotelTargetMoneyCouponVO couponVO) {
        System.out.println(couponVO);
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO,coupon);
        coupon.setTargetMoney(couponVO.getTargetMoney());
        coupon.setDiscountMoney(couponVO.getDiscountMoney());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public CouponVO addHotelTimeCoupon(HotelTimeCouponVO couponVO) {
        System.out.println(couponVO);
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO,coupon);
        DateTimeFormatter formatter=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        coupon.setStartTime(LocalDateTime.parse(couponVO.getDiscountDate()[0]+" 00:00:00",formatter));
        coupon.setEndTime(LocalDateTime.parse(couponVO.getDiscountDate()[1]+" 00:00:00",formatter));
        if(couponVO.getDiscount()>0){
            coupon.setDiscount(couponVO.getDiscount());
        }else{
            coupon.setTargetMoney(couponVO.getTargetMoney());
            coupon.setDiscountMoney(couponVO.getDiscountMoney());
        }
        System.out.println(coupon);
        //System.out.println(coupon.getEndTime());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }


    @Override
    public CouponVO addHotelMultiRoomCoupon(HotelMultiRoomCouponVO couponVO){
        System.out.println(couponVO);
        Coupon coupon = new Coupon();
        BeanUtils.copyProperties(couponVO,coupon);
        coupon.setTargetRoomNum(couponVO.getTargetRoomNum());
        int result = couponMapper.insertCoupon(coupon);
        couponVO.setId(result);
        return couponVO;
    }

    @Override
    public ResponseVO cancelCoupon(int couponId) {
        try{
            couponMapper.cancelCoupon(couponId);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(COUPON_DONT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }

}
