package com.example.hotel.blImpl.admin;


import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdminServiceImplTest {
    private final static String ACCOUNT_EXIST = "账号已存在";
    @Autowired
    private AdminService adminService;

    @Test
    @Transactional
    public void addManagerTest() {
        //新增管理员,这个没用到数据库里的
        UserForm userForm = new UserForm();
        userForm.setEmail("111111@qq.com");
        userForm.setPassword("123456");
        ResponseVO res = adminService.addManager(userForm);
        assertEquals(true, (boolean) res.getContent());
        res = adminService.addManager(userForm);
        assertEquals(ACCOUNT_EXIST, res.getMessage());
    }
}
