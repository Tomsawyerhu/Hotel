<template>
    <div>
        <a-list
                :dataSource="hotelCommentList"
        >
            <a-list-item slot="renderItem" slot-scope="item">
                <comment :comment-item="item"></comment>
            </a-list-item>
        </a-list>
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
            return {}
        },
        props: {
            hotelCommentList: {
                type: Array
            }
        },
        computed: {
            ...mapGetters(['currentComment'])
        },
        mounted() {

        },
        methods: {
            ...mapMutations([]),
            ...mapActions([]),
        }

    }
</script>

<style scoped>
    .head-message {
        font-size: 20px;
        text-align: center;
    }
</style>
