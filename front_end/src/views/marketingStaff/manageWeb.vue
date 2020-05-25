<template>
    <div class="manageWeb-wrapper">
        <a-tabs>
            <a-tab-pane tab="网站优惠策略" key="1">
                <WebsiteCoupon></WebsiteCoupon>
            </a-tab-pane>
            <a-tab-pane tab="异常订单处理" key="2">
                <AbnormalOrderList :order-list="abnormalOrderList">
                </AbnormalOrderList>
            </a-tab-pane>
            <a-tab-pane tab="用户信用充值" key="3">
                <AddCredit>
                </AddCredit>
            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import AbnormalOrderList from "../order/components/abnormalOrderList";
    import AddCredit from "./componets/addCredit";
    import WebsiteCoupon from "./componets/websiteCoupon";

    export default {
        name: 'manageWeb',
        data() {
            return {
                formLayout: 'horizontal',
                pagination: {},
            }
        },
        components: {
            AbnormalOrderList,
            AddCredit,
            WebsiteCoupon,
        },
        computed: {
            ...mapGetters([
                'abnormalOrderList'
            ]),
        },
        async mounted() {
            await this.getAbnormalOrders()
        },
        methods: {
            ...mapMutations([]),
            ...mapActions([
                'getAbnormalOrders',
            ]),
        }
    }

</script>
<style scoped lang="less">
    .manageWeb-wrapper {
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
    .manageWeb-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>