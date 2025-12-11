package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.dto.ExpenseDTO;
import com.sanjeev.Expense_Tracker_v1.model.Expense;
import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.repo.ExpenseRepo;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cglib.core.Local;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.naming.AuthenticationNotSupportedException;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    ExpenseRepo expenseRepo;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserRepo userRepo;

    public String addExpense(Expense expense) {
        //here i need to add the userid to the expense before saving it
        //first we need to get the logged in user
        //how can we get the logged in user
        //can we get by using
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        expense.setUser(user);
        expenseRepo.save(expense);
        return "";
    }

    private UserDetails getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication != null && authentication.isAuthenticated()) {
            return (UserDetails) authentication.getPrincipal();
        }
        return null;

    }

    public void deleteExpense(Long id) {

        //we need to fetch the expense from that id
//        Expense expense = expenseRepo.findById(id).orElse(null);

        //the user should be owner of that expense
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        //now we have the logged in user
        //first we need to get the expenses of that user
        Expense expense = expenseRepo.findById(id).orElse(null);
        if(expense != null && expense.getUser().getId().equals(user.getId())) {
            //remove that expense for that user
            expenseRepo.delete(expense);
        }else {
            throw new RuntimeException("Expense not found or you are not authorized to delete this expense");
        }
    }

    public void updateExpense(Long id, Expense expense) {

        //first we need to get the logged in user
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());

        //now we need to fetch the expense from the database
        Expense existingExpense = expenseRepo.findById(id).orElse(null);
        if(existingExpense != null && existingExpense.getUser().getId().equals(user.getId())) {
            //update the expense details
            existingExpense.setAmount(expense.getAmount());
            existingExpense.setCategory(expense.getCategory());
            existingExpense.setDate(expense.getDate());
            existingExpense.setDescription(expense.getDescription());
            //save the updated expense
            expenseRepo.save(existingExpense);
        }else {
            throw new RuntimeException("Expense not found or you are not authorized to update this expense");
        }
    }

    public List<ExpenseDTO> getWeeklyExpenses() {

        //we need to get the last week expenses of the logged in user
        UserDetails userDetails = getLoggedInUser();
        User user = userRepo.findByEmail(userDetails.getUsername());
        System.out.println("user is :" + user.getEmail());
        LocalDate today = LocalDate.now();

        LocalDate lastWeek = LocalDate.now().minusWeeks(1);
        LocalDate startOfLastWeek = lastWeek.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
        LocalDate endOfLastWeek = lastWeek.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
        System.out.println("start of week :" + startOfLastWeek);
        System.out.println("end of week :" + endOfLastWeek);

        return expenseRepo.findExpensesByDateRange(user.getId(), startOfLastWeek, endOfLastWeek);
    }
}
