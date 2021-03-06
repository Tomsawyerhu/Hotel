package com.example.hotel.bl.admin;

import com.example.hotel.po.User;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserFormLink;
import com.example.hotel.vo.UserVO;

import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
public interface AdminService {

    /**
     * 添加酒店管理人员账号
     * @param userForm
     * @return
     */
    ResponseVO addManager(UserFormLink userForm);

    /**
     * 添加网站营销人员账号
     * @param userForm
     * @return
     */
    ResponseVO addStaff(UserForm userForm);

    /**
     * 获得所有酒店管理人员信息
     * @return
     */
    List<UserVO> getAllManagers();

    /**
     * 获得所有用户的信息
     * @return
     */
    List<UserVO> getAllClients();
    /**
     * 获得所有网站营销人员的信息
     * @return
     */
    List<UserVO> getAllStaff();

    /**
     *
     * @param userId
     * @return
     */
    ResponseVO deleteUser(Integer userId);
}
