import { axios } from '@/utils/request'
const api = {
    marketingStaffPre: '/api/marketingStaff'
}

export function addCreditAPI(data) {
    return axios({
        url: `${api.marketingStaffPre}/${data.userEmail}/${data.amount}/addCredit`,
        method: 'POST',
        data
    })
}
