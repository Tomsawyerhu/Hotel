import { axios } from '@/utils/request'
const api = {
    orderPre: '/api/order'
}
export function reserveHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/addOrder`,
        method: 'POST',
        data,
    })
}
export function getAllOrdersAPI() {
    return axios({
        url: `${api.orderPre}/getAllOrders`,
        method: 'GET',
    })
}
export function getUserOrdersAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/getUserOrders`,
        method: 'GET',
    })
}
export function getUserOrdersInCertainHotelAPI(data) {
    return axios({
        url: `${api.orderPre}/${data.userId}/${data.hotelId}/getUserOrdersInCertainHotel`,
        method: 'GET',
    })
}
export function cancelOrderAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/annulOrder`,
        method: 'GET',
    })
}

export function getOrderDetailsAPI(orderId) {
    return axios({
        url: `${api.orderPre}/${orderId}/getOrder`,
        method: 'GET',
    })

}

export function getAbnormalOrderListAPI() {
    return axios({
        url: `${api.orderPre}/getAbnormalOrders`,
        method: 'GET',
    })
}

//撤销异常订单，恢复用户该次被扣除的全部信用值
export function cancelAbnormalOrderAPI(orderId,userId) {
    return axios({
        url: `${api.orderPre}/${orderId}/${userId}/annulAbnormalOrder`,
        method: 'GET',
    })
}

//更改订单状态至已入住并增加信用分 1:已预定 2:已入住 3:已撤销 4:异常
export function checkInAPI(data) {
    return axios({
        url: `${api.orderPre}/checkIn/${data.orderId}/${data.credit}`,
        method: 'GET',
    })
}

//置为异常
export function badOrderAPI(data) {
    return axios({
        url: `${api.orderPre}/badOrder/${data.orderId}`,
        method: 'GET',
    })
}

//办理退房
export function checkOutAPI(data) {
    return axios({
        url: `${api.orderPre}/checkOut/${data.orderId}`,
        method: 'GET',
    })
}
