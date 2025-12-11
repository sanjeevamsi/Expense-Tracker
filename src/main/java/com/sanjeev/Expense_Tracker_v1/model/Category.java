package com.sanjeev.Expense_Tracker_v1.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Category {

    @Id
    private int id;
    private String category_name;
    //1 category can have multiple expenses
    @OneToMany(mappedBy = "category")
    private List<Expense> expense;
}
