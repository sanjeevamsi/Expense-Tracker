package com.sanjeev.Expense_Tracker_v1.service;

import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.model.UserPrincipal;
import com.sanjeev.Expense_Tracker_v1.repo.UserRepo;
import org.apache.tomcat.util.net.jsse.JSSEUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //so we got the username we need to fetch the user from the database
        System.out.println("the email is " + email);
        User user = userRepo.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("User not found !!");
        }

        //once we have the user we need to return the UserDetails object
        return new UserPrincipal(user);
    }
}
