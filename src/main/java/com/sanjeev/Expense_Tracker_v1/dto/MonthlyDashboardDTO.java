package com.sanjeev.Expense_Tracker_v1.dto;

import org.springframework.boot.jackson.autoconfigure.JacksonProperties;
import org.springframework.data.util.Pair;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class MonthlyDashboardDTO {

    private BigDecimal totalIncome;
    private BigDecimal totalExpense;
    private BigDecimal netSavings;
    private int expenseCount;
    private int incomeSources;
    //here we need to add the hashmap for the chart data
    private List<MonthlySummaryDTO> monthlyIncomeExpenseChartData;


    public MonthlyDashboardDTO(BigDecimal totalIncome, BigDecimal totalExpense, BigDecimal netSavings, int expenseCount, int incomeSources, List<MonthlySummaryDTO> monthlyIncomeExpenseChartData) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netSavings = netSavings;
        this.expenseCount = expenseCount;
        this.incomeSources = incomeSources;
        this.monthlyIncomeExpenseChartData = monthlyIncomeExpenseChartData;
    }

    public BigDecimal getTotalIncome() {
        return totalIncome;
    }

    public void setTotalIncome(BigDecimal totalIncome) {
        this.totalIncome = totalIncome;
    }

    public BigDecimal getTotalExpense() {
        return totalExpense;
    }

    public void setTotalExpense(BigDecimal totalExpense) {
        this.totalExpense = totalExpense;
    }

    public BigDecimal getNetSavings() {
        return netSavings;
    }

    public void setNetSavings(BigDecimal netSavings) {
        this.netSavings = netSavings;
    }

    public int getExpenseCount() {
        return expenseCount;
    }

    public void setExpenseCount(int expenseCount) {
        this.expenseCount = expenseCount;
    }

    public int getIncomeSources() {
        return incomeSources;
    }

    public void setIncomeSources(int incomeSources) {
        this.incomeSources = incomeSources;
    }

    public List<MonthlySummaryDTO> getMonthlyIncomeExpenseChartData() {
        return monthlyIncomeExpenseChartData;
    }

    public void setMonthlyIncomeExpenseChartData(List<MonthlySummaryDTO> monthlyIncomeExpenseChartData) {
        this.monthlyIncomeExpenseChartData = monthlyIncomeExpenseChartData;
    }
}
