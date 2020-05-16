<template>
    <a-form>
        <a-form-item label="订单号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
            <span>{{ currentOrderInfo.id}}</span>
        </a-form-item>
        <a-form-item label="酒店名称" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
            <span>{{ currentOrderInfo.hotelName}}</span>
        </a-form-item>
        <a-form-item :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 2 }">
            <a-button @click="back">返回</a-button>
        </a-form-item>
    </a-form>
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