package com.sanjeev.Expense_Tracker_v1.model;


import jakarta.persistence.*;
import lombok.Data;
import org.springframework.cglib.core.Local;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Data
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    private BigDecimal amount;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private IncomeCategory incomeCategory;
    private LocalDate date;
    private String description;
    private LocalDate createdAt;
    private LocalDate updatedAt;

}
