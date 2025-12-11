package com.sanjeev.Expense_Tracker_v1.repo;

import com.sanjeev.Expense_Tracker_v1.dto.ExpenseDTO;
import com.sanjeev.Expense_Tracker_v1.model.Expense;
import com.sanjeev.Expense_Tracker_v1.model.User;
import jakarta.persistence.JoinColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface ExpenseRepo extends JpaRepository<Expense, Long> {

    @Query("""
            SELECT new com.sanjeev.Expense_Tracker_v1.dto.ExpenseDTO(e.amount, e.description)
            FROM Expense e
            WHERE e.user.id = :id
                AND e.date BETWEEN :startDate AND :endDate
            ORDER BY e.date DESC
            """)
    List<ExpenseDTO> findExpensesByDateRange(Long id, LocalDate startDate, LocalDate endDate);

    List<Expense> findByUser(User user);

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE e.user.id = :id")
    BigDecimal getTotalExpenseByUser(Long id);

    @Query("SELECT COALESCE(SUM(e.amount),0) FROM Expense e WHERE FUNCTION('MONTH', e.date) = :month AND FUNCTION('YEAR', e.date) = :year AND e.user.id = :id")
    BigDecimal findTotalExpenseByUserInMonthAndYear(int month, int year, Long id);

    @Query("SELECT COUNT(e) FROM Expense e WHERE FUNCTION('MONTH', e.date) = :month AND FUNCTION('YEAR', e.date) = :year AND e.user.id = :id")
    int countAllExpensesByUser(int month, int year, Long id);
}
