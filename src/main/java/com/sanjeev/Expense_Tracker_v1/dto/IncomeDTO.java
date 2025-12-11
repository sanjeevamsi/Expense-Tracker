package com.sanjeev.Expense_Tracker_v1.dto;


import java.math.BigDecimal;
import java.time.LocalDate;

public class IncomeDTO {

    private BigDecimal amount;
    private Long categoryId;
    private String description;
    private LocalDate date;

    public IncomeDTO(BigDecimal amount, Long categoryId, String description, LocalDate date) {
        this.amount = amount;
        this.categoryId = categoryId;
        this.description = description;
        this.date = date;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }
}
