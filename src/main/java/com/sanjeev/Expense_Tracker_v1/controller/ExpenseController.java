package com.sanjeev.Expense_Tracker_v1.controller;

import com.sanjeev.Expense_Tracker_v1.dto.ExpenseDTO;
import com.sanjeev.Expense_Tracker_v1.model.Expense;
import com.sanjeev.Expense_Tracker_v1.service.ExpenseService;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expense-tracker")
public class ExpenseController {

    @Autowired
    private ExpenseService expenseService;

    // i want to add the expense
    @PostMapping("/add-expense")
    public String addExpense(@RequestBody Expense expense) {
        expenseService.addExpense(expense);
        return "Expense added successfully!";
    }

    //delete expense
    @PutMapping("/delete-expense/{id}")
    public String deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return "Expense deleted successfully!";
    }

    //update expense
    @PutMapping("/update-expense/{id}")
    public String updateExpense(@PathVariable Long id, @RequestBody Expense expense) {
        expenseService.updateExpense(id, expense);
        return "Expense updated successfully!";
    }

    //let say we want the last week expenses of the logged in user
    @GetMapping("/weekly-expenses")
    public List<ExpenseDTO> getWeeklyExpenses() {
        return expenseService.getWeeklyExpenses();
    }
}
