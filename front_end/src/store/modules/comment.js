import {
    addCommentAPI,
    addCommentLikeNumAPI,
    getHotelCommentListAPI,
    addCommentDislikeNumAPI,
    subCommentDislikeNumAPI,
    subCommentLikeNumAPI,
    getCommentByIdAPI
} from '@/api/comment'

import {message} from 'ant-design-vue'



const comment = {
    state: {
        hotelCommentList: [],
        currentComment: {},
        addCommentVisible: false
    },
    mutations: {
        set_hotelCommentList: function (state, data) {
            state.hotelCommentList = data
        },
        set_currentComment: function (state, data) {
            state.currentComment = data
        },
        set_addCommentVisible: function (state, data) {
            state.addCommentVisible = data
        }

    },
    actions: {
        getHotelCommentList: async ({commit}, hotelId) => {
            const res = await getHotelCommentListAPI(hotelId);
            if (res) {
                commit('set_hotelCommentList', res)
            }
        },
        getCommentById: async ({commit}, commentId) => {
            const res = await getCommentByIdAPI(commentId);
            if (res) {
                commit('set_currentComment', res)
            }
        },
        addComment: async ({commit}, data) => {
            const res = await addCommentAPI(data);
        },
        addCommentLikeNum: async ({commit}, data) => {
            const res = await addCommentLikeNumAPI(data);
        },
        addCommentDislikeNum: async ({commit}, data) => {
            const res = await addCommentDislikeNumAPI(data);
        },
        subCommentLikeNum: async ({commit}, data) => {
            const res = await subCommentLikeNumAPI(data);
        },
        subCommentDislikeNum: async ({commit}, data) => {
            const res = await subCommentDislikeNumAPI(data);
        },

    }
}
export default comment