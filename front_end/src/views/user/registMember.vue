<template>
    <a-modal
            :visible="registMemberVisible"
            title="注册会员"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="会员类型" v-bind="formItemLayout">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择会员类型' }] }]"
                        @change="changeType"
                >
                    <a-select-option value="1">个人会员</a-select-option>
                    <a-select-option value="2">企业会员</a-select-option>
                </a-select>
            </a-form-item>
            <a-form-item label="填写生日" v-bind="formItemLayout" v-if="type==1">
                <a-input
                        placeholder="格式为xxxx-xx-xx"
                        v-decorator="['birthday', { rules: [{ required: true, message: '请选择出生日期' }] }]"
                />
            </a-form-item>
            <a-form-item label="填写企业名称" v-bind="formItemLayout" v-if="type==2">
                <a-input
                        placeholder="请填写企业名称"
                        v-decorator="['companyName', { rules: [{ required: true, message: '请输入企业名称' }] }]"
                />
            </a-form-item>
            <a-form-item label="填写密码" v-bind="formItemLayout" v-if="type==1">
                <a-input
                        type="password"
                        placeholder="请填写密码"
                        v-decorator="['password', { rules: [{ required: true, message: '请输入密码'}] }]"
                >
                    <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>
            <a-form-item label="填写密码" v-bind="formItemLayout" v-if="type==2">
                <a-input
                        type="password"
                        placeholder="请填写密码"
                        v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }] }]"
                >
                    <a-icon slot="prefix" type="lock" :style="{ color: 'rgba(0,0,0,.25)' }"/>
                </a-input>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'registMember',
        data() {
            return {
                formItemLayout: {
                    labelCol: {
                        xs: { span: 12 },
                        sm: { span: 6 },
                    },
                    wrapperCol: {
                        xs: { span: 24 },
                        sm: { span: 16 },
                    },
                },
                type:0,
            }
        },
        computed: {
            ...mapGetters([
                'userId',
                'registMemberParams',
                'registMemberVisible'
            ])
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, { name: 'registMember' });
        },
        mounted() {
        },
        methods: {
            ...mapMutations([
                'set_registMemberParams',
                'set_registMemberVisible'
            ]),
            ...mapActions([
                'addMember',
            ]),
            cancel() {
                this.set_registMemberVisible(false)
            },
            changeType(v){
                this.type=v
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        if(Number(this.type)==1){
                        const data = {
                            password: this.$md5(this.form.getFieldValue('password')).toString().substring(0,10),
                            memberType: '个人会员',
                            birthday: this.form.getFieldValue('birthday'),
                            companyName: '未注册',
                        }
                        this.set_registMemberParams(data)
                        this.addMember()
                    } else{
                        const data = {
                            password: this.$md5(this.form.getFieldValue('password')).toString().substring(0,10),
                            memberType: '企业会员',
                            birthday: '1900-01-01',
                            companyName: this.form.getFieldValue('companyName'),
                        }
                        this.set_registMemberParams(data)
                        this.addMember()
                    }}
                });
            },
        }
    }
</script>

