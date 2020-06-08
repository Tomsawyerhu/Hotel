import { axios } from '@/utils/request'
const api = {
    couponPre: '/api/coupon'
}
export function hotelBirthdayAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelBirthday`,
        method: 'POST',
        data,
    })
}
export function hotelTargetMoneyAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTargetMoney`,
        method: 'POST',
        data,
    })
}
export function hotelMultiRoomAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelMultiRoom`,
        method: 'POST',
        data,
    })
}
export function hotelTimeAPI(data) {
    return axios({
        url: `${api.couponPre}/hotelTime`,
        method: 'POST',
        data,
    })
}

export function hotelAllCouponsAPI(hotelId) {
    return axios({
        url: `${api.couponPre}/hotelAllCoupons`,
        method: 'GET',
        params: {hotelId: hotelId},
    })
}

export function websiteAllCouponsAPI() {
    return axios({
        url: `${api.couponPre}/websiteAllCoupons`,
        method: 'GET',
    })
}

export function orderMatchCouponsAPI(params) {
    return axios({
        url: `${api.couponPre}/orderMatchCoupons`,
        method: 'GET',
        params,
    })
}

export function cancelCouponAPI(couponId) {
    return axios({
        url: `${api.couponPre}/${couponId}/cancelCoupon`,
        method: 'POST',
        couponId,
    })
}

export function getCouponById(couponId) {
    return axios({
        url: `${api.couponPre}/${couponId}/getCouponInfo`,
        method: 'GET',
        couponId,
    })
}