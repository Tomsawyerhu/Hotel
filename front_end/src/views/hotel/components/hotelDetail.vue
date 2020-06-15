<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style=" width: 240px">
                        <img
                                alt="example"
                                :src="currentHotelInfo.pictureUrl"
                                slot="cover"
                                referrerPolicy="no-referrer"
                                v-if="currentHotelInfo.pictureUrl!=null"
                        />
                        <img
                                alt="example"
                                src="@/assets/cover.jpeg"
                                slot="cover"
                                referrerPolicy="no-referrer"
                                v-else
                        />
                    </a-card>
                    <div class="info">
                        <div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称:</span>
                            <span class="value">{{ currentHotelInfo.name }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址:</span>
                            <span class="value">{{ currentHotelInfo.address }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate.toFixed(1) }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="value">{{ currentHotelInfo.description }}</span>
                            <!--<span>{{ currentHotelInfo.rooms }}</span>-->
                        </div>
                    </div>
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息" key="1">
                        <RoomList :rooms="currentHotelInfo.rooms"></RoomList>
                    </a-tab-pane>

                    <a-tab-pane tab="历史订单" key="2">
                        <OrderList :order-list="userOrdersInCertainHotel">

                        </OrderList>
                    </a-tab-pane>
                    <a-tab-pane tab="住客评论" key="3">
                        <CommentList :hotel-comment-list="hotelCommentList"></CommentList>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
    import {mapGetters, mapActions, mapMutations} from 'vuex'
    import RoomList from './roomList'
    import OrderList from '@/views/order/components/userOrderList'
    import CommentList from './commentList'
    import hotel from "../../../store/modules/hotel";

    export default {
        name: 'hotelDetail',
        components: {
            OrderList,
            RoomList,
            CommentList,
        },
        data() {
            return {
                pictureUrl: ""
            }
        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'userInfo',
                'currentHotelId',
                'userId',
                'userOrdersInCertainHotel',
                'hotelCommentList',
            ])
        },
        mounted() {
            this.set_currentHotelId(Number(this.$route.params.hotelId))
            //console.log("hotelId in mounted "+this.currentHotelId)
            this.getHotelById()
            this.getUserOrdersInCertainHotel({userId: this.userId, hotelId: this.currentHotelId}) //加载客户在该酒店的历史订单
            this.getHotelCommentList(Number(this.$route.params.hotelId)) //加载酒店评论列表
        },
        beforeRouteUpdate(to, from, next) {
            this.set_currentHotelId(Number(to.params.hotelId))
            this.getHotelById()
            this.getUserOrdersInCertainHotel()
            next()
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId',
            ]),
            ...mapActions([
                'getHotelById',
                'getUserOrdersInCertainHotel',
                'getHotelCommentList',
            ]),
        }
    }
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }

    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;

        .info {
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;

            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;

                .label {
                    margin-right: 10px;
                    font-size: 18px;
                }

                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>