package com.engagetech.expensify.api;

import com.engagetech.expensify.domains.Expense;
import com.engagetech.expensify.dtos.ExpenseDto;
import com.engagetech.expensify.services.ExpenseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

/**
 * Created on 4/11/18.
 */


@RequestMapping("/app/expenses")
@Controller
public class ExpenseController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExpenseController.class);

    @Autowired
    private ExpenseService expenseService;

    @GetMapping
    @ResponseBody
    public List<Expense> index() {
        return expenseService.getAllExpenses();
    }

    @PostMapping
    @ResponseBody
    public Expense addExpense(@RequestBody ExpenseDto expenseDto) {
        LOGGER.info(" New Expense submitted via api call");
        //Chain the persist call with convert Dto to expense entity
        return expenseService.saveExpense(connvertToExpense(expenseDto));
    }


    private Expense connvertToExpense(ExpenseDto expenseDto) {

        Expense expense = new Expense();
        if (!Objects.isNull(expenseDto.getAmount())) expense.setAmount(expenseDto.getAmount());
        if (!Objects.isNull(expenseDto.getDate())) expense.setDate(expenseDto.getDate());
        if (!Objects.isNull(expenseDto.getReason())) expense.setReason(expenseDto.getReason());

        return expense;
    }
}
