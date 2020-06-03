<template>
    <a-modal
            :visible="addRoomVisible"
            title="添加房间"
            cancelText="取消"
            okText="添加完成"
            @cancel="cancelEdit"
            @ok="EditSubmit"
    >
        <!--<p>{{currentHotelInfo}}}</p>-->
        <a-form  :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-form-item label="房间类型">
                <a-input
                        v-model="value4">
                </a-input>
            </a-form-item>
            <a-form-item label="早餐">
                <a-input
                        v-model="value1"
                />
            </a-form-item>
            <a-form-item label="入住人数">
                <a-input
                        v-model="value2"
                />
            </a-form-item>
            <a-form-item label="价格">
                <a-input
                        v-model="value3">
                </a-input>
            </a-form-item>
            <a-form-item label="总数量">
                <a-input
                        v-model="value5">
                </a-input>
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "newRoom",
        data(){
            return {
                value1:'',
                value2:'',
                value3:'',
                value4:'',
                value5:''
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
                'addRoomVisible',
                'currentRoom'
            ])
        },
        methods: {
            ...mapMutations([
                'set_currentHotelInfo_d',
                'set_addRoomVisible',
                'set_currentRoom'

            ]),
            ...mapActions(['addNewRoom','getHotelById']),
            cancelEdit() {
                this.$store.commit('set_addRoomVisible',false)
            },
            EditSubmit() {
                const Info={ hotelId:this.currentHotelInfo.id, id: 0, roomType: this.value4, price: this.value3, curNum: this.value5, total: this.value5, breakfast: this.value1, peopleNum: this.value2}
                this.$store.commit('set_currentRoom',Info)
                this.addNewRoom(this.currentRoom).then(this.getHotelById())
                this.getHotelById()
                console.log(this.currentHotelInfo)
            },
        }
    }
</script>

<style scoped>

</style>