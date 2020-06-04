<template>
    <div class="manageUser-wrapper">
        <a-tabs>
            <a-tab-pane tab="用户账户" key="1">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <!--<p>{{userList}}</p>-->
                </div>
                <a-table
                        :columns="columns2"
                        :dataSource="clientList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary" @click="edit(record)">修改账户信息</a-button>
                        <!--{{record}}-->
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" @click="delete_(record)">删除用户</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="酒店管理人员账户" key="2">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    {{managerList}}
                </div>
                <a-table
                        :columns="columns1"
                        :dataSource="managerList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary" @click="edit(record)">修改账户信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" @click="delete_(record)">删除用户</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
            <a-tab-pane tab="网站营销人员账户" key="3">
                <div style="width: 100%; text-align: right; margin:20px 0">
                    <a-button type="primary" @click="addStaff"><a-icon type="plus" />添加账户</a-button>
                    <!--<p>{{staffList}}</p>-->
                </div>
                <a-table
                        :columns="columns2"
                        :dataSource="staffList"
                        bordered
                >
                    <span slot="price" slot-scope="text">
                        <span>￥{{ text }}</span>
                    </span>
                    <span slot="action" slot-scope="text, record">
                        <a-button type="primary" @click="edit(record)">修改账户信息</a-button>
                        <a-divider type="vertical"></a-divider>
                        <a-button type="danger" @click="delete_(record)">删除用户</a-button>
                    </span>
                </a-table>
            </a-tab-pane>
        </a-tabs>
        <AddManagerModal></AddManagerModal>
        <editUserInfo></editUserInfo>
        <addStaffModal></addStaffModal>
    </div>
</template>
<script>
    import { mapGetters, mapMutations, mapActions } from 'vuex'
    import AddManagerModal from './components/addManagerModal'
    import editUserInfo from "./components/editUserInfo";
    import addStaffModal from "./components/addStaffModal";
    const columns1 = [
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
        },
        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
        {
            title:'管理的酒店的编号',
            dataIndex:'manage_hotelId'
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    const columns2 = [
        {
            title: '用户邮箱',
            dataIndex: 'email',
        },
        {
            title: '用户名',
            dataIndex: 'userName',
        },
        {
            title: '用户手机号',
            dataIndex: 'phoneNumber',
        },
        {
            title: '信用值',
            dataIndex: 'credit',
        },
        {
            title: '操作',
            key: 'action',
            scopedSlots: { customRender: 'action' },
        },
    ];
    export default {
        name: 'manageUser',
        data(){
            return {
                formLayout: 'horizontal',
                pagination: {},
                columns1,
                columns2,
                data: [],
                form: this.$form.createForm(this, { name: 'manageUser' }),
            }
        },
        components: {
            AddManagerModal,
            editUserInfo,
            addStaffModal
        },
        computed: {
            ...mapGetters([
                'addManagerModalVisible',
                'managerList',
                'clientList',
                'staffList'
            ])
        },
        async mounted() {
            await this.getManagerList()
            await this.getStaffList()
            await this.getClientList()

        },
        methods: {
            ...mapActions([
                'getManagerList',
                'getClientList',
                'getStaffList',
                'deleteAccount'
            ]),
            ...mapMutations([
                'set_addManagerModalVisible',
                'set_targetAccount',
                'set_addStaffModalVisible',
                'set_editUser',
                'set_UserInfoEditVisible'
            ]),
            addManager(){
                this.set_addManagerModalVisible(true)
            },
            addStaff(){
                this.set_addStaffModalVisible(true)
            },
            delete_(record){
                this.set_targetAccount(record)
                this.deleteAccount()
            },
            edit(record){
                this.set_targetAccount(record)
                console.log(record)
                this.$store.commit('set_UserInfoEditVisible',true)
            },

        }
    }
</script>
<style scoped lang="less">
    .manageUser-wrapper {
        padding: 50px;
        .chart {
            display: flex;
            align-items: center;
            justify-content: space-between;
            margin-top: 20px
        }
    }
</style>
<style lang="less">
    .manageUser-wrapper {
        .ant-tabs-bar {
            padding-left: 30px
        }
    }
</style>
<style lang="less">

</style>