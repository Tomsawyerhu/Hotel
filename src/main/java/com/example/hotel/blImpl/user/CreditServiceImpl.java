package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.CreditService;
import com.example.hotel.data.user.CreditHistoryMapper;
import com.example.hotel.po.CreditHistory;
import com.example.hotel.vo.CreditHistoryVO;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CreditServiceImpl implements CreditService {
    @Autowired
    CreditHistoryMapper creditHistoryMapper;

    @Override
    public ResponseVO getCreditHistoriesByUserId(int userId) {
        List<CreditHistory> list=creditHistoryMapper.getCreditHistoriesByUserId(userId);
        List<CreditHistoryVO> voList=new ArrayList<>();
        for(CreditHistory creditHistory:list){
            CreditHistoryVO vo=new CreditHistoryVO();
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
        creditHistory.setTime(new Date());
        creditHistoryMapper.insertCreditHistory(creditHistory);
        return ResponseVO.buildSuccess();
    }
}
