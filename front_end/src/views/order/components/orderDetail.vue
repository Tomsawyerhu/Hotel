<template>
    <div>
        <a-descriptions borderd size="default" title="订单详情" bordered layout="vertical">
            <a-descriptions-item label="订单号">
                {{currentOrderInfo.id}}
            </a-descriptions-item>
            <a-descriptions-item label="用户编号">
                {{currentOrderInfo.userId}}
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
                <a-button type="primary" @click="showModal" v-if="editable">
                    修改订单
                </a-button>
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
        <a-modal
                :visible="visible"
                title="工作人员功能"
                cancelText="取消"
                okText="提交"
                @cancel="handleCancel"
                @ok="handleOk">

            <a-tabs default-active-key="1" @change="changePane">
                <a-tab-pane key="1" tab="手工补登记" :disabled="currentOrderInfo.orderState!='异常'">
                    <a-form :form="form1" v-if="currentOrderInfo.orderState=='异常'">
                        <a-form-item label="订单号">
                            <a-input
                                    :disabled="true"
                                    style="width: 40px"
                                    v-decorator="['orderId1',{initialValue:currentOrderInfo.id}]"
                            />
                        </a-form-item>
                        <a-form-item label="原预定时间">
                            {{currentOrderInfo.checkInDate}}
                        </a-form-item>
                        <a-form-item label="实际入住时间">
                            <a-input
                                    placeholder="请填写入住时间"
                                    v-decorator="['realtime1', { rules: [{ required: true, message: '请填写入住时间' }] }]"
                            />
                        </a-form-item>
                        <a-form-item label="恢复信用值">
                            <a-input
                                    :disabled="true" style="width: 80px"
                                    v-decorator="['credit1',{initialValue:Math.floor(currentOrderInfo.price/2)}]"
                            />
                        </a-form-item>
                        <a-form-item label="备注">
                            <a-input
                                    v-decorator="['mentions', { rules: [{  message: '请填写备注' }] }]"
                                    type="textarea"/>
                        </a-form-item>
                    </a-form>
                </a-tab-pane>
                <a-tab-pane key="2" tab="执行订单" :disabled="currentOrderInfo.orderState!='已预订'">
                    <a-form :form="form2" v-if="currentOrderInfo.orderState=='已预订'">
                        <a-form-item label="订单号">
                            <a-input
                                    :disabled="true" style="width: 40px"
                                    v-decorator="['orderId2',{initialValue:currentOrderInfo.id}]"
                            ></a-input>
                        </a-form-item>
                        <a-form-item label="预定时间">
                            {{currentOrderInfo.checkInDate}}
                        </a-form-item>
                        <a-form-item label="入住时间">
                            <a-input
                                    v-decorator="['realtime2', { rules: [{ required: true, message: '请填写入住时间' }] }]"
                            />
                        </a-form-item>
                        <a-form-item label="增加信用值">
                            <a-input
                                    :disabled="true" style="width: 80px"
                                    v-decorator="['credit2',{initialValue:currentOrderInfo.price}]"
                            />
                        </a-form-item>
                    </a-form>
                </a-tab-pane>
                <a-tab-pane key="3" tab="置为异常" :disabled="currentOrderInfo.orderState=='异常'">
                    <a-form :form="form3" v-if="currentOrderInfo.orderState!='异常'">
                        <a-form-item label="订单号">
                            <a-input
                                    :disabled="true" style="width: 40px"
                                    v-decorator="['orderId3',{initialValue:currentOrderInfo.id}]"
                            ></a-input>
                        </a-form-item>
                        <a-form-item label="扣除信用值">
                            <a-input
                                    :disabled="true" style="width: 80px"
                                    v-decorator="['credit3',{initialValue:currentOrderInfo.price}]"
                            />
                        </a-form-item>
                    </a-form>
                </a-tab-pane>
                <a-tab-pane key="4" tab="办理退房" :disabled="currentOrderInfo.orderState!='已执行'">
                    <a-form :form="form4" v-if="currentOrderInfo.orderState=='已执行'">
                        <a-form-item label="订单号">
                            <a-input
                                    :disabled="true" style="width: 40px"
                                    v-decorator="['orderId4',{initialValue:currentOrderInfo.id}]"
                            ></a-input>
                        </a-form-item>
                        <a-form-item label="预定时间">
                            {{currentOrderInfo.checkInDate}}
                        </a-form-item>
                        <a-form-item label="规定退房时间">
                            {{currentOrderInfo.checkInDate}}
                        </a-form-item>
                        <a-form-item label="实际退房时间">
                            <a-input
                                    v-decorator="['checkoutTime4', { rules: [{ required: true, message: '请填写实际退房时间' }] }]"
                            />
                        </a-form-item>
                    </a-form>
                </a-tab-pane>

            </a-tabs>
        </a-modal>
    </div>


</template>


<script>
    import {mapGetters, mapActions} from 'vuex'

    export default {
        name: 'orderDetails',
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
            handleOk(e) {
                this.loading = true;
                e.preventDefault();
                let data = {}
                if (Number(this.pane) === 1) {
                    this.form1.validateFieldsAndScroll((err, values) => {
                        data.orderId = this.form1.getFieldValue("orderId1")
                        data.realTime = this.form1.getFieldValue('realtime1')
                        data.mentions = this.form1.getFieldValue('mentions')
                        data.credit = this.form1.getFieldValue("credit1")
                        //将订单状态改为已入住,恢复信用值
                        this.checkIn(data)
                    })
                } else if (Number(this.pane) === 2) {
                    this.form2.validateFieldsAndScroll((err, values) => {
                        data.orderId = this.form2.getFieldValue("orderId2")
                        data.realTime = this.form2.getFieldValue("realtime2")
                        data.credit = this.form2.getFieldValue("credit2")
                        //将订单状态改为已入住,增加信用值
                        this.checkIn(data)
                    })
                } else if (Number(this.pane) === 3) {
                    this.form3.validateFieldsAndScroll((err, values) => {
                        data.orderId = this.form3.getFieldValue("orderId3")
                        data.credit = this.form3.getFieldValue("credit3")
                        //置为异常
                        this.badOrder(data)
                    })
                } else if (Number(this.pane) === 4) {
                    this.form4.validateFieldsAndScroll((err, values) => {
                        data.orderId = this.form4.getFieldValue("orderId4")
                        data.checkoutTime = this.form4.getFieldValue("checkoutTime4")
                        //办理退房
                        this.checkOut(data)
                    })
                }
                setTimeout(() => {
                    this.visible = false;
                    this.loading = false;
                }, 1000);


            },
            handleCancel(e) {
                this.visible = false;
            },
            changePane(e) {
                this.pane = Number(e)
            }
        },
        beforeCreate() {
            this.form1 = this.$form.createForm(this, {name: 'amendOrder1'})
            this.form2 = this.$form.createForm(this, {name: 'amendOrder2'})
            this.form3 = this.$form.createForm(this, {name: 'amendOrder3'})
            this.form4 = this.$form.createForm(this, {name: 'amendOrder4'})
        }
    }

</script>