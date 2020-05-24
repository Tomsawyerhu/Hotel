import Vue from 'vue'
import Vuex from 'vuex'
import user from './modules/user'
import hotel from './modules/hotel'
import hotelManager from './modules/hotelManager'
import admin from './modules/admin'
import order from './modules/order'
import getters from './getters'
import marketingStaff from "./modules/marketingStaff";
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    hotel,
    user,
    hotelManager,
    admin,
    order,
    marketingStaff
  },
  state: {
  },
  mutations: {
  },
  actions: {
  },
  getters
})
