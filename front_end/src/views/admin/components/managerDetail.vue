<template>
    <div>
        <div name="没有酒店管理人员" v-if="currentHotelInfo.managerId==null">
            <a-descriptions borderd size="default" title="酒店管理人员详情" bordered layout="vertical">

                <a-descriptions-item label="是否有酒店管理人员">无</a-descriptions-item>

                <a-descriptions-item label="添加酒店管理人员">
                    <a-button type="primary" :disabled="currentHotelInfo.manage_hotelId!=null" @click="addManager">添加</a-button>
                </a-descriptions-item>
            </a-descriptions>
        </div>
        <div name="有酒店管理人员" v-if="currentHotelInfo.managerId!=null">
        <a-descriptions borderd size="default" title="酒店管理人员详情" bordered layout="vertical">

            <a-descriptions-item label="是否有酒店管理人员">有</a-descriptions-item>

            <a-descriptions-item label="用户邮箱">
                {{targetAccount.email}}
            </a-descriptions-item>
            <a-descriptions-item label="用户名">
                {{targetAccount.userName}}
            </a-descriptions-item>
            <a-descriptions-item label="用户手机号">
                {{targetAccount.phoneNumber}}
            </a-descriptions-item>
            <a-descriptions-item label="信用值">
                {{targetAccount.credit}}
            </a-descriptions-item>
            <a-descriptions-item label="操作">
                <a-button @click="editManager" type="primary">
                    修改信息
                </a-button>
            </a-descriptions-item>
        </a-descriptions>
        </div>
        <a-button @click="back" type="primary" block>
            返回
        </a-button>
        <add-manager-modal :back="back"/>
        <edit-user-info/>
    </div>


</template>


<script>
    import {mapGetters, mapActions,mapMutations} from 'vuex'
    import addManagerModal from './addManagerModal'
    import editUserInfo from "./editUserInfo";
    export default {
        name: 'managerDetail',
        props: {
            back:{
                function () {
                }
            }
        },
        components:{
            addManagerModal,
            editUserInfo
        },
        data() {
            return {

            }
        },
        mounted() {
            if(this.currentHotelInfo.managerId!=null){
                this.$store.dispatch('getUserInfoById',this.currentHotelInfo.managerId)
            }
        },

        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'targetAccount',
            ]),
        },
        methods: {
            ...mapMutations([
                'set_targetAccount',
                'set_addManagerModalVisible'
            ]),
            ...mapActions(['checkIn', 'badOrder', 'checkOut']),
            edit(){

            },
            addManager(){
                this.set_addManagerModalVisible(true)
            },
            editManager(){
                this.$store.commit('set_UserInfoEditVisible',true)
            }
        }
    }

</script>