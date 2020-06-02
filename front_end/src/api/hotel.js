import {axios} from '@/utils/request'

const api = {
    hotelPre: '/api/hotel'
}

export function getHotelsAPI() {
    return axios({
        url: `${api.hotelPre}/all`,
        method: 'get',
    })
}

export function getHotelByIdAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.hotelId}/detail`,
        method: 'GET',
    })
}

export function UpdateHotelByIdAPI(data) {
    return axios({
        url: `${api.hotelPre}/UpdateDetail`,
        method: 'POST',
        data
    })
}

export function getUserOrderedHotelsAPI(param) {
    return axios({
        url: `${api.hotelPre}/${param.userId}/userOrdered`,
        method: 'GET',
    })
}

//搜索酒店的api
export function searchHotelAPI(param) {
    return axios({
        url: `${api.hotelPre}/searchHotel`,
        method: 'POST',
        data:param
    })
}
