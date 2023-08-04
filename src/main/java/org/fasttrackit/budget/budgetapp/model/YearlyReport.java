package org.fasttrackit.budget.budgetapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class YearlyReport {
    private int year;
    private double yearlyIncome;
    private double yearlyBills;
    private double yearlyExpenses;
    private double yearlyRemainingAmount;
}
