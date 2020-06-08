<template>
    <a-modal
            :visible="UserInfoEditVisible"
            title="账户信息编辑"
            cancelText="取消"
            okText="编辑完成"
            @cancel="cancelEdit"
            @ok="EditSubmit"
    >
        <!--<p>{{currentHotelInfo}}}</p>-->
        <a-form  :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }">
            <a-form-item label="用户名">
                <a-input
                        v-model="value2"
                        place_holder="targetAccount.userName"
                />
            </a-form-item>
            <a-form-item label="用户手机号">
                <a-input
                        v-model="value1"
                        :default-value="targetAccount.phoneNumber"
                />
            </a-form-item>
        </a-form>
    </a-modal>
</template>

<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    export default {
        name: "editUserInfo",
        data(){
            return {
                value1:'',
                value2:'',
            }
        },

        computed: {
            ...mapGetters([
                'UserInfoEditVisible',
                'targetAccount'
            ])
        },
        methods: {
            ...mapMutations([
                'set_UserInfoEditVisible',
                'set_targetAccount'
            ]),
            ...mapActions(['updateUserInfo']),
            cancelEdit() {
                this.$store.commit('set_UserInfoEditVisible',false)
            },
            EditSubmit() {
                const Info={id:this.targetAccount.id,
                    email:this.targetAccount.email,
                    password:this.targetAccount.password,
                    userName:this.value2,
                    phoneNumber:this.value1,
                    credit:this.targetAccount.credit,
                    userType:this.targetAccount.userType,
                    manage_hotelId:this.targetAccount.manage_hotelId}
                this.$store.commit('set_targetAccount',Info)
                this.$store.dispatch('updateAccountInfo')

            }

        }
    }
</script>

<style scoped>

</style>
