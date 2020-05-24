import {
    getAbnormalOrderListAPI,
    cancelAbnormalOrderAPI,
} from '@/api/order'
import {
    addCreditAPI
} from '@/api/user'
import {
    websiteAllCouponsAPI,
    cancelCouponAPI
} from '@/api/coupon'
import {message} from 'ant-design-vue'

const marketingStaff = {
    state: {
        abnormalOrderList: [],
        addWebCouponVisible: false,
        websiteCouponList: [],
    },
    mutations: {
        set_abnormalOrderList(state, data) {
            state.abnormalOrderList = data
        },
        set_addWebCouponVisible(state, data) {
            state.addWebCouponVisible = data
        },
        set_websiteCouponList(state, data) {
            state.websiteCouponList = data
        },
    },
    actions: {
        //获取异常订单列表
        getAbnormalOrders: async ({state, commit}) => {
            const res = await getAbnormalOrderListAPI()
            if (res) {
                commit('set_abnormalOrderList', res)
            }
        },
        //获取网站优惠列表
        getWebsiteCouponList: async ({state, commit}) => {
            const res = await websiteAllCouponsAPI()
            if (res) {
                commit('set_websiteCouponList', res)
            }
        },
        //撤销异常订单
        cancelAbnormalOrder: async ({state, dispatch}, data) => {
            const res = await cancelAbnormalOrderAPI(data.orderId, data.userId)
            if (res) {
                dispatch('getAbnormalOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        //撤销优惠券
        cancelCoupon: async ({state, dispatch}, couponId) => {
            const res = await cancelCouponAPI(couponId)
            if (res) {
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        addCredit: async ({state, commit}, data) => {
            const res = await addCreditAPI(data)
            if (res) {
                message.success('充值成功')
            } else {
                message.error('充值失败')
            }
        },
    }
}
export default marketingStaff