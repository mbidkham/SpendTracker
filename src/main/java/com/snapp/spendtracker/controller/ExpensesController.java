package com.snapp.spendtracker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/expenses")
public class ExpensesController {

    @PostMapping(value = "/add")
    public void addExpenses(){


    }
}
