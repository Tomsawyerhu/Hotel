package com.example.hotel.bl.marketingStaff;

import com.example.hotel.vo.ResponseVO;

public interface MarketingStaffService {
    /**
     * 网站营销人员为用户充值信用值
     * @param userEmail
     * @param amount
     * @return
     */
    ResponseVO addCredit(String userEmail, double amount);
}
