package com.example.hotel.bl.user;

import com.example.hotel.po.Order;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;

/**
 * @author huwen
 * @date 2019/3/23
 */
public interface AccountService {

    /**
     * 注册账号
     *
     * @return
     */
    ResponseVO registerAccount(UserVO userVO);

    /**
     * 用户登录，登录成功会将用户信息保存再session中
     *
     * @return
     */
    UserVO login(UserForm userForm);

    /**
     * 获取用户个人信息
     *
     * @param id
     * @return
     */
    UserVO getUserInfo(int id);
    UserVO getUserInfoByEmail(String Email);
    /**
     * 更新用户个人信息
     *
     * @param id
     * @param username
     * @param phonenumber
     * @return
     */
    ResponseVO updateUserInfo(int id, String username, String phonenumber);

    ResponseVO modifyPassword(int id, String password);

    /**
     * 网站营销人员撤销异常订单，恢复用户信用值
     *
     * @param userid
     * @param amount
     * @return
     */
    ResponseVO addCreditByAnnulAbnormalOrder(int userid, double amount);


    /**
     * 撤销订单与订单执行最晚时间不超过6h需要扣除订单价值一半的信用值
     *
     * @param id
     * @param order
     */
    void subCreditByAnnulOrder(int id, Order order);



    /**
     * 酒店工作人员人员为用户增加信用值
     *
     * @param id
     * @param amount
     * @return
     */
    ResponseVO addCreditAsWorker(int id, double amount);

    /**
     * 酒店工作人员人员为用户减少信用值
     *
     * @param id
     * @param amount
     * @return
     */
    ResponseVO subCreditAsWorker(int id, double amount);

    ResponseVO addMember(String password, String memberType, String birthday,String companyName);

}

