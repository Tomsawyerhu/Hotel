import Vue from 'vue'
import App from './App.vue'
import router from './router'
import Antd from 'ant-design-vue'
import 'ant-design-vue/dist/antd.css'
import store from './store'
import Distpicker from 'v-distpicker'
import "./utils/validate"
import '@/permission'
import md5 from 'js-md5'

Vue.config.productionTip = false
const options = {
  namespace: 'pro__',
  name: 'ls',
  storage: 'local'
}
Vue.use(Antd)
Vue.component('v-distpicker', Distpicker)


Vue.directive('title', {
  inserted: function (el, binding) {
    document.title = el.dataset.title
  }
})

/*md5密码加密*/
Vue.prototype.$md5=md5;

new Vue({
  router,
  store,
  render: h => h(App)
}).$mount('#app')



