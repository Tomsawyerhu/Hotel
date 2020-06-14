package com.example.hotel.blImpl.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.bl.user.CreditService;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.po.CreditHistory;
import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class AccountServiceImpl implements AccountService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    private final static String ACCOUNT_DONT_EXIST = "账号不存在";
    private final static String EMAIL_DONT_EXIST ="该用户邮箱不存在，请确认用户邮箱";
    private final static String ANNUL_ABNORMAL_ORDER="撤销异常订单";
    private final static String LESS_THAN_SIX="距离预计入住时间不足6小时撤销订单";
    private final static String ADD_CREDIT_AS_WORKER="酒店工作人员增加信用值";
    private final static String SUB_CREDIT_AS_WORKER="酒店工作人员扣除信用值";
    @Autowired
    private AccountMapper accountMapper;
    @Autowired
    private CreditService creditService;

    @Override
    public ResponseVO registerAccount(UserVO userVO) {
        User user = new User();
        BeanUtils.copyProperties(userVO, user);
        try {
            accountMapper.createNewAccount(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess();
    }

    @Override
    public UserVO login(UserForm userForm) {
        User user = accountMapper.getAccountByName(userForm.getEmail());
        System.out.println(userForm.getPassword());
        if (user==null || !user.getPassword().equals(userForm.getPassword())) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public UserVO getUserInfo(int id) {
        User user = accountMapper.getAccountById(id);
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public UserVO getUserInfoByEmail(String email) {
        User user = accountMapper.getAccountByName(email);
        if (user == null) {
            return null;
        }
        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(user,userVO);
        return userVO;
    }

    @Override
    public ResponseVO updateUserInfo(int id, String username, String phonenumber) {
        int index=1;
        try {
            index=accountMapper.updateAccount(id, username, phonenumber);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        if(index<=0){return ResponseVO.buildFailure(UPDATE_ERROR);}
        return ResponseVO.buildSuccess(true);
    }

    public ResponseVO modifyPassword(int id, String password) {
        int index=1;
        try {
            index=accountMapper.modifyPassword(id, password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        if(index<=0){return ResponseVO.buildFailure(UPDATE_ERROR);}
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO addCreditByAnnulAbnormalOrder(int userid, double amount) {

        int index=1;
        try {
            index=accountMapper.addCreditById(userid, amount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_DONT_EXIST);
        }
        if(index<=0){return ResponseVO.buildFailure(ACCOUNT_DONT_EXIST);}
        creditService.insertCreditHistory(userid,1,amount,ANNUL_ABNORMAL_ORDER);
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public void subCreditByAnnulOrder(int id, Order order) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        //System.out.println(order.getCreateDate());
        Date checkInDate = null;
        try {
            checkInDate = df.parse(order.getCheckInDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long checkIn = checkInDate.getTime();
        long now = System.currentTimeMillis();
        //距离订单执行不足6h才会扣除信用值
        if (checkIn - now >= 0 && checkIn - now < 6 * 60 * 60 * 1000) {
            double amount = order.getPrice() / 2;
            accountMapper.subCreditById(id, amount);
            creditService.insertCreditHistory(id,-1,amount,LESS_THAN_SIX);
        }
    }


    @Override
    public ResponseVO addCreditAsWorker(int id, double amount) {
        User user = accountMapper.getAccountById(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_DONT_EXIST);
        }else{
            accountMapper.addCreditById(id,amount);
            creditService.insertCreditHistory(id,1,amount,ADD_CREDIT_AS_WORKER);
        }
        return ResponseVO.buildSuccess(true);

    }

    @Override
    public ResponseVO subCreditAsWorker(int id, double amount) {
        User user = accountMapper.getAccountById(id);
        if(user==null){
            return ResponseVO.buildFailure(ACCOUNT_DONT_EXIST);
        }else{
            accountMapper.subCreditById(id,amount);
            creditService.insertCreditHistory(id,-1,amount,SUB_CREDIT_AS_WORKER);
        }
        return ResponseVO.buildSuccess(true);
    }

    @Override
    public ResponseVO addMember(String password, String memberType, String birthday,String companyName){
        int index=1;
        try {
            index=accountMapper.addMember(password, memberType, birthday,companyName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(UPDATE_ERROR);
        }
        if(index<=0){return ResponseVO.buildFailure(UPDATE_ERROR);}
        return ResponseVO.buildSuccess(true);
    }
}

