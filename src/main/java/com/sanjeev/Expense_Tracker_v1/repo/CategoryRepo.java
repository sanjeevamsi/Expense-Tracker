package com.sanjeev.Expense_Tracker_v1.repo;

import com.sanjeev.Expense_Tracker_v1.dto.CategoryDTO;
import com.sanjeev.Expense_Tracker_v1.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CategoryRepo extends JpaRepository<Category, Integer> {


    @Query("SELECT new com.sanjeev.Expense_Tracker_v1.dto.CategoryDTO(c.id, c.category_name) FROM Category c")
    List<CategoryDTO> categories();
}
