import { axios } from '@/utils/request'  //这里@是通过配置文件简化路径，好像就是主目录的意思
const api = {
    adminPre: '/api/admin'
}
export function getManagerListAPI(){
    return axios({
        url: `${api.adminPre}/getAllManagers`,   //``是ES6语法，可以查一查，${}在``中使用变量值，就是转义吧
        method: 'POST'
    })
}
export function addManagerAPI(data) {
    return axios({
        url: `${api.adminPre}/addManager`,
        method: 'POST',
        data
    })
}