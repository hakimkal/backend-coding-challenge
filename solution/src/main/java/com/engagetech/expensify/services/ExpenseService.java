package com.engagetech.expensify.services;

import com.engagetech.expensify.domains.Expense;
import com.engagetech.expensify.repositories.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by abdulhakim on 4/11/18.
 */
@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }
}
