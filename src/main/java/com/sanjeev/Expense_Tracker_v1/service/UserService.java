package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public String saveUser(User user) {
        userRepo.save(user);
        return "";
    }

    public boolean getUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user != null;
    }
}
