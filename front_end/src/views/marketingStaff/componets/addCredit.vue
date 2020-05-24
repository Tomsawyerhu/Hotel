<template>
    <div class="addCredit-wrapper">
        <a-form :form="form" style="margin-top: 30px">
            <a-form-item label="用户邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <a-input
                        placeholder="请填写要充值信用值的用户的邮箱"
                        v-decorator="['email', { rules: [{ required: true, message: '请输入用户邮箱' }] }]"
                >
                    <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>
            <a-form-item label="确认用户邮箱" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <a-input
                        placeholder="请确认用户邮箱"
                        v-decorator="['emailConfirm', { rules: [{ required: true, message: '请确认用户邮箱' }, { validator: this.handleEmailCheck }] , validateTrigger: 'blur'}]">
                    >
                    <a-icon slot="prefix" type="mail" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>
            <a-form-item label="充值数额" :label-col="{ span: 3 }" :wrapper-col="{ span: 8, offset: 1 }">
                <a-input
                        prefix="¥"
                        placeholder="请填写要充值的数额"
                        v-decorator="['amount', { rules: [{ required: true, message: '请输入充值数额' }, { validator: this.handleAmount }] , validateTrigger: 'blur'}]">
                </a-input>
                <a-icon slot="prefix" type="¥" :style="{ color: 'rgba(0,0,0,.25)' }"/>
            </a-form-item>
            <a-form-item :wrapper-col="{ span: 8, offset: 4 }">
                <a-button style="margin-left: 305px" type="primary" @click="addCreditByUserEmail">
                    充值
                </a-button>
            </a-form-item>
        </a-form>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: 'addCredit',
        data() {
            return {
                formLayout: 'horizontal',
                form: this.$form.createForm(this, {name: 'coordinated'}),
            }
        },
        computed: {
            ...mapGetters([]),
        },
        methods: {
            ...mapMutations([]),
            ...mapActions([
                'addCredit'
            ]),
            addCreditByUserEmail() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            userEmail: this.form.getFieldValue('email'),
                            amount: Number(this.form.getFieldValue('amount')),
                        }
                        this.addCredit(data)
                    }
                })
            },
            handleAmount(rule, value, callback) {
                if (value <= 0) {
                    callback(new Error('充值数额需大于0'))
                }
                callback()
            },
            handleEmailCheck(rule, value, callback) {
                const email = this.form.getFieldValue('email')
                if (value === undefined) {
                    callback(new Error('请输入用户邮箱'))
                }
                if (value && email && value.trim() !== email.trim()) {
                    callback(new Error('请确认用户邮箱'))
                }
                callback()
            },
        }
    }
</script>

<style scoped lang="less">
    .addCredit-wrapper {
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
    .addCredit-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>