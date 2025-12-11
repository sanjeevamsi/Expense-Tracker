package com.sanjeev.Expense_Tracker_v1.repo;


import com.sanjeev.Expense_Tracker_v1.dto.IncomeCategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.IncomeCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IncomeCategoryRepo extends JpaRepository<IncomeCategory, Long> {


    @Query("SELECT new com.sanjeev.Expense_Tracker_v1.dto.IncomeCategoryDTO(ic.id, ic.category) FROM IncomeCategory ic")
    List<IncomeCategoryDTO> findAllIncomeCategories();
}
