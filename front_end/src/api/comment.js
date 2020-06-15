import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}

export function addCommentLikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/addLikeNum`,
        method: 'POST',
    })
}

export function addCommentDislikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/addDislikeNum`,
        method: 'POST',
    })
}

export function subCommentLikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/subLikeNum`,
        method: 'POST',
    })
}

export function subCommentDislikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/subDislikeNum`,
        method: 'POST',
    })
}

export function addCommentAPI(data) {
    return axios({
        url: `${api.commentPre}/addComment`,
        method: 'POST',
        data
    })
}

export function getHotelCommentListAPI(hotelId) {
    return axios({
        url: `${api.commentPre}/${hotelId}/getHotelCommentList`,
        method: 'GET',
    })
}

export function getCommentByIdAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/getComment`,
        method: 'GET',
    })
}




