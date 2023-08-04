package org.fasttrackit.budget.budgetapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Expense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "expense_month")
    private String month;
    @Column(name = "expense_name")
    private String name;

    @Column(name = "expense_year")
    private int year ;
    @Column(name = "expense_amount")
    private double amount;

    @Column
    private String date;

    @Column(name = "expense_category")
    private String category; //Entertainment, Dinning Out, Shopping

    @ManyToOne
    private Income income;
}
