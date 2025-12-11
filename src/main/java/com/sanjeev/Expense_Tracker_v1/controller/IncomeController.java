package com.sanjeev.Expense_Tracker_v1.controller;


import com.sanjeev.Expense_Tracker_v1.dto.IncomeDTO;
import com.sanjeev.Expense_Tracker_v1.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class IncomeController {
    @Autowired
    private IncomeService incomeService;

    @PostMapping("/income")
    public String addIncome(@RequestBody IncomeDTO incomeDTO) {
        incomeService.addIncome(incomeDTO);
        return "Income added successfully";
    }

    @GetMapping("/income")
    public List<IncomeDTO> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PutMapping("/income/{id}")
    public String updateIncome(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO) {
        incomeService.updateIncome(id, incomeDTO);
        return "Income updated successfully";
    }

    @DeleteMapping("/income/{id}")
    public String deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
        return "Income deleted successfully";
    }

    //we need to get the income of that month

}
