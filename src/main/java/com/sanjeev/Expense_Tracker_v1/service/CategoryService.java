package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.dto.CategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.Category;
import com.sanjeev.Expense_Tracker_v1.repo.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepo categoryRepo;

    public List<CategoryDTO> getAllCategories() {
        return categoryRepo.categories();
    }
}
