<template>
    <div class="manageHotel-wrapper">
        <a-tabs>
            <a-tab-pane tab="酒店管理" key="1">
                <div name="酒店管理" v-if="!changeManager">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addHotel"><a-icon type="plus" />添加酒店</a-button>
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="hotelList"
                        bordered
                >
                    <span slot="action" slot-scope="record">  <!--slot="action",就是选定key为action的列,record就是当前行的原始数据-->
                        <!--<a-button type="primary" size="small" @click="addRoom(record)">录入房间</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="info" size="small" @click="showCoupon(record)">优惠策略</a-button>
                        <a-divider type="vertical"></a-divider>-->

                        <a-popconfirm
                                title="确定想删除该酒店吗？"
                                @confirm="deleteHotel(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除酒店</a-button>   <!--不同级别的按钮，在ant-design里可以看到-->
                        </a-popconfirm>
                        <!--{{userInfo}}-->
                        <!--{{record}}-->
                        <a-divider type="vertical"></a-divider>

                        <a-button type="primary" size="small"  @click="SeeManager(record)" >编辑酒店工作人员信息</a-button>
                        <!--<a-button type="primary" size="small"  v-if="record.managerId==NULL" @click="addManager(record)">添加酒店工作人员</a-button>
                        <a-button type="primary" size="small"  v-if="record.managerId!=NULL" @click="editManager(record)">修改酒店工作人员的信息</a-button>-->


                    </span>
                </a-table>
                </div>

                <div name="管理人员" v-if="changeManager">
                    <manager-detail :back="quitSeeManager"/>
                </div>
            </a-tab-pane>
            <!--a-tab-pane tab="订单管理" key="2">
                <a-table
                        :columns="columns2"
                        :dataSource="orderList"
                        bordered
                        v-show="!showDetail"
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="roomType" slot-scope="text">
                        <span v-if="text == '大床房'">大床房</span>
                        <span v-if="text == '双床房'">双床房</span>
                        <span v-if="text == '家庭房'">家庭房</span>
                    </span>
                    <span slot="action" slot-scope="record">
                        <a-button type="primary" size="small" @click="showOrderDetails(record.id)">订单详情</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-popconfirm
                                title="确定想删除该订单吗？"
                                @confirm="deleteOrder(record)"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">删除订单</a-button>
                        </a-popconfirm>
                        <a-divider type="vertical"></a-divider>
                        <a-tag color="grey" v-if="record.orderState=='已撤销'">已撤销</a-tag>
                        <a-tag color="blue" v-else-if="record.orderState=='已执行'">已执行</a-tag>
                        <a-tag color="red" v-else-if="record.orderState=='异常'">异常</a-tag>
                        <a-tag color="yellow" v-else-if="record.orderState=='已退房'">已退房</a-tag>
                        <a-tag color="green" v-else>已预订</a-tag>
                    </span>
                </a-table>
                <order-details  v-if="showDetail" :back="setShowDetailFalse" :editable="true">

                </order-details>
            </a-tab-pane-->

        </a-tabs>
        <addManagerModal></addManagerModal>
        <AddHotelModal></AddHotelModal>
        <AddRoomModal></AddRoomModal>

        <EditManager></EditManager>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddHotelModal from './components/addHotelModal'
    import AddRoomModal from './components/addRoomModal'
    //import OrderDetails from "../order/components/orderDetail";
    import addManagerModal from "./components/addManagerModal";
    import EditManager from "@/views/admin/components/editUserInfo"
    import managerDetail from "./components/managerDetail";
    const moment = require('moment')
    const columns1 = [
        {
            title: '酒店名',
            dataIndex: 'name',
        },
        {
            title: '商圈',
            dataIndex: 'bizRegion',
        },
        {
            title: '地址',
            dataIndex: 'address',
        },
        {
            title: '酒店星级',
            dataIndex: 'hotelStar'
        },
        {
            title: '评分',
            dataIndex: 'rate',
        },
        {
            title: '简介',
            dataIndex: 'description',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    const columns2 = [
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
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name: 'manageHotel',
        data(){
            return {
                changeManager:false,
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                form: this.$form.createForm(this, { name: 'manageHotel' }),
                showDetail:false,
                orderDetailInfo:{},
            }
        },
        components: {
            //OrderDetails,
            AddHotelModal,
            AddRoomModal,
            addManagerModal,
            EditManager,
            managerDetail
        },
        computed: {
            ...mapGetters([
                'orderList',
                'hotelList',
                'addHotelModalVisible',
                'addRoomModalVisible',
                'activeHotelId',
                'couponVisible',
                'userInfo',
                'userHotelList',
                'userOrderList',
            ]),
        },
        async mounted() {
            await this.getHotelList()
            await this.getAllOrders()
        },
        methods: {
            ...mapMutations([
                'set_addHotelModalVisible',
                'set_addRoomModalVisible',
                'set_activeHotelId',
                'set_currentOrderId',
                'set_currentHotelInfo',
                'set_addManagerModalVisible',
                'set_targetAccount',
                'set_UserInfoEditVisible',
                'set_currentHotelInfo'
            ]),
            ...mapActions([
                'getHotelList',
                'getAllOrders',
                'getOrderDetails',
                'getHotelById',
                'addManager',
                'deleteHotelById'
            ]),
            setShowDetailFalse(){
                // console.log("false")
                this.showDetail=false;
            },
            setShowDetailTrue(){
                this.showDetail=true;
            },
            addHotel() {
                this.set_addHotelModalVisible(true)
            },
            addRoom(record) {
                this.set_activeHotelId(record.id)
                this.set_addRoomModalVisible(true)
            },

            deleteHotel(record){
                this.deleteHotelById(record.id)
            },
            deleteOrder(){

            },
            showOrderDetails(orderId){ //查看订单详细信息
                this.set_currentOrderId(orderId)
                this.getOrderDetails()
                this.setShowDetailTrue()
                console.log(this.orderDetailInfo)
            },
            SeeManager(record){
                this.set_currentHotelInfo(record)
                this.changeManager=true
            },
            quitSeeManager(){
                this.changeManager=false
            },


        }
    }
</script>
<style scoped lang="less">
    .manageHotel-wrapper {
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
    .manageHotel-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>