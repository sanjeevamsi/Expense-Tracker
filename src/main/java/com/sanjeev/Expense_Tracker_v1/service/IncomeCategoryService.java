package com.sanjeev.Expense_Tracker_v1.service;


import com.sanjeev.Expense_Tracker_v1.dto.IncomeCategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.IncomeCategory;
import com.sanjeev.Expense_Tracker_v1.repo.IncomeCategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IncomeCategoryService {

    @Autowired
    private IncomeCategoryRepo incomeCategoryRepo;

    public List<IncomeCategoryDTO> getIncomeCategories() {
        return incomeCategoryRepo.findAllIncomeCategories();
    }
}
