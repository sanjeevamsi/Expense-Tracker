package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.dto.MonthlySummaryDTO;
import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.repo.ExpenseRepo;
import com.sanjeev.Expense_Tracker_v1.repo.IncomeRepo;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Month;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

@Service
public class DashboardService {

    @Autowired
    private IncomeRepo incomeRepo;
    @Autowired
    private ExpenseRepo expenseRepo;
    @Autowired
    private IncomeService incomeService;
    @Autowired
    private UserRepo userRepo;


    public BigDecimal getTotalIncomeForCurrentMonth(int month, int year) {

        UserDetails userDetails = incomeService.getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        //we need to get the total income for the current month
        return incomeRepo.findTotalIncomeByUserInMonthAndYear(month, year, user.getId());
    }

    public BigDecimal getTotalExpenseForCurrentMonth(int month, int year) {

        UserDetails userDetails = incomeService.getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        //we need to get the total income for the current month
        return expenseRepo.findTotalExpenseByUserInMonthAndYear(month, year, user.getId());
    }

    public int countAllIncomeSources() {

        //we need to count all income sources for the logged in user
        UserDetails userDetails = incomeService.getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());

        //once we got the user we need to find the income categories
        //vamsi user id = 1,
        return incomeRepo.countIncomeSourcesByUserId(user.getId());
    }

    public int countAllExpenses(int month, int year) {
        UserDetails userDetails = incomeService.getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());

        return expenseRepo.countAllExpensesByUser(month, year, user.getId());
    }

    private String getMonthName(int month) {
        return Month.of(month).getDisplayName(TextStyle.FULL, Locale.ENGLISH);
    }

    public List<MonthlySummaryDTO> getMonthlyIncomeExpenseChartData() {

        List<MonthlySummaryDTO> list = new ArrayList<>();
        LocalDate currentDate = LocalDate.now();
        int year = currentDate.getYear();

        for (int i = 1; i <= 12; i++) {
            BigDecimal totalIncome = getTotalIncomeForCurrentMonth(i, year);
            BigDecimal totalExpense = getTotalExpenseForCurrentMonth(i, year);

            list.add(new MonthlySummaryDTO(
                    getMonthName(i),
                    totalIncome,
                    totalExpense
            ));
        }
        return list;
    }
}
