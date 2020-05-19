<template>
    <div class="info-wrapper">
    <a-table
            :columns="columns"
            :dataSource="orderList"
            bordered
            v-show="!showDetail"
    >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
        <span slot="roomType" slot-scope="text">
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
                    </span>
        <a-tag slot="orderState" color="blue" slot-scope="text" v-if="text==='已入住'">
            {{ text }}
        </a-tag>
        <a-tag slot="orderState" color="red" slot-scope="text" v-else-if="text==='异常'" >
            {{ text }}
        </a-tag>
        <a-tag slot="orderState" color="green" slot-scope="text" v-else-if="text==='已预定'">
            {{ text }}
        </a-tag>
        <a-tag slot="orderState" color="gray" slot-scope="text" v-else>
            {{ text }}
        </a-tag>
        <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small"  @click="showOrderDetails(record.id)">查看详情</a-button>
                        <a-divider type="vertical" v-if="record.orderState == '已预订'"></a-divider>
                        <a-popconfirm
                                title="你确定撤销该笔订单吗？"
                                @confirm="confirmCancelOrder(record.id)"
                                @cancel="cancelCancelOrder"
                                okText="确定"
                                cancelText="取消"
                                v-if="record.orderState == '已预订'"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>

                    </span>
    </a-table>
    <order-detail  v-if="showDetail" v-bind:back="setShowDetailFalse">

    </order-detail>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import orderDetail from './orderDetail'

    const columns = [
        {
            title: '订单号',
            dataIndex: 'id',
        },
        {
            title: '酒店名',
            dataIndex: 'hotelName',
        },
        {
            title: '房型',
            dataIndex: 'roomType',
            scopedSlots: { customRender: 'roomType' }
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: { customRender: 'checkInDate' }
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: { customRender: 'checkOutDate' }
        },
        {
            title: '入住人数',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            dataIndex: 'price',
        },
        {
            title: '状态',
            filters: [{ text: '已预订', value: '已预订' }, { text: '已撤销', value: '已撤销' }, { text: '已入住', value: '已入住' },{ text: '异常', value: '异常' }],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: { customRender: 'orderState' }
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },

    ];
    export default {
        name: 'orderList',
        data(){
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, { name: 'coordinated' }),
                orderDetailInfo:{},
                showDetail:false

            }
        },
        props: {      //由父组件传递datasource
            orderList:{
                type: Array
            }
        },
        components: {
            orderDetail
        },
        computed: {
            ...mapGetters([
                'userId',
                'userOrderList'
            ]),

        },
        /*async mounted() {
            await this.getUserOrders()
        },*/
        methods: {
            ...mapMutations(['set_currentOrderId']),
            ...mapActions([
                'cancelOrder',
                'orderDetails',
            ]),
            setShowDetailFalse(){
                // console.log("false")
                this.showDetail=false;
            },
            setShowDetailTrue(){
                this.showDetail=true;
            },
            confirmCancelOrder(orderId){
                this.cancelOrder(orderId)
            },
            cancelCancelOrder() {

            },
            showOrderDetails(orderId){ //查看订单详细信息
                this.set_currentOrderId(orderId)
                this.orderDetailInfo=this.orderDetails()
                this.setShowDetailTrue()
                console.log(this.orderDetailInfo)
            },
        }
    }
</script>
<!--
<style scoped lang="less">
    .info-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .info-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>-->
