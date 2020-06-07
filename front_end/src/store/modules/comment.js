import {
    addCommentAPI,
    addDislikeNumAPI,
    addLikeNumAPI,
    getHotelCommentListAPI
} from '@/api/comment'

import {message} from 'ant-design-vue'

const comment = {
    state: {
        commentList: [],
        currentComment:{},


    },
    mutations: {
        set_commentList: function(state, data) {
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
                commit('set_commentList',res)
            }
        },
    }
}
export default comment