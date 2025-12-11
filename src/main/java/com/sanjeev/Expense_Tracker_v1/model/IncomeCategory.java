package com.sanjeev.Expense_Tracker_v1.model;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class IncomeCategory {

    //income category should have id and name
    @Id
    private Long id;
    private String category;


    //one category can have many incomes
    //let say take for salary every month the user gets the salary so it is one category with many incomes
    @OneToMany(mappedBy = "incomeCategory")
    private List<Income> incomes;
}
