package com.sanjeev.Expense_Tracker_v1.controller;

import com.sanjeev.Expense_Tracker_v1.dto.IncomeCategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.IncomeCategory;
import com.sanjeev.Expense_Tracker_v1.service.IncomeCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class IncomeCategoryController {

    @Autowired
    private IncomeCategoryService incomeCategoryService;

    // now the user logs in and want to add Income
    //first he have to know how many income sources he have and what is the total income

    @GetMapping("/income-categories")
    public List<IncomeCategoryDTO> getIncomeCategories() {
        return incomeCategoryService.getIncomeCategories();
    }
}
