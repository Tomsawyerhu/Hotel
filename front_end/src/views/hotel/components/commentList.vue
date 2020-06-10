<template>
    <div>
        <div v-if="isEmpty(hotelCommentList)" class="head-message">暂无留言内容</div>
        <div v-else class="head-message">留言内容</div>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";

    export default {
        name: "commentList",
        components: {

        },
        data() {
            return {

            }
        },
        computed: {
            ...mapGetters([
                'hotelCommentList',
                'currentHotelId',
            ])
        },
        mounted() {
            this.getHotelCommentList(this.currentHotelId)
            console.log(this.currentHotelId+"in commentList.vue")
        },
        methods: {
            ...mapMutations([

            ]),
            ...mapActions([
                'getHotelCommentList',
            ]),
            isEmpty(data){
                console.log(data+"in isEmpty")
                return data.length===0
            },
        }

    }
</script>

<style scoped>
    .head-message {
        font-size: 20px;
        text-align: center;
    }
</style>
