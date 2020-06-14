package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.CreditService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.data.user.CreditHistoryMapper;
import com.example.hotel.po.CreditHistory;
import com.example.hotel.po.User;
import com.example.hotel.vo.CreditHistoryVO;
import com.example.hotel.vo.CreditHistoryOutVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditHistoryMapper creditHistoryMapper;
    @Autowired
    AccountMapper accountMapper;

    @Override
    public ResponseVO getCreditHistoriesByUserId(int userId) {
        List<CreditHistory> list=creditHistoryMapper.getCreditHistoriesByUserId(userId);
        List<CreditHistoryOutVO> voList=new ArrayList<>();
        for(CreditHistory creditHistory:list){
            CreditHistoryOutVO vo=new CreditHistoryOutVO();
            BeanUtils.copyProperties(creditHistory,vo);
            voList.add(vo);
        }
        return ResponseVO.buildSuccess(voList);
    }

    @Override
    public ResponseVO insertCreditHistory(int userId, int type, double value, String message) {
        CreditHistory creditHistory=new CreditHistory();
        creditHistory.setUserId(userId);
        creditHistory.setType(type);
        creditHistory.setValue(value);
        creditHistory.setMessage(message);
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateString = formatter.format(currentTime);
        //System.out.println(dateString);
        creditHistory.setTime(dateString);

        creditHistoryMapper.insertCreditHistory(creditHistory);
        return ResponseVO.buildSuccess();
    }

    @Override
    public ResponseVO changeCredit(int userId,double amount,int type){
        User user = accountMapper.getAccountById(userId);
        if(user==null){
            return ResponseVO.buildFailure("该用户不存在");
        }else{
            if(type>0){
                accountMapper.addCreditById(userId,amount);
            }
            else {
                accountMapper.subCreditById(userId,amount);
            }
        }
        return ResponseVO.buildSuccess(true);

    }
}
