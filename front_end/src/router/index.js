import Vue from 'vue'
import VueRouter from 'vue-router'
import Login from '../views/login.vue'
//配置路由相关信息
Vue.use(VueRouter)//安装插件
const routes = [//映射关系
  {
    path: '/login',
    name: 'login',
    component: Login
  },
  {
    path: '/',
    redirect: '/NJUSE'//重定向
  },
  {
    path: '/NJUSE',
    name: 'layout',
    redirect: '/hotel/hotelList',
    component: ()=> import('@/views/layout'),//懒加载
    children: [//嵌套路由
      {
        path: '/hotel/hotelList',
        name: 'hotelList',
        component: () => import('@/views/hotel/hotelList')
      },
      {
        path: '/hotel/hotelDetail/:hotelId',
        name: 'hotelDetail',
        component: () => import('@/views/hotel/hotelDetail')
      },
      {
        path: '/hotel/hotelDetailEdit/:hotelId',
        name: 'hotelDetailEdit',
        component: () => import('@/views/hotel/hotelDetailEdit')
      },
      {
        path: '/user/info/:userId',
        name: 'userInfo',
        component: () => import('@/views/user/info')
      },
      {
        path: '/hotelManager/manageHotel',
        name: 'manageHotel',
        component: () => import('@/views/hotelManager/manageHotel')
      },
      {
        path: '/admin/manageUser',
        name: 'manageUser',
        component: () => import('@/views/admin/manageUser')
      },
      {
        path: '/marketingStaff/manageWeb',
        name: 'manageWeb',
        component: () => import('@/views/marketingStaff/manageWeb')
      }
    ]
  },
]
const createRouter = () => new VueRouter({
  mode: 'history', // require service support(history model,hash model)
  scrollBehavior: () => ({ y: 0 }),
  routes
})
const router = createRouter()

export function resetRouter() {
  const newRouter = createRouter()
  router.matcher = newRouter.matcher // reset router
}

export default router//导出router对象
