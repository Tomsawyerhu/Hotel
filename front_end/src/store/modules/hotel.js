import { message } from 'ant-design-vue'
import store from '@/store'
import {
    getHotelsAPI,
    getHotelByIdAPI,
    UpdateHotelByIdAPI,
    searchHotelAPI
} from '@/api/hotel'
import {
    reserveHotelAPI
} from '@/api/order'
import {
    orderMatchCouponsAPI,
} from '@/api/coupon'

const hotel = {
    state: {
        hotelList: [

        ],
        hotelListParams: {
            pageNo: 0,
            pageSize: 12
        },
        hotelListLoading: true,
        currentHotelId: '',
        currentHotelInfo: {

        },

        orderModalVisible: false,
        roomEditVisible:false,
        descEditVisible:false,
        addRoomVisible:false,
        currentOrderRoom: {

        },
        orderMatchCouponList: [

        ]
    },
    mutations: {
        set_hotelList: function(state, data) {
            state.hotelList = data
        },
        set_hotelListParams: function(state, data) {
            state.hotelListParams = {
                ...state.hotelListParams,
                ...data,
            }
        },
        set_hotelListLoading: function(state, data) {
            state.hotelListLoading = data
        },
        set_currentHotelId: function(state, data) {
            state.currentHotelId = data
        },
        set_currentHotelInfo: function(state, data) {
            state.currentHotelInfo = {
                ...state.currentHotelInfo,
                ...data,
            }
        },
        set_currentHotelInfo_d: function(state, data) {
            state.currentHotelInfo.description = data
        }
        ,
        set_orderModalVisible: function(state, data) {
            state.orderModalVisible = data
        },
        set_roomEditVisible: function(state, data) {
            state.roomEditVisible = data
        },
        set_descEditVisible: function(state, data) {
            state.descEditVisible = data
        },
        set_currentOrderRoom: function(state, data) {
            state.currentOrderRoom = {
                ...state.currentOrderRoom,
                ...data,
            }
        },
        set_orderMatchCouponList: function(state, data) {
            state.orderMatchCouponList = data
        },
        set_addRoomVisible:function(state, data) {
            state.addRoomVisible = data
        }
    },

    actions: {
        getHotelList: async({commit, state}) => {
            const res = await getHotelsAPI()
            if(res){
                commit('set_hotelList', res)
                commit('set_hotelListLoading', false)
            }
        },
        getHotelById: async({commit, state}) => {
            const res = await getHotelByIdAPI({
                hotelId: state.currentHotelId
            })
            if(res){
                console.log(res)
                commit('set_currentHotelInfo', res)
            }
        },
        addOrder: async({ state, commit }, data) => {
            const res = await reserveHotelAPI(data)
            console.log(res)
            if(res){
                message.success('预定成功')
                commit('set_orderModalVisible', false)
            }
        },
        getOrderMatchCoupons: async({ state, commit }, data) => {
            const res = await orderMatchCouponsAPI(data)
            //这边做一次过滤将所有0值属性改为无
            if(res){
                res.forEach(v=>{
                    for(let property in v){
                        if(v[property]==0){
                            v[property]="无"
                        }
                    }
                })
                console.log(res)
                commit('set_orderMatchCouponList', res)
            }
        },
        updateHotelInfo:async ({state,commit},data)=>{
            const res=await UpdateHotelByIdAPI(data)
            //console.log(res)
            if(res){
                message.success('更新成功')
                commit('set_descEditVisible', false)
            }
        },
        searchHotel:async ({state,commit},data)=>{
            console.log(data)
            const res= await  searchHotelAPI(data)
            console.log(res)
            if(res){
                commit('set_hotelList', res)
                message.success('搜索成功')
            }else{
                message.error('搜索失败')
            }
        }

    }
}

export default hotel