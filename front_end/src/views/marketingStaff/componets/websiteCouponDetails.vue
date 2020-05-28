<template>
    <div>
        <a-modal
                :visible="couponDetailVisible"
                title="优惠策略详情"
                width="900px"
                :footer="null"
                okText="确定"
                @cancel="cancelShowCouponDetail"
        >
            <a-descriptions borderd size="default" bordered layout="vertical">
                <a-descriptions-item label="优惠券ID" >
                    {{currentCouponInfo.id}}
                </a-descriptions-item>
                <a-descriptions-item label="优惠券名" >
                    {{currentCouponInfo.couponName}}
                </a-descriptions-item>
                <a-descriptions-item label="优惠券类型"  v-if="currentCouponInfo.couponType=='1'">
                    生日特惠
                </a-descriptions-item>
                <a-descriptions-item label="优惠券类型"  v-if="currentCouponInfo.couponType=='2'">
                    多间特惠
                </a-descriptions-item>
                <a-descriptions-item label="优惠券类型"  v-if="currentCouponInfo.couponType=='3'">
                    满减特惠
                </a-descriptions-item>
                <a-descriptions-item label="优惠券类型"  v-if="currentCouponInfo.couponType=='4'">
                    限时特惠
                </a-descriptions-item>
                <a-descriptions-item label="目标金额"  v-if="currentCouponInfo.targetMoney>0">
                    {{currentCouponInfo.targetMoney}}
                </a-descriptions-item>
                <a-descriptions-item label="优惠金额"  v-if="currentCouponInfo.targetMoney>0">
                    {{currentCouponInfo.discountMoney}}
                </a-descriptions-item>
                <a-descriptions-item label="折扣"  v-if="currentCouponInfo.discount>0">
                    {{currentCouponInfo.discount}}%
                </a-descriptions-item>
                <a-descriptions-item label="开始时间" >
                    {{currentCouponInfo.startTime}}
                </a-descriptions-item>
                <a-descriptions-item label="结束时间" >
                    {{currentCouponInfo.endTime}}
                </a-descriptions-item>
                <a-descriptions-item label="优惠券简介" >
                    {{currentCouponInfo.description}}
                </a-descriptions-item>
            </a-descriptions>
        </a-modal>
    </div>
</template>

<script>
    import AddWebsiteCoupon from "./addWebsiteCoupon";
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: 'websiteCouponDetails',
        data() {
            return {

            }
        },
        components: {
        },
        computed: {
            ...mapGetters([
                'currentCouponInfo',
                'couponDetailVisible',
            ])
        },
        async mounted() {
        },
        methods: {
            ...mapMutations([
                'set_addWebCouponVisible',
                'set_couponDetailVisible',
            ]),
            ...mapActions([
            ]),
            cancelShowCouponDetail(){
                this.set_couponDetailVisible(false)
            }
        }
    }
</script>