import {axios} from '@/utils/request'

const api = {
    userPre: '/api/user'
}

export function loginAPI(data) {
    return axios({
        url: `${api.userPre}/login`,
        method: 'POST',
        data
    })
}

export function registerAPI(data) {
    return axios({
        url: `${api.userPre}/register`,
        method: 'POST',
        data
    })
}

export function getUserInfoAPI(id) {
    return axios({
        url: `${api.userPre}/${id}/getUserInfo`,
        method: 'GET'
    })
}

export function getUserInfoByEmailAPI(email) {
    return axios({
        url: `${api.userPre}/${email}/getUserInfoByEmail`,
        method: 'GET'
    })
}

export function updateUserInfoAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/update`,
        method: 'POST',
        data
    })
}

export function modifyPasswordAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/userInfo/modifyPassword`,
        method: 'POST',
        data
    })
}

export function getCreditHistoriesAPI(data) {
    return axios({
        url: `${api.userPre}/${data}/getCreditHistories`,
        method: 'GET'
    })
}

export function addCreditHistoryAPI(data) {
    return axios({
        url: `${api.userPre}/addCreditHistory`,
        method: 'POST',
        data
    })
}

export function changeCreditAPI(data) {
    return axios({
        url: `${api.userPre}/${data.id}/${data.amount}/${data.type}/changeCredit`,
        method: 'POST'
    })
}

export function addMemberAPI(data) {
    return axios({
        url: `${api.userPre}/${data.password}/${data.memberType}/${data.birthday}/${data.companyName}/addMember`,
        method: 'POST',
        data,
    })
}



