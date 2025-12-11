package com.sanjeev.Expense_Tracker_v1.service;


import com.sanjeev.Expense_Tracker_v1.dto.IncomeDTO;
import com.sanjeev.Expense_Tracker_v1.model.Income;
import com.sanjeev.Expense_Tracker_v1.model.IncomeCategory;
import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.repo.IncomeCategoryRepo;
import com.sanjeev.Expense_Tracker_v1.repo.IncomeRepo;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Service
public class IncomeService {

    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private IncomeCategoryRepo incomeCategoryRepo;
    @Autowired
    private UserRepo userRepo;

    public UserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;
    }

    public void addIncome(IncomeDTO incomeDTO) {
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        IncomeCategory category = incomeCategoryRepo.findById(incomeDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        Income income = new Income();
        income.setAmount(incomeDTO.getAmount());
        income.setDescription(incomeDTO.getDescription());
        income.setUser(user);
        income.setIncomeCategory(category);
        income.setDate(incomeDTO.getDate());
        income.setCreatedAt(LocalDate.now());
        income.setUpdatedAt(LocalDate.now());
        incomeRepo.save(income);
    }

    public List<IncomeDTO> getAllIncomes() {
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());

        return incomeRepo.findByUser(user).stream()
                .map(income -> new IncomeDTO(
                        income.getAmount(),
                        income.getIncomeCategory().getId(),
                        income.getDescription(),
                        income.getDate()
                ))
                .collect(java.util.stream.Collectors.toList());
    }

    public void updateIncome(Long id, IncomeDTO incomeDTO) {
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        Income income = incomeRepo.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        if(!income.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("You are not authorized to update this income");
        }
        IncomeCategory category = incomeCategoryRepo.findById(incomeDTO.getCategoryId()).orElseThrow(() -> new RuntimeException("Category not found"));
        income.setAmount(incomeDTO.getAmount());
        income.setDescription(incomeDTO.getDescription());
        income.setIncomeCategory(category);
        income.setDate(incomeDTO.getDate());
        income.setUpdatedAt(LocalDate.now());
        incomeRepo.save(income);
    }

    public void deleteIncome(Long id) {
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        Income income = incomeRepo.findById(id).orElseThrow(() -> new RuntimeException("Income not found"));
        if(!income.getUser().getId().equals(user.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        incomeRepo.delete(income);
    }

}
