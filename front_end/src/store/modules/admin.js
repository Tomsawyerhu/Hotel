import {
    getManagerListAPI,
    addManagerAPI,
    getClientListAPI,
    getStaffListAPI,
    deleteAccountAPI,
    addStaffAPI,
    deleteHotelAPI
} from '@/api/admin'
import {updateUserInfoAPI}from '@/api/user'
import { message } from 'ant-design-vue'

const admin = {
    state: {
        managerList: [

        ],
        clientList: [

        ],
        staffList: [

        ],
        addManagerModalVisible: false,
        addStaffModalVisible:false,
        addManagerParams: {
            email:'',
            password:'',
            hotelId:0
        },
        UserInfoEditVisible:false,
        targetAccount:{
            id:0,
            email:'',
            password:'',
            userName:'',
            phoneNumber:'',
            credit:'',
            userType:'',
            user_hotel_id:''
        }
    },
    mutations: {
        set_managerList: function(state, data) {
            state.managerList = data
        },
        set_clientList: function(state, data) {
            state.clientList = data
        },
        set_staffList: function(state, data) {
            state.staffList = data
        },
        set_addManagerModalVisible: function(state, data) {
            state.addManagerModalVisible = data
        },
        set_addManagerParams: function(state, data) {
            state.addManagerParams = {
                ...state.addManagerParams,
                ...data,
            }
        },
        set_targetAccount: function(state, data) {
            state.targetAccount = {
                ...state.targetAccount,
                ...data,
            }
        },
        set_addStaffModalVisible:function (state,data) {
            state.addStaffModalVisible=data
        },
        set_UserInfoEditVisible:function (state,data) {
            state.UserInfoEditVisible=data
        },
    },
    actions: {
        updateAccountInfo: async({ state, commit,dispatch }) => {
            console.log(state.targetAccount)
            const res = await updateUserInfoAPI(state.targetAccount)
            if(res){
                dispatch('getManagerList')
                dispatch('getStaffList')
                dispatch('getClientList')
                dispatch('getHotelList')
                //console.log(state.orderList[1])
                message.success('更改成功')
                commit('set_UserInfoEditVisible',false)
            }
            else message.error('更改失败')
        },
        getManagerList: async({ commit }) => {
            const res = await getManagerListAPI()
            if(res){
                commit('set_managerList', res)
            }
        },
        getClientList: async({ commit }) => {
            const res = await getClientListAPI()
            if(res){
                commit('set_clientList', res)
            }
        },
        getStaffList: async({ commit }) => {
            const res = await getStaffListAPI()
            if(res){
                commit('set_staffList', res)
            }
        },
        deleteAccount:async({ state, commit, dispatch }) => {
            const res = await deleteAccountAPI(state.targetAccount.id)
            if(res) {
                commit('set_targetAccount',{
                    id:0,
                    email:'',
                    password:'',
                    userName:'',
                    phoneNumber:'',
                    credit:'',
                    userType:'',
                    user_hotel_id:''
                })
                message.success('删除成功')
                dispatch('getManagerList')
                dispatch('getClientList')
                dispatch('getStaffList')
            }else{
                message.error('删除失败')
            }
        },
        addManager: async({ state, commit, dispatch }) => {
            const res = await addManagerAPI(state.addManagerParams)
            if(res) {
                commit('set_addManagerParams',{
                    email:'',
                    password:'',
                    hotelId:0
                })
                commit('set_addManagerModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
                dispatch('getClientList')
                dispatch('getStaffList')
                dispatch('getHotelList')
            }else{
                message.error('添加失败')
            }
        },
        addStaff: async({ state, commit, dispatch },data) => {
            const res = await addStaffAPI(data)
            if(res) {
                commit('set_addStaffModalVisible', false)
                message.success('添加成功')
                dispatch('getManagerList')
                dispatch('getClientList')
                dispatch('getStaffList')
            }else{
                message.error('添加失败')
            }
        },
        deleteHotelById:async({state,commit,dispatch},data)=>{
            const res = await deleteHotelAPI(data)
            //console.log(state.activeHotelId)
            if(res){
                //刷新页面
                //location.reload();
                dispatch('getHotelList')
                dispatch('getAllOrders')
                message.success('删除成功')
            }else{
                message.error("删除失败")
            }
        },

    }
}
export default admin