package com.sanjeev.Expense_Tracker_v1.config;

import com.sanjeev.Expense_Tracker_v1.service.CustomUserDetailsService;
import com.sanjeev.Expense_Tracker_v1.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    private JwtService jwtService;
    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        System.out.println("Inside Jwt Filter");

        String path = request.getRequestURI();
        System.out.println("Request URI: " + path);

        if(path.equals("/expense-tracker/login") || path.equals("/expense-tracker/signUp") || path.equals("/expense-tracker/categories") || path.equals("/expense-tracker/income-categories")) {
            filterChain.doFilter(request, response);
            return;
        }
        //so normally we pass the token via the Authorizaton header
        String authorizationHeader = request.getHeader("Authorization");

        //from this authorization header we need to extract the token we send it with the name of Bearer token
        String token = null;
        String username = "";

        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            token = authorizationHeader.substring(7);
            username = jwtService.extractUsername(token);
        }

        //once we got the token we need to extract the username from the token
        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //we need to validate the token

            //also we need to check the userdetails from the token i mean is that user is valid or not
            UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
//            UserDetails userDetails = context.getBean(CustomUserDetailsService.class).loadUserByUsername(username);
            if(jwtService.validateToken(token, username)) {
                //if that particular token is valid we need to set the authentication in the security context
                //we need to pass to next request
                UsernamePasswordAuthenticationToken authenticationFilter = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationFilter.setDetails(new WebAuthenticationDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationFilter);
            }
        }
        filterChain.doFilter(request, response);

    }
}
