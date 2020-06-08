<template>
    <div>
        <a-modal
                :visible="addCommentVisible"
                title="评价订单"
                cancelText="取消"
                okText="确定"
                @cancel="cancel"
                @ok="handleSubmit"
        >
            <a-form :form="form" style="margin-top: 30px">
                <a-form-item label="订单评分">
                    <a-rate v-model="rate"
                            v-decorator="['rate', { rules: [{ required: true, message: '请给这次订单打分' }] }]"
                    >
                    </a-rate>
                </a-form-item>
                <a-form-item label="订单评价">
                    <a-textarea
                            v-decorator="['content', { rules: [{ required: false}] }]"
                    >
                    </a-textarea>
                </a-form-item>
            </a-form>
        </a-modal>
    </div>
</template>

<script>
    import orderDetail from "./orderDetail";
    import { message } from 'ant-design-vue'
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: "addComment",
        data() {
            return {
                formLayout: 'horizontal',
                form: this.$form.createForm(this, {name: 'coordinated'}),
                rate: 0,
            }

        },
        props: {},
        components: {},
        computed: {
            ...mapGetters([
                'addCommentVisible',
                'currentOrderId',
                'currentOrderInfo',
            ]),

        },
        methods: {
            ...mapMutations(['set_addCommentVisible']),
            ...mapActions(['addComment']),

        },
        cancel(){

        },
        handleSubmit(){
            this.form.validateFields((err, values) => {
                if (!err) {
                    const data = {
                        content: this.form.getFieldValue('content'),
                        rate: Number(this.form.getFieldValue('rate')),
                        orderId: this.currentOrderId,
                        roomNum: this.currentOrderInfo.roomNum,
                        roomType: this.currentOrderInfo.roomType,
                        hotelId: this.currentOrderInfo.hotelId,
                    }
                    this.addComment(data).then(()=>{
                        message.success("评价成功")
                        this.set_addCommentVisible(false)
                    })

                }
            })
        }
    }
</script>

<style scoped>

</style>