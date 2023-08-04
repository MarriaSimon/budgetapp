package org.fasttrackit.budget.budgetapp.controller.budget;

import lombok.RequiredArgsConstructor;
import org.fasttrackit.budget.budgetapp.model.Income;
import org.fasttrackit.budget.budgetapp.repository.IncomeRepository;
import org.fasttrackit.budget.budgetapp.service.IncomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("income") //http://post:port/income

public class IncomeController {

    @Autowired
    private IncomeService incomeService;

    @PostMapping
    public Income addIncome(@RequestBody Income income) {
        return incomeService.addIncome(income);
    }

    @GetMapping("/{id}")
    public Income getIncomeById(@PathVariable Long id) {
        return incomeService.getIncomeById(id);
    }

    @GetMapping
    public List<Income> getAllIncomes() {
        return incomeService.getAllIncomes();
    }

    @PutMapping("/{id}")
    public Income updateIncome(@PathVariable Long id, @RequestBody Income updatedIncome) {
        updatedIncome.setId(id);
        return incomeService.updateIncome(id,updatedIncome);
    }

    @DeleteMapping("/{id}")
    public void deleteIncome(@PathVariable Long id) {
        incomeService.deleteIncome(id);
    }
}
