package com.example.hotel.blImpl.marketingStaff;

import com.example.hotel.bl.marketingStaff.MarketingStaffService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MarketingStaffServiceImpl implements MarketingStaffService {
    private final static String EMAIL_DONT_EXIST ="该用户邮箱不存在，请确认用户邮箱";

    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO addCredit(String userEmail, double amount) {
        User user = accountMapper.getAccountByName(userEmail);
        if(user==null){
            return ResponseVO.buildFailure(EMAIL_DONT_EXIST);
        }else{
            accountMapper.addCreditByEmail(userEmail,amount);
        }
        return ResponseVO.buildSuccess(true);
    }
}
