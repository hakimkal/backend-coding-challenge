package com.engagetech.expensify.services;

import com.engagetech.expensify.domains.Expense;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.SimpleDateFormat;

/**
 * Created by abdulhakim on 4/11/18.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class ExpenseTests {

    @Autowired
    private ExpenseService expenseService;





    @Test
    public void testCanCreateExpense() throws  Exception{
        SimpleDateFormat sdf = new  SimpleDateFormat("dd/MM/yyyy");


        Expense expense = new Expense();

        expense.setAmount(120);
        expense.setReason("Testing reason");
        expense.setDate(sdf.parse("12/12/2016"));
        Expense expense1 = expenseService.saveExpense(expense);

        Assert.assertNotNull(expense1);
        Assert.assertNotNull(expense1.getId());
    }


}
