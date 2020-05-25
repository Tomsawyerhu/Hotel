<template>
    <div>
        <a-modal
                :visible="couponDetailVisible"
                title="优惠策略详情"
                width="900px"
                :footer="null"
                @cancel="cancelShowCouponDetail"
        >
            <a-descriptions>
                <a-descriptions-item>
                    {{currentCouponInfo.startTime}}
                </a-descriptions-item>
                <a-descriptions-item>
                    {{currentCouponInfo.endTime}}
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