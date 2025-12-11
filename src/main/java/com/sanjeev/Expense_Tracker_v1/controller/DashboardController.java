package com.sanjeev.Expense_Tracker_v1.controller;


import com.sanjeev.Expense_Tracker_v1.dto.MonthlyDashboardDTO;
import com.sanjeev.Expense_Tracker_v1.dto.MonthlySummaryDTO;
import com.sanjeev.Expense_Tracker_v1.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/expense-tracker/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    //so lets build the dashboard controller
    //so here we are building for the monthly summary dashboard
    @GetMapping("/monthly-summary")
    public MonthlyDashboardDTO getMonthlySummary() {

        LocalDate currentDate = LocalDate.now();
        int month = currentDate.getMonthValue();
        int year = currentDate.getYear();
        BigDecimal totalIncomeKpi = dashboardService.getTotalIncomeForCurrentMonth(month, year);
        BigDecimal totalExpenseKpi = dashboardService.getTotalExpenseForCurrentMonth(month, year);
        BigDecimal netSavingsKpi = totalIncomeKpi.subtract(totalExpenseKpi);
        int noOfIncomeSourcesKpi = dashboardService.countAllIncomeSources();
        int noOfExpensesKpi = dashboardService.countAllExpenses(month, year);

        List<MonthlySummaryDTO> monthlyIncomeExpenseChartData = dashboardService.getMonthlyIncomeExpenseChartData();
        return new MonthlyDashboardDTO(
                totalIncomeKpi,
                totalExpenseKpi,
                netSavingsKpi,
                noOfIncomeSourcesKpi,
                noOfExpensesKpi,
                monthlyIncomeExpenseChartData
        );
    }
}
