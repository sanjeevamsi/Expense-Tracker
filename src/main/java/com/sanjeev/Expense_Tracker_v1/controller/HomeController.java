package com.sanjeev.Expense_Tracker_v1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-tracker")
public class HomeController {


    @GetMapping("/home")
    public String home() {
        return "Welcome to Expense Tracker Application!";
    }
}
