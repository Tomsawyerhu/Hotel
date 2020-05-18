<template>
    <div class="info-wrapper">
        <a-tabs>
            <a-tab-pane tab="我的信息" key="1">
                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <a-input
                            placeholder="请填写用户名"
                            v-decorator="['userName', { rules: [{ required: true, message: '请输入用户名' }] }]"
                            v-if="modify">
                            <a-icon slot="prefix" type="user" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
                        <span v-if="!modify">{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        <span>{{ userInfo.email }}</span>
                    </a-form-item>
                    
                    <a-form-item label="手机号" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <a-input
                            placeholder="请填写手机号"
                            v-decorator="['phoneNumber', { rules: [{ required: true, message: '请输入手机号' }] }]"
                            v-if="modify">
                            <a-icon slot="prefix" type="phone" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
                        <span v-if="!modify">{{ userInfo.phoneNumber}}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-form-item  label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modifyPasswordVisible">
                        <a-input
                                 type="password"
                                 visibilityToggle="false"
                                 placeholder="请输入新密码"
                                 v-decorator="['newPassword',  {rules: [{ required: true, message: '请输入密码' }, { validator: this.handlePassword }], validateTrigger: 'blur'}]">
                            <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
                    </a-form-item>
                    <a-form-item    label="确认密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }" v-if="modifyPasswordVisible">
                        <a-input
                                type="password"
                                visibilityToggle="false"
                                placeholder="请确认密码"
                                v-decorator="['newPasswordConfirm', { rules: [{ required: true, message: '请确认密码' }, { validator: this.handlePasswordCheck }] , validateTrigger: 'blur'}]">
                            <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modify">
                        <a-button type="primary" @click="saveModify">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModify">
                            取消
                        </a-button>
                    </a-form-item>
                     <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-if="modifyButtonVisible">
                        <a-button type="primary" @click="modifyInfo">
                            修改信息
                        </a-button>
                    </a-form-item>
                    <a-form-item  :wrapper-col="{ span: 8, offset: 4 }"  v-if="modifyPasswordButtonVisible">
                        <a-button type="primary"  @click="modifyUserPassword">
                            修改密码
                        </a-button>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 12, offset: 5 }" v-if="modifyPasswordVisible">
                        <a-button type="primary" @click="savePassword">
                            保存
                        </a-button>
                        <a-button type="default" style="margin-left: 30px" @click="cancelModifyPassword">
                            取消
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
            <a-tab-pane tab="我的订单" key="2" >
                <a-table
                    :columns="columns"
                    :dataSource="userOrderList"
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

            </a-tab-pane>

        </a-tabs>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import orderDetail from '../order/components/orderDetail'

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
    name: 'info',
    data(){
        return {
            modify: false,
            modifyButtonVisible:true,
            modifyPasswordVisible:false,
            modifyPasswordButtonVisible:true,
            formLayout: 'horizontal',
            pagination: {},
            columns,
            data: [],
            form: this.$form.createForm(this, { name: 'coordinated' }),
            orderDetailInfo:{},
            showDetail:false

        }
    },
    components: {
        orderDetail
    },
    computed: {
        ...mapGetters([
            'userId',
            'userInfo',
            'userOrderList'
        ]),

    },
    async mounted() {
        await this.getUserInfo()
        await this.getUserOrders()
    },
    methods: {
        ...mapMutations(['set_currentOrderId']),
        ...mapActions([
            'getUserInfo',
            'getUserOrders',
            'updateUserInfo',
            'cancelOrder',
            'orderDetails',
            'modifyPassword'
        ]),
        setShowDetailFalse(){
           // console.log("false")
            this.showDetail=false;
        },
        setShowDetailTrue(){
            this.showDetail=true;
        },
        saveModify() {
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        userName: this.form.getFieldValue('userName'),
                        phoneNumber: this.form.getFieldValue('phoneNumber'),
                        password: this.form.getFieldValue('password')
                    }
                    this.updateUserInfo(data).then(()=>{
                        this.modify = false
                        this.modifyButtonVisible = true
                        this.modifyPasswordButtonVisible = true
                    })
                }
            });
        },
        modifyInfo() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'userName': this.userInfo.userName,
                    'phoneNumber': this.userInfo.phoneNumber,
                })
            }, 0)
            this.modify = true
            this.modifyButtonVisible = false
            this.modifyPasswordButtonVisible = false
        },
        modifyUserPassword(){
            this.modifyPasswordVisible = true
            this.modifyPasswordButtonVisible = false
            this.modifyButtonVisible = false
        },
        savePassword(){

            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        password: this.form.getFieldValue('newPassword')
                    }
                    console.log("s")
                    this.modifyPassword(data).then(()=>{
                        this.modifyPasswordVisible = false
                        this.modifyButtonVisible = true
                        this.modifyPasswordButtonVisible = true
                    })
                }
            });
        },
        cancelModifyPassword(){
            this.modifyPasswordVisible = false
            this.modifyPasswordButtonVisible = true
            this.modifyButtonVisible = true
        },
        cancelModify() {
            this.modify = false
            this.modifyButtonVisible = true
            this.modifyPasswordButtonVisible = true
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
        // handler
        handleUsernameOrEmail (rule, value, callback) {
            const { state } = this
            const regex = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
            if (regex.test(value)) {
                callback()
            } else {
                callback(new Error('请输入有效用户名或邮箱'))
            }
            callback()
        },
        checkEmail(rule, value, callback) {
            const re = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/
            if (re.test(value)) {
                callback();
            } else {
                callback(new Error('请输入有效邮箱'));
            }
            callback()
        },
        handlePassword(rule, value, callback) {
            if (value.length < 6) {
                callback(new Error('密码长度至少6位'))
            }
            callback()
        },
        handlePasswordCheck (rule, value, callback) {
            const password = this.form.getFieldValue('newPassword')
            console.log(password)
            if (value === undefined) {
                callback(new Error('请输入密码'))
            }
            if (value && password && value.trim() !== password.trim()) {
                callback(new Error('两次密码不一致'))
            }
            callback()
        },
        
    }
}
</script>
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
    
</style>