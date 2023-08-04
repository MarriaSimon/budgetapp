package org.fasttrackit.budget.budgetapp.service;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.exception.ResourceNotFoundException;
import org.fasttrackit.budget.budgetapp.model.Expense;
import org.fasttrackit.budget.budgetapp.model.Income;
import org.fasttrackit.budget.budgetapp.repository.IncomeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Getter
public class IncomeService {

    @Autowired
    private IncomeRepository incomeRepository;
    private List<Income> incomeList = new ArrayList<>();

    public Income addIncome(Income income) {
        return incomeRepository.save(income);
    }

    public Income getIncomeById(Long id) {
        Income income = incomeRepository.findById(id).orElseThrow(() -> {
            return new ResourceNotFoundException("Income not found: " + id);
        });
        return income;
    }

    public List<Income> getAllIncomes() {
        return incomeRepository.findAll();
    }

    public Income updateIncome(Long id, Income updatedIncome) {
        Income existingIncome = incomeRepository.findById(id).orElse(null);
        if (existingIncome != null) {
            existingIncome.setSource(updatedIncome.getSource());
            existingIncome.setAmount(updatedIncome.getAmount());
            existingIncome.setFrequency(updatedIncome.getFrequency());
            return incomeRepository.save(existingIncome);
        }
        return null;
    }

    public void deleteIncome(Long id) {
        incomeRepository.deleteById(id);
    }

    public List<Income> getAllIncomesFilterable(String name, String Category, double minAmount, double maxAmount, String dueDate) {
        return null;
    }
}
