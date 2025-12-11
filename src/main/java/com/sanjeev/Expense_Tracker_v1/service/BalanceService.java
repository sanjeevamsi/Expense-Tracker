package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.dto.IncomeDTO;
import com.sanjeev.Expense_Tracker_v1.model.Expense;
import com.sanjeev.Expense_Tracker_v1.model.Income;
import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.repo.ExpenseRepo;
import com.sanjeev.Expense_Tracker_v1.repo.IncomeRepo;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class BalanceService {

    @Autowired
    private IncomeService incomeService;
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private ExpenseRepo expenseRepo;

    private UserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }


    public BigDecimal calculateBalance() {
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        BigDecimal totalIncome = incomeRepo.getTotalIncomeByUser(user.getId());
        BigDecimal totalExpense = expenseRepo.getTotalExpenseByUser(user.getId());

        return totalIncome.subtract(totalExpense);
    }
}
