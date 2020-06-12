package com.example.hotel.bl.user;

import com.example.hotel.vo.ResponseVO;

public interface CreditService {
    ResponseVO getCreditHistoriesByUserId(int userId);
    ResponseVO insertCreditHistory(int userId,int type,double value,String message);
    ResponseVO changeCredit(int userId,double amount,int type);
}
