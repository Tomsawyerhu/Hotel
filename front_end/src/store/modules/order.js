import { message } from 'ant-design-vue'
import {getUserOrdersInCertainHotelAPI,getOrderDetailsAPI} from '@/api/order'

const getDefaultSate=()=>{

}

const order={
    state: {
        userOrdersInCertainHotel:[],
        currentOrderId: '',   //用户选择查看的当前订单
        currentOrderInfo: {}
    },
    //修改state一定只能通过mutation，通过action来commit mutation中的操作来改变state
    mutations: {
        set_userOrdersInCertainHotel(state,data){
            state.userOrdersInCertainHotel = data
        },
        set_currentOrderId: (state,data) =>{
            state.currentOrderId = data
        },
        set_currentOrderDetail:(state,data)=>{
            state.currentOrderInfo=data
        },

    },
    actions: {
        getUserOrdersInCertainHotel: async({ state, commit },data) => {
            console.log(data)
            const res = await getUserOrdersInCertainHotelAPI(data)
            console.log(res)
            if(res){
                commit('set_userOrdersInCertainHotel', res)
            }else{
                message.error("暂无订单")
            }
        },
        getOrderDetails:async ({ state, commit })=> {
            const res=await getOrderDetailsAPI(state.currentOrderId)
            if(res){
                commit('set_currentOrderDetail',res)
            }
        },

    }
};
export default order