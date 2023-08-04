package org.fasttrackit.budget.budgetapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.Bill;
import org.fasttrackit.budget.budgetapp.model.Expense;
import org.fasttrackit.budget.budgetapp.service.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public List<Expense> getExpensesByMonthAndCategory(String month, String category) {
        if (month != null && category != null) {
            return expenseRepository.findByMonthContainingIgnoreCaseAndCategoryContainingIgnoreCase(month, category);
        } else if (month != null) {
            return expenseRepository.findAll().stream()
                    .filter(expense -> expense.getMonth().equalsIgnoreCase(month))
                    .collect(Collectors.toList());
        } else if (category != null) {
            return expenseRepository.findByCategoryContainingIgnoreCase(category);
        } else {
            return expenseRepository.findAll();
        }
    }

    public double getSumOfExpensesForYear(int year) {
        List<Expense> allExpensesForYear = expenseRepository.findByYear(year);

        double sum = allExpensesForYear.stream()
                .mapToDouble(Expense::getAmount)
                .sum();

        return sum;
    }


}

