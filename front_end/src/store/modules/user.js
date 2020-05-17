import router, {resetRouter} from '@/router'
import {removeToken, setToken} from '@/utils/auth'
import {message} from 'ant-design-vue'
import {getUserInfoAPI, loginAPI, registerAPI, updateUserInfoAPI,} from '@/api/user'

import {cancelOrderAPI, getOrderDetailsAPI, getUserOrdersAPI,} from '@/api/order'
import {modifyPasswordAPI} from '@/api/user';

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {

        },
        userOrderList: [

        ],
        currentOrderId: '',   //用户选择查看的当前订单
        currentOrderInfo: {}

    }
}

const user = {
    state : getDefaultState(),

    mutations: {
        reset_state: function(state) {
            state.token = '',
            state.userId = '',
            state.userInfo = {
                
            },
            state.userOrderList = [],
            state.currentOrderId = '',
                state.currentOrderInfo = {

            }
        },
        set_token: function(state, token){
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        set_currentOrderId: (state,data) =>{
            state.currentOrderId = data
        },
        set_currentOrderDetail:(state,data)=>{
            state.currentOrderInfo=data
        },
    },

    actions: {
        login: async ({ dispatch, commit }, userData) => {
            const res = await loginAPI(userData)
            if(res){
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo')
                router.push('/hotel/hotelList')
            }
        },
        register: async({ commit }, data) => {
            const res = await registerAPI(data)
            if(res){
                message.success('注册成功')
            }
        },
        getUserInfo({ state, commit }) {
            return new Promise((resolve, reject) => {
              getUserInfoAPI(state.userId).then(response => {
                const data = response
                if (!data) {
                  reject('登录已过期，请重新登录')
                }
                commit('set_userInfo', data)
                commit('set_userId', data.id)
                resolve(data)
              }).catch(error => {
                reject(error)
              })
            })
        },
        updateUserInfo: async({ state, dispatch }, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if(res){
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        //修改密码的方法
        modifyPassword: async({state,dispatch},data)=>{
            const params ={
                id:state.userId,
                ...data,
            }
            const res = await modifyPasswordAPI(params)
            if(res){
                message.success('修改成功')
            }else{
                message.error('修改失败')
            }
        },
        getUserOrders: async({ state, commit }) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if(res){
                commit('set_userOrderList', res)
                //console.log(state.userOrderList)
            }
        },
        cancelOrder: async({ state, dispatch }, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if(res) {
                dispatch('getUserOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            }else{
                message.error('撤销失败')
            }
        },
        orderDetails:async ({ state, commit })=> {
            const res=await getOrderDetailsAPI(state.currentOrderId)
            if(res){
                commit('set_currentOrderDetail',res)
            }
        },
        logout: async({ commit }) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
          // remove token
        resetToken({ commit }) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
    }
}

export default user