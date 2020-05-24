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