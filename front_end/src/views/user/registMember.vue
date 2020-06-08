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
                        placeholder="请填写出生日期"
                        v-decorator="['birth_date', { rules: [{ required: true, message: '请输入出生日期，格式为YYYY-MM-DD' }] }]"
                />
            </a-form-item>
            <a-form-item label="填写企业名称" v-bind="formItemLayout" v-if="type==2">
                <a-input
                        placeholder="请填写企业名称"
                        v-decorator="['company_name', { rules: [{ required: true, message: '请输入企业名称' }] }]"
                />
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
                'set_addHotelParams',
                'set_registMemberVisible'
            ]),
            ...mapActions([
                'addHotel'
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
                        if(type==1){
                        const data = {
                            member_type: '个人会员',
                            birth_date: this.form.getFieldValue('birth_date'),
                            company_name: null,
                        }
                        this.set_registMemberParams(data)
                        this.addHotel()
                    }
                    }else{
                        const data = {
                            member_type: '企业会员',
                            birth_date: null,
                            company_name: this.form.getFieldValue('company_name'),
                        }
                        this.set_registMemberParams(data)
                        this.addHotel()
                    }
                });
            },
        }
    }
</script>
