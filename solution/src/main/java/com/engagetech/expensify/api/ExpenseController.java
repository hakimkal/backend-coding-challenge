package com.engagetech.expensify.api;

import com.engagetech.expensify.domains.Expense;
import com.engagetech.expensify.dtos.ExpenseDto;
import com.engagetech.expensify.services.ExpenseService;
import com.github.sarxos.xchange.ExchangeCache;
import com.github.sarxos.xchange.ExchangeException;
import com.github.sarxos.xchange.ExchangeRate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
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


    @GetMapping("convertoGBP/{eurAmount}")
    @ResponseBody
    public BigDecimal convertEuroToGBP(@PathVariable BigDecimal eurAmount) throws ExchangeException {

        return getConvertedGBP(eurAmount);
    }

    @PostMapping
    @ResponseBody
    public Expense addExpense(@RequestBody ExpenseDto expenseDto) throws  ExchangeException {
        LOGGER.info(" New Expense submitted via api call");
        //Chain the persist call with convert Dto to expense entity
        return expenseService.saveExpense(convertToExpense(expenseDto));
    }


    private Expense convertToExpense(ExpenseDto expenseDto) throws ExchangeException {

        Expense expense = new Expense();
        if (!Objects.isNull(expenseDto.getAmount())) {
            if (expenseDto.getAmount().matches("(?i).*EUR.*")) {

                String amount = expenseDto.getAmount();
                amount = amount.replaceAll("[^\\d.]", "");

                BigDecimal convertedAmount = getConvertedGBP(new BigDecimal(amount));

                expense.setAmount(convertedAmount.doubleValue());


            } else {

                expense.setAmount(Double.parseDouble(expenseDto.getAmount()));
            }

        }
        if (!Objects.isNull(expenseDto.getDate())) expense.setDate(expenseDto.getDate());
        if (!Objects.isNull(expenseDto.getReason())) expense.setReason(expenseDto.getReason());

        return expense;
    }

    private BigDecimal getConvertedGBP(BigDecimal amount) throws ExchangeException {
        ExchangeCache.setParameter("openexchangerates.org.apikey", "cc8b7fcc82b7447c8686500c87869212");

        // define base currency
        ExchangeCache cache = new ExchangeCache("GBP");

        // get the EUR to GBP exchange rate
        ExchangeRate rate = cache.getRate("EUR");

        // convert
        return  rate.convert(amount);


    }

}
