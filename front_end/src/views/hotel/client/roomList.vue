<template>
    <div class="room-list">
        <div style="width: 100%; text-align: right; margin:20px 0">
            <a-button type="primary" @click="addRoom" v-if="userInfo.userType=='HotelManager'"><a-icon type="plus" />添加房间</a-button>
        </div>
        <div class="list">
            <a-table
                    :columns="columns"
                    :dataSource="rooms"
            >
                <!-- <span slot="roomType" slot-scope="text">
                     <span>￥{{ text }}</span>
                 </span>
                 <span slot="bedType" slot-scope="text">
                     <span>￥{{ text }}</span>
                 </span>
                 <span slot="breakfast" slot-scope="text">
                     <span>￥{{ text }}</span>
                 </span>
                 <span slot="peopleNum" slot-scope="text">
                     <span>￥{{ text }}</span>
                 </span>-->
                <span slot="price" slot-scope="text">
                    <span>￥{{ text }}</span>
                </span>
                <span slot="action" slot-scope="text, record">
                    <a-button type="primary" @click="order(record)" v-if="userInfo.userType=='Client'">预定</a-button>
                    <!--<p>{{record}}</p>-->
                </span>
                <!--<span slot="action" slot-scope="text, record" v-if="key===2">
                    <a-button type="primary" @click="order(record)">编辑</a-button>
                </span>-->

            </a-table>
        </div>
        <OrderModal></OrderModal>
    </div>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import OrderModal from './orderModal'
    const columns = [
        {
            title: '房型',
            dataIndex: 'roomType',
            key: 'roomType',
        },
        {
            title: '早餐',
            dataIndex: 'breakfast',
            key: 'breakfast',
        },
        {
            title: '入住人数',
            key: 'peopleNum',
            dataIndex: 'peopleNum',
        },
        {
            title: '现有房间',
            key: '',
            dataIndex: 'peopleNum',
        },
        {
            title: '入住人数',
            key: 'peopleNum',
            dataIndex: 'peopleNum',
        },
        {
            title: '房价',
            key: 'price',
            dataIndex: 'price',
            scopedSlots: { customRender: 'price'}
        },
        {
            title: '行动',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name:'roomList',
        props: {

            rooms: {
                type: Array
            }
        },
        data() {
            return {
                columns,
            }
        },
        components: {
            OrderModal,
        },
        computed: {
            ...mapGetters([
                'orderModalVisible',
                'roomEditVisible',
                'userInfo'
            ])
        },
        mounted() {

        },
        methods: {
            ...mapMutations([
                'set_orderModalVisible',
                'set_currentOrderRoom',
                'set_roomEditVisible',
                'set_currentRoom',
                'set_addRoomVisible'
            ]),
            ...mapActions([

            ]),
            order(record) {
                this.set_currentOrderRoom(record)
                this.set_orderModalVisible(true)
            },
            edit(record){
                this.set_currentRoom(record)
                /*console.log(this.roomEditVisible)*/
                this.set_roomEditVisible(true)
            },
            addRoom(){
                this.set_addRoomVisible(true)
            }
        }
    }
</script>