package com.sanjeev.Expense_Tracker_v1.repo;

import com.sanjeev.Expense_Tracker_v1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
