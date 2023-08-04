package org.fasttrackit.budget.budgetapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.model.Expense;
import org.fasttrackit.budget.budgetapp.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;
    private List<Expense> expenseList = new ArrayList<>();

    public Expense addExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found: " + id));
    }

    public List<Expense> getAllExpenses() {
        return expenseRepository.findAll();
    }

    public Expense updateExpense(Long id, Expense updatedExpense) {
        Expense existingExpense = expenseRepository.findById(id).orElse(null);
        if (existingExpense != null) {
            existingExpense.setName(updatedExpense.getName());
            existingExpense.setAmount(updatedExpense.getAmount());
            existingExpense.setDate(updatedExpense.getDate());
            existingExpense.setCategory(updatedExpense.getCategory());
            return expenseRepository.save(existingExpense);
        }
        return null;
    }

    public void deleteExpense(Long id) {
        expenseRepository.deleteById(id);
    }

    public List<Expense> getAllExpensesFilterable(String name, String Category, double minAmount, double maxAmount, String dueDate) {
        return null;
    }
}

