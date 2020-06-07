package com.example.hotel.controller.marketingStaff;

import com.example.hotel.bl.marketingStaff.MarketingStaffService;
import com.example.hotel.vo.ResponseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/marketingStaff")
public class MarketingStaffController {

    @Autowired
    private MarketingStaffService marketingStaffService;

    @PostMapping("/{userEmail}/{amount}/addCredit")
    public ResponseVO addCreditAsSale(@PathVariable String userEmail, @PathVariable double amount) {
        return marketingStaffService.addCredit(userEmail,amount);
    }
}
