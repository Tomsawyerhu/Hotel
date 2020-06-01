<!--
<template>
    <a-modal
            :visible="roomEditVisible"
            title="房间信息编辑"
            cancelText="取消"
            okText="编辑完成"
            @cancel="cancelEdit"
            @ok="EditSubmit"
    >
        &lt;!&ndash;<p>{{currentHotelInfo}}}</p>&ndash;&gt;
        <a-form  :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-form-item label="早餐">
                <a-input
                        v-model="value2"
                />
            </a-form-item>
            <a-form-item label="入住人数">
                <a-input
                        v-model="value3"
                />
            </a-form-item>
            <a-form-item label="价格">
                <a-input
                        v-model="value4">
                </a-input>
            </a-form-item>
        </a-form>
        <a-button type="primary" @click="deleteRoom">删除此房间</a-button>
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "editUserInfo",
        data(){
            return {
                value2:'',
                value3:'',
                value4:''
                /*value: {
                    roomType :this.currentRoom.roomType,
                    breakfast:this.currentRoom.breakfast,
                    peopleNum:this.currentRoom.peopleNum,
                    price:this.currentRoom.price
                }*/
            }
        },

        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'roomEditVisible',
                'currentRoom'
            ])
        },
        methods: {
            ...mapMutations([
                'set_currentHotelInfo_d',
                'set_roomEditVisible',
                'set_currentRoom'

            ]),
            ...mapActions(['updateRoomInfo','deleteRoom']),
            cancelEdit() {
                this.$store.commit('set_roomEditVisible',false)
            },
            EditSubmit() {
                const Info={ hotelId:this.currentHotelInfo.id, id: this.currentRoom.id, roomType: this.currentRoom.roomType, price: this.value4, curNum: this.currentRoom.curNum, total: this.currentRoom.total, breakfast: this.value2, peopleNum: this.value3}
                this.$store.commit('set_currentRoom',Info)
                /*console.log(this.currentHotelInfo)*/
                this.$store.dispatch('updateRoomInfo',this.currentRoom)
                this.$store.commit('set_roomEditVisible',false)

            },
            deleteRoom(){
                this.$store.dispatch('deleteRoom',this.currentRoom.id)
                this.$store.commit('set_roomEditVisible',false)
            }
        }
    }
</script>

<style scoped>

</style>-->
