package com.engagetech.expensify.repositories;

import com.engagetech.expensify.domains.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by abdulhakim on 4/11/18.
 */
@Repository
public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
