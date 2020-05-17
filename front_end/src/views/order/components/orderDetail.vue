<template>
    <a-descriptions borderd size="default" title="订单详情">
        <a-descriptions-item label="订单号">
            {{currentOrderInfo.id}}
        </a-descriptions-item>
        <a-descriptions-item label="酒店名称">
            {{currentOrderInfo.hotelName}}
        </a-descriptions-item>
        <a-descriptions-item label="创建日期">
            {{currentOrderInfo.createDate}}
        </a-descriptions-item>
        <a-descriptions-item label="入住时间">
            {{currentOrderInfo.checkInDate}}
        </a-descriptions-item>
        <a-descriptions-item label="离店时间">
            {{currentOrderInfo.checkOutDate}}
        </a-descriptions-item>
    </a-descriptions>

</template>



<script>
    import {mapGetters} from 'vuex'
    export default {
        name:'orderDetails',
        props:{
            back:function (){}
        },
        data(){
            return {

            }
        },
        computed:{
            ...mapGetters([
                'currentOrderInfo'
            ]),
        },
    }

</script>