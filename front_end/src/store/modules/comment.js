import {
    addCommentAPI,
    addDislikeNumAPI,
    addLikeNumAPI,
    getHotelCommentListAPI
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
            const res = getHotelCommentListAPI(hotelId);
            if (res) {
                commit('set_hotelCommentList', res)
            }
        },
        addComment: async ({commit}, data) => {
            const res = addCommentAPI(data);
        },
    }
}
export default comment