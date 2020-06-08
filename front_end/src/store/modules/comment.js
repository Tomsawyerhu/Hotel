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
        currentComment:{},


    },
    mutations: {
        set_hotelCommentList: function(state, data) {
            state.commentList = data
        },
        set_currentComment: function(state, data) {
            state.currentComment = data
        },

    },
    actions: {
        getHotelCommentList: async({commit},hotelId) => {
            const res = getHotelCommentListAPI(hotelId);
            if(res){
                commit('set_hotelCommentList',res)
            }
        },
    }
}
export default comment