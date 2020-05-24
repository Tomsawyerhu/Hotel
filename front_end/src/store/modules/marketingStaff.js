import {
    getAbnormalOrderListAPI,
    cancelAbnormalOrderAPI
} from '@/api/order'
import {message} from 'ant-design-vue'

const marketingStaff = {
    state: {
        abnormalOrderList: []
    },
    mutations: {
        set_abnormalOrderList(state, data) {
            state.abnormalOrderList = data
        }
    },
    actions: {
        getAbnormalOrders: async({ state, commit }) => {
            const res = await getAbnormalOrderListAPI()
            if(res){
                commit('set_abnormalOrderList', res)
            }
        },
        cancelAbnormalOrder: async ({state, dispatch}, orderId,userId) => {
            const res = await cancelAbnormalOrderAPI(orderId,userId)
            if (res) {
                dispatch('getAbnormalOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
    }
}
export default marketingStaff