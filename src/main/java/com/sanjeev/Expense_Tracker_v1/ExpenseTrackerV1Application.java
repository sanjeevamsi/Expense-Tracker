package com.sanjeev.Expense_Tracker_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExpenseTrackerV1Application {

	public static void main(String[] args) {
		SpringApplication.run(ExpenseTrackerV1Application.class, args);
		System.out.println("Here you go...Expense Tracker is running!");
	}
}
