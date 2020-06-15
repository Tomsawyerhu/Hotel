<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                                alt="example"
                                src="@/assets/cover.jpeg"
                                slot="cover"
                                referrerPolicy="no-referrer"
                        />
                    </a-card>
                    <a-form :form="form" class="info">
                        <a-form-item label="酒店名称" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.name }}</span>
                            <a-input
                                    placeholder="请填写酒店名字"
                                    v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名字' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="地址" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.address }}</span>
                            <a-input
                                    placeholder="请填写酒店地址"
                                    v-decorator="['hotelAddress', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="评分" class="items">
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </a-form-item>
                        <a-form-item label="星级" class="items">
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </a-form-item>
                        <a-form-item label="酒店简介" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.description }}</span>
                            <a-input
                                    placeholder="请填写酒店简介"
                                    v-decorator="['hotelDescription', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item class="items">
                            <a-button-group>
                            <a-button type="primary" @click="modifyBegin" v-if="!modify">编辑酒店信息</a-button>
                            <a-button type="primary" @click="showCouponList( currentHotelInfo.id )" v-if="!modify">优惠策略</a-button>
                            </a-button-group>
                        </a-form-item>
                        <a-form-item class="items">
                            <a-button type="primary" @click="modifyFinish" v-if="modify">保存编辑</a-button>
                            <a-divider type="vertical"></a-divider>
                            <a-button  @click="modify=false" v-if="modify">取消</a-button>
                        </a-form-item>
                    </a-form>
                        <!--<div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称:</span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.name }}</span>
                            <a-input
                                    placeholder="请填写酒店名字"
                                    v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名字' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址:</span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.address }}</span>
                            <a-input
                                    placeholder="请填写酒店地址"
                                    v-decorator="['hotelAddress', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="edit"></span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.description }}</span>
                            <a-input
                                    placeholder="请填写酒店简介"
                                    v-decorator="['hotelDescription', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                    v-if="modify" >
                            </a-input>
                            &lt;!&ndash;{{currentHotelInfo}}&ndash;&gt;
                            &lt;!&ndash;<span>{{ currentHotelInfo.rooms }}</span>&ndash;&gt;
                        </div>
                        <a-button type="primary" @click="modifyBegin" v-if="!modify">编辑酒店信息</a-button>

                    </div>-->
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <!--{{currentHotelInfo}}-->
                        <RoomList :rooms="currentHotelInfo.rooms" ></RoomList>
                    </a-tab-pane>


                    <a-tab-pane tab="订单管理" key="2">
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
                        <span v-if="text == 'BigBed'">大床房</span>
                        <span v-if="text == 'DoubleBed'">双床房</span>
                        <span v-if="text == 'Family'">家庭房</span>
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
                    </a-tab-pane>
                </a-tabs>


            </div>
        </a-layout-content>
        <coupon></coupon>
    </a-layout>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import RoomList from './components/roomList'
    import Coupon from "./components/coupon"
    import OrderDetails from "../order/components/orderDetail";
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
        name: 'hotelDetailEdit',
        components: {
            OrderDetails,
            Coupon,
            RoomList,
        },
        data() {
            return {
                modify:false,
                form: this.$form.createForm(this, {name: 'coordinated'}),
                columns2,
                showDetail:false,
                orderDetailInfo:{},
                pagination: {},
            }
        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'descEditVisible',
                'userInfo',
                'orderList',
                'userOrderList',
                'activeHotelId',
            ])
        },
        async mounted() {
            this.set_currentHotelId(Number(this.$route.params.hotelId))
            this.getHotelById()
            await this.getAllOrders()
        },
        beforeRouteUpdate(to, from, next) {
            this.set_currentHotelId(Number(to.params.hotelId))
            this.getHotelById()
            next()
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId',
                'set_currentHotelInfo',
                'set_activeHotelId',
                'set_couponVisible',
                'set_activeHotelId',
                'set_currentOrderId',
            ]),
            ...mapActions([
                'getHotelById',
                'getHotelCoupon',
                'getAllOrders',
                'getOrderDetails',

            ]),
            setShowDetailFalse(){
                // console.log("false")
                this.showDetail=false;
            },
            setShowDetailTrue(){
                this.showDetail=true;
            },
            deleteOrder(){

            },
            showOrderDetails(orderId){ //查看订单详细信息
                this.set_currentOrderId(orderId)
                this.getOrderDetails()
                this.setShowDetailTrue()
                console.log(this.orderDetailInfo)
            },
            showCouponList(id){
                console.log(id)
                this.set_activeHotelId(id)
                this.getHotelCoupon()
                this.set_couponVisible(true)
            },
            edit(){
                this.set_descEditVisible(true)
            },
            modifyBegin() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'hotelName': this.currentHotelInfo.name,
                        'hotelAddress': this.currentHotelInfo.address,
                        'hotelDescription':this.currentHotelInfo.description
                    })
                }, 0)
                this.modify = true
            },
            modifyFinish() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            id:this.currentHotelInfo.id,
                            name:this.form.getFieldValue('hotelName'),
                            address:this.form.getFieldValue('hotelAddress'),
                            bizRegion:this.currentHotelInfo.bizRegion,
                            hotelStar:this.currentHotelInfo.hotelStar,
                            rate:this.currentHotelInfo.rate,
                            description:this.form.getFieldValue('hotelDescription'),
                            phoneNumber:this.currentHotelInfo.phoneNumber,
                            managerId:this.currentHotelInfo.managerId,
                            rooms:this.currentHotelInfo.rooms
                        }
                        this.$store.dispatch('updateHotelInfo',data)
                        this.modify=false
                    }
                });
            },
        }
    }
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px ;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>