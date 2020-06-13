import router, {resetRouter} from '@/router'
import {removeToken, setToken} from '@/utils/auth'
import {message} from 'ant-design-vue'
import {getUserInfoAPI, loginAPI, registerAPI, updateUserInfoAPI} from '@/api/user'

import {cancelOrderAPI, getOrderDetailsAPI, getUserOrdersAPI,} from '@/api/order'
import {modifyPasswordAPI,getCreditHistoriesAPI,addCreditHistoryAPI,getUserInfoByEmailAPI,changeCreditAPI,addMemberAPI} from '@/api/user';
import {getUserOrderedHotelsAPI} from '@/api/hotel';

const getDefaultState = () => {
    return {
        userId: '',
        userInfo: {},
        userHotelList: [],
        userOrderList: [],
        creditList:[],
        currentAccountId:'',
        registMemberVisible: false,
        registMemberParams: {
            password: '',
            memberType: '',
            birthday: '',
            companyName:'',
        },
    }
}

const user = {
    state: getDefaultState(),

    mutations: {
        reset_state: function (state) {
            state.token = '',
                state.userId = '',
                state.userInfo = {},
                state.userHotelList = [],
                state.userOrderList = []
        },
        set_token: function (state, token) {
            state.token = token
        },
        set_email: (state, data) => {
            state.email = data
        },
        set_userId: (state, data) => {
            state.userId = data
        },
        set_user_hotel_id: (state, data) => {
            state.user_hotel_id = data
        },
        set_userInfo: (state, data) => {
            state.userInfo = {
                ...state.userInfo,
                ...data
            }
        },
        set_userHotelList: (state, data) => {
            state.userHotelList = data
        },
        set_userOrderList: (state, data) => {
            state.userOrderList = data
        },
        get_userInfo: (state, data) => {
            return state.userInfo
        },
        set_creditList: (state, data) => {
            state.creditList = data
        },
        set_currentAccountId: (state, data) => {
            state.currentAccountId = data
        },
        set_registMemberVisible: function (state, data) {
            state.registMemberVisible = data
        },
        set_registMemberParams: function (state, data) {
            state.registMemberParams = {
                ...state.registMemberParams,
                ...data,
            }
            /*set_currentCreditInfo:(state,data)=>{
                return state.currentCreditInfo
            },*/
        },
    },
    actions: {
        login: async ({dispatch, commit, state}, userData) => {
            const res = await loginAPI(userData)
            if (res) {
                setToken(res.id)
                commit('set_userId', res.id)
                dispatch('getUserInfo').then(() => {
                    console.log("this.userInfo.userType=" + state.userInfo.userType)
                    //根据userType来选择router.push的路由,要通过promise异步调用，保证userInfo已经更新
                    if (state.userInfo.userType == "MarketingStaff") {
                        router.push('/marketingStaff/manageWeb')
                    } else if (state.userInfo.userType == "Admin") {
                        router.push('/admin/manageUser')
                    } else if (state.userInfo.userType == "HotelManager") {
                        router.push('/hotelManager/hotelDetailEdit/'+state.userInfo.manage_hotelId)
                    } else {
                        router.push('/hotel/hotelList')
                    }
                })
            }
        },
        register: async ({commit}, data) => {
            const res = await registerAPI(data)
            if (res) {
                message.success('注册成功')
            }
        },
        getUserInfoByEmail:async ({commit}, data) => {
            return new Promise((resolve, reject) => {
                getUserInfoByEmailAPI(data).then(response => {
                    const data = response
                    console.log(data)
                    if (!data) {
                        reject('失败')
                    }
                    commit('set_currentAccountId', data.id)

                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        getUserInfoById:async ({commit}, data) => {
            return new Promise((resolve, reject) => {
                getUserInfoAPI(data).then(response => {
                    const data = response
                    if (!data) {
                        reject('失败')
                    }
                    commit('set_targetAccount', data)
                    resolve(data)
                }).catch(error => {
                    reject(error)
                })
            })
        },
        getUserInfo({state, commit}) {
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
        updateUserInfo: async ({state, dispatch}, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await updateUserInfoAPI(params)
            if (res) {
                message.success('修改成功')
                dispatch('getUserInfo')
            }
        },
        //修改密码的方法
        modifyPassword: async ({state, dispatch}, data) => {
            const params = {
                id: state.userId,
                ...data,
            }
            const res = await modifyPasswordAPI(params)
            if (res) {
                message.success('修改成功')
            } else {
                message.error('修改失败')
            }
        },
        getUserOrders: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrdersAPI(data)
            if (res) {
                commit('set_userOrderList', res)
                //console.log(state.userOrderList)
            }
        },
        getUserOrderedHotels: async ({state, commit}) => {
            const data = {
                userId: Number(state.userId)
            }
            const res = await getUserOrderedHotelsAPI(data)
            if (res) {
                commit('set_userHotelList', res)
            }
        },
        cancelOrder: async ({state, dispatch}, orderId) => {
            const res = await cancelOrderAPI(orderId)
            if (res) {
                dispatch('getUserOrders')//dispatch是异步操作action的方法
                message.success('撤销成功')
            } else {
                message.error('撤销失败')
            }
        },
        logout: async ({commit}) => {
            removeToken()
            resetRouter()
            commit('reset_state')
        },
        // remove token
        resetToken({commit}) {
            return new Promise(resolve => {
                removeToken() // must remove  token  first
                commit('reset_state')
                resolve()
            })
        },
        getCreditHistories: async ({state, commit},data) => {
            const res = await getCreditHistoriesAPI(data)
            if (res) {
                console.log(res)
                commit('set_creditList', res)
            }
        },
        addCreditHistory: async ({state, commit,dispatch},data) => {
            const res = await addCreditHistoryAPI(data)
            if (res) {
                console.log(res)
                message.success("更新信用记录成功")
                dispatch('getCreditHistories',this.userId)
            }
        },
        changeCredit:async ({state, commit},data) => {
            const res = await changeCreditAPI(data)
            if (res) {
                console.log(res)
                message.success("更新信用值成功")

            }
        },
        addMember: async({ state, dispatch, commit }) => {
            const res = await addMemberAPI(state.registMemberParams)
            if(res){
                dispatch('getUserInfo');
                commit('set_registMemberParams', {
                    password: '',
                    memberType: '',
                    birthday: '',
                    companyName:'',
                })
                commit('set_registMemberVisible', false)
                message.success('添加成功')
            }else{
                message.error('添加失败,账号密码错误')
            }
        },

    }
}

export default user