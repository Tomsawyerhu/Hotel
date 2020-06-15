<template>
    <div class="info">
        <!--{{creditList}}-->
        <a-timeline pending="Recording..." >
            <a-timeline-item v-for="(item,i) in creditList" :key="i">
                <p>
                    <a-icon type="plus" spin="true" v-if="item.type==1"/>
                    <a-icon type="minus" spin="true" v-else/>
                    <span>{{item.message}}</span>

                </p>

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