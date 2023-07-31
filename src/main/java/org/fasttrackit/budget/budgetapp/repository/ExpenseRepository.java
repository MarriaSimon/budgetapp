package org.fasttrackit.budget.budgetapp.repository;

import org.fasttrackit.budget.budgetapp.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
