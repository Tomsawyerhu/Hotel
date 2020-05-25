<template>
    <div>
        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addWebCoupon">
                <a-icon type="plus"/>
                添加优惠策略
            </a-button>
        </div>
        <a-table
                :columns="columns"
                :dataSource="websiteCouponList"
                bordered
        >
            <a-tag color="red" slot="couponName" slot-scope="text">
                {{text}}
            </a-tag>
            <span slot="action" slot-scope="record">
                        <a-popconfirm
                                title="您确定要撤销该网站优惠吗？"
                                @confirm="confirmCancelCoupon(record.id)"
                                @cancel="cancelCancelCoupon"
                                okText="确定"
                                cancelText="取消"
                        >
                            <a-button type="danger" size="small">撤销</a-button>
                        </a-popconfirm>
            </span>
        </a-table>
        <AddWebsiteCoupon></AddWebsiteCoupon>
    </div>
</template>
<script>
    import Vue from 'vue'

    var vm = new Vue({
        el: ''

    })
    import {mapGetters, mapMutations, mapActions} from 'vuex'
    import AddWebsiteCoupon from "./addWebsiteCoupon";

    const columns = [
        // 这里定义列表头
        {
            title: "优惠券ID",
            dataIndex: 'id'
        },
        {
            title: "优惠类型",
            dataIndex: 'couponType'
        },
        {
            title: "折扣",
            dataIndex: 'discount',
        },
        {
            title: "优惠简介",
            dataIndex: 'description',
        },
        {
            title: "优惠金额",
            dataIndex: 'discountMoney',
        },
        {
            title: "开始时间",
            dataIndex: 'startTime',
            scopedSlots: {customRender: 'startTime'}
        },
        {
            title: "结束时间",
            dataIndex: 'endTime',
            scopedSlots: {customRender: 'endTime'}
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: {customRender: 'action'},
        },
    ];
    export default {
        name: 'coupon',
        data() {
            return {
                columns
            }
        },
        components: {
            AddWebsiteCoupon,
        },
        computed: {
            ...mapGetters([
                'websiteCouponList',
            ])
        },
        async mounted() {
            await this.getWebsiteCouponList()
        },
        methods: {
            ...mapMutations([
                'set_addWebCouponVisible'
            ]),
            ...mapActions([
                'getWebsiteCouponList',
                'cancelCoupon'
            ]),
            addWebCoupon() {
                this.set_addWebCouponVisible(true)
            },
            confirmCancelCoupon(couponId) {
                this.cancelCoupon(couponId).then(()=>{
                    this.getWebsiteCouponList()
                })
            },
            cancelCancelCoupon() {

            },
        },
    }
</script>
<style scoped>

</style>