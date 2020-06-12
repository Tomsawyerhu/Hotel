package com.example.hotel.controller.user;

import com.example.hotel.bl.user.AccountService;
import com.example.hotel.bl.user.CreditService;
import com.example.hotel.po.User;
import com.example.hotel.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/user")
public class AccountController {
    private final static String ACCOUNT_INFO_ERROR = "用户名或密码错误";
    @Autowired
    private AccountService accountService;
    @Autowired
    private CreditService creditService;

    @PostMapping("/login")
    public ResponseVO login(@RequestBody UserForm userForm) {
        UserVO user = accountService.login(userForm);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);

    }

    @PostMapping("/register")
    public ResponseVO registerAccount(@RequestBody UserVO userVO) {
        System.out.println(userVO.getUserType());
        return accountService.registerAccount(userVO);
    }


    @GetMapping("/{id}/getUserInfo")
    public ResponseVO getUserInfo(@PathVariable int id) {
        UserVO user = accountService.getUserInfo(id);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @GetMapping("/{email}/getUserInfoByEmail")
    public ResponseVO getUserInfoByEmail(@PathVariable String email) {
        UserVO user = accountService.getUserInfoByEmail(email);
        if (user == null) {
            return ResponseVO.buildFailure(ACCOUNT_INFO_ERROR);
        }
        return ResponseVO.buildSuccess(user);
    }

    @PostMapping("/{id}/userInfo/update")
    public ResponseVO updateInfo(@RequestBody UserInfoVO userInfoVO, @PathVariable int id) {
        return accountService.updateUserInfo(id, userInfoVO.getUserName(), userInfoVO.getPhoneNumber());

    }

    @PostMapping("/{id}/userInfo/modifyPassword")
    public ResponseVO modifyPassword(@RequestBody UserInfoVO userInfoVO, @PathVariable int id) {
        return accountService.modifyPassword(id, userInfoVO.getPassword());
    }


    @GetMapping("/addCreditAsSale/{orderId}/{amount}")
    public ResponseVO addCreditAsWorker(@PathVariable int orderId, @PathVariable double amount) {
        return accountService.addCreditAsWorker(orderId,amount);
    }

    @GetMapping("/{userId}/getCreditHistories")
    public ResponseVO getCreditHistories(@PathVariable int userId){
        return creditService.getCreditHistoriesByUserId(userId);
    }

    @PostMapping("/addCreditHistory")
    public ResponseVO addCreditHistory(@RequestBody CreditHistoryVO creditHistoryVO){

        return creditService.insertCreditHistory(creditHistoryVO.getUserId(),creditHistoryVO.getType(),creditHistoryVO.getValue(),creditHistoryVO.getMessage());
    }

    @PostMapping("/{id}/{amount}/{type}/changeCredit")
    public ResponseVO changeCredit(@PathVariable int id,@PathVariable double amount,@PathVariable int type){

        return creditService.changeCredit(id,amount,type);
    }

}
