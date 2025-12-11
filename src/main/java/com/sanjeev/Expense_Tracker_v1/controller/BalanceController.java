package com.sanjeev.Expense_Tracker_v1.controller;

import com.sanjeev.Expense_Tracker_v1.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/expense-tracker")
public class BalanceController {

    @Autowired
    public BalanceService balanceService;


    @GetMapping("/balance")
    public BigDecimal getBalance() {
        return balanceService.calculateBalance();
    }

}
