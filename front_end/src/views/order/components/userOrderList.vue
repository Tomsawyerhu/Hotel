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
            <a-tag slot="orderState" color="red" slot-scope="text" v-else-if="text==='异常'">
                {{ text }}
            </a-tag>
            <a-tag slot="orderState" color="green" slot-scope="text" v-else-if="text==='已预订'">
                {{ text }}
            </a-tag>
            <a-tag slot="orderState" color="yellow" slot-scope="text" v-else-if="text==='已退房'">
                {{ text }}
            </a-tag>
            <a-tag slot="orderState" color="gray" slot-scope="text" v-else>
                {{ text }}
            </a-tag>
            <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetails(record.id)">查看详情</a-button>
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
                        <a-divider type="vertical" v-if="record.orderState == '已执行'"></a-divider>
                        <a-button type="primary" size="small" @click="startComment(record.id)"
                                  v-if="record.orderState == '已执行'" :disabled="hasCommented(record.id)">评价订单</a-button>
            </span>
        </a-table>
        <order-detail v-if="showDetail" :back="setShowDetailFalse">
        </order-detail>
        <AddComment></AddComment>

    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import orderDetail from './orderDetail'
    import AddComment from './addComment'

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
            scopedSlots: {customRender: 'roomType'}
        },
        {
            title: '入住时间',
            dataIndex: 'checkInDate',
            scopedSlots: {customRender: 'checkInDate'}
        },
        {
            title: '离店时间',
            dataIndex: 'checkOutDate',
            scopedSlots: {customRender: 'checkOutDate'}
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
            filters: [{text: '已预订', value: '已预订'}, {text: '已撤销', value: '已撤销'}, {
                text: '已执行',
                value: '已执行'
            }, {text: '已完成', value: '已完成'}, {text: '异常', value: '异常'},],
            onFilter: (value, record) => record.orderState.includes(value),
            dataIndex: 'orderState',
            scopedSlots: {customRender: 'orderState'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },

    ];
    export default {
        name: 'userOrderList',
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns,
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),
                orderDetailInfo: {},
                showDetail: false

            }
        },
        props: {      //由父组件传递datasource
            orderList: {
                type: Array
            }
        },
        components: {
            orderDetail,
            AddComment
        },
        computed: {
            ...mapGetters([
                'userId',
                'userOrderList',
            ]),

        },
        methods: {
            ...mapMutations([
                'set_currentOrderId',
                'set_addCommentVisible',
            ]),
            ...mapActions([
                'cancelOrder',
                'getOrderDetails',
                'getOrderDetailsById',
                'addCreditHistory',
                'changeCredit',
                'getCreditHistories'
            ]),
            setShowDetailFalse() {
                // console.log("false")
                this.showDetail = false;
            },
            setShowDetailTrue() {
                this.showDetail = true;
            },
            confirmCancelOrder(orderId) {
                const data={
                    userId:this.userId,
                    value:2,
                    type:-1,
                    message:"您撤销订单，信用减少两点"
                }
                this.addCreditHistory(data).then(this.getCreditHistories(this.userId))
                const data2={
                    id:data.userId,
                    amount:data.value,
                    type:data.type
                }
                this.changeCredit(data2)
                this.cancelOrder(orderId)
                this.getCreditHistories(this.userId)
            },
            cancelCancelOrder() {

            },
            showOrderDetails(orderId) { //查看订单详细信息
                this.set_currentOrderId(orderId)
                this.getOrderDetails()
                this.setShowDetailTrue()
                console.log(this.orderDetailInfo)
            },
            startComment(orderId) {
                this.set_currentOrderId(orderId)
                this.getOrderDetails()
                this.set_addCommentVisible(true)
            },
            hasCommented(orderId) {
                //获取promise对象里的值
                let orderInfo = this.getOrderDetailsById(Number(orderId)).res
                this.getOrderDetailsById(Number(orderId)).then(res => {
                    console.log(res)
                })
                console.log("in "+orderInfo)
                console.log(orderInfo)
                return orderInfo
            }

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
