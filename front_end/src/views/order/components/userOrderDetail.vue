<template>
    <div>
        <a-descriptions borderd size="default" title="订单详情" bordered layout="vertical">
            <a-descriptions-item label="订单号">
                {{currentOrderInfo.id}}
            </a-descriptions-item>
            <a-descriptions-item label="酒店ID">
                {{currentOrderInfo.hotelId}}
            </a-descriptions-item>
            <a-descriptions-item label="酒店名称">
                {{currentOrderInfo.hotelName}}
            </a-descriptions-item>
            <a-descriptions-item label="创建日期">
                {{currentOrderInfo.createDate}}
            </a-descriptions-item>
            <a-descriptions-item label="预定入住时间">
                {{currentOrderInfo.checkInDate}}
            </a-descriptions-item>
            <a-descriptions-item label="预定离店时间">
                {{currentOrderInfo.checkOutDate}}
            </a-descriptions-item>
            <a-descriptions-item label="订单状态">
                <a-tag color="grey" v-if="currentOrderInfo.orderState=='已撤销'">已撤销</a-tag>
                <a-tag color="blue" v-else-if="currentOrderInfo.orderState=='已执行'">已执行</a-tag>
                <a-tag color="red" v-else-if="currentOrderInfo.orderState=='异常'">异常</a-tag>
                <a-tag color="yellow" v-else-if="currentOrderInfo.orderState=='已退房'">已退房</a-tag>
                <a-tag color="green" v-else>已预订</a-tag>
            </a-descriptions-item>
            <a-descriptions-item label="登记人数">
                {{currentOrderInfo.peopleNum}}
            </a-descriptions-item>
            <a-descriptions-item label="预订房间">
                {{currentOrderInfo.roomType}}/{{currentOrderInfo.roomNum}}间
            </a-descriptions-item>
            <a-descriptions-item label="有无儿童">
                {{Number(currentOrderInfo.haveChild)===0?'有':'无'}}
            </a-descriptions-item>
            <a-descriptions-item label="预留手机号">
                预留手机号:{{currentOrderInfo.phoneNumber}}
            </a-descriptions-item>
            <a-descriptions-item >
                <a-button @click="back" size="small" type="primary" style="width: 70px" block>
                    返回
                    <a-icon type="backward" theme="filled" />
                </a-button>
            </a-descriptions-item>
        </a-descriptions>
    </div>


</template>


<script>
    import {mapGetters, mapActions} from 'vuex'

    export default {
        name: 'userOrderDetail',
        props: {
            back: {
                function() {
                }
            },
            editable: {
                type: Boolean,
                default: false
            }
        },
        data() {
            return {
                orderId: null,
                loading: false,
                visible: false,
                pane: 1,

            }
        },

        computed: {
            ...mapGetters([
                'currentOrderInfo'
            ]),
        },
        methods: {
            ...mapActions(['checkIn', 'badOrder', 'checkOut']),
            showModal() {
                this.visible = true;
            },
            handleCancel(e) {
                this.visible = false;
            },
            changePane(e) {
                this.pane = Number(e)
            }
        },
        beforeCreate() {
        }
    }

</script>