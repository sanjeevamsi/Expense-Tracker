package com.sanjeev.Expense_Tracker_v1.dto;

public class IncomeCategoryDTO {

    private Long id;
    private String category;

    public IncomeCategoryDTO(Long id, String category) {
        this.id = id;
        this.category = category;
    }

    public IncomeCategoryDTO() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
