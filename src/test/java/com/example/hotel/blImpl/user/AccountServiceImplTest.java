package com.example.hotel.blImpl.user;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String UPDATE_ERROR = "修改失败";
    private final static String ACCOUNT_DONT_EXIST = "账号不存在";
    private final static String EMAIL_DONT_EXIST ="该用户邮箱不存在，请确认用户邮箱";
    @Autowired
    private AccountServiceImpl accountService;

    @Test
    @Transactional
    public void registerAccountTest() {
        UserVO userVO = new UserVO();
        userVO.setEmail(""); //这里设置一个数据库里已有的邮箱即可
        ResponseVO responseVO = accountService.registerAccount(userVO);
        assertEquals(ACCOUNT_EXIST, responseVO.getMessage());
    }

    @Test
    @Transactional
    public void loginTest(){
        UserForm userForm1 = new UserForm(); //登录失败
        UserForm userForm2 = new UserForm(); //登录成功
        userForm1.setEmail("000@qq.com");
        userForm2.setEmail(); //这里设置一个数据库里已有的邮箱
        UserVO user1 = accountService.login(userForm1);
        UserVO user2 = accountService.login(userForm2);
        assertEquals(null,user1);
        assertEquals(,user2.getId()); //这里填用户的id
    }

    @Test
    @Transactional
    public void getUserInfoTest(){
        UserVO user1 = accountService.getUserInfo(1000); //这里填一个数据库里不存在的用户id值
        UserVO user2 = accountService.getUserInfo(1);
        assertEquals(null,user1);
        assertEquals(2,(int)user2.getId());
    }

    @Test
    @Transactional
    public void updateUserInfoTest(){
        ResponseVO responseVO = accountService.updateUserInfo(1000,"67","123456");
        assertEquals(UPDATE_ERROR,responseVO.getMessage());
    }

    @Test
    @Transactional
    public void modifyPasswordTest(){
        ResponseVO responseVO = accountService.modifyPassword(1000,"123456");
        assertEquals(UPDATE_ERROR,responseVO.getMessage());
    }

    @Test
    @Transactional
    public void addCreditByAnnulAbnormalOrderTest(){
        ResponseVO responseVO = accountService.addCreditByAnnulAbnormalOrder(1000,0.0);
        assertEquals(ACCOUNT_DONT_EXIST,responseVO.getMessage());
    }

}
