<template>
    <div class="info">
        <a-table
                :columns="columns"
                :dataSource="creditList"
                bordered
        >

        </a-table>
    </div>
</template>



<script>
    import {mapActions, mapGetters, mapMutations} from 'vuex'

    const columns=[
        {
            title:'类型',
            dataIndex:'type'
        },
        {
            title: '修改的信用值大小',
            dataIndex: 'value',
        },
        {
            title: '信息',
            dataIndex: 'message',
        },
    ]

    export default {
        name: "creditInfo",
        data(){
            return {
                columns
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