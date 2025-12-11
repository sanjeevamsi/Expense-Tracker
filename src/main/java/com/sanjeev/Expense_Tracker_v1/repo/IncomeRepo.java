package com.sanjeev.Expense_Tracker_v1.repo;

import com.sanjeev.Expense_Tracker_v1.dto.IncomeDTO;
import com.sanjeev.Expense_Tracker_v1.model.Income;
import com.sanjeev.Expense_Tracker_v1.model.User;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Arrays;

public interface IncomeRepo extends JpaRepository<Income, Long> {

    List<Income> findByUser(User user);

    @Query("SELECT COALESCE(SUM(i.amount),0) FROM Income i WHERE i.user.id = :id")
    BigDecimal getTotalIncomeByUser(Long id);


    @Query("SELECT COALESCE(SUM(i.amount),0) FROM Income i WHERE FUNCTION('MONTH', i.date) = :month AND FUNCTION('YEAR', i.date) = :year AND i.user.id = :userId")
    BigDecimal findTotalIncomeByUserInMonthAndYear(int month, int year, Long userId);


    @Query("SELECT COUNT(i.incomeCategory) FROM Income i WHERE i.user.id = :id")
    int countIncomeSourcesByUserId(Long id);
}
