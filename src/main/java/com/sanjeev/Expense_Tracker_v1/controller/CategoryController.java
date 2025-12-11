package com.sanjeev.Expense_Tracker_v1.controller;

import com.sanjeev.Expense_Tracker_v1.dto.CategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.Category;
import com.sanjeev.Expense_Tracker_v1.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/categories")
    public List<CategoryDTO> getAllCategories() {
        return categoryService.getAllCategories();
    }


}
