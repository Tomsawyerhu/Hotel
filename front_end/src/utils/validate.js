import VeeValidate,{Validator} from 'vee-validate';

import Vue from 'vue'
import zh_CN from 'vee-validate/dist/locale/zh_CN';

Vue.use(VeeValidate, {
    dictionary: {
        zh_CN: zh_CN
    }
});
Validator.localize('zh_CN');


//手机号
Validator.extend('phone',{
    getMessage:
        field=>'请输入正确的手机号码',
    validate:value=>{
        return /^1[2-9]\d{9}$/.test(value)
    }

})

Validator.extend('name',{
    getMessage:
        field=>'请输入真实姓名',
    validate:value=>{
        return /^[\u4e00-\u9fa5A-Za-z]*$/.test(value)
    }
})

Validator.extend('checkInDate',{
    getMessage:
        field=>'选择时间不能早于当前时间',
    validate:
        value=>{
            let now=Date.now()
            let regEx = new RegExp("\\-","gi");
            let checkIn=value[0].format("YYYY-MM-DD").replace(regEx,'/')
            let checkInDate=Number(new Date(checkIn))
            //console.log(now)
            //console.log(checkInDate)
            return checkInDate-now>=0
        }
})

Validator.extend('checkInDate2',{
    getMessage:
        field=>'只接受7天之内的预定',
    validate:
        value=>{
            let now=Date.now()
            let regEx = new RegExp("\\-","gi");
            let checkIn=value[0].format("YYYY-MM-DD").replace(regEx,'/')
            let checkInDate=Number(new Date(checkIn))
            //console.log(now)
            //console.log(checkInDate)
            return checkInDate-now<=7*24*60*60*1000//暂定为7天
        }
})

