import { axios } from '@/utils/request'
const api = {
    hotelPre: '/api/hotel'
}
export function addRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/roomInfo`,
        method: 'POST',
        data,
    })
}
export function addHotelAPI(data) {
    return axios({
        url: `${api.hotelPre}/addHotel`,
        method: 'POST',
        data,
    })
}
export function UpdateHotelByIdAPI(data) {
    return axios({
        url: `${api.hotelPre}/UpdateDetail`,
        method: 'POST',
        data
    })
}
export function updateRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/updateRoom`,
        method: 'POST',
        data,
    })
}
export function addNewRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/addNewRoom`,
        method: 'POST',
        data,
    })
}
export function deleteRoomAPI(data) {
    return axios({
        url: `${api.hotelPre}/deleteRoom/${data}`,
        method: 'POST',
    })
}
