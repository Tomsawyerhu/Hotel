<template>
    <div>
        <div v-if="isEmpty(this.hotelCommentList)" class="head-message">暂无留言内容</div>
        <div v-else>
            <div class="head-message">留言内容</div>
            <a-list
                    :datasource="hotelCommentList"
            ></a-list>
        </div>
    </div>
</template>

<script>
    import {mapActions, mapGetters, mapMutations} from "vuex";
    import Comment from "./comment";

    export default {
        name: "commentList",
        components: {
            Comment
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
        },
        methods: {
            ...mapMutations([

            ]),
            ...mapActions([
                'getHotelCommentList',
            ]),
            isEmpty(data){
                console.log("in isEmpty")
                console.log(data)
                console.log(data[0])
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
