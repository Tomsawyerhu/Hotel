import { axios } from '@/utils/request'
const api = {
    commentPre: '/api/comment'
}

export function addLikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/addLikeNum`,
        method: 'POST',
    })
}

export function addDislikeNumAPI(commentId) {
    return axios({
        url: `${api.commentPre}/${commentId}/addDislikeNum`,
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



