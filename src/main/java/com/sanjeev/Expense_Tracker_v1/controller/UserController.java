package com.sanjeev.Expense_Tracker_v1.controller;

import com.sanjeev.Expense_Tracker_v1.dto.UserDTO;
import com.sanjeev.Expense_Tracker_v1.model.User;
import com.sanjeev.Expense_Tracker_v1.service.JwtService;
import com.sanjeev.Expense_Tracker_v1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/expense-tracker")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(12);

    //signup
    @PostMapping("/signUp")
    public String signUp(@RequestBody User user) {
        //we need to hash the password before saving the user
        //first check if the user already exists

        if(userService.getUserByEmail(user.getEmail())) {
            return "User already exists with this email!";
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userService.saveUser(user);
        return "User signed up successfully!";
    }

    //so when user logs in , we need to get some token
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        //how can we know that the user is valid or not
        //normally the doaAuthenticationProvider will take care of it
        //but here are just simulating it
        //okay lets send to this AUthenticationManager
        System.out.println("Here i am!!");
        Authentication authentication =authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword())
        );
        if(authentication.isAuthenticated()){
            //we need to generate the token and return it
            return jwtService.generateToken(user.getEmail());
        }
        return "Login failed!!";

    }
}
