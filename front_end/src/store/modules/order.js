import { message } from 'ant-design-vue'
import {getUserOrdersInCertainHotelAPI} from '@/api/order'

const getDefaultSate=()=>{

}

const order={
    state: {
        userOrdersInCertainHotel:[]

    },
    //修改state一定只能通过mutation，通过action来commit mutation中的操作来改变state
    mutations: {
        set_userOrdersInCertainHotel(state,data){
            state.userOrdersInCertainHotel = data
        }

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

    }
};
export default order