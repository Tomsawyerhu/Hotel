package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    @Autowired
    private AccountMapper accountMapper;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO,user);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public User login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        if (null == user || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        return user;
    }

    @Override
    public User getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        return user;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String password, String username, String phonenumber) {
        try {
            accountMapper.updateAccount(id, password, username, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public void subCreditByAnnulOrder(int id, Order order) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-mm-dd");
        LocalDateTime orderCheckInDate = LocalDateTime.parse(order.getCheckInDate());
        LocalDateTime now = LocalDateTime.now();
        Duration duration = Duration.between(orderCheckInDate,now);
        long hours = duration.toHours();
        //距离订单执行不足6h才会扣除信用值
        if(hours>0){
            if(hours<6){
                double amount = order.getPrice()/2;
                accountMapper.subCreditByAnnulOrder(id,amount);
            }
        }
    }
}
