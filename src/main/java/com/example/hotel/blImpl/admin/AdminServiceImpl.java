package com.example.hotel.blImpl.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.bl.user.AccountService;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.data.admin.AdminMapper;
import com.example.hotel.data.user.AccountMapper;
import com.example.hotel.enums.UserType;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@Service
public class AdminServiceImpl implements AdminService {
    private final static String ACCOUNT_EXIST = "账号已存在";
    private final static String EXIT_MANAGER= "该酒店已有管理人员";
    @Autowired
    AdminMapper adminMapper;
    @Autowired
    AccountMapper accountMapper;

    @Autowired
    private HotelService hotelService;

    @Override
    public ResponseVO addManager(UserFormLink userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(UserType.HotelManager);
        user.setCredit(100);
        user.setManage_hotelId(userForm.getHotelId());
        /*System.out.println(user.getPassword());
        System.out.println(user.getManage_hotelId());
        System.out.println(user.getUserType());*/
        HotelVO hotelVO=hotelService.retrieveHotelDetails(Integer.parseInt(userForm.getHotelId()));
        /*System.out.println(hotelVO.getManagerId());*/
        if(hotelVO.getManagerId()==null){
            try {
                adminMapper.addManager(user);
                user=accountMapper.getAccountByName(userForm.getEmail());
                hotelVO.setManagerId(user.getId());
                hotelService.updateHotelManager(hotelVO);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                return ResponseVO.buildFailure(ACCOUNT_EXIST);
            }
            return ResponseVO.buildSuccess(true);
        }
        else return ResponseVO.buildFailure(EXIT_MANAGER);
    }

    @Override
    public ResponseVO addStaff(UserForm userForm) {
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setPassword(userForm.getPassword());
        user.setUserType(UserType.MarketingStaff);
        try {
            adminMapper.addStaff(user);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseVO.buildFailure(ACCOUNT_EXIST);
        }
        return ResponseVO.buildSuccess(true);
    }
    @Override
    public List<UserVO> getAllManagers() {
        List<UserVO> managerVOs = new ArrayList<>();
        List<User> managers = adminMapper.getAllManagers();
        for(User manager: managers){
            UserVO managerVO = new UserVO();
            BeanUtils.copyProperties(manager,managerVO);
            managerVOs.add(managerVO);
        }
        return managerVOs;
    }

    @Override
    public List<UserVO> getAllClients() {
        List<UserVO> UsersVOs = new ArrayList<>();
        List<User> clients = adminMapper.getAllClients();
        for(User client: clients){
            UserVO clientVO = new UserVO();
            BeanUtils.copyProperties(client,clientVO);
            UsersVOs.add(clientVO);
        }
        return UsersVOs;
    }

    @Override
    public List<UserVO> getAllStaff() {
        List<UserVO> UsersVOs = new ArrayList<>();
        List<User> staffs = adminMapper.getAllStaff();
        for(User staff: staffs){
            UserVO staffVO = new UserVO();
            BeanUtils.copyProperties(staff,staffVO);
            UsersVOs.add(staffVO);
        }
        return UsersVOs;
    }

    @Override
    public ResponseVO deleteUser(Integer userId){
        List<UserVO> m=getAllClients();
        m.addAll(getAllStaff());
        m.addAll(getAllManagers());
        boolean exist=false;
        UserVO uservo=null;
        for(UserVO userVO:m){
            if(userVO.getId().equals(userId)){
                exist=true;
                uservo=userVO;
                break;
            }
        }
        if(exist){
            if(uservo.getManage_hotelId()!=null){hotelService.deLink(Integer.parseInt(uservo.getManage_hotelId()));}
            adminMapper.deleteUser(userId);
            return ResponseVO.buildSuccess("删除成功");
        }
        else return ResponseVO.buildFailure("删除失败");

    }


}
