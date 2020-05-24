import {
    getAbnormalOrderListAPI,
    cancelAbnormalOrderAPI,
} from '@/api/order'
import {
    addCreditAPI
} from'@/api/user'
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
        cancelAbnormalOrder: async ({state, dispatch},data) => {
            const res = await cancelAbnormalOrderAPI(data.orderId,data.userId)
            if (res) {
                dispatch('getAbnormalOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        addCredit: async({ state, commit },data) => {
            const res = await addCreditAPI(data)
            if(res){
                message.success('充值成功')
            }else{
                message.error('充值失败')
            }
        },
    }
}
export default marketingStaff