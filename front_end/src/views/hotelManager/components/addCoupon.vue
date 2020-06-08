<template>
    <a-modal
            :visible="addCouponVisible"
            title="添加优惠策略"
            cancelText="取消"
            okText="确定"
            @cancel="cancel"
            @ok="handleSubmit"
    >
        <a-form :form="form" style="margin-top: 30px" v-bind="formItemLayout">
            <a-form-item label="优惠券类型">
                <a-select
                        v-decorator="[
                    'type',
                    { rules: [{ required: true, message: '请选择优惠券类型' }] }]"
                        @change="changeType"

                >
                    <a-select-option value="1">生⽇特惠</a-select-option>
                    <a-select-option value="2">多间特惠</a-select-option>
                    <a-select-option value="3">满减特惠</a-select-option>
                    <a-select-option value="4">限时特惠</a-select-option>
                </a-select>
            </a-form-item>

            <a-form-item label="券名" v-bind="formItemLayout" >
                <a-input
                        placeholder="请填写券名"
                        v-decorator="['name', { rules: [{ required: true, message: '请填写券名' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠简介" v-bind="formItemLayout" >
                <a-input
                        type="textarea"
                        :rows="4"
                        placeholder="请填写优惠简介"
                        v-decorator="['description', { rules: [{ required: true, message: '请填写优惠简介' }] }]"
                />
            </a-form-item>


            <a-form-item label="优惠条件" v-bind="formItemLayout" v-if="type==2">
                <a-input-number   :min="1" :max="10" step="1"
                                  :formatter="value => ` ${value}间起`"
                                  :parser="value => value.replace(/间/, '')"
                                  v-decorator="['targetRoomNum', { initialValue:5 }]"
                />
                <br/>
                每间折扣
                <a-input-number   :min="1.0" :max="10.0" step="0.5"
                                  :formatter="value => `${value}折`"
                                  :parser="value => value.replace(/折/,'')"
                                  v-decorator="['targetRoomDiscount', { initialValue:5.0 }]"
                />
            </a-form-item>
            <a-form-item label="达标金额" v-bind="formItemLayout" v-if="type==3||type==1">
                <a-input
                        placeholder="请填写达标金额"
                        v-decorator="['targetMoney', { rules: [{ required: true, message: '请填写达标金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="优惠金额" v-bind="formItemLayout" v-if="type==3||type==1">
                <a-input
                        placeholder="请填写优惠金额"
                        v-decorator="['discountMoney', { rules: [{ required: true, message: '请填写优惠金额' }] }]"
                />
            </a-form-item>
            <a-form-item label="折扣类型" v-if="type==4" >
                <a-radio-group default-value="1" @change="changeRadio"
                        v-decorator="['discountType']">
                    <a-radio name="dazhe" value="1">打折
                        <a-input-number   :min="1.0" :max="10.0" step="0.5"
                                          :formatter="value => `${value}折`"
                                          :parser="value => value.replace(/折/,'')"
                                          v-decorator="['discount1', { initialValue:5.0 }]"
                        />
                    </a-radio>
                    <br/>
                    <a-radio name="manjian" value="2">满
                        <a-input-number   :min="100" :max="2000" step="100"
                                          :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                                          :parser="value => value.replace(/¥\s?|(,*)/g, '')"
                                          v-decorator="['discount2', { initialValue:500 }]"
                        />减
                        <a-input-number   :min="100" :max="2000" step="50"
                                          :formatter="value => `¥ ${value}`.replace(/\B(?=(\d{3})+(?!\d))/g, ',')"
                                          :parser="value => value.replace(/¥\s?|(,*)/g, '')"
                                          v-decorator="['discount3', { initialValue:100 }]"
                        />
                    </a-radio>
                </a-radio-group>

            </a-form-item>

            <a-form-item label="时间限制" v-bind="formItemLayout" v-if="type==4">
                <a-range-picker
                    format="YYYY-MM-DD"
                    v-decorator="[
                        'date',
                        {
                            rules: [{ required: true, message: '请选择活动时间' }]
                        }
                    ]"
                    :placeholder="['开始时间','结束时间']"
                    v-validate="'checkInDate'"
                    data-vv-name="checkInDate"
            />
                <div class="err" style="color: #ff0000">{{errors.first('checkInDate')}}</div>
            </a-form-item>
        </a-form>
    </a-modal>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    export default {
        name: 'addCouponModal',
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
                discountType:1,
                birthdayCouponSelected:false
            }
        },
        computed: {
            ...mapGetters([
                'activeHotelId',
                'addCouponVisible',
            ])
        },
        beforeCreate() {
            // 表单名默认为“form”
            this.form = this.$form.createForm(this, { name: 'addCouponModal' });
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_addCouponVisible'
            ]),
            ...mapActions([
                // addHotelCoupon：添加酒店策略接口
                'addHotelCoupon'
            ]),
            cancel() {
                this.set_addCouponVisible(false)
            },
            changeType(v){
                //console.log(v)
                this.type=v
            },
            changeRadio(){
                if(this.discountType===1){this.discountType+=1;}
                else{this.discountType-=1;}
            },
            handleSubmit(e) {
                e.preventDefault();
                this.form.validateFieldsAndScroll((err, values) => {
                    if (!err) {
                        const data = {
                            //me:注意要把hotelId加上
                            id:0,
                            couponName: this.form.getFieldValue('name'),
                            description: this.form.getFieldValue('description'),
                            couponType: Number(this.form.getFieldValue('type')),
                            hotelId: Number(this.activeHotelId),
                            status: 1
                        }
                        if(Number(this.type)===4){
                            //限时特惠
                            console.log("限时特惠")
                            data.discountDate=[String(this.form.getFieldValue('date')[0].format("YYYY-MM-DD")),String(this.form.getFieldValue('date')[1].format("YYYY-MM-DD"))]
                            if(this.discountType===2){
                                //满减类型
                                data.targetMoney=Number(this.form.getFieldValue('discount2'))
                                data.discountMoney=Number(this.form.getFieldValue('discount3'))
                            }else {
                                //打折类型
                                data.discount=this.form.getFieldValue('discount1')
                            }
                        }else if(Number(this.type)===3||Number(this.type)===1){
                            //满减特惠,生日特惠
                            data.targetMoney=Number(this.form.getFieldValue('targetMoney'))
                            data.discountMoney=Number(this.form.getFieldValue('discountMoney'))
                        }else if(Number(this.type)===2){
                            console.log(this.form.getFieldValue('targetRoomDiscount'))
                            data.discount=this.form.getFieldValue('targetRoomDiscount')
                            data.targetRoomNum=this.form.getFieldValue('targetRoomNum')
                        }else if(Number(this.type)===1){
                            //生日特惠
                        }
                        console.log(this.activeHotelId)
                        this.addHotelCoupon(data)
                    }
                });
            },
        }
    }
</script>