<template>
    <a-modal
            :visible="roomEditVisible"
            title="房间信息编辑"
            cancelText="取消"
            okText="编辑完成"
            @cancel="cancelEdit"
            @ok="EditSubmit"
    >
        <!--<p>{{currentHotelInfo}}}</p>-->
        <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-form-item label="早餐">
                <a-radio-group v-model="value" >
                    <a-radio  value="有">
                        有
                    </a-radio>
                    <a-radio  value="无">
                        无
                    </a-radio>
                </a-radio-group>
            </a-form-item>
            <a-form-item label="入住人数">
                <a-input
                        placeholder="请填写入住人数" v-model="value3"></a-input>
                        <!--v-decorator="['peopleNum', { rules: [{ required: true, message: '请填写入住人数' }] }]"-->
            </a-form-item>
            <a-form-item label="价格">
                <a-input
                        placeholder="请填写价格" v-model="value4"/>
                        <!--v-decorator="['price', { rules: [{ required: true, message: '请填写价格' }] }]"-->
            </a-form-item>
            <a-form-item label="总共房间">
            <a-input
                    placeholder="请填写总共房间" v-model="value1"/>
                    <!--v-decorator="['total', { rules: [{ required: true, message: '请填写总共房间' }] }]"-->
        </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "roomEdit",
        data(){
            return {
                form: this.$form.createForm(this, {name: 'coordinated'}),
                value:'',
                value1:'',
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
        mounted() {
            setTimeout(() => {
                this.form.setFieldsValue({
                    'peopleNum': this.currentHotelInfo.peopleNum,
                    'price': this.currentHotelInfo.price,
                    'total':this.currentHotelInfo.total
                })
            }, 0)

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
            ...mapActions(['updateRoomInfo','deleteRoom','getHotelById']),
            cancelEdit() {
                this.$store.commit('set_roomEditVisible',false)
            },
            EditSubmit() {
                const Info={ hotelId:this.currentHotelInfo.id, id: this.currentRoom.id, roomType: this.currentRoom.roomType, price: this.value4, curNum: this.currentRoom.curNum, total: this.value1, breakfast: this.value, peopleNum: this.value3}
                this.$store.commit('set_currentRoom',Info)
                /*console.log(this.currentHotelInfo)*/
                this.updateRoomInfo(this.currentRoom).then(this.getHotelById())
                this.$store.commit('set_roomEditVisible',false)
                this.getHotelById()
            }
        }
    }
</script>

<style scoped>

</style>