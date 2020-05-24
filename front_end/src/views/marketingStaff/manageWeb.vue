<template>
    <div class="manageWeb-wrapper">
        <a-tabs>
            <a-tab-pane tab="网站优惠策略" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary">
                        <a-icon type="plus"/>
                        添加优惠策略
                    </a-button>
                </div>
            </a-tab-pane>
            <a-tab-pane tab="异常订单处理" key="2">
                <AbnormalOrderList :order-list="abnormalOrderList">

                </AbnormalOrderList>




            </a-tab-pane>
        </a-tabs>
    </div>
</template>
<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'
    import AbnormalOrderList from "../order/components/abnormalOrderList";
    export default {
        name: 'manageWeb',
        data(){
            return {
                formLayout: 'horizontal',
                pagination: {},
                data: [],
                form: this.$form.createForm(this, { name: 'coordinated' }),
            }
        },
        components: {
            AbnormalOrderList
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
            ...mapMutations([
            ]),
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