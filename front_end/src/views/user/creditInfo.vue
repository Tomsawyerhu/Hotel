<template>
    <div class="info">
        <!--{{creditList}}-->
        <a-timeline pending="Recording..." >
            <a-timeline-item v-for="(item,i) in creditList" :key="i">
                <p>{{item.message}}</p>
                <p v-if="item.type==-1">减少信用</p>
                <p v-if="item.type==1">增加信用</p>
                <p>时间:{{item.time}}</p>
                <p>修改大小:{{item.value}}</p>
            </a-timeline-item>

        </a-timeline>
    </div>
</template>



<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'



    export default {
        name: "creditInfo",
        data(){
            return {
            }
        },
        props:{
            creditList:{
                type:Array
            }
        },
        computed: {
            ...mapGetters([
                'userId'
            ]),

        },
        methods: {
            ...mapMutations(['']),
            ...mapActions([

            ]),

        }
    }
</script>

<style scoped>

</style>