<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title }}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px">
                        <img
                                alt="example"
                                src="@/assets/cover.jpeg"
                                slot="cover"
                                referrerPolicy="no-referrer"
                        />
                    </a-card>
                    <a-form :form="form" >
                        <a-form-item label="酒店名称">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.name }}</span>
                            <a-input
                                    placeholder="请填写酒店名字"
                                    v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名字' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="地址">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.address }}</span>
                            <a-input
                                    placeholder="请填写酒店地址"
                                    v-decorator="['hotelAddress', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="评分">
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </a-form-item>
                        <a-form-item label="星级">
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </a-form-item>
                        <a-form-item label="酒店简介">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.description }}</span>
                            <a-input
                                    placeholder="请填写酒店简介"
                                    v-decorator="['hotelDescription', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item>
                            <a-button type="primary" @click="modifyBegin" v-if="!modify">编辑酒店信息</a-button>
                        </a-form-item>
                        <a-form-item>
                            <a-button type="primary" @click="modifyFinish" v-if="modify">保存编辑</a-button>
                            <a-divider type="vertical"></a-divider>
                            <a-button  @click="modify=false" v-if="modify">取消</a-button>
                        </a-form-item>
                    </a-form>
                        <!--<div class="items" v-if="currentHotelInfo.name">
                            <span class="label">酒店名称:</span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.name }}</span>
                            <a-input
                                    placeholder="请填写酒店名字"
                                    v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名字' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </div>
                        <div class="items" v-if="currentHotelInfo.address">
                            <span class="label">地址:</span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.address }}</span>
                            <a-input
                                    placeholder="请填写酒店地址"
                                    v-decorator="['hotelAddress', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </div>
                        <div class="items" v-if="currentHotelInfo.rate">
                            <span class="label">评分:</span>
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </div>
                        <div class="items" v-if="currentHotelInfo.hotelStar">
                            <span class="label">星级:</span>
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </div>
                        <div class="items" v-if="currentHotelInfo.description">
                            <span class="label">酒店简介:</span>
                            <span class="edit"></span>
                            <span class="value" v-if="!modify">{{ currentHotelInfo.description }}</span>
                            <a-input
                                    placeholder="请填写酒店简介"
                                    v-decorator="['hotelDescription', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                    v-if="modify" >
                            </a-input>
                            &lt;!&ndash;{{currentHotelInfo}}&ndash;&gt;
                            &lt;!&ndash;<span>{{ currentHotelInfo.rooms }}</span>&ndash;&gt;
                        </div>
                        <a-button type="primary" @click="modifyBegin" v-if="!modify">编辑酒店信息</a-button>

                    </div>-->
                </div>
                <a-divider></a-divider>
                <a-tabs>
                    <a-tab-pane tab="房间信息">
                        {{currentHotelInfo}}
                        <RoomList :rooms="currentHotelInfo.rooms" ></RoomList>
                    </a-tab-pane>



                </a-tabs>
            </div>
        </a-layout-content>
    </a-layout>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import RoomList from './roomList'
    export default {
        name: 'hotelDetailEdit',
        components: {
            RoomList,
        },
        data() {
            return {
                modify:false,
                form: this.$form.createForm(this, {name: 'coordinated'}),
            }
        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
                'descEditVisible',
                'userInfo'
            ])
        },
        mounted() {
            this.set_currentHotelId(Number(this.$route.params.hotelId))
            this.getHotelById()
        },
        beforeRouteUpdate(to, from, next) {
            this.set_currentHotelId(Number(to.params.hotelId))
            this.getHotelById()
            next()
        },
        methods: {
            ...mapMutations([
                'set_currentHotelId',
                'set_descEditVisible',
                'set_currentHotelInfo'
            ]),
            ...mapActions([
                'getHotelById'
            ]),
            edit(){
                this.set_descEditVisible(true)
            },
            modifyBegin() {
                setTimeout(() => {
                    this.form.setFieldsValue({
                        'hotelName': this.currentHotelInfo.name,
                        'hotelAddress': this.currentHotelInfo.address,
                        'hotelDescription':this.currentHotelInfo.description
                    })
                }, 0)
                this.modify = true
            },
            modifyFinish() {
                this.form.validateFields((err, values) => {
                    if (!err) {
                        const data = {
                            id:this.currentHotelInfo.id,
                            name:this.form.getFieldValue('hotelName'),
                            address:this.form.getFieldValue('hotelAddress'),
                            bizRegion:this.currentHotelInfo.bizRegion,
                            hotelStar:this.currentHotelInfo.hotelStar,
                            rate:this.currentHotelInfo.rate,
                            description:this.form.getFieldValue('hotelDescription'),
                            phoneNumber:this.currentHotelInfo.phoneNumber,
                            managerId:this.currentHotelInfo.managerId,
                            rooms:this.currentHotelInfo.rooms
                        }
                        this.$store.dispatch('updateHotelInfo',data)
                        this.modify=false
                    }
                });
            },
        }
    }
</script>
<style scoped lang="less">
    .hotelDetailCard {
        padding: 50px 50px;
    }
    .hotel-info {
        display: flex;
        align-items: stretch;
        justify-content: flex-start;
        .info{
            padding: 10px 0;
            display: flex;
            flex-direction: column;
            margin-left: 20px;
            .items {
                display: flex;
                align-items: center;
                margin-bottom: 10px;
                .label{
                    margin-right: 10px;
                    font-size: 18px;
                }
                .value {
                    margin-right: 15px
                }
            }
        }
    }
</style>