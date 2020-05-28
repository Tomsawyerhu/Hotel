import {
    getAbnormalOrderListAPI,
    cancelAbnormalOrderAPI,
} from '@/api/order'
import {
    addCreditAPI
} from '@/api/user'
import {
    websiteAllCouponsAPI,
    cancelCouponAPI,
    getCouponById,
} from '@/api/coupon'
import {message} from 'ant-design-vue'

const marketingStaff = {
    state: {
        abnormalOrderList: [],
        addWebCouponVisible: false,
        websiteCouponList: [],
        currentCouponId: 0,
        currentCouponInfo: [],
        couponDetailVisible: false,
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
        set_currentCouponId(state, data) {
            state.currentCouponId = data
        },
        set_currentCouponInfo(state, data) {
            state.currentCouponInfo = data
        },
        set_couponDetailVisible(state, data) {
            state.couponDetailVisible = data
        }
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
        getCurrentCouponInfo: async ({state, commit}, couponId) => {
            const res = await getCouponById(couponId)
            if (res) {
                commit('set_currentCouponInfo',res)
            } else {
                message.error('查看失败')
            }
        },

    }
}
export default marketingStaff