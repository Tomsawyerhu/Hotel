<template>
    <div class="hotelList">
        <a-layout>
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper">
                        <HotelCard :hotel="item" v-for="item in userHotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>
                        <a-pagination showQuickJumper :total="userHotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
    </div>
</template>
<script>
    import HotelCard from '../hotelCard'
    import { mapGetters, mapActions, mapMutations } from 'vuex'

    export default {
        name: 'home',
        components: {
            HotelCard
        },
        data(){
            return{
                emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}]
            }
        },
        async mounted() {
            await this.getUserOrderedHotels().then(()=>{
                this.set_hotelListLoading(false)
            })
        },
        computed: {
            ...mapGetters([
                'userHotelList',
                'hotelListLoading'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading'
            ]),
            ...mapActions([
                'getUserOrderedHotels'
            ]),

            pageChange(page, pageSize) {
                const data = {
                    pageNo: page - 1
                }
                this.set_hotelListParams(data)
                this.set_hotelListLoading(true)
                this.getUserOrderedHotels()
                console.log("userHotel "+this.userHotelList)
            },
            jumpToDetails(id){
                this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
            }
        }
    }
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;
        .emptyBox {
            height: 0;
            margin: 10px 10px
        }
        .card-wrapper{
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }
        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }
</style>