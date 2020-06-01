<template>
    <a-modal
            :visible="descEditVisible"
            title="酒店详情"
            cancelText="取消"
            okText="编辑完成"
            @cancel="cancelEdit"
            @ok="EditSubmit"
    >
        <p>{{currentHotelInfo}}}</p>
        <a-textarea
                v-model="value"
                placeholder="Controlled autosize"
                :auto-size="{ minRows: 8, maxRows: 10}"

        />
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "ddescribe",
        data(){
            return {
                value:''
            }
        },

        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'descEditVisible',
            ])
        },
        methods: {
            ...mapMutations([
                'set_currentHotelInfo_d',
                'set_descEditVisible'
            ]),
            ...mapActions(['updateHotelInfo']),
            cancelEdit() {
                this.$store.commit('set_descEditVisible',false)
            },
            EditSubmit() {
                this.$store.commit('set_currentHotelInfo_d',this.value)
                console.log(this.currentHotelInfo)
                this.$store.dispatch('updateHotelInfo',this.currentHotelInfo)
                this.$store.commit('set_descEditVisible',false)
            }
        }
    }
</script>

<style scoped>

</style>