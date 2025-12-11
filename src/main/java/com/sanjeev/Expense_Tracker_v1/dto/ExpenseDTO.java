package com.sanjeev.Expense_Tracker_v1.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ExpenseDTO {

    private BigDecimal amount;
    private String description;


    public ExpenseDTO() {
    }

    public ExpenseDTO(BigDecimal amount, String description) {
        this.amount = amount;
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
