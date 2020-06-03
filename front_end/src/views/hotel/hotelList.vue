<template>
    <div class="hotelList">
        <a-layout>
            <a-layout-content style="min-width: 800px">
                <a-spin :spinning="hotelListLoading">
                    <div class="card-wrapper">
                        <HotelCard :hotel="item" v-for="item in hotelList" :key="item.index" @click.native="jumpToDetails(item.id)"></HotelCard>
                        <div v-for="item in emptyBox" :key="item.name" class="emptyBox ant-col-xs-7 ant-col-lg-5 ant-col-xxl-3">
                        </div>
                        <a-pagination showQuickJumper :total="hotelList.totalElements" :defaultCurrent="1" @change="pageChange"></a-pagination>
                        <a-icon
                                class="trigger"
                                type="search"
                                @click="showSearch"
                        />
                    </div>
                </a-spin>
            </a-layout-content>
        </a-layout>
        <a-drawer
                title="条件搜索"
                :width="720"
                :visible="showSearchModal"
                :body-style="{ paddingBottom: '80px' }"
                @close="dismissSearch"
        >
            <a-form :form="form" :label-col="{ span: 5 }" :wrapper-col="{ span: 12 }" >
                <a-checkbox :default-checked="true" @change="change1" />
                <a-form-item label="酒店名称" >
                    <a-input
                            v-decorator="['hotelName', { rules: [{ message: '请填写酒店名称' }] }]"></a-input>
                </a-form-item>
                <a-checkbox :default-checked="true"  @change="change2"/>
                <a-form-item label="酒店地址">
                    <a-cascader
                            :options="addrOptions"
                            :load-data="loadData"
                            placeholder="请选择地址"
                            change-on-select
                            v-decorator="['hotelAddr']"
                    />
                </a-form-item>
                <a-checkbox :default-checked="true"  @change="change3"/>
                <a-form-item label="酒店星级">
                    <a-rate v-model="rate" :disabled="starProhibited">
                    </a-rate>
                </a-form-item>
            </a-form>
            <div
                    :style="{
          position: 'absolute',
          right: 0,
          bottom: 0,
          width: '100%',
          borderTop: '1px solid #e9e9e9',
          padding: '10px 16px',
          background: '#fff',
          textAlign: 'right',
          zIndex: 1,
        }"
            >
                <a-button :style="{ marginRight: '8px' }" @click="search">
                    搜索
                </a-button>
                <a-button type="primary" @click="cancel">
                    取消
                </a-button>
            </div>
        </a-drawer>
    </div>
</template>
<script>
    import HotelCard from './hotelCard'
    import { mapGetters, mapActions, mapMutations } from 'vuex'
    const Options= [
        {
            value: '江苏',
            label: '江苏',
            isLeaf: false,
        },
        {
            value: '浙江',
            label: '浙江',
            isLeaf: false,
        },
    ]
    export default {
        name: 'home',
        components: {
            HotelCard
        },
        data(){
            return{
                emptyBox: [{ name: 'box1' }, { name: 'box2'}, {name: 'box3'}],
                showSearchModal:false,
                rate:3,
                starProhibited:false,
                addrProhibited:false,
                nameProhibited:false,
                addrOptions:Options
            }
        },
        async mounted() {
            await this.getHotelList()
        },
        computed: {
            ...mapGetters([
                'hotelList',
                'hotelListLoading'
            ])
        },
        methods: {
            ...mapMutations([
                'set_hotelListParams',
                'set_hotelListLoading'
            ]),
            ...mapActions([
                'getHotelList',
            ]),

            pageChange(page, pageSize) {
                const data = {
                    pageNo: page - 1
                }
                this.set_hotelListParams(data)
                this.set_hotelListLoading(true)
                this.getHotelList()
            },
            jumpToDetails(id){
                this.$router.push({ name: 'hotelDetail', params: { hotelId: id }})
            },
            showSearch(){
                this.showSearchModal=true
            },
            dismissSearch(){
                this.showSearchModal=false
            },
            search(){
                let data={}
                if(!this.nameProhibited){
                    data.hotelName=this.form.getFieldValue("hotelName")
                }
                if(!this.addrProhibited){
                    //此处返回Array对象
                    data.hotelAddr=this.form.getFieldValue("hotelAddr")
                }
                if(!this.starProhibited){
                    data.hotelStar=this.rate
                }
                console.log(data)

            },
            cancel(){
                this.showSearchModal=false
            },
            change1(e){
                this.nameProhibited=!e.target.checked
            },
            change2(e){
                this.addrProhibited=!e.target.checked
            },
            change3(e){
                this.starProhibited=!e.target.checked
            },
            loadData(selectedOptions) {
                //console.log(selectedOptions)
                const targetOption = selectedOptions[selectedOptions.length - 1];
                targetOption.loading = true;

                // load options lazily
                setTimeout(() => {
                    targetOption.loading = false;
                    targetOption.children = [
                        //此处要改为具体的地址
                        {
                            label: 'xx',
                            value: 'xx',
                        },
                        {
                            label: 'yy',
                            value: 'yy',
                        },
                    ];
                    this.addrOptions=[...this.addrOptions]
                }, 1000);
            },
        },
        beforeCreate() {
            this.form = this.$form.createForm(this, {name: 'searchModal'})
        }
    }
</script>
<style scoped lang="less">
    .hotelList {
        text-align: center;
        padding: 50px 0;
        .emptyBox {
            height: 0;
            margin: 10px 10px
        }
        .card-wrapper{
            display: flex;
            justify-content: space-around;
            flex-wrap: wrap;
            flex-grow: 3;
            min-height: 800px
        }
        .card-wrapper .card-item {
            margin: 30px;
            position: relative;
            height: 188px;
        }
    }


    .trigger {
        font-size: 18px;
        line-height: 40px;
        cursor: pointer;
        transition: color 0.3s;
    }

    .trigger:hover {
        color: #1890ff;
    }



</style>