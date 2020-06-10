package com.example.hotel.data.user;

import com.example.hotel.po.CreditHistory;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CreditHistoryMapper {
    List<CreditHistory>  getCreditHistoriesByUserId(int id);
    int insertCreditHistory(CreditHistory history);
}
