<template>
    <a-layout>
        <a-layout-content>
            <div class="hotelDetailCard">
                <h1>
                    {{ currentHotelInfo.title}}
                </h1>
                <div class="hotel-info">
                    <a-card style="width: 240px" v-if="!modify">
                        <img
                                alt="example"
                                :src="currentHotelInfo.pictureUrl"
                                slot="cover"
                                referrerPolicy="no-referrer"
                                v-if="currentHotelInfo.pictureUrl!=null"
                        />
                        <img
                        alt="example"
                        src="@/assets/cover.jpeg"
                        slot="cover"
                        referrerPolicy="no-referrer"
                        v-else
                        />
                    </a-card>
                    <a-form :form="form" class="info">
                        <a-form-item label="酒店名称" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.name }}</span>
                            <a-input
                                    placeholder="请填写酒店名字"
                                    v-decorator="['hotelName', { rules: [{ required: true, message: '请输入酒店名字' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="地址" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.address }}</span>
                            <a-input
                                    placeholder="请填写酒店地址"
                                    v-decorator="['hotelAddress', { rules: [{ required: true, message: '请输入酒店地址' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="评分" class="items">
                            <span class="value">{{ currentHotelInfo.rate }}</span>
                        </a-form-item>
                        <a-form-item label="星级" class="items">
                            <a-rate style="font-size: 15px" :value="currentHotelInfo.rate" disabled allowHalf/>
                        </a-form-item>
                        <a-form-item label="酒店简介" class="items">
                            <span class="value" v-if="!modify">{{ currentHotelInfo.description }}</span>
                            <a-input
                                    placeholder="请填写酒店简介"
                                    v-decorator="['hotelDescription', { rules: [{ required: true, message: '请输入酒店简介' }] }]"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item label="酒店照片" class="items">
                            <a-input
                                    placeholder="请填写酒店照片url"
                                    v-model="value"
                                    v-if="modify" >
                            </a-input>
                        </a-form-item>
                        <a-form-item class="items">
                            <a-button-group>
                            <a-button type="primary" @click="modifyBegin" v-if="!modify">编辑酒店信息</a-button>
                            <a-button type="primary" @click="showCouponList( currentHotelInfo.id )" v-if="!modify">优惠策略</a-button>
                            </a-button-group>
                        </a-form-item>
                        <a-form-item class="items">
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
                    <a-tab-pane tab="房间信息" key="1">
                        <!--{{currentHotelInfo}}-->
                        <RoomList :rooms="currentHotelInfo.rooms" ></RoomList>
                    </a-tab-pane>
                </a-tabs>
            </div>
        </a-layout-content>
        <coupon></coupon>
    </a-layout>
</template>
<script>
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    import RoomList from './components/roomList'
    import Coupon from "./components/coupon"
    export default {
        name: 'hotelDetailEdit',
        components: {
            Coupon,
            RoomList,
        },
        data() {
            return {
                value:'',
                modify:false,
                form: this.$form.createForm(this, {name: 'coordinated'}),
            }
        },
        computed: {
            ...mapGetters([
                'currentHotelInfo',
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
                'set_currentHotelInfo',
                'set_activeHotelId',
                'set_couponVisible',
            ]),
            ...mapActions([
                'getHotelById',
                'getHotelCoupon',

            ]),
            showCouponList(id){
                console.log(id)
                this.set_activeHotelId(id)
                this.getHotelCoupon()
                this.set_couponVisible(true)
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
                this.value=this.currentHotelInfo.pictureUrl
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
                            pictureUrl:this.value,
                            rooms:this.currentHotelInfo.rooms
                        }
                        console.log(data.pictureUrl)
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
        padding: 50px ;
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