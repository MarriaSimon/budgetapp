package org.fasttrackit.budget.budgetapp.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class MonthlyReport {

    private String month;
    private int year;
    private double income;
    private double totalBills;
    private double totalExpenses;
    private double remainingAmount;

}
