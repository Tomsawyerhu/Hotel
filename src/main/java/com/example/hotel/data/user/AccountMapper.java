package com.example.hotel.data.user;

import com.example.hotel.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;


@Mapper
@Repository
public interface AccountMapper {

    /**
     * 创建一个新的账号
     *
     * @return
     */
     int createNewAccount(User user);

    /**
     * 根据用户名查找账号
     * @param email
     * @return
     */
     User getAccountByName(@Param("email") String email);

     User getAccountById(@Param("id") int id);

    /**
     * 更新用户信息
     * @param id
     * @param username
     * @param phonenumber
     * @return
     */
     int updateAccount(@Param("id") int id, @Param("userName") String username, @Param("phoneNumber") String phonenumber);

    /**
     * 修改用户密码
     * @param id
     * @param password
     * @return
     */
     int modifyPassword(@Param("id") int id,@Param("password") String password);

     int subCreditById(@Param("id") int id,@Param("amount") double amount);

     int addCreditById(@Param("userid") int userid,@Param("amount") double amount);

     int addCreditByEmail(@Param("email") String email,@Param("amount") double amount);
}
