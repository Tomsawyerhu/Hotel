import {addHotelAPI, addRoomAPI,deleteHotelAPI,updateRoomAPI,deleteRoomAPI,UpdateHotelByIdAPI} from '@/api/hotelManager'
import {getAllOrdersAPI,cancelOrderAPI} from '@/api/order'
import {hotelAllCouponsAPI, hotelTargetMoneyAPI,hotelTimeAPI,hotelMultiRoomAPI,hotelBirthdayAPI} from '@/api/coupon'
import {message} from 'ant-design-vue'
import {updateUserInfoAPI} from "../../api/user";
import {getHotelByIdAPI} from "../../api/hotel";

const hotelManager = {
    state: {
        orderList: [],
        addHotelParams: {
            name: '',
            address: '',
            bizRegion:'XiDan',
            hotelStar:'',
            rate: 0,
            description:'',
            phoneNum:'',
            managerId:'',
            pictureUrl:''
        },
        addHotelModalVisible: false,
        addRoomParams: {
            hotelId: '',
            price: '',
            total: 0,
            curNum: 0,
        },
        addRoomModalVisible: false,
        couponVisible: false,
        addCouponVisible: false,
        activeHotelId: 0,
        couponList: [],
        currentRoom: {
            id:'',
            roomType:'',
            price:0,
            curNum:0,
            total:0,
            breakfast:'',
            peopleNum:0,
            hotelId:0
        },
    },
    mutations: {
        set_orderList: function(state, data) {
            state.orderList = data
        },
        set_addHotelModalVisible: function(state, data) {
            state.addHotelModalVisible = data
        },
        set_addHotelParams: function(state, data) {
            state.addHotelParams = {
                ...state.addHotelParams,
                ...data,
            }
        },
        set_addRoomModalVisible: function(state, data) {
            state.addRoomModalVisible = data
        },
        set_addRoomParams: function(state, data) {
            state.addRoomParams = {
                ...state.addRoomParams,
                ...data,
            }
        },
        set_couponVisible: function(state, data) {
            state.couponVisible = data
        },
        set_activeHotelId: function(state, data) {
            state.activeHotelId = data
        },
        set_couponList: function(state, data) {
            state.couponList = data
        },
        set_addCouponVisible: function(state, data) {
            state.addCouponVisible =data
        },
        set_currentRoom:function (state,data) {
            state.currentRoom={
                ...state.currentRoom,
                ...data
            }
        },

    },
    actions: {
        getAllOrders: async({ state, commit }) => {
            const res = await getAllOrdersAPI()
            if(res){
                commit('set_orderList', res)
                //console.log(state.orderList[1])
            }
        },
        addHotel: async({ state, dispatch, commit }) => {
            const res = await addHotelAPI(state.addHotelParams)
            if(res){
                dispatch('getHotelList');
                commit('set_addHotelParams', {
                    name: '',
                    address: '',
                    bizRegion:'XiDan',
                    hotelStar:'',
                    rate: 0,
                    description:'',
                    phoneNum:'',
                    managerId:'',
                    pictureUrl:''
                })
                commit('set_addHotelModalVisible', false)
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        updateHotelInfo:async ({state,commit,dispatch},data)=>{
            console.log(data)
            const res=await UpdateHotelByIdAPI(data)
            console.log(res)
            if(res){
                message.success('更新成功')
                commit('set_descEditVisible', false)
                dispatch('getHotelById')
            }
            else message.error('更新失败')
        },
        updateRoomInfo: async({ state, commit,dispatch },data) => {
            //console.log(data)
            const res = await updateRoomAPI(data)
            console.log(res)
            if(res){
                message.success('更新成功')
                commit('set_roomEditVisible', false)
            }
            else{
                message.error('更新失败')
            }
        },
        addNewRoom:async ({state,commit,dispatch},data)=>{
            const res=await addRoomAPI(data)
            //console.log(res)
            if(res){
                commit('set_addRoomVisible', false)
                dispatch('getHotelById')
                message.success('添加成功')
            }
            else{
                message.error('添加失败')
            }
        },
        addRoom: async({ state, dispatch, commit }) => {
            const res = await addRoomAPI(state.addRoomParams)
            //console.log(res)
            if(res){
                commit('set_addRoomModalVisible', false)
                commit('set_addRoomParams', {
                    roomType: '',
                    hotelId: '',
                    price: '',
                    total: 0,
                    curNum: 0,
                })
                message.success('添加成功')
            }else{
                message.error('添加失败')
            }
        },
        deleteRoom:async({ state, commit },data) => {
            const res = await deleteRoomAPI(data)
            console.log(res)
            if(res){
                commit('set_roomEditVisible',false)
                message.success('删除成功')
            }else{
                message.error('删除失败')
            }
        },
        getHotelCoupon: async({ state, commit }) => {
            const res = await hotelAllCouponsAPI(state.activeHotelId)
            if(res) {
                // 获取到酒店策略之后的操作（将获取到的数组赋值给couponList）
                commit('set_couponList',res);
            }
        },
        addHotelCoupon: async({state,dispatch ,commit}, data) => {
            let res=null;
            if(Number(data.couponType)===3){
                res=await hotelTargetMoneyAPI(data)
            }else if(Number(data.couponType)===4){
                res=await hotelTimeAPI(data)
            }else if(Number(data.couponType)===2){
                res=await hotelMultiRoomAPI(data)
            }else if(Number(data.couponType)===1){
                //todo
                res=await hotelBirthdayAPI(data)
            }
            if(res){
                // 添加成功后的操作（提示文案、modal框显示与关闭，调用优惠列表策略等）
                dispatch('getHotelCoupon')
                commit('set_addCouponVisible', false)
                commit('set_couponVisible',true)
                message.success('添加策略成功')
            }else{
                // 添加失败后的操作
                message.error('添加失败');
            }
        },
        /* deleteOrderById:async({state,commit},id)=>{
             const res = await cancelOrderAPI(id)
             //console.log(state.activeHotelId)
             if(res){
                //刷新页面
                location.reload();
                message.success('删除成功')
             }else{
                 message.error('删除失败')
             }
         }*/
    }
};
export default hotelManager