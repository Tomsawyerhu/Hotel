package com.example.hotel.controller.admin;

import com.example.hotel.bl.admin.AdminService;
import com.example.hotel.blImpl.admin.AdminServiceImpl;
import com.example.hotel.bl.hotel.HotelService;
import com.example.hotel.vo.ResponseVO;
import com.example.hotel.vo.UserForm;
import com.example.hotel.vo.UserFormLink;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: chenyizong
 * @Date: 2020-03-04
 */
@RestController()
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    AdminService adminService;

    @Autowired
    HotelService hotelService;


    @PostMapping("/addManager")
    public ResponseVO addManager(@RequestBody UserFormLink userForm) {

        return adminService.addManager(userForm);
    }

    @PostMapping("/addStaff")
    public ResponseVO addStaff(@RequestBody UserForm userForm) {

        return adminService.addStaff(userForm);
    }

    @PostMapping("/getAllManagers")
    public ResponseVO getAllManagers() {
        return ResponseVO.buildSuccess(adminService.getAllManagers());
    }

    @PostMapping("/getAllClients")
    public ResponseVO getAllClients() {
        return ResponseVO.buildSuccess(adminService.getAllClients());
    }

    @PostMapping("/getAllStaff")
    public ResponseVO getAllStaff() {
        return ResponseVO.buildSuccess(adminService.getAllStaff());
    }

    @PostMapping("/deleteAccount/{userId}")
    public ResponseVO deleteAccount(@PathVariable Integer userId) { return adminService.deleteUser(userId);
    }

    @PostMapping("/deleteHotel/{hotelId}")
    public ResponseVO deleteHotel(@PathVariable Integer hotelId) { return ResponseVO.buildSuccess(hotelService.deleteHotel(hotelId));
    }
}
