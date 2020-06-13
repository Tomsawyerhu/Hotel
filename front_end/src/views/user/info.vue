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
                    <a-form-item label="密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                 v-if="modifyPasswordVisible">
                        <a-input
                                type="password"
                                visibilityToggle="false"
                                placeholder="请输入新密码"
                                v-decorator="['newPassword',  {rules: [{ required: true, message: '请输入密码' }, { validator: this.handlePassword }], validateTrigger: 'blur'}]">
                            <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                        </a-input>
                    </a-form-item>
                    <a-form-item label="确认密码" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }"
                                 v-if="modifyPasswordVisible">
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
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" v-if="modifyPasswordButtonVisible">
                        <a-button type="primary" @click="modifyUserPassword">
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
            <a-tab-pane tab="我的订单" key="2" v-if="userInfo.userType=='Client'">
                <OrderList :order-list="userOrderList">
                </OrderList>
            </a-tab-pane>
            <a-tab-pane tab="预定过的酒店" key="3" v-if="userInfo.userType=='Client'">
                <UserOrderedHotelList>

                </UserOrderedHotelList>
            </a-tab-pane>
            <a-tab-pane tab="注册会员" key="4" v-if="userInfo.userType=='Client'">
                <a-form :form="form" style="margin-top: 30px">
                    <a-form-item label="用户名" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <span>{{ userInfo.userName }}</span>
                    </a-form-item>
                    <a-form-item label="信用值" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }">
                        <span>{{ userInfo.credit }}</span>
                    </a-form-item>
                    <a-alert
                            v-if="Number(userInfo.credit)<60"
                            message="警告"
                            description="您的信用值未满60，不得注册会员！"
                            type="error"
                            showIcon
                            closable></a-alert>
                    <a-form-item label="会员类型" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }" v-if="userInfo.memberType!=='未注册'&&userInfo.memberType!==null">
                        <span>{{ userInfo.memberType }}</span>
                    </a-form-item>
                    <a-form-item label="会员生日" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }" v-if="userInfo.birthday!=null&&userInfo.birthday!=='1900-01-01T00:00:00.000+0800'">
                        <span>{{ userInfo.birthday }}</span>
                    </a-form-item>
                    <a-form-item label="公司名称" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1  }" v-if=" userInfo.companyName!=='未注册'&&userInfo.companyName!==null">
                        <span>{{ userInfo.companyName }}</span>
                    </a-form-item>
                    <a-form-item :wrapper-col="{ span: 8, offset: 4 }" >
                        <a-button type="primary" @click="registMember" v-if="Number(userInfo.credit)>=60 && userInfo.memberType==null">
                            会员注册
                        </a-button>
                    </a-form-item>
                </a-form>
            </a-tab-pane>
        </a-tabs>
        <registMember></registMember>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import orderDetail from '../order/components/orderDetail'
    import OrderList from "../order/components/userOrderList"
    import UserOrderedHotelList from "../hotel/components/userOrderedHotelList";
    import registMember from "./registMember";
    import RegistMember from "./registMember";

    export default {
        name: 'info',
        data() {
            return {
                modify: false,
                modifyButtonVisible: true,
                modifyPasswordVisible: false,
                modifyPasswordButtonVisible: true,
                formLayout: 'horizontal',
                pagination: {},
                data: [],
                form: this.$form.createForm(this, {name: 'coordinated'}),
                //memberForm: this.$form.createForm(this, {name: 'coordinated2'}),
            }
        },
        components: {
            RegistMember,
            OrderList,
            UserOrderedHotelList,
        },
        computed: {
            ...mapGetters([
                'userId',
                'userInfo',
                'userOrderList',
                'registMemberVisible',
            ]),

        },
        async mounted() {
            await this.getUserInfo()
            await this.getUserOrders()
        },
        methods: {
            ...mapMutations(['set_currentOrderId','set_registMemberVisible']),
            ...mapActions([
                'getUserInfo',
                'getUserOrders',
                'updateUserInfo',
                'cancelOrder',
                'getOrderDetails',
                'modifyPassword'
            ]),
            saveModify() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            userName: this.form.getFieldValue('userName'),
                            phoneNumber: this.form.getFieldValue('phoneNumber'),
                            password: this.$md5(this.form.getFieldValue('password')).toString().substring(0,10)
                        }
                        this.updateUserInfo(data).then(() => {
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
            modifyUserPassword() {
                this.modifyPasswordVisible = true
                this.modifyPasswordButtonVisible = false
                this.modifyButtonVisible = false
            },
            savePassword() {

                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            password: this.$md5(this.form.getFieldValue('newPassword')).toString().substring(0,10)
                        }
                        console.log("s")
                        this.modifyPassword(data).then(() => {
                            this.modifyPasswordVisible = false
                            this.modifyButtonVisible = true
                            this.modifyPasswordButtonVisible = true
                        })
                    }
                });
            },
            cancelModifyPassword() {
                this.modifyPasswordVisible = false
                this.modifyPasswordButtonVisible = true
                this.modifyButtonVisible = true
            },
            cancelModify() {
                this.modify = false
                this.modifyButtonVisible = true
                this.modifyPasswordButtonVisible = true
            },
            confirmCancelOrder(orderId) {
                this.cancelOrder(orderId)
            },
            cancelCancelOrder() {

            },
            // handler
            handleUsernameOrEmail(rule, value, callback) {
                const {state} = this
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
            handlePasswordCheck(rule, value, callback) {
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
            registMember() {
                this.set_registMemberVisible(true)
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