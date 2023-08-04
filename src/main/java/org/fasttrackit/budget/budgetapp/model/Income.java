package org.fasttrackit.budget.budgetapp.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Income {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    @Column(name = "income_month")
    private String month;

    @Column(name = "income_year")
    private int year;

    @Column
    private String source;

    @Column(name = "income_amount")
    private double amount;

    @Column
    private String date;

    @Column
    private String frequency;

    @OneToMany(mappedBy = "income", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private List<Bill> bills;

    @OneToMany(mappedBy = "income", cascade = {CascadeType.PERSIST, CascadeType.ALL})
    private List<Expense> expenses;

}
